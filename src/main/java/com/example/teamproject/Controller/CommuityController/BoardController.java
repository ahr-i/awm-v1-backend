package com.example.teamproject.Controller.CommuityController;



import com.example.teamproject.Dto.CommuityDto.BoardDto.BoardDto;
import com.example.teamproject.Dto.CommuityDto.BoardDto.UserLogDto;
import com.example.teamproject.JpaClass.CommunityTable.BoardEntity;
import com.example.teamproject.Service.BoardService;
import com.example.teamproject.Service.SpringSecurityLogin.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


@RequiredArgsConstructor
@Slf4j
@RestController
public class BoardController {
    /**
     * 김동근 파트
     * 커뮤니티에 대한 전반적인 crud를 기술한다.
     */

    //글 생성
    private final BoardService service;
    @PostMapping("user/board/save/{locationId}")

    public ResponseEntity save(@PathVariable int locationId, @RequestPart("dto") BoardDto dto,
                               @RequestPart(value = "file" , required = false) MultipartFile file,Authentication authentication){
      return service.BoardSave(dto,locationId,file,authentication);
    }

    //글 상세조회
    @GetMapping("/board/findBoard/{postId}")
    @ResponseBody
    public ResponseEntity findByBoard(@PathVariable int postId){
        return service.findByPostAndComment(postId);
    }
    //글 삭제
    @GetMapping("/user/remove/{postId}")
    public ResponseEntity removeBoard(@PathVariable int postId,Authentication authentication){
        ResponseEntity responseEntity = service.removeBoardPost(postId, authentication);
        return responseEntity;
    }

    //글 수정
    @GetMapping("/user/update/{postId}")
    public ResponseEntity updateBoard(@PathVariable int postId,Authentication authentication){

        BoardEntity boardUser = service.updateFindByPost(postId);
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();

        if(principal.getUserInfo().getUserId().equals(boardUser.getUserId())) {
            BoardDto dto = BoardDto.DetailToBoardDto(boardUser);
            return ResponseEntity.ok().body(dto);

        }else if(boardUser == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    @PostMapping("/user/update/{postId}")
    public ResponseEntity updateBoardDto(@PathVariable int postId,@RequestBody BoardDto dto) {

        try {
            BoardEntity entity = service.updatePost(dto);

            if(entity != null) {
                return ResponseEntity.ok().body("글 수정이 완료 되었습니다.");
            }else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("글 수정이 완료되지 않았습니다.");
        }catch (IOException e){
            log.info("파일 용량 초과");
        }
       return null;
    }


    @GetMapping("/board/paging/{locationId}")
    public ResponseEntity paging(@RequestParam(defaultValue = "0") int page,@PathVariable int locationId){
        Page<BoardDto> findPage = service.page(page, locationId);
        if(findPage==null) {
            return ResponseEntity.noContent().build();
        }else {
           return ResponseEntity.ok().body(findPage);
        }
    }

    /**
     * 신승엽 파트
     * 해당 (로그) 파트에 대한 글들을 수정 삭제 한다.
     */
    @PostMapping("user/log/save/{locationId}")
    public ResponseEntity logUserSave(@RequestBody UserLogDto dto, @PathVariable int locationId, Authentication
            authentication) {
        return service.saveLogPost(dto,locationId,authentication);
    }

    @GetMapping("/log/findBoard/{id}")
    public ResponseEntity DetailLogBoard(@PathVariable int id){
        return service.findByDetailBoard(id);
    }
    @GetMapping("log/paging/{locationId}")
    public ResponseEntity  logPage(@PathVariable int locationId, @RequestParam(defaultValue = "0") int page){
        Page<UserLogDto> logPage = service.findLogPage(page,locationId);

        return logPage == null ? ResponseEntity.status(HttpStatus.NO_CONTENT).body("컨텐츠가 없습니다.")
                : ResponseEntity.ok().body(logPage);
    }
    @DeleteMapping("user/logBoard/delete/{postId}")
    public ResponseEntity deleteLogBoard(@PathVariable int postId,Authentication authentication) {
        ResponseEntity responseEntity = service.deleteLogBoard(postId, authentication);
        return responseEntity;
    }
    @PostMapping("/user/logBoard/likeCount/{postId}")
    public ResponseEntity likeCount(@PathVariable int postId,Authentication authentication){
        ResponseEntity responseEntity = service.checkLogBoardLike(postId, authentication);
        return responseEntity;
    }
    @PostMapping("/user/logBoard/badCount/{postId}")
    public ResponseEntity badCount(@PathVariable int postId,Authentication authentication){
        ResponseEntity responseEntity = service.checkLogBoardBadCount(postId, authentication);
        return responseEntity;
    }

}
