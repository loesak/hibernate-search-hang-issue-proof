package com.example.hibernatesearchhangissueproof;

import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Indexed
public class Thing {

    @Id
    UUID id;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(nullable = false, unique = true)
    @FullTextField(analyzer = "english_custom")
    String name;

}
