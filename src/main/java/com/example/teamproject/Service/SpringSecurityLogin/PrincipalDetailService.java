package com.example.teamproject.Service.SpringSecurityLogin;
import com.example.teamproject.Dto.UserDto;
import com.example.teamproject.JpaClass.UserTable.UserEntity;
import com.example.teamproject.Repository.MySQL.JpaRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

//로그인 Service
@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<UserEntity> findByUser = userRepository.findByUserId(userId);

        if(findByUser != null) {
            UserDto dto = UserDto.UserEntityToUserDto(findByUser.get());
            return new PrincipalDetails(dto);
        }else throw new UsernameNotFoundException("찾을 수 없는 유저");
    }
}
