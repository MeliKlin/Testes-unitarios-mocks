package com.meli.obterdiploma.services;

import com.meli.obterdiploma.model.StudentDTO;
import com.meli.obterdiploma.repository.IStudentDAO;
import com.meli.obterdiploma.repository.IStudentRepository;
import com.meli.obterdiploma.service.StudentService;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private IStudentDAO studentDAO;
    @Mock
    private IStudentRepository studentRepository;
    private StudentDTO studentDTO;
    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    public void testSetup() {
        studentDTO = new StudentDTO();
        studentDTO.setId(1L);
        studentDTO.setStudentName("John Doe");
    }

    @Test
    public void testCreateStudentMethod() {
        studentService.create(studentDTO);
        Mockito.verify(studentDAO, Mockito.times(1)).save(studentDTO);
    }

    @Test
    public void testReadStudentMethod() {
        Mockito.when(studentDAO.findById(Mockito.any())).thenReturn(studentDTO);

        StudentDTO student = studentService.read(1L);
        assertEquals(student, studentDTO);
    }

    @Test
    public void testUpdateStudentMethod() {
        studentService.update(studentDTO);
        Mockito.verify(studentDAO, Mockito.times(1)).save(studentDTO);
    }

    @Test
    public void testDeleteStudentMethod() {
        studentService.delete(1L);
        Mockito.verify(studentDAO, Mockito.times(1)).delete(1L);
    }

    @Test
    public void testGetAllStudentsMethod() {
        studentService.getAll();
        Mockito.verify(studentRepository, Mockito.times(1)).findAll();
    }
}
