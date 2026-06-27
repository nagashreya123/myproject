package org.example.my_app.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.my_app.dto.NoteRequest;
import org.example.my_app.dto.NoteResponse;
import org.example.my_app.entity.Note;
import org.example.my_app.exception.NoteNotFoundException;
import org.example.my_app.mapper.NoteMapper;
import org.example.my_app.repo.NotesRepo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class NoteService {
    private final NotesRepo notesRepo;
    private final NoteMapper noteMapper;

    @Cacheable("notes")
    public List<NoteResponse> getAllNotes(){
        log.info("Fetching notes");
        return notesRepo.findAll()
                .stream()
                .map(noteMapper::toResponse)
                .toList();
    }

    @CacheEvict(value = "notes" , allEntries = true)
    public NoteResponse createNote(NoteRequest request){
        log.info("Creating new note");
        Note note = noteMapper.toEntity(request);
        note.setCreatedAt(LocalDateTime.now());
        note.setUpdatedAt(LocalDateTime.now());
        return noteMapper.toResponse(notesRepo.save(note));
    }

    @CacheEvict(value = "notes" , allEntries = true)
    public NoteResponse updateNote(Long id,NoteRequest request){
        log.info("Updating note {}",id);
        Note note = notesRepo.findById(id)
                .orElseThrow(() -> new NoteNotFoundException("Note not found with id " + id));
        note.setTitle(request.getTitle());
        note.setDescription(request.getDescription());
        note.setUpdatedAt(LocalDateTime.now());
        return noteMapper.toResponse(notesRepo.save(note));
    }

    @CacheEvict(value = "notes" , allEntries = true)
    public void deleteNote(Long id){
        log.info("Deleting notes {}",id);
        if (!notesRepo.existsById(id)){
            throw new NoteNotFoundException("Note not found with id " + id);
        }
        notesRepo.deleteById(id);
    }

}
