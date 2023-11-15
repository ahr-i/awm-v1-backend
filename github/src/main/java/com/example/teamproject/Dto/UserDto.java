<<<<<<< HEAD
package com.example.teamproject.Dto;


import com.example.teamproject.JpaClass.UserTable.User;
import com.example.teamproject.prvoider.Oauth2UserInfo;
import lombok.Data;

@Data
public class UserDto {

    private String userId;
    private String nickName;


    private int rankScore = 0;
    private String image;
    private String image_hash;
    private String phoneNumber;
    private String email;
    private int state = 0;
    private String provider;
    private String password;

    public User oauthTransfer(Oauth2UserInfo info){
        String randomName = CharacterName.getRandomName();
        return User.builder().userId(info.getProviderId())
                .image(null)
                .nickName(randomName)
                .phoneNumber(null).email(info.getEmail())
                .password(null).build();
    }
    public User TransferUser(User user){
        String randomName = CharacterName.getRandomName();
        return User.builder().userId(user.getUserId())
                .nickName(randomName)
                .image(null).phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .password(user.getPassword()).rankScore(0)
                .build();
    }

}
=======
package com.example.teamproject.Dto;


import com.example.teamproject.JpaClass.UserTable.User;
import com.example.teamproject.prvoider.Oauth2UserInfo;
import lombok.Data;

@Data
public class UserDto {

    private String userId;
    private String nickName;


    private int rankScore = 0;
    private String image;
    private String image_hash;
    private String phoneNumber;
    private String email;
    private int state = 0;
    private String provider;
    private String password;

    public User oauthTransfer(Oauth2UserInfo info){
        String randomName = CharacterName.getRandomName();
        return User.builder().userId(info.getProviderId())
                .image(null)
                .nickName(randomName)
                .phoneNumber(null).email(info.getEmail())
                .password(null).build();
    }
    public User TransferUser(User user){
        String randomName = CharacterName.getRandomName();
        return User.builder().userId(user.getUserId())
                .nickName(randomName)
                .image(null).phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .password(user.getPassword()).rankScore(0)
                .build();
    }

}
>>>>>>> ec39bc92820df73215dd9b39b629f9db2cbb79f2
