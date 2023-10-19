package com.system.backend.Entity;

import javax.persistence.*;

@Entity
@Table(name = "[post_management]")
public class PostManagement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "[product_id]",nullable = false)
    private Integer productID;

    @ManyToOne
    @JoinColumn(name = "[role_id]", nullable = false, referencedColumnName = "[role_id]")
    private Role role;


    @ManyToOne
    @JoinColumn(name = "[user_id]", nullable = false, referencedColumnName = "[user_id]")
    private User user;

    @ManyToOne
    @JoinColumn(name = "[post_id]", nullable = false, referencedColumnName = "[post_id]")
    private PostDetail postDetail;

    // getters and setters
}
