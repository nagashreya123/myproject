package org.example.my_app.controller;

import lombok.RequiredArgsConstructor;
import org.example.my_app.entity.Note;
import org.example.my_app.repo.NotesRepo;
import org.example.my_app.service.NoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NotesController {

    private final NoteService noteService;
    @GetMapping("/notes")
    public List<Note> getAllNotes(){
        return noteService.getAllNotes();
    }

    @PostMapping("/notes")
    public Note createNote(@RequestBody Note note){
        return noteService.createNote(note);
    }

    @PutMapping("/notes/{id}")
    public Note updateNote(@PathVariable Long id, @RequestBody Note note){
        return noteService.updateNote(id,note);
    }

    @DeleteMapping("/notes/{id}")
    public void deleteNote(@PathVariable Long id){
        noteService.deleteNote(id);
    }
}
