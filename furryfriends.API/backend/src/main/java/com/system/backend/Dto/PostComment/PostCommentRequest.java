package com.system.backend.Dto.PostComment;

import com.system.backend.Entity.PostDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCommentRequest {
    private Integer comment_id;
    private String comment;
    private String publish_date_comment;
}
