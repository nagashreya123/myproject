package org.example.my_app.service;

import lombok.AllArgsConstructor;
import org.example.my_app.dto.NoteRequest;
import org.example.my_app.dto.NoteResponse;
import org.example.my_app.entity.Note;
import org.example.my_app.exception.NoteNotFoundException;
import org.example.my_app.mapper.NoteMapper;
import org.example.my_app.repo.NotesRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class NoteService {
    private final NotesRepo notesRepo;
    private final NoteMapper noteMapper;

    public List<NoteResponse> getAllNotes(){
        return notesRepo.findAll()
                .stream()
                .map(noteMapper::toResponse)
                .toList();
    }

    public NoteResponse createNote(NoteRequest request){
        Note note = noteMapper.toEntity(request);
        note.setCreatedAt(LocalDateTime.now());
        note.setUpdatedAt(LocalDateTime.now());
        return noteMapper.toResponse(notesRepo.save(note));
    }

    public NoteResponse updateNote(Long id,NoteRequest request){
        Note note = notesRepo.findById(id)
                .orElseThrow(() -> new NoteNotFoundException("Note not found with id " + id));
        note.setTitle(request.getTitle());
        note.setDescription(request.getDescription());
        note.setUpdatedAt(LocalDateTime.now());
        return noteMapper.toResponse(notesRepo.save(note));
    }

    public void deleteNote(Long id){
        if (!notesRepo.existsById(id)){
            throw new NoteNotFoundException("Note not found with id " + id);
        }
        notesRepo.deleteById(id);
    }


}
