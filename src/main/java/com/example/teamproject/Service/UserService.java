package com.example.teamproject.Service;


import com.example.teamproject.Dto.UserDto;
import com.example.teamproject.Dto.UserProfileDto;
import com.example.teamproject.JpaClass.LocationTable.Location;
import com.example.teamproject.JpaClass.UserTable.CategoryList;
import com.example.teamproject.JpaClass.UserTable.UserEntity;
import com.example.teamproject.Repository.JpaRepository.CategoryListRepository;
import com.example.teamproject.Repository.JpaRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository repository;
    private final CategoryListRepository categoryListRepository;

    public void join(UserDto info) {
        UserEntity userEntity = UserDto.UserDtoTransferUser(info);
        repository.save(userEntity);
    }

    public UserDto findByUser(UserDto info) {
        Optional<UserEntity> byUserId = repository.findByUserId(info.getUserId());
        if(byUserId.isPresent()) {
          return UserDto.UserEntityToUserDto(byUserId.get());
        } return  null;

    }

    /* 유저의 프로필 변경 */
    public boolean editProfile(UserProfileDto dto, String userId) {
        try {
            // 기존의 유저 userId 기반으로 검색
            Optional<UserEntity> existingUser = repository.findByUserId(userId);

            // 없는 유저일 때
            if (!existingUser.isPresent()) return false;

            // Update를 위함
            UserEntity updatedUser = existingUser.get();

            // 설정할 Nick Name이 있을 때
            if(dto.getNickName() != null)
                updatedUser.setNickName(dto.getNickName());
            // 설정할 Image가 있을 때
            if(dto.getImage() != null)
                updatedUser.setImage(Base64.getDecoder().decode(dto.getImage()));
            // Update
            // JPA가 자동으로 Update를 하지 않음... Issue
            repository.save(updatedUser);

            return true;
        } catch (Exception e) {
            log.info(e.getMessage());

            return false;
        }
    }

    /* 유저의 Category List 설정 */
    public boolean editCategoryList(UserProfileDto dto, String userId) {
        try {
            // 기존의 Category List 검색
            Optional<CategoryList> list = categoryListRepository.findByUserId(userId);
            CategoryList myList = new CategoryList();

            // 기존의 Category List가 없는 경우
            if(list.isPresent()) myList = list.get();

            // Dto 내용 DB에 저장
            myList.setUserId(userId);
            myList.setCategoryList(dto.getCategoryList());
            categoryListRepository.save(myList);

            return true;
        } catch (Exception e) {
            log.info(e.getMessage());

            return false;
        }
    }

    /* 성향이 비슷한 유저 추천 */
    public UserProfileDto searchSimilarUser(String userId) {
        try {
            // 나의 Id 기반으로 Category List 조회
            Optional<CategoryList> myList = categoryListRepository.findByUserId(userId);
            // 나를 제외한 User의 Category List 조회
            List<CategoryList> otherUserList = categoryListRepository.findOtherUser(userId);

            // 나의 Category List가 없다면 진행 못함
            if (!myList.isPresent()) return null;

            // ','를 기준으로 파싱하여 Hash
            Set<String> myCategories = new HashSet<>(Arrays.asList(myList.get().getCategoryList().split(",")));
            // Hash한 것을 기준으로 Map 작성
            Map<String, Integer> userSimilarityScores = new HashMap<>();

            // 다른 유저의 Category List Setting
            for (CategoryList otherUserCategoryList : otherUserList) {
                // 다른 유저의 HashMap 생성
                Set<String> otherCategories = new HashSet<>(Arrays.asList(otherUserCategoryList.getCategoryList().split(",")));

                // 나의 Category List와 같지 않은 Category 제외
                otherCategories.retainAll(myCategories);
                // 남은 List의 Category 수 세기
                userSimilarityScores.put(otherUserCategoryList.getUserId(), otherCategories.size());
            }
            // 총 점수 계산 (이것이 Random 값 범위임)
            int totalScore = userSimilarityScores.values().stream().mapToInt(Integer::intValue).sum();

            // 총 점수가 양수일 때만 진행 가능
            if (totalScore > 0) {
                // 랜덤 값 생성
                int randomIndex = new Random().nextInt(totalScore);
                int currentSum = 0;

                // 랜덤 값에 따라 유저 하나를 무조건 찾음
                for (Map.Entry<String, Integer> entry : userSimilarityScores.entrySet()) {
                    currentSum += entry.getValue();

                    // 유저 찾음
                    if (randomIndex < currentSum) {
                        String resultUserId = entry.getKey();
                        Optional<UserEntity> resultUser = repository.findByUserId(resultUserId);

                        // 유저의 정보 Return
                        return UserProfileDto.userEntryToDto(resultUser.get());
                    }
                }
            }

            return null;
        } catch (Exception e) {
            log.info(e.getMessage());

            return null;
        }
    }
}
