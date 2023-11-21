<<<<<<< HEAD
package com.example.teamproject.Dto;

import java.util.Random;

public enum CharacterName {
    가렌, 카타리나, 제드;

    public static String getRandomName() {
        Random random = new Random();
        CharacterName[] characterNames = CharacterName.values();
        int randomIndex = random.nextInt(characterNames.length);
        return characterNames[randomIndex].toString(); // Enum을 String으로 변환
    }
=======
package com.example.teamproject.Dto;

import java.util.Random;

public enum CharacterName {
    가렌, 카타리나, 제드;

    public static String getRandomName() {
        Random random = new Random();
        CharacterName[] characterNames = CharacterName.values();
        int randomIndex = random.nextInt(characterNames.length);
        return characterNames[randomIndex].toString(); // Enum을 String으로 변환
    }
>>>>>>> ec39bc92820df73215dd9b39b629f9db2cbb79f2
}