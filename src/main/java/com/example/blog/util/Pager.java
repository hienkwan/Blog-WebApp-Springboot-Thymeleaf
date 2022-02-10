package com.example.blog.util;

import com.example.blog.dto.PostInfoDto;
import com.example.blog.model.Post;
import org.springframework.data.domain.Page;

public class Pager {
    private final Page<PostInfoDto> posts;

    public Pager(Page<PostInfoDto> posts) {
        this.posts = posts;
    }

    public int getPageIndex() {
        return posts.getNumber() + 1;
    }

    public int getPageSize() {
        return posts.getSize();
    }

    public boolean hasNext() {
        return posts.hasNext();
    }

    public boolean hasPrevious() {
        return posts.hasPrevious();
    }

    public int getTotalPages() {
        return posts.getTotalPages();
    }

    public long getTotalElements() {
        return posts.getTotalElements();
    }

    public Page<PostInfoDto> getPosts() {
        return posts;
    }

    public boolean indexOutOfBounds() {
        return getPageIndex() < 0 || getPageIndex() > getTotalElements();
    }
}
