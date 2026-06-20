package org.example.my_app.repo;

import org.example.my_app.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotesRepo extends JpaRepository<Note,Long> {
}
