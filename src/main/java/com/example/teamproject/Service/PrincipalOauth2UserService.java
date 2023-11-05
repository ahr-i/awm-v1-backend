package com.example.teamproject.Service;

import com.example.teamproject.JpaClass.OauthUser_Info;
import com.example.teamproject.JpaClass.UserTable.AuthUserDetail;
import com.example.teamproject.Repository.JPARePository;
import com.example.teamproject.Repository.MemberRepository;
import com.example.teamproject.Repository.Oauth2Repository;
import com.example.teamproject.Security.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
    /**
     * 사용자가 구글 API로 LoginForm으로 들어갈시 여기로 들어가게 된다.
     * 구글 로그인 버튼 -> 로그인 창 -> 로그인을 완료하면 code를 리턴(OAuth Client 라이브러리가 받음) -> Access Token 요청
     * userRequest 정보 -> loadUser 함수호출 -> 구글로 부터 회원 프로필 받음
     *
     */

    private final MemberRepository repository;
    private final Oauth2Repository oauth2Repository;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String sub = oAuth2User.getAttribute("sub");
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String provider = userRequest.getClientRegistration().getClientId();

        


        AuthUserDetail findByUser = oauth2Repository.findAllByUserId(sub);
        if(findByUser == null){
            AuthUserDetail detail = new AuthUserDetail().builder()
                            .userId(sub)
                                    .provider(provider)
                                            .code(registrationId)
                                                    .build();



            oauth2Repository.save(detail);
        }

        return new PrincipalDetails(oAuth2User.getAttributes());
    }
}
