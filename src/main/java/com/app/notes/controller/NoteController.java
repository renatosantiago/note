package com.app.notes.controller;

import com.app.notes.dto.NoteDto;
import com.app.notes.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/note")
public class NoteController {

    @Autowired
    private NoteService service;

    @PostMapping(value = "/save")
    public NoteDto saveNote(@RequestBody NoteDto noteDto) {
        return service.saveNote(noteDto);
    }

    @GetMapping(value = "/list/{userId}")
    public List<NoteDto> findByUserId(@PathVariable Long userId) {
        return service.findByUserId(userId);
    }

    @DeleteMapping(value = "/delete/{noteId}")
    public void deleteNote(@PathVariable Long noteId) {
        service.deleteNote(noteId);
    }
}
