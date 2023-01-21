package com.app.notes.mapper;

import com.app.notes.dto.NoteDto;
import com.app.notes.entities.Note;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface NoteMapper {

    @Mapping(target = "user.name", ignore = true)
    @Mapping(target = "user.email", ignore = true)
    @Mapping(target = "user.password", ignore = true)
    @Mapping(target = "userId", source = "user.id")
    NoteDto mapToDto(Note note);

    @Mapping(target = "user.id", source = "userId")
    Note mapToEntity(NoteDto noteDto);

    List<NoteDto> toListDto(List<Note> notes);

}
