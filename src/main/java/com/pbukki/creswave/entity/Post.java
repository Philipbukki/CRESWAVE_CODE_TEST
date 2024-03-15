package com.pbukki.creswave.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "posts")
@NoArgsConstructor @AllArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String content;
    @JsonManagedReference
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments;
    @CreatedBy
    private String createdBy;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedBy
    private String updatedBy;
    @LastModifiedDate
    private LocalDateTime updatedAt;

}
