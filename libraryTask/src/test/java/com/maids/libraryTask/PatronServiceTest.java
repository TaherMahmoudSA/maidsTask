package com.maids.libraryTask;

import com.maids.libraryTask.dto.PatronDTO;
import com.maids.libraryTask.entity.Patron;
import com.maids.libraryTask.mapper.PatronMapper;
import com.maids.libraryTask.repository.PatronRepository;
import com.maids.libraryTask.service.PatronService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
@SpringBootTest
public class PatronServiceTest {

@Autowired
    private  PatronService patronService;

    @MockBean
    private  PatronRepository patronRepository;

    @MockBean
    private  PatronMapper patronMapper;

    @Test
    public void testGetAllPatrons() {
        List<Patron> patrons = Arrays.asList(new Patron(1L, "Patron Name", "Contact Info"));
        Mockito.when(patronRepository.findAll()).thenReturn(patrons);
        Mockito.when(patronMapper.toDto(Mockito.any(Patron.class))).thenReturn(new PatronDTO(1L, "Patron Name", "Contact Info"));

        List<PatronDTO> result = patronService.getAllPatrons();

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Patron Name", result.get(0).getName());
    }

    @Test
    public void testGetPatronById() {
        Long patronId = 1L;
        Patron patron = new Patron(patronId, "Patron Name", "Contact Info");
        Mockito.when(patronRepository.findById(patronId)).thenReturn(Optional.of(patron));
        Mockito.when(patronMapper.toDto(patron)).thenReturn(new PatronDTO(patronId, "Patron Name", "Contact Info"));

        PatronDTO result = patronService.getPatronById(patronId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Patron Name", result.getName());
    }

    @Test
    public void testSavePatron() {
        PatronDTO patronDTO = new PatronDTO(null, "New Patron", "Contact Info");
        Patron patron = new Patron(null, "New Patron", "Contact Info");
        Mockito.when(patronMapper.toEntity(patronDTO)).thenReturn(patron);
        Mockito.when(patronRepository.save(patron)).thenReturn(patron);
        Mockito.when(patronMapper.toDto(patron)).thenReturn(new PatronDTO(1L, "New Patron", "Contact Info"));

        PatronDTO result = patronService.savePatron(patronDTO);

        Assertions.assertNotNull(result.getId());
        Assertions.assertEquals("New Patron", result.getName());
    }

    @Test
    public void testDeletePatron() {
        Long patronId = 1L;
        patronService.deletePatron(patronId);
        Mockito.verify(patronRepository, Mockito.times(1)).deleteById(patronId);
    }
}

