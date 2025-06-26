package org.sopt.domain;

import jakarta.persistence.*;
import org.sopt.dto.type.ErrorMessage;
import org.sopt.exception.CustomException;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @OneToMany(mappedBy = "comment")
    private final List<CommentLike> commentLikes = new ArrayList<>();

    protected Comment() {}

    public Comment(String content, User author, Post post) {
        this.content = content;
        this.user = author;
        this.post = post;
    }

    public Long getId() {
        return id;
    }
    public String getContent() { return content; }
    public String getUserName() { return user.getName(); }
    public void updateContent(String content) {
        this.content = content;
    }
    public Post getPost() { return post; }

    public boolean validateIdIsSame(Long requestUserId) {
        if (!user.getId().equals(requestUserId)) {
            throw new CustomException(ErrorMessage.UNAUTHORIZED_ERROR);
        }
        return false;
    }
}
