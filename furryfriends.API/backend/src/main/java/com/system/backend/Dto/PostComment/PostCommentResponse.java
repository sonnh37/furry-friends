package com.system.backend.Dto.PostComment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCommentResponse {
    private Integer comment_id;
    private Integer post_id;
    private Integer user_id;
    private String user_name;
    private String account;
    private String comment;
    private String publish_date_comment;
}
