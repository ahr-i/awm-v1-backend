package com.example.teamproject.Controller.TestController;

import com.example.teamproject.JpaClass.UserTable.ImageTest;
import com.example.teamproject.Repository.ImageTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class TestController {

    private final ImageTestRepository repository;

    @GetMapping("/PostTest")
    public String save1() {
        return "post";
    }

    @PostMapping("/PostTest")
    public ResponseEntity save2(@RequestParam("imageFile") MultipartFile imageFile) {

        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                // MultipartFile을 byte[]로 변환
                byte[] imageData = imageFile.getBytes();

                // ImageTest 엔티티 생성 및 데이터 설정
                ImageTest imageTest = new ImageTest();
                imageTest.setImage(imageData);

                // 데이터베이스에 저장
                repository.save(imageTest);

                return ResponseEntity.ok().body("성공 ");
            } catch (IOException e) {
                e.printStackTrace();

            }
        } else {
            return ResponseEntity.badRequest().body("실패");
        }
        return null;
    }
    @GetMapping("test/{id}")
    public String show(@PathVariable int id , Model model){
        Optional<ImageTest> byId = repository.findById(id);
        String string = Base64.getEncoder().encodeToString(byId.get().getImage());
        model.addAttribute("imageBase64",string);
        return "imageTest";
    }
}