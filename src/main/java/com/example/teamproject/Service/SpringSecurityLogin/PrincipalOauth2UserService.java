package com.example.teamproject.Service.SpringSecurityLogin;

//import com.example.teamproject.Repository.JPARePository;
import com.example.teamproject.Dto.UserDto.UserDto;
import com.example.teamproject.JpaClass.UserTable.Oauth2UserEntity;
        import com.example.teamproject.Repository.Oauth2Repository.Oauth2Repository;
import com.example.teamproject.prvoider.Oauth2UserInfo;
import com.example.teamproject.prvoider.googleOauthUser;
import com.example.teamproject.prvoider.naverOauthUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
    /**
     * 사용자가 구글 API로 LoginForm으로 들어갈시 여기로 들어가게 된다.
     * 구글 로그인 버튼 -> 로그인 창 -> 로그인을 완료하면 code를 리턴(OAuth Client 라이브러리가 받음) -> Access Token 요청
     * userRequest 정보 -> loadUser 함수호출 -> 구글로 부터 회원 프로필 받음
     * 여기서의 ProviderId는 Email임.
     */

    private final Oauth2Repository oauth2Repository;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Oauth2UserInfo userInfo = null;
        Oauth2UserEntity entity = null;
        Optional<Oauth2UserEntity> oauth2User = null;
        UserDto dto = null;

        if(userRequest.getClientRegistration().getRegistrationId().equals("google")){
            log.info("구글 로그인 요청");
         userInfo = new googleOauthUser(oAuth2User.getAttributes());
            oauth2User = oauth2Repository.findByProviderUserId(userInfo.getProviderId());
            if(!oauth2User.isPresent()) {
                entity = Oauth2UserEntity.saveTransferOauth2User(userInfo);
                oauth2Repository.save(entity);
            }

            Optional<Oauth2UserEntity> oauthUser = oauth2Repository.findByProviderUserId(userInfo.getProviderId());
             dto = UserDto.oauthTransferEntity(oauthUser.get());
        }

        else if(userRequest.getClientRegistration().getRegistrationId().equals("naver")){
            log.info("네이버 로그인 요청");
            userInfo = new naverOauthUser((Map)oAuth2User.getAttributes().get("response"));
             oauth2User = oauth2Repository.findByProviderUserId(userInfo.getProviderId());
            if(!oauth2User.isPresent()) {
                entity = Oauth2UserEntity.saveTransferOauth2User(userInfo);
                oauth2Repository.save(entity);
            }

            Optional<Oauth2UserEntity> oauthUser = oauth2Repository.findByProviderUserId(userInfo.getProviderId());
             dto = UserDto.oauthTransferEntity(oauthUser.get());

        }
        return new PrincipalDetails(oAuth2User.getAttributes(),dto);
    }
}
