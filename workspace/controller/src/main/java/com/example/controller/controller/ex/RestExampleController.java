package com.example.controller.controller.ex;

import com.example.controller.domain.Member;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/**")
public class RestExampleController {
    @GetMapping("ex01")
    public String ex01(){
        return "한동석";
    }

    @GetMapping("/ex02")
    public Member ex02(){
        Member member = new Member();
        member.setName("한동석");
        member.setAge(20);
        return member;
    }
}
