package com.example.book.springboot.domain.posts;

import com.example.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity // 테이블과 링크될 클래스임을 선언, default로 클래스의 카멜케이스 이름을 '_'로 테이블 이름과 매칭됨.
public class Posts extends BaseTimeEntity {

    @Id // 테이블의 PK 필드임을 선언 
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성규칙, IDENTITY : auto_increment
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
