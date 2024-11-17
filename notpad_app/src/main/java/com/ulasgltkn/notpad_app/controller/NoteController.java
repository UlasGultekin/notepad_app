package com.ulasgltkn.notpad_app.controller;

import com.ulasgltkn.notpad_app.entity.Note;
import com.ulasgltkn.notpad_app.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/note")
@CrossOrigin
public class NoteController {
    private final NoteService noteService;
    @PostMapping()
    public ResponseEntity<?> saveOrUpdate(@RequestBody Note note){
      return noteService.saveOrUpdate(note);

    }
    @GetMapping("/{path}")
    public ResponseEntity<?> getPath(@PathVariable("path") String path){
     return noteService.getPath(path);
    }
}
