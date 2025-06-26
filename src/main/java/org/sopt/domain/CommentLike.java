package org.sopt.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "comment_like", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"comment_id", "user_id"})
})
public class CommentLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentLikeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    protected CommentLike() {}

    public CommentLike(Comment comment, Post post, User user) {
        this.comment = comment;
        this.post = post;
        this.user = user;
    }
}
