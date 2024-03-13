package com.pbukki.creswave.repository;

import com.pbukki.creswave.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByTitleContainingOrContentContaining(String title, String content);

}
