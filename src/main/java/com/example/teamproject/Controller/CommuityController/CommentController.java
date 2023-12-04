package com.example.teamproject.Controller.CommuityController;


import com.example.teamproject.Dto.CommuityDto.BoardDto.CommentDto;
import com.example.teamproject.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user/comment/")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService service;
    @PostMapping("/save/{postId}")
    public ResponseEntity save(@RequestBody CommentDto dto, @PathVariable int postId, Authentication authentication){
        return  service.save(dto, postId, authentication);
    }

}
