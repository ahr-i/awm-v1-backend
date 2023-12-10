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

    public boolean editNickName(UserProfileDto dto, String userId) {
        try {
            Optional<UserEntity> existingUser = repository.findByUserId(userId);

            if (!existingUser.isPresent()) return false;

            UserEntity updatedUser = existingUser.get();

            if(dto.getNickName() != null)
                updatedUser.setNickName(dto.getNickName());
            if(dto.getImage() != null)
                updatedUser.setImage(Base64.getDecoder().decode(dto.getImage()));
            repository.save(updatedUser);

            return true;
        } catch (Exception e) {
            log.info(e.getMessage());

            return false;
        }
    }

    public boolean editCategoryList(UserProfileDto dto, String userId) {
        try {
            Optional<CategoryList> list = categoryListRepository.findByUserId(userId);
            CategoryList myList = new CategoryList();

            if(list.isPresent()) myList = list.get();

            myList.setUserId(userId);
            myList.setCategoryList(dto.getCategoryList());
            categoryListRepository.save(myList);

            return true;
        } catch (Exception e) {
            log.info(e.getMessage());

            return false;
        }
    }

    public UserProfileDto searchSimilarUser(String userId) {
        try {
            Optional<CategoryList> myList = categoryListRepository.findByUserId(userId);
            List<CategoryList> otherUserList = categoryListRepository.findOtherUser(userId);

            if (!myList.isPresent()) return null;

            Set<String> myCategories = new HashSet<>(Arrays.asList(myList.get().getCategoryList().split(",")));
            Map<String, Integer> userSimilarityScores = new HashMap<>();

            for (CategoryList otherUserCategoryList : otherUserList) {
                Set<String> otherCategories = new HashSet<>(Arrays.asList(otherUserCategoryList.getCategoryList().split(",")));

                otherCategories.retainAll(myCategories);
                userSimilarityScores.put(otherUserCategoryList.getUserId(), otherCategories.size());
            }
            int totalScore = userSimilarityScores.values().stream().mapToInt(Integer::intValue).sum();

            if (totalScore > 0) {
                int randomIndex = new Random().nextInt(totalScore);
                int currentSum = 0;

                for (Map.Entry<String, Integer> entry : userSimilarityScores.entrySet()) {
                    currentSum += entry.getValue();

                    if (randomIndex < currentSum) {
                        String resultUserId = entry.getKey();
                        Optional<UserEntity> resultUser = repository.findByUserId(resultUserId);

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
