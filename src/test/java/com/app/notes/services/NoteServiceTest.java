package com.app.notes.services;

import com.app.notes.dto.NoteDto;
import com.app.notes.entities.Note;
import com.app.notes.entities.User;
import com.app.notes.mapper.NoteMapper;
import com.app.notes.repositories.NoteRepository;
import com.app.notes.services.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class NoteServiceTest {

    @InjectMocks
    private NoteService noteService;
    @Mock
    private NoteRepository noteRepository;
    @Mock
    private NoteMapper noteMapper;
    private long existingId;
    private long nonExistingId;

    @BeforeEach()
    void setUp() throws Exception {
        existingId = 1L;
        nonExistingId = 2000L;
        doNothing().when(noteRepository).deleteById(existingId);
        doThrow(ResourceNotFoundException.class).when(noteRepository).deleteById(nonExistingId);
        when(noteMapper.toListDto(getListNote())).thenReturn(getListNoteDto());
        when(noteRepository.findByUserId(existingId)).thenReturn(getListNote());
    }

    @Test
    public void deleteShouldDoNothingWhenIdExists() {
        Assertions.assertDoesNotThrow(() -> {
            noteService.deleteNote(existingId);
        });
        verify(noteRepository, times(1)).deleteById(existingId);
    }

    @Test
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            noteService.deleteNote(nonExistingId);
        });
        verify(noteRepository, times(1)).deleteById(nonExistingId);
    }

    @Test
    public void findByUserIdShouldReturnList() {
        List<NoteDto> result = noteService.findByUserId(existingId);
        Assertions.assertNotNull(result);
        verify(noteRepository, times(1)).findByUserId(existingId);
    }

    @Test
    public void saveNoteShouldSaveNote() {
        NoteDto request = requestDto();
        NoteDto dto = getNoteDto();
        Note note = getNoteEntity();
        Note savedNote = getSavedNoteEntity();

        when(noteMapper.mapToEntity(request)).thenReturn(note);
        when(noteMapper.mapToDto(savedNote)).thenReturn(dto);

        when(noteRepository.save(note)).thenReturn(savedNote);

        NoteDto result = noteService.saveNote(request);

        Assertions.assertNotNull(result);
        verify(noteRepository, times(1)).save(note);
    }

    private List<Note> getListNote() {
        List<Note> notes = new ArrayList<>();
        User user = new User(1L, "Alex", "alex@email.com", "");
        notes.add(new Note(1L, "descripton", user));
        return notes;
    }

    private List<NoteDto> getListNoteDto() {
        List<NoteDto> litDto = new ArrayList<>();
        litDto.add(new NoteDto(1L,"description",1L));
        return litDto;
    }

    private NoteDto requestDto() {
        return new NoteDto(null,"description",1L);
    }
    private NoteDto getNoteDto() {
        return new NoteDto(1L,"description",1L);
    }

    private Note getNoteEntity() {
        User user = new User(1L, "", "", "");
        return new Note(null,"description",user);
    }

    private Note getSavedNoteEntity() {
        User user = new User(1L, "Alex", "alex@email", "asdf");
        return new Note(1L,"description",user);
    }
}
