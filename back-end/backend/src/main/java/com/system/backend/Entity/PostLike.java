package com.system.backend.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "[post_like]")
public class PostLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "[like_id]", nullable = false)
    private Integer likeID;

    @ManyToOne
    @JoinColumn(name = "[post_id]", nullable = false, referencedColumnName = "[post_id]")
    private PostDetail postDetail;

    @ManyToOne
    @JoinColumn(name = "[user_id]", nullable = false, referencedColumnName = "[user_id]")
    private User user;

    @Column(name = "[status]")
    private Boolean status;

    // getters and setters
}
