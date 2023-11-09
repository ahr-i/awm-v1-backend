package com.example.teamproject.Service.SpringSecurityLogin;


import com.example.teamproject.JpaClass.UserTable.User;
import com.example.teamproject.Repository.JpaRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//로그인 Service
@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {
    private final UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        User findUser = repository.findByUserId(userId);

        if(findUser != null) {
            return new PrincipalDetails(findUser);
        }else throw new UsernameNotFoundException("찾을 수 없는 유저");
    }
}
