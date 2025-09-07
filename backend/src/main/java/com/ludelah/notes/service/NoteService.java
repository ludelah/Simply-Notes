package com.ludelah.notes.service;


import com.ludelah.notes.model.Note;
import com.ludelah.notes.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    private final NoteRepository repo;

    public NoteService(NoteRepository repo) {
        this.repo = repo;
    }

    public List<Note> listActive(String token) {
        return repo.findActiveNotes(token);
    }

    public List<Note> listArchived(String token) {
        return repo.findArchivedNotes(token);
    }


    public Note create(Note note, String token) {
        note.setUser_token(token);
        return repo.save(note);
    }

    public Optional<Note> findById(Long id) {
        return repo.findById(id);
    }

    public Note update(Note note, String user) {
        Note noteToUpdate = repo.findById(note.getId()).orElseThrow(); // TODO: Create exceptions

        if (!noteToUpdate.getUser_token().equals(user))
        {
            throw new RuntimeException("Not authorized to update this note");
        }

        noteToUpdate.setTitle(note.getTitle());
        noteToUpdate.setContent(note.getContent());
        noteToUpdate.setUpdated_at(Instant.now());

        return repo.save(noteToUpdate);

    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Note setArchived(Long id, boolean archived) {
        Note note = repo.findById(id).orElseThrow();
        note.setArchived(archived);
        note.setUpdated_at(java.time.Instant.now());
        return repo.save(note);
    }
}
