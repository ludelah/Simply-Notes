package com.ludelah.notes.controller;

import com.ludelah.notes.dto.NoteDto;
import com.ludelah.notes.model.Note;
import com.ludelah.notes.service.NoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notes")
public class NotesController {
    private final NoteService service;

    private NoteDto toDto(Note n) {
        return new NoteDto(n.getId(), n.getTitle(), n.getContent());
    }

    private Note fromDto(NoteDto d) {
        var n = new Note();
        if (d.id != null) n.setId(d.id);
        n.setTitle(d.title);
        n.setContent(d.content);
        return n;
    }

    public NotesController(NoteService service) {
        this.service = service;
    }

    @GetMapping("/active")
    public List<NoteDto> listActive(@RequestHeader("X-User") String token) {
        return service.listActive(token).stream().map(this::toDto).collect(Collectors.toList());
    }

    @GetMapping("/archived")
    public List<NoteDto> listArchived(@RequestHeader("X-User") String token) {
        return service.listArchived(token).stream().map(this::toDto).collect(Collectors.toList());
    }

    @PostMapping("/active")
    public NoteDto create(@RequestHeader("X-User") String token, @RequestBody NoteDto dto) {
        return toDto(service.create(fromDto(dto), token));
    }

    @PutMapping("/{id}")
    public NoteDto update(@PathVariable Long id, @RequestHeader("X-User") String token, @RequestBody NoteDto dto) {
        dto.id = id;
        return toDto(service.update(fromDto(dto), token));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PostMapping("/{id}/archive")
    public NoteDto archive(@PathVariable Long id) {
        return toDto(service.setArchived(id, true));
    }

    @PostMapping("/{id}/unarchive")
    public NoteDto unarchive(@PathVariable Long id) {
        return toDto(service.setArchived(id, false));
    }
}
