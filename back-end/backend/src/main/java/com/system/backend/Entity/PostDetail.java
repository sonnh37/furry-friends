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

@Table(name = "[post_detail]")
public class PostDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "[post_id]", nullable = false)
    private Integer post_id;

    @ManyToOne
    @JoinColumn(name = "[user_id]", nullable = false, referencedColumnName = "[user_id]")
    private User user;

    @Column(name = "[title]", columnDefinition = "nvarchar(255)")
    private String title;

    @Column(name = "[content]" , columnDefinition = "nvarchar(3000)")
    private String content;

    @Column(name = "[publishdate]")
    private String publishDate;

    @Column(name = "[image]" , columnDefinition = "varchar(max)")
    private String image;


    @Column(name = "[total_like]")
    private Integer totalLike;
    @Column(name = "[total_comment]")
    private Integer totalComment;

    // getters and setters
}