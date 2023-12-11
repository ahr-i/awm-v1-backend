package com.example.teamproject.Service.CommunityService;


import com.example.teamproject.Dto.CommuityDto.BoardDto.CommentDto;
import com.example.teamproject.JpaClass.CommunityTable.BoardEntity;
import com.example.teamproject.JpaClass.CommunityTable.CommentEntity;
import com.example.teamproject.Repository.CommuityRepository.BoardRepository;
import com.example.teamproject.Repository.CommuityRepository.CommentRepository;
import com.example.teamproject.Service.SpringSecurityLogin.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {

    private final CommentRepository repository;
    private final BoardRepository boardRepository;

    public ResponseEntity save(CommentDto dto, int postId, Authentication authentication){

          Optional<BoardEntity> byId = boardRepository.findById(postId);

          if(byId.isPresent()) {
              PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
              String userId = principal.getUserInfo().getUserId();
              CommentEntity entity = CommentDto.TransferCommentEntity(dto,byId.get(),userId);
              repository.save(entity);
             return ResponseEntity.ok().body("댓글 등록이 완료 되었습니다.");
          }else if(!byId.isPresent())  return ResponseEntity.status(HttpStatus.NO_CONTENT).body("해당 게시글이 없습니다.");

          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("댓글 등록에 실패 했습니다");
    }
}
