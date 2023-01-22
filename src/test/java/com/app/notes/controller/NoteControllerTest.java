package com.app.notes.controller;

import com.app.notes.dto.NoteDto;
import com.app.notes.services.NoteService;
import com.app.notes.services.exception.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(NoteController.class)
public class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private NoteService service;
    @Autowired
    private ObjectMapper mapper;
    private Long existingId;
    private Long nonExistingId;
    private List<NoteDto> dtoList;
    private NoteDto requestDto;
    private NoteDto noteDto;


    @BeforeEach
    void setUp() throws Exception {
        requestDto = getRequestDto();
        dtoList = getListNoteDto();
        existingId = 1L;
        nonExistingId = 2L;

        Mockito.when(service.findByUserId(existingId)).thenReturn(dtoList);
        Mockito.when(service.findByUserId(existingId)).thenReturn(dtoList);
        Mockito.when(service.saveNote(requestDto)).thenReturn(noteDto);
        Mockito.doNothing().when(service).deleteNote(existingId);
        Mockito.doThrow(ResourceNotFoundException.class).when(service).deleteNote(nonExistingId);
    }

    @Test
    public void findByUserIdShoulReturnListOfNote() throws Exception {
        ResultActions result =
                mockMvc.perform(get("/note/list/{userId}", existingId)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$[0].id").exists());
        result.andExpect(jsonPath("$[0].description").exists());
        result.andExpect(jsonPath("$[0].userId").exists());
    }

    @Test
    public void saveNoteShouldSaveSimpleNote() throws Exception {
        String jsonBody = mapper.writeValueAsString(requestDto);
        ResultActions result =
                mockMvc.perform(post("/note/save")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
    }

    @Test
    public void deleteNoteShouldDoNothingWhenIdExists() throws Exception{
        ResultActions result =
                mockMvc.perform(delete("/note/delete/{noteId}", existingId)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
    }

    @Test
    public void deleteNoteShouldThrowExceptionWhenDoesNotExistingId() throws Exception{
        ResultActions result =
                mockMvc.perform(delete("/note/delete/{noteId}", nonExistingId)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isNotFound());
    }

    private NoteDto getNoteDto() {
        return new NoteDto(1L,"description",1L);
    }

    private NoteDto getRequestDto() {
        return new NoteDto(null,"description",1L);
    }

    private List<NoteDto> getListNoteDto() {
        List<NoteDto> litDto = new ArrayList<>();
        litDto.add(new NoteDto(1L,"description",1L));
        return litDto;
    }

}
