package com.dev.linkdln.postService.service;

import com.dev.linkdln.postService.auth.AuthContextHolder;
import com.dev.linkdln.postService.client.ConnectionsServiceClient;
import com.dev.linkdln.postService.dto.PersonDto;
import com.dev.linkdln.postService.dto.PostCreateRequestDto;
import com.dev.linkdln.postService.dto.PostDto;
import com.dev.linkdln.postService.entity.Post;
import com.dev.linkdln.postService.exception.ResourceNotFoundException;
import com.dev.linkdln.postService.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;

    private final ModelMapper modelMapper;

    private final ConnectionsServiceClient connectionsServiceClient;


    public PostDto createPost(PostCreateRequestDto postCreateRequestDto, Long userId) {
        log.info("Creating Post for user with id: {}", userId);
        Post post = modelMapper.map(postCreateRequestDto, Post.class);
        post.setUserId(userId);
        post = postRepository.save(post);
        return modelMapper.map(post, PostDto.class);


    }

    public PostDto getPostById(Long postId) {
        log.info("Getting the post with ID: {}", postId);

        Long userId = AuthContextHolder.getCurrentUserId();


        List<PersonDto> personDtoList = connectionsServiceClient.getFirstDegreeConnections(userId);

        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post Not Found " + postId));

        return modelMapper.map(post, PostDto.class);
    }

    public List<PostDto> getAllPostsOfUser(Long userId) {
        log.info("Getting all the posts of a user with ID: {}", +userId);
        List<Post> postList = postRepository.findByUserId(userId);

        return postList.stream().map((element) -> modelMapper.map(element, PostDto.class)).collect(Collectors.toList());

    }
}
