package org.sopt.domain;

import jakarta.persistence.*;
import org.sopt.dto.type.ErrorMessage;
import org.sopt.exception.CustomException;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @OneToMany(mappedBy = "post")
    private List<CommentLike> commentLikes;

    protected Post() {
    }

    public Post (String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return content;
    }

    public User getUser() {
        return user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void renameTitle(String newTitle) {
        this.title = newTitle;
    }

    public void validateIdIsSame(Long requestUserId) {
        if (!user.getId().equals(requestUserId)) {
            throw new CustomException(ErrorMessage.UNAUTHORIZED_ERROR);
        }
    }
}
