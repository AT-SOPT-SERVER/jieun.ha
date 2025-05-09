package org.sopt.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    protected Post() {
    }

    public Post(@NotBlank String title, @NotBlank String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public Long getId() {
        return this.id;
    }

    @NotBlank
    public String getTitle() {
        return this.title;
    }

    @NotBlank
    public String getContent() {
        return content;
    }

    public User getUser() {
        return user;
    }

    public void renameTitle(String newTitle) {
        this.title = newTitle;
    }
}
