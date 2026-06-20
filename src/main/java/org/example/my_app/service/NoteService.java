package org.example.my_app.service;

import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.example.my_app.entity.Note;
import org.example.my_app.repo.NotesRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class NoteService {
    private final NotesRepo notesRepo;

    public List<Note> getAllNotes(){
        return notesRepo.findAll();
    }

    public Note createNote(Note note){
        note.setCreatedAt(LocalDateTime.now());
        note.setUpdatedAt(LocalDateTime.now());
        return notesRepo.save(note);
    }

    public Note updateNote(Long id,Note updatedNote){
        Note note = notesRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
        note.setTitle(updatedNote.getTitle());
        note.setDescription(updatedNote.getDescription());
        note.setUpdatedAt(LocalDateTime.now());
        return notesRepo.save(note);
    }

    public void deleteNote(Long id){
        if (!notesRepo.existsById(id)){
            throw new RuntimeException("Not found");
        }
        notesRepo.deleteById(id);
    }


}
