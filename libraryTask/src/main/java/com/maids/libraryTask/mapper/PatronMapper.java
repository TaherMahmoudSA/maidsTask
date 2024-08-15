package com.maids.libraryTask.mapper;

import com.maids.libraryTask.dto.PatronDTO;
import com.maids.libraryTask.entity.Patron;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PatronMapper {
    PatronDTO toDto(Patron patron);

    Patron toEntity(PatronDTO patronDTO);
    void updatePatronFromDto(PatronDTO patronDto, @MappingTarget Patron patron);
}
