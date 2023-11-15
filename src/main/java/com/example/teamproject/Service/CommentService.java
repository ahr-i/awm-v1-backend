package com.example.teamproject.Service;


import com.example.teamproject.Dto.CommuityDto.CommentDto;
import com.example.teamproject.JpaClass.CommunityTable.BoardEntity;
import com.example.teamproject.JpaClass.CommunityTable.CommentEntity;
import com.example.teamproject.Repository.CommuityRepository.BoardRepository;
import com.example.teamproject.Repository.CommuityRepository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {

    private final CommentRepository repository;
    private final BoardRepository boardRepository;

    public Boolean save(CommentDto dto){

      try {
          Optional<BoardEntity> byId = boardRepository.findById(dto.getPostId());
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
