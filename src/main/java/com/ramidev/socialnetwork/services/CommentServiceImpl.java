package com.ramidev.socialnetwork.services;

import com.ramidev.socialnetwork.dto.comment.CommentCreateDto;
import com.ramidev.socialnetwork.dto.comment.CommentDto;
import com.ramidev.socialnetwork.dto.comment.CommentEditDto;
import com.ramidev.socialnetwork.dto.comment.CommentPostDto;
import com.ramidev.socialnetwork.entities.Comment;
import com.ramidev.socialnetwork.entities.Post;
import com.ramidev.socialnetwork.entities.Profile;
import com.ramidev.socialnetwork.exception.BadRequestException;
import com.ramidev.socialnetwork.exception.NotFoundException;
import com.ramidev.socialnetwork.repositories.CommentRepository;
import com.ramidev.socialnetwork.repositories.PostRepository;
import com.ramidev.socialnetwork.repositories.ProfileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CommentDto> getAll() {
        return commentRepository.findAll()
                .stream()
                .map(data -> modelMapper.map(data, CommentDto.class))
                .toList();
    }

    @Override
    public List<CommentDto> getAllByPostId(Long postId) {
        if(!postRepository.existsById(postId))
            throw new NotFoundException(String.format("La publicación %s no existe!", postId));

        return commentRepository.findAllByPostId(postId)
                .stream()
                .map(data -> modelMapper.map(data, CommentDto.class))
                .toList();
    }

    @Override
    public CommentPostDto createInPost(Long postId, String email, CommentCreateDto commentCreateDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(String.format("La publicación %s no existe!", postId)));

        Profile profile = profileRepository.findByUserEmail(email)
                .orElseThrow(() -> new NotFoundException(String.format("El usuario %s no existe!", email)));

        Comment comment = modelMapper.map(commentCreateDto, Comment.class);
        comment.setPost(post);
        comment.setProfile(profile);

        return modelMapper.map(commentRepository.save(comment), CommentPostDto.class);
    }

    @Override
    public CommentDto editById(Long postId, Long id, CommentEditDto commentEditDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(String.format("La publicación %s no existe!", postId)));

        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("El comentario %s no existe!", postId)));

        if(!comment.getPost().getId().equals(post.getId()))
            throw new BadRequestException("El comentario no pertenece a la publicación");

        comment.setDescription(commentEditDto.getDescription());

        return modelMapper.map(commentRepository.save(comment), CommentDto.class);
    }

    @Override
    public String deleteById(Long postId, Long id) {
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new NotFoundException(String.format("La publicación %s no existe!", postId)));

        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("El comentario %s no existe!", postId)));

        if(!comment.getPost().getId().equals(post.getId()))
            throw new BadRequestException("El comentario no pertenece a la publicación");

        commentRepository.deleteById(id);
        return "Comentario eliminado correctamente!";
    }
}
