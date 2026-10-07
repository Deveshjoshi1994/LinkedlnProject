package com.dev.linkdln.postService.service;

import com.dev.linkdln.postService.entity.Post;
import com.dev.linkdln.postService.entity.PostLike;
import com.dev.linkdln.postService.exception.ResourceNotFoundException;
import com.dev.linkdln.postService.repository.PostLikeRepository;
import com.dev.linkdln.postService.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;

    private final PostRepository postRepository;

    private final ModelMapper modelMapper;

    public void likePost(Long postId) {

        Long userId = 1L;
        log.info("User with ID:{} liking the post with Id {}", userId, postId);
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found with ID: " + postId));

        boolean hasAlreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId, postId);

        if (hasAlreadyLiked)
            throw new com.dev.linkdln.postService.exception.BadRequestException("Cannot like post again");

        PostLike postLike = new PostLike();
        postLike.setPostId(postId);
        postLike.setUserId(userId);
        postLikeRepository.save(postLike);

        //TODO:sent notification to the owner of the post
    }

    public void unlikePost(Long postId) {

    
    }
}
