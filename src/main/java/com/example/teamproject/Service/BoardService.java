package com.example.teamproject.Service;
import com.example.teamproject.Dto.CommuityDto.BoardDto.BoardDto;
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

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {
    private final CommentRepository commentRepository;
    private final BoardRepository repository;
    private final LocationRepository locationRepository;
    public Boolean BoardSave(BoardDto dto, int locationId, String userId){
        try {
            /*
            첨부 파일 없을 때
             */
            Optional<Location> locationEntity = locationRepository.findById(locationId);
            BoardEntity boardEntity = BoardDto.SaveToBoardEntity(dto, locationEntity, userId);
            repository.save(boardEntity);

        }catch (IOException e){
            log.info("IO 예외 {}",e.getMessage());
            return false;
        }
        return true;
    }
    @Transactional
    public void updateHit(int postId){
        repository.updateHit(postId);
    }

    /**
     * 글 상세보기를 할때는 ReadToDto를 사용할 것.
     * @param postId
     * @return
     */
    public Response findByPostAndContent(int postId){
        Optional<BoardEntity> boardEntity = repository.findById(postId);
        List<CommentEntity> comment = commentRepository.findAllByEntityOrderByCreatTimeDesc(boardEntity.get());
        if(boardEntity.isPresent()) {
            BoardDto dto = BoardDto.DetailToBoardDto(boardEntity.get());
            Long commentCount = commentRepository.countAllByEntity(boardEntity.get());
            Response response = new Response(dto,comment,commentCount);
            return response;
        }
        else return null;
    }
    public boolean removePost(int postId){
        try {
            repository.removeByPostId(postId);
            return true;
        }catch (Exception e) {
            return false;
        }
    }
    public Page<BoardDto> page(int page, int locationId){

        List<BoardEntity> allBy = repository.findAllByLocation_LocationId(locationId);

        allBy.stream().filter(entity ->  entity.getReportCount() >= 10).forEach(entity -> repository.delete(entity));

        PageRequest createdDate = PageRequest.of(page, 3, Sort.by("createTime").descending());
        Page<BoardEntity> findPost = repository.findAllByLocation_LocationId(locationId, createdDate);

        Page<BoardDto> sendPostdto = BoardDto.PageToBoardPostDto(findPost, commentRepository);
        return sendPostdto;

    }
    public BoardEntity updatePost(BoardDto dto) throws IOException {
        Optional<BoardEntity> byId = repository.findById(dto.getPostId());

        if (byId.isPresent()) {
            BoardEntity entity = byId.get();
            BoardEntity saveentity = BoardDto.updatePost(dto, byId.get());
            repository.save(saveentity);
            return byId.get();
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
