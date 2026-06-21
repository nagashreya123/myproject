package org.example.my_app.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.my_app.dto.NoteRequest;
import org.example.my_app.dto.NoteResponse;
import org.example.my_app.service.NoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NotesController {

    private final NoteService noteService;
    @GetMapping("/notes")
    public List<NoteResponse> getAllNotes(){
        return noteService.getAllNotes();
    }

    @PostMapping("/notes")
    public NoteResponse createNote(@Valid @RequestBody NoteRequest request){
        return noteService.createNote(request);
    }

    @PutMapping("/notes/{id}")
    public NoteResponse updateNote(@PathVariable Long id, @RequestBody NoteRequest request){
        return noteService.updateNote(id,request);
    }

    @DeleteMapping("/notes/{id}")
    public void deleteNote(@PathVariable Long id){
        noteService.deleteNote(id);
    }
}
