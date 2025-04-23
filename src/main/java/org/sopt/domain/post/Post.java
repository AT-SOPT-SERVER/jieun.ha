package org.sopt.domain.post;

public class Post {
    private final int id;
    private String title;

    public Post(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public void renameTitle(String newTitle) {
        this.title = newTitle;
    }
}
