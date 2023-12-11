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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


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

    @PostMapping("/user/update/{postId}")
    public ResponseEntity updateBoardDto(@PathVariable int postId, @RequestPart("dto") BoardDto dto
                                         , @RequestPart(value = "file",required = false)MultipartFile file,
                                         Authentication authentication) {

        Optional<BoardEntity> board = service.findBoard(postId);

        if(!board.isEmpty()) {
            PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
            String userId = principal.getUserInfo().getUserId();
            if(!userId.equals(board.get().getUserId())) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("글 작성자가 아닙니다");
        }else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("삭제된 글이거나 글을 찾을 수 없습니다.");

        service.updatePost(dto, file, postId);
         return ResponseEntity.ok("글 등록이 정상적으로 되었습니다.");

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
    @GetMapping("user/profile")
    public ResponseEntity userProFile(Authentication authentication){

        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        return ResponseEntity.ok().body(principal.getUserInfo());
    }

}
