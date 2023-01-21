package com.app.notes.services;

import com.app.notes.dto.NoteDto;
import com.app.notes.entities.Note;
import com.app.notes.mapper.NoteMapper;
import com.app.notes.repositories.NoteRepository;
import com.app.notes.services.exception.GenericException;
import com.app.notes.services.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private NoteMapper mapper;

    @Transactional
    public NoteDto saveNote(NoteDto noteDto) {
        Note note = mapper.mapToEntity(noteDto);
        return mapper.mapToDto(noteRepository.save(note));
    }

    @Transactional
    public List<NoteDto> findByUserId(Long userId) {
        return mapper.toListDto(noteRepository.findByUserId(userId));
    }

    @Transactional
    public void deleteNote(Long noteId) {
        try {
            noteRepository.deleteById(noteId);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found " + noteId);
        } catch (Exception e) {
            throw new GenericException(e.getMessage());
        }
    }
}
