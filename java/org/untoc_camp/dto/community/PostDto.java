package org.untoc_camp.dto.community;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDto {
    private String author;
    private String title;
    private String content;
    private String password;
}
