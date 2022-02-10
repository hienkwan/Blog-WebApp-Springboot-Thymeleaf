package com.example.blog.dto;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.util.Date;

@Getter
@Setter
public class PostInfoDto {
    private ObjectId _id;

    private String title;

    private String content;

    private Date createdDate;

    private String author;
}
