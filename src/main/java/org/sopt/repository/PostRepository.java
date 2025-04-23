package org.sopt.repository;

import org.sopt.domain.post.Post;

import java.util.ArrayList;
import java.util.List;

// 데이터 관리
public class PostRepository {
    List<Post> postList = new ArrayList<>();

    public void save(Post post) {
        postList.add(post);
    }

    public List<Post> findAll() {
        return new ArrayList<>(postList);
    }

    public Post findById(int id) {
        for (Post post : postList) {
            if (post.getId() == id) {
                return post;
            }
        }
        return null;
    }

    public boolean deletePostById(int id) {
        return postList.removeIf(post ->
                post.getId() == id
        );
    }

    public boolean modifyPostTitleById(int id, String newTitle) {
        Post post = findById(id);

        if (post != null) {
            post.renameTitle(newTitle);
            return true;
        }
        return false;
    }
}
