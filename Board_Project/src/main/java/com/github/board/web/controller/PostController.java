package com.github.board.web.controller;

import com.github.board.repository.Posts.Post;
import com.github.board.service.PostService;
import com.github.board.service.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class PostController {


    private final PostService postService;

    // 게시물 생성
    @PostMapping("/posts")
    public ResponseEntity<String> createPost(@RequestBody Post post) {

        Post createdPost = postService.createPost(post);
        if (createdPost != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("게시물이 생성되었습니다");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("게시물 생성에 실패했습니다");
        }
    }

    // 모든 게시물 조회
    @GetMapping("/posts")
    public List<Post> getAllPosts() {

        return postService.getAllPosts();
    }

    // 게시물 조회 by ID

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Integer id) {
        Optional<Post> post = postService.getPostById(id);
        if (post.isPresent()) {
            return ResponseEntity.ok(post.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 게시물 수정
    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Integer id, @RequestBody Post updatedPost) {
        try {
            Post post = postService.updatePost(id, updatedPost);
            return ResponseEntity.ok(post);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 게시물 삭제
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Integer id) {
        try {
            postService.deletePost(id);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    //추가: 이메일로 게시물 검색 API
    @GetMapping("/byEmail/{email}")
    public List<Post> getPostByAuthor(@PathVariable(name = "email") String author) {
        // author 매개변수를 사용하여 로직 수행
        return postService.getPostsByAuthor(author);
    }
}

