package com.ludelah.notes.repository;

import com.ludelah.notes.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Arrays;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    @Query("SELECT n FROM Note n WHERE n.user_token = :token AND n.archived = false ORDER BY n.created_at DESC")
    List<Note> findActiveNotes(@Param("token") String token);

    @Query("SELECT n FROM Note n WHERE n.user_token = :token AND n.archived = true ORDER BY n.created_at DESC")
    List<Note> findArchivedNotes(@Param("token") String token);

}
