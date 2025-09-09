package com.ludelah.notes.repository;

import com.ludelah.notes.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByUserTokenAndArchivedFalse(String token);

    List<Note> findByUserTokenAndArchivedTrue(String token);

}
