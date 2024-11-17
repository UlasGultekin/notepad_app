package com.ulasgltkn.notpad_app.repository;

import com.ulasgltkn.notpad_app.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface NoteRepository extends JpaRepository<Note, UUID> {
    Optional findByPath(String path);
}