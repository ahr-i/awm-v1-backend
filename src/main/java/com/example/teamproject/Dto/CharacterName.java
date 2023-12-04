package com.example.teamproject.Dto;

import java.util.Random;

public enum CharacterName {
    익명의유저;

    public static String getRandomName() {
        Random random = new Random();
        CharacterName[] characterNames = CharacterName.values();
        int randomIndex = random.nextInt(characterNames.length);
        return characterNames[randomIndex].toString(); // Enum을 String으로 변환
    }
}