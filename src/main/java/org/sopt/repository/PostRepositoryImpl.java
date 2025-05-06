package org.sopt.repository;

import org.sopt.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepositoryImpl extends JpaRepository<Post, Long> {
}
