package com.maids.libraryTask.mapper;

import com.maids.libraryTask.dto.BorrowingRecordDTO;
import com.maids.libraryTask.entity.BorrowingRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BorrowingRecordMapper {

    BorrowingRecordDTO toDto(BorrowingRecord borrowingRecord);


    BorrowingRecord toEntity(BorrowingRecordDTO borrowingRecordDTO);
}
