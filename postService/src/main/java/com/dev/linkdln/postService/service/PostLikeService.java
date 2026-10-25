package com.dev.linkdln.postService.service;

import com.dev.linkdln.postService.auth.AuthContextHolder;
import com.dev.linkdln.postService.entity.Post;
import com.dev.linkdln.postService.entity.PostLike;
import com.dev.linkdln.postService.event.PostLiked;
import com.dev.linkdln.postService.exception.BadRequestException;
import com.dev.linkdln.postService.exception.ResourceNotFoundException;
import com.dev.linkdln.postService.repository.PostLikeRepository;
import com.dev.linkdln.postService.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;

    private final PostRepository postRepository;

    private final ModelMapper modelMapper;

    private final KafkaTemplate<Long, PostLike> postLikeKafkaTemplate;

    @Transactional
    public void likePost(Long postId) {

        Long userId = AuthContextHolder.getCurrentUserId();
        log.info("User with ID:{} liking the post with Id {}", userId, postId);
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found with ID: " + postId));

        boolean hasAlreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId, postId);

        if (hasAlreadyLiked)
            throw new BadRequestException("Cannot like post again");

        PostLike postLike = new PostLike();
        postLike.setPostId(postId);
        postLike.setUserId(userId);
        postLikeRepository.save(postLike);

        //TODO:sent kafka notification to the owner of the post
        PostLiked postLiked = PostLiked.builder()
                .postId(postId)
                .likedByUserId(userId)
                .ownerUserId(post.getUserId())
                .build();

        postLikeKafkaTemplate.send("post_liked_topic", postLike);


    }

    @Transactional
    public void unlikePost(Long postId) {
        Long userId = 1L;
        log.info("User with ID:{} unliking the post with Id {}", userId, postId);
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found with ID: " + postId));

        boolean hasAlreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId, postId);

        if (!hasAlreadyLiked)
            throw new com.dev.linkdln.postService.exception.BadRequestException("you Cannot unlike post which not liked");


        postLikeRepository.deleteByUserIdAndPostId(userId, postId);


    }
}
