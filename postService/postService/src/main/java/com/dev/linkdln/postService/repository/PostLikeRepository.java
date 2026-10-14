package com.dev.linkdln.postService.repository;


import com.dev.linkdln.postService.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface    PostLikeRepository extends JpaRepository<PostLike, Long> {

    boolean existsByUserIdAndPostId(Long userId, Long postId);

    void deleteByUserIdAndPostId(Long userId, Long postId);
}
