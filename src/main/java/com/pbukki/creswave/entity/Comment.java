package com.pbukki.creswave.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name="comments")
@AllArgsConstructor @NoArgsConstructor
@Setter @Getter
public class Comment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String body;
    @JsonBackReference
    @JoinColumn(name = "post_id")
    @ManyToOne(cascade = CascadeType.DETACH)
    private Post post;
}
