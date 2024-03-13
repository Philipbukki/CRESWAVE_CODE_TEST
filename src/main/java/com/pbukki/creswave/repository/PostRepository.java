package com.pbukki.creswave.repository;

import com.pbukki.creswave.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PostRepository extends JpaRepository<Post,Long> {
}
