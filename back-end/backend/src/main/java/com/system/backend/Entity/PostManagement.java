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
@Table(name = "[post_management]")
public class PostManagement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "[stt]")
    private Integer stt;

    @ManyToOne
    @JoinColumn(name = "[role_id]", referencedColumnName = "[role_id]")
    private Role role;


    @ManyToOne
    @JoinColumn(name = "[user_id]", referencedColumnName = "[user_id]")
    private User user;

    @ManyToOne
    @JoinColumn(name = "[post_id]", referencedColumnName = "[post_id]")
    private PostDetail postDetail;

    // getters and setters
}
