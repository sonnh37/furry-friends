package com.system.backend.Dto.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostRequest {
    private Integer user_id;

    private String title;
    @Lob
    private String content;
    private String publishdate;
    @Lob
    private String image;
}
