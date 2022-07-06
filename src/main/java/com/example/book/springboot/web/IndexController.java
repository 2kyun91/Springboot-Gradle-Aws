package com.example.book.springboot.web;

import com.example.book.springboot.config.auth.LoginUser;
import com.example.book.springboot.config.auth.dto.SessionUser;
import com.example.book.springboot.domain.user.User;
import com.example.book.springboot.service.posts.PostsService;
import com.example.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
//    private final HttpSession session; // 공통된 로직이므로 @LoginUser 어노테이션으로 대체한다.

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser sessionUser) { // Model : 클라이언트에서 사용할 수 있는 객체를 저장할 수 있다, findAllDesc() 결과를 저장.
        model.addAttribute("posts", postsService.findAllDesc());

        //SessionUser sessionUser = (SessionUser) session.getAttribute("user");

        if (!ObjectUtils.isEmpty(sessionUser)) {
            model.addAttribute("userName", sessionUser.getName());
            model.addAttribute("userEmail", sessionUser.getEmail());
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String savePosts() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }
}
