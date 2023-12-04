package com.example.teamproject.Repository.MongoDB;

import com.example.teamproject.JpaClass.ChatTable.Chat;
import com.example.teamproject.JpaClass.ChatWoo.ChatWoo;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;

public interface ChatRepository extends ReactiveMongoRepository<ChatWoo,String> {
    @Tailable
    @Query("{sender:?0,receiver:?1}")
    Flux<Chat> findBySender(String sender,String receiver);


    @Tailable
    @Query("{roomNumber:?0}")
    Flux<ChatWoo> findByRoomNumber(Integer roomNumber);
}
