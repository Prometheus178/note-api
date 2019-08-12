package com.example.eazynotes.controller;

import com.example.eazynotes.exception.ResourceNotFoundException;
import com.example.eazynotes.model.Note;
import com.example.eazynotes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    NoteRepository noteRepository;

    @GetMapping("/notes")
    public List<Note> getAllNotes(){
        return noteRepository.findAll();
    }

    @PostMapping("/notes")
    public Note createNote(@Valid @RequestBody Note note){
        return noteRepository.save(note);
    }

    @GetMapping("/notes/{id}")
    public Note getNoteById(@PathVariable(value = "id") Long id){
        return noteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note","id", id));
    }

    @PutMapping("/notes/{id}")
    public Note updateNote(@PathVariable(value = "id") Long id,@Valid @RequestBody Note noteDetails){
        Note note = noteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note","id", id));
        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());

        Note updatedNote = noteRepository.save(note);
        return updatedNote;
    }
    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long id){
        Note note = noteRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Note","id", id));
        noteRepository.delete(note);
        return ResponseEntity.ok().build();
    }



}
