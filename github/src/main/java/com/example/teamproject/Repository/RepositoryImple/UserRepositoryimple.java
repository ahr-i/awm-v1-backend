<<<<<<< HEAD
package com.example.teamproject.Repository.RepositoryImple;


import com.example.teamproject.JpaClass.UserTable.User;
import com.example.teamproject.Repository.JpaRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryimple {

    private final UserRepository repository;

    public User findByUserId(String userId){
        return repository.findByUserId(userId);
    }


}
=======
package com.example.teamproject.Repository.RepositoryImple;


import com.example.teamproject.JpaClass.UserTable.User;
import com.example.teamproject.Repository.JpaRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryimple {

    private final UserRepository repository;

    public User findByUserId(String userId){
        return repository.findByUserId(userId);
    }


}
>>>>>>> ec39bc92820df73215dd9b39b629f9db2cbb79f2
