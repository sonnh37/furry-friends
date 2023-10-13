package com.system.backend.Enity;

import javax.persistence.*;

@Entity
@Table(name = "[post_detail]")
public class PostDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "[post_id]", nullable = false)
    private Integer postID;

    @ManyToOne
    @JoinColumn(name = "[user_id]", nullable = false, referencedColumnName = "[user_id]")
    private User user;

    @Column(name = "[title]")
    private String title;

    @Column(name = "[content]")
    private String content;

    @Column(name = "[publishdate]")
    private String publishDate;

    @Column(name = "[image]")
    private String image;

    @Column(name = "[comment]")
    private String comment;

    @Column(name = "[total_like]")
    private Integer totalLike;

    // getters and setters
}