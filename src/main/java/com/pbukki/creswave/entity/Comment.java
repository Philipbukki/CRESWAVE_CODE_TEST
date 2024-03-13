package com.pbukki.creswave.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="comments")
@AllArgsConstructor @NoArgsConstructor
@Setter @Getter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String body;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "post_id",nullable = false)
    private Post post;
}
