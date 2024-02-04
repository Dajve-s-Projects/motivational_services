package com.app.motivation_services.model;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Author")
@Data
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "AUTHORNAME")
    @NonNull
    private String authorName;

    public Author() {}

    public Author(@NonNull String authorName) {
        this.authorName = authorName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(@NonNull String authorName) {
        this.authorName = authorName;
    }
}
