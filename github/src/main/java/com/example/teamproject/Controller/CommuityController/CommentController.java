package com.example.teamproject.Controller.CommuityController;


import com.example.teamproject.Dto.CommuityDto.CommentDto;
import com.example.teamproject.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user/comment/")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService service;


    @PostMapping("/save")
    public ResponseEntity save(@RequestBody CommentDto dto){
        Boolean save = service.save(dto);

        if(save) {
            return ResponseEntity.ok().body("댓글 등록의 성공하였습니다.");
        }else return ResponseEntity.badRequest().body("댓글 등록의 실패 하였습니다.");
    }




}
