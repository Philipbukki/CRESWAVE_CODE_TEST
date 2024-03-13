package com.pbukki.creswave.repository;

import com.pbukki.creswave.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByPostIdAndId(long postId, long commentId);
}
