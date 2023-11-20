package com.example.teamproject.Controller.CommuityController;



import com.example.teamproject.Dto.CommuityDto.BoardDto.BoardDto;
import com.example.teamproject.Dto.CommuityDto.Response;
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

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
@RestController
public class BoardController {


    //글 생성
    private final BoardService service;
    @PostMapping("user/board/save/{locationId}")

    public ResponseEntity save(@PathVariable int locationId, @RequestBody BoardDto dto, Authentication authentication){
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        Boolean aBoolean = service.BoardSave(dto,locationId,principal.getUserInfo().getUserId());
        if(aBoolean) {
            return ResponseEntity.ok().build();
        }
        else return ResponseEntity.badRequest().build();
    }

    //글 상세조회
    @GetMapping("/board/findBoard/{postId}")
    @ResponseBody
    public ResponseEntity findByBoard(@PathVariable int postId){
        Response byPost = service.findByPostAndContent(postId);
        if(byPost != null){
            service.updateHit(postId);
            return ResponseEntity.ok().body(byPost);
        }
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("게시글을 찾을 수 없습니다.");
    }
    //글 삭제
    @GetMapping("/user/remove/{postId}")
    public ResponseEntity removeBoard(@PathVariable int postId){
        boolean check = service.removePost(postId);
        if(check) return ResponseEntity.ok().body("삭제가 완료되었습니다");
        else return ResponseEntity.badRequest().body("삭제가 완료되지 않았습니다.");
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


    //list/1?page=1
    @GetMapping("/board/paging/{locationId}")
    public ResponseEntity paging(@RequestParam(defaultValue = "0") int page,@PathVariable int locationId){
        Page<BoardDto> findPage = service.page(page, locationId);

        if(findPage == null) {
            return ResponseEntity.notFound().build();
        }else {
           return ResponseEntity.ok().body(findPage);
        }
    }
}
