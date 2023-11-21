package com.example.teamproject.Service;

import com.example.teamproject.Dto.CommuityDto.BoardDto;

import com.example.teamproject.Dto.CommuityDto.updateUserDto;
import com.example.teamproject.Dto.CommuityDto.Response;
import com.example.teamproject.JpaClass.CommunityTable.BoardEntity;
import com.example.teamproject.JpaClass.CommunityTable.CommentEntity;
import com.example.teamproject.JpaClass.LocationTable.Location;
import com.example.teamproject.Repository.CommuityRepository.BoardRepository;
import com.example.teamproject.Repository.CommuityRepository.CommentRepository;
import com.example.teamproject.Repository.LoactionRepository.LocationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {
    private final CommentRepository commentRepository;
    private final BoardRepository repository;
    private final LocationRepository locationRepository;
    public Boolean BoardSave(BoardDto dto,int locationId,String userId){
        try {
            Optional<Location> byId = locationRepository.findById(locationId);

            if(byId.isPresent()) {
                repository.save(BoardEntity.Dto_To_Entity(dto,byId,userId));
                return true;
            }

        }catch (Exception e){
            log.info("멤버 포스트 저장 오류 : {}",e.getMessage());
            e.printStackTrace();
            return false;
        }
        return null;
    }
    @Transactional
    public void updateHit(int postId){
        repository.updateHit(postId);
    }
    public Response findByPostAndContent(int postId){
        Optional<BoardEntity> byId = repository.findById(postId);
        List<CommentEntity> comment = commentRepository.findAllByEntityOrderByCreatTimeDesc(byId.get());
        if(byId.isPresent()) {
            Optional<updateUserDto> boardDto = byId.map(entity ->
                    new updateUserDto(entity.getId(),entity.getUserId(),entity.getBoardTitle()
                    ,entity.getBoardContent(),entity.getBoardHits(),entity.getCreateTime()));
            Response response = new Response(boardDto.get(),comment);
            return response;
        }
        else return null;
    }
    public boolean removePost(int postId){
        try {
            repository.removeById(postId);
            return true;
        }catch (Exception e) {
            return false;
        }
    }
    public Page<updateUserDto> page(int page, int locationId){

        List<BoardEntity> allBy = repository.findAllByLocation_LocationId(locationId);

        allBy.stream().filter(entity ->  entity.getReportCount() >= 10).forEach(entity -> repository.delete(entity));

        PageRequest createdDate = PageRequest.of(page, 3, Sort.by("createTime").descending());
        Page<BoardEntity> findPost = repository.findAllByLocation_LocationId(locationId, createdDate);
        Page<updateUserDto> findBoardDtos = findPost.map(entity -> new updateUserDto(entity.getId(), entity.getBoardWriter(), entity.getBoardTitle()
                , entity.getBoardContent(), entity.getBoardHits(), entity.getCreateTime()));
        return findBoardDtos;
    }
    public BoardEntity updateFindPostUser(int postId){
        Optional<BoardEntity> byId = repository.findById(postId);
        if(byId.isPresent()){
            return byId.get();
        }
        return null;
    }
    public BoardEntity updatePost(int postId, updateUserDto dto){
        Optional<BoardEntity> byId = repository.findById(dto.getId());

        if(byId.isPresent()) {
            BoardEntity entity = byId.get();

            entity.setBoardContent(dto.getBoardContent());
            entity.setBoardTitle(dto.getBoardTitle());

            repository.save(entity);
        }
            return null;
    }

    public BoardEntity updateFindByPost(int postId){
        Optional<BoardEntity> byId = repository.findById(postId);

        if(byId.isPresent()) {
           return byId.get();
        }else return null;

    }
}
