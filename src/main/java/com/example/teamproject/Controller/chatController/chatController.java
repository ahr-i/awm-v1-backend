package com.example.teamproject.Controller.chatController;


import com.example.teamproject.JpaClass.ChatTable.Chat;
import com.example.teamproject.JpaClass.ChatWoo.ChatWoo;
import com.example.teamproject.Repository.MongoDB.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class chatController {
    private final ChatRepository repository;



    @GetMapping(value = "/chat/roomNumber/{roomNumber}" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ChatWoo> getMsg(@PathVariable Integer roomNumber){
        return repository.findByRoomNumber(roomNumber).subscribeOn(Schedulers.boundedElastic());

    }
    @PostMapping("/chat")
    public Mono<ChatWoo> setMsg(@RequestBody ChatWoo chat){
        chat.setCreateAt(LocalDateTime.now());
       return repository.save(chat);
    }

}
