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
        return repo.findByUserTokenAndArchivedFalse(token);
    }

    public List<Note> listArchived(String token) {
        return repo.findByUserTokenAndArchivedTrue(token);
    }


    public Note create(Note note, String token) {
        note.setUserToken(token);
        return repo.save(note);
    }

    public Optional<Note> findById(Long id) {
        return repo.findById(id);
    }

    public Note update(Note note, String user) {
        Note noteToUpdate = repo.findById(note.getId()).orElseThrow(); // TODO: Create exceptions

        if (!noteToUpdate.getUserToken().equals(user))
        {
            throw new RuntimeException("Not authorized to update this note");
        }

        noteToUpdate.setTitle(note.getTitle());
        noteToUpdate.setContent(note.getContent());
        noteToUpdate.setUpdatedAt(Instant.now());

        return repo.save(noteToUpdate);

    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Note setArchived(Long id, boolean archived) {
        Note note = repo.findById(id).orElseThrow();
        note.setArchived(archived);
        note.setUpdatedAt(java.time.Instant.now());
        return repo.save(note);
    }
}
