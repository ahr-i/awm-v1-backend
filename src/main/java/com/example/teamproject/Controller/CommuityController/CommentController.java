package com.example.teamproject.Controller.CommuityController;


import com.example.teamproject.Dto.CommuityDto.BoardDto.CommentDto;
import com.example.teamproject.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user/comment/")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService service;


    @PostMapping("/save/{postId}")
    public ResponseEntity save(@RequestBody CommentDto dto, @PathVariable int postId){
        Boolean save = service.save(dto,postId);

        if(save) {
            return ResponseEntity.ok().body("댓글 등록의 성공하였습니다.");
        }else return ResponseEntity.badRequest().body("댓글 등록의 실패 하였습니다.");
    }




}
