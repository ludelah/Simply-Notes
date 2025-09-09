package com.ludelah.notes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "notes")
@Getter
@Setter
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* Uses a session token to associate notes with users.
    This makes the app a bit more insecure, we could steal a token and spoof it to get other people's notes.
    But it makes the app more efficient and faster to develop for the moment.
    NOT TO BE USED IN PROD!!! */
    @Column(name = "user_token", nullable = false)
    private String userToken;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private boolean archived = false;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    @Column(name = "updated_at")
    private Instant updatedAt;
}
