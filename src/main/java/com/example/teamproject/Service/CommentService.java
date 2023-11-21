package com.example.teamproject.Service;


import com.example.teamproject.Dto.CommuityDto.BoardDto.CommentDto;
import com.example.teamproject.JpaClass.CommunityTable.BoardEntity;
import com.example.teamproject.JpaClass.CommunityTable.CommentEntity;
import com.example.teamproject.Repository.CommuityRepository.BoardRepository;
import com.example.teamproject.Repository.CommuityRepository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {

    private final CommentRepository repository;
    private final BoardRepository boardRepository;

    public Boolean save(CommentDto dto,int postId){

      try {
          Optional<BoardEntity> byId = boardRepository.findById(postId);
          if(byId.isPresent()) {
              CommentEntity entity = CommentDto.TransferCommentEntity(dto,byId.get());
              repository.save(entity);
              return true;
          }
      }catch (Exception e){
          return false;
      }
        return null;
    }
}
