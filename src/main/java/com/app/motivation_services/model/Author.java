package com.app.motivation_services.model;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Author")
@Data
public class Author {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter
    @Setter
    @Column(name = "AUTHORNAME")
    @NonNull
    private String authorName;

    public Author() {}
}
