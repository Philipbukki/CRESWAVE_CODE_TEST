package com.pbukki.creswave.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "posts")
@NoArgsConstructor @AllArgsConstructor
@ToString @Setter @Getter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String content;
    @JsonManagedReference
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<Comment> comments;
    public void addComment(Comment comment) {
        if (comments == null) {
            comments = new HashSet<>();
        }
        comments.add(comment);
        comment.setPost(this);
    }

}
