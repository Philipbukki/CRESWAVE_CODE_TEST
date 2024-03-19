package com.pbukki.creswave.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @Schema(description = "name",example = "ROLE_USER")
    private String name;
    public Role(String name) {
        this.name = name;
    }
}