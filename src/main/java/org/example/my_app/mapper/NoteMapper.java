package org.example.my_app.mapper;

import org.example.my_app.dto.NoteRequest;
import org.example.my_app.dto.NoteResponse;
import org.example.my_app.entity.Note;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoteMapper {

    public Note toEntity(NoteRequest request);

    public NoteResponse toResponse(Note note);
}
