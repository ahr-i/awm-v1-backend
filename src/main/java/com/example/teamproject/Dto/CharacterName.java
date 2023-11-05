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
}