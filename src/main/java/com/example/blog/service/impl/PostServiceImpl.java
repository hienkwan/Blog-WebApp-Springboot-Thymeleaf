package com.example.blog.service.impl;

import com.example.blog.exception.PostNotFoundException;
import com.example.blog.model.Comment;
import com.example.blog.model.Post;
import com.example.blog.repository.CommentRepository;
import com.example.blog.repository.PostRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.example.blog.service.PostService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;


    @Override
    public Page<Post> findAllOrderByDatePageable(int page) {
        return postRepository.findAllByOrderByCreatedDateDesc(PageRequest.of(subtractPageByOne(page),5));
    }

    @Override
    public Optional<Post> findPostById(ObjectId id) {
        return postRepository.findById(id);
    }

    @Override
    public Post addCommentByPostId(Comment comment, String postId) throws PostNotFoundException {
        ObjectId  objectId= new ObjectId(postId);
        Optional<Post> post = postRepository.findById(objectId);
        if(post.isPresent()){
            comment.setCommentDate(new Date());
            List<Comment> commentList = post.get().getComments();
            if(commentList==null){
                commentList = new ArrayList<>();
            }
            commentList.add(comment);
            post.get().setComments(commentList);
            return postRepository.save(post.get());
        }else{
            throw new PostNotFoundException("Post id:"+postId+" not found");
        }
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    private int subtractPageByOne(int page){
        return (page < 1) ? 0 : page - 1;
    }
}
