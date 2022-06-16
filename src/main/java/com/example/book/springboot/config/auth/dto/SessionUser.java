package com.example.book.springboot.config.auth.dto;

import com.example.book.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

/**
 * 해당 클래스는 인증된 사용자 정보
 * 필요한 필드만 선언
 */
@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
