package com.maids.libraryTask.service;

import com.maids.libraryTask.dto.PatronDTO;
import com.maids.libraryTask.entity.Patron;
import com.maids.libraryTask.exceptions.ResourceNotFoundException;
import com.maids.libraryTask.mapper.PatronMapper;
import com.maids.libraryTask.repository.PatronRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatronService {


    private final PatronRepository patronRepository;


    private final PatronMapper patronMapper;

    public List<PatronDTO> getAllPatrons() {
        return patronRepository.findAll()
                .stream()
                .map(patronMapper::toDto)
                .collect(Collectors.toList());
    }

    public PatronDTO getPatronById(Long id) {
        return patronRepository.findById(id)
                .map(patronMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Patron not found with id " + id));
    }

    public PatronDTO savePatron(PatronDTO patronDTO) {
        Patron patron = patronMapper.toEntity(patronDTO);
        return patronMapper.toDto(patronRepository.save(patron));
    }
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public PatronDTO updatePatron(Long id, PatronDTO patronDto) {
        Patron existingPatron = patronRepository.findById(id).orElseThrow(() -> new RuntimeException("Patron not found"));
        patronMapper.updatePatronFromDto(patronDto, existingPatron);
        Patron updatedPatron = patronRepository.save(existingPatron);
        return patronMapper.toDto(updatedPatron);
    }

    public void deletePatron(Long id) {
        patronRepository.deleteById(id);
    }
}
