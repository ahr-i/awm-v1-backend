package com.example.teamproject.Service;
import com.example.teamproject.Dto.CommuityDto.BoardDto.BoardDto;
import com.example.teamproject.Dto.CommuityDto.Response;
import com.example.teamproject.Dto.UserLogDto;
import com.example.teamproject.JpaClass.CommunityTable.BoardEntity;
import com.example.teamproject.JpaClass.CommunityTable.CommentEntity;
import com.example.teamproject.JpaClass.CommunityTable.LogBoardCountEntity;
import com.example.teamproject.JpaClass.CommunityTable.UserPostEntity;
import com.example.teamproject.JpaClass.LocationTable.Location;
import com.example.teamproject.Repository.CommuityRepository.BoardRepository;
import com.example.teamproject.Repository.CommuityRepository.CommentRepository;
import com.example.teamproject.Repository.CommuityRepository.LogBoardCountEntityRepository;
import com.example.teamproject.Repository.CommuityRepository.UserPostRepository;
import com.example.teamproject.Repository.LoactionRepository.LocationRepository;
import com.example.teamproject.Service.SpringSecurityLogin.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PipedReader;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {
    private final CommentRepository commentRepository;
    private final BoardRepository repository;
    private final LocationRepository locationRepository;
    private final UserPostRepository logBoardRepository;
    private final LogBoardCountEntityRepository logBoardCountEntityRepository;

    public ResponseEntity BoardSave(BoardDto dto, int locationId,MultipartFile file,Authentication authentication) {
        try {

            Optional<Location> locationEntity = locationRepository.findById(locationId);

            if(locationEntity.isPresent()) {
                PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
                String userId = principal.getUserInfo().getUserId();
                BoardEntity boardEntity = BoardDto.SaveToBoardEntity(dto, locationEntity, userId, file);
                repository.save(boardEntity);
                return ResponseEntity.ok().body("등록이 완료 되었습니다.");
            }

        } catch (IOException e) {
            log.info("IO 예외 {}", e.getMessage());
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 처리 중 에러가 발생 했습니다.");
        }
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("글을 등록할 수 없습니다.");
    }

    @Transactional
    public void updateHit(int postId) {
        repository.updateHit(postId);
    }

    /**
     * 글 상세보기를 할때는 ReadToDto를 사용할 것.
     *
     * @param postId
     * @return
     */
    public ResponseEntity findByPostAndComment(int postId) {

        Optional<BoardEntity> boardEntity = repository.findById(postId);

        List<CommentEntity> comment = commentRepository.findAllByEntityOrderByCreatTimeDesc(boardEntity.get());
        if (boardEntity.isPresent()) {
            updateHit(postId);
            BoardDto dto = BoardDto.DetailToBoardDto(boardEntity.get());

            Long commentCount = commentRepository.countAllByEntity(boardEntity.get());

            Response response = new Response(dto, comment, commentCount);

            return ResponseEntity.ok().body(response);

        } else return ResponseEntity.status(HttpStatus.NO_CONTENT).body("해당 게시글이 존재 하지 않습니다.");
    }

    public ResponseEntity removeBoardPost(int postId,Authentication authentication) {

        Optional<BoardEntity> byId = repository.findById(postId);

        if(byId.isPresent()) {
            PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
            String userId = principal.getUserInfo().getUserId();
            if(userId.equals(byId.get().getUserId())) {
               return ResponseEntity.ok().body("삭제가 완료 되었습니다.");
            }else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유저가 일치 하지 않습니다.");

        }else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("삭제된 게시글이거나 해당 경로가 없습니다.");
    }

    public String findUser(int postId) {
        Optional<BoardEntity> byId = repository.findById(postId);

        if (byId.isPresent()) {
            return byId.get().getUserId();
        } else return null;
    }

    public Page<BoardDto> page(int page, int locationId) {

        List<BoardEntity> allBy = repository.findAllByLocation_LocationId(locationId);

        allBy.stream().filter(entity -> entity.getReportCount() >= 10).forEach(entity -> repository.delete(entity));

        PageRequest createdDate = PageRequest.of(page, 3, Sort.by("createTime").descending());

        Page<BoardEntity> findPost = repository.findAllByLocation_LocationId(locationId, createdDate);

        if (!findPost.isEmpty()) {
            Page<BoardDto> sendPostdto = BoardDto.PageToBoardPostDto(findPost, commentRepository);
            return sendPostdto;
        } else return null;


    }

    public BoardEntity updatePost(BoardDto dto) throws IOException {
        Optional<BoardEntity> byId = repository.findById(dto.getPostId());

        if (byId.isPresent()) {
            BoardEntity entity = byId.get();
            BoardEntity saveentity = BoardDto.updatePost(dto, byId.get());
            repository.save(saveentity);
            return byId.get();
        }
        return null;
    }

    public BoardEntity updateFindByPost(int postId) {
        Optional<BoardEntity> byId = repository.findById(postId);

        if (byId.isPresent()) {
            return byId.get();
        } else return null;
    }

    /**
     * 신승엽 파트
     */
    public ResponseEntity saveLogPost(UserLogDto dto, int locationId, Authentication authentication) {
        Optional<Location> byId = locationRepository.findById(locationId);

        if (byId.isPresent()) {
            PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
            String userId = principal.getUserInfo().getUserId();
            String nickName = principal.getUserInfo().getNickName();
            logBoardRepository.save(UserLogDto.TransferUserEntity(dto, byId, userId,nickName));
            return ResponseEntity.ok().body("등록이 완료 되었습니다.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("게시글을 등록할 수 없습니다.");
    }

    public ResponseEntity findByDetailBoard(int id) {
        Optional<UserPostEntity> byId = logBoardRepository.findById(id);

        if (byId.isPresent()) {
            UserLogDto userLogDto = UserLogDto.TransferUserLogDto(byId.get());
            return ResponseEntity.ok().body(userLogDto);
        } else return ResponseEntity.status(HttpStatus.NO_CONTENT).body("게시글이 존재하지 않거나 삭제 되었습니다.");

    }

    public Page<UserLogDto> findLogPage(int page, int locationId) {
        PageRequest createAt = PageRequest.of(page, 5, Sort.by("createAt").descending());

        Page<UserPostEntity> logPage = logBoardRepository.findAllByLocation_LocationId(locationId, createAt);

        return logPage.isEmpty() ? null : logPage.map(UserLogDto::TransferPageUserLogDto);
    }

    public ResponseEntity deleteLogBoard(int postId, Authentication authentication) {

        Optional<UserPostEntity> byId = logBoardRepository.findById(postId);
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        String userId = principal.getUserInfo().getUserId();

        if (byId.isPresent()) {
            if (userId.equals(byId.get().getUserId())) {
                logBoardRepository.delete(byId.get());
              return   ResponseEntity.ok().body("삭제가 완료 되었습니다.");

            }else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유저가 일치하지 않습니다.");

        }else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("해당 글이 없거나 잘못된 요청입니다.");
    }
    public void likeCountPlus(int postId){
         logBoardRepository.updateHit(postId);
    }
    public void badCountPlus(int postId){
        logBoardRepository.updateBadCountHit(postId);
    }
    public ResponseEntity checkLogBoardLike(int postId,Authentication authentication){
        Optional<LogBoardCountEntity> likePost = logBoardCountEntityRepository.findByLogBoardEntity_IdAndCountCheck(postId,1);
        Optional<UserPostEntity> byId = logBoardRepository.findById(postId);
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        String userId = principal.getUserInfo().getUserId();
        if(!likePost.isEmpty()) {

            if(likePost.get().getUserId().equals(userId) && likePost.get().getCountCheck() == 1) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("해당글에 이미 좋아요를 했습니다.");
            }
            }else {

            if(byId.isPresent()){
                LogBoardCountEntity entity = LogBoardCountEntity.likeCount(userId, byId.get());
                likeCountPlus(postId);
                logBoardCountEntityRepository.save(entity);
                return  ResponseEntity.status(HttpStatus.OK).body("좋아요가 완료 되었습니다");

            }else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 글을 찾을 수 없습니다.");

            }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("잘못된 요청 입니다.");
    }

    public ResponseEntity checkLogBoardBadCount(int postId,Authentication authentication) {
        Optional<LogBoardCountEntity> logPost = logBoardCountEntityRepository.findByLogBoardEntity_IdAndCountCheck(postId,0);
        Optional<UserPostEntity> byId = logBoardRepository.findById(postId);
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        String userId = principal.getUserInfo().getUserId();

        if(!logPost.isEmpty()) {

            if(logPost.get().getUserId().equals(userId) && logPost.get().getCountCheck() == 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("싫어요를 이미 하였습니다.");
            } else {
                if(!byId.isPresent()) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 글을 찾을 수 없습니다.");}
            }

        }else {
            badCountPlus(postId);
            logBoardCountEntityRepository.save(LogBoardCountEntity.BadCount(byId.get(),userId));
            return ResponseEntity.status(HttpStatus.OK).body("싫어요가 등록 되었습니다.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 요청 입니다.");
    }
}
