package com.ramidev.socialnetwork.services;

import com.ramidev.socialnetwork.dto.post.PostCreateDto;
import com.ramidev.socialnetwork.dto.post.PostDto;
import com.ramidev.socialnetwork.entities.Post;
import com.ramidev.socialnetwork.entities.Profile;
import com.ramidev.socialnetwork.exception.NotFoundException;
import com.ramidev.socialnetwork.repositories.PostRepository;
import com.ramidev.socialnetwork.repositories.ProfileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PostDto> getAll() {
        return postRepository.findAll().stream().map(data -> modelMapper.map(data, PostDto.class)).toList();
    }

    @Override
    public PostDto getById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("La publicación %s no existe!", id)));

        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto create(String email, PostCreateDto postCreateDto) {
        Profile profile = profileRepository.findByUserEmail(email).orElseThrow(() -> new NotFoundException(String.format("Usuario %s no encontrado!", email)));
        Post post = modelMapper.map(postCreateDto, Post.class);
        post.setProfile(profile);
        return modelMapper.map(postRepository.save(post), PostDto.class);
    }

    @Override
    public PostDto editById(Long id, Map<Object, Object> postEdit) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("La publicación %s no existe!", id)));

        postEdit.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Post.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, post, value);
        });
        return modelMapper.map(postRepository.save(post), PostDto.class);
    }

    @Override
    public String deleteById(Long id) {
        if(!postRepository.existsById(id))
            throw new NotFoundException(String.format("La publicación %s no existe!", id));

        postRepository.deleteById(id);
        return "Publicación eliminada correctamente!";
    }
}
