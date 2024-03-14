package com.pbukki.creswave.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Schema(
        name = "Roles",
        description = "Table holding User Roles"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Roles")
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "name",example = "ROLE_USER")
    private String name;
}