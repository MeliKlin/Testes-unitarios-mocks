package com.meli.obterdiploma.services;

import com.meli.obterdiploma.model.StudentDTO;
import com.meli.obterdiploma.model.SubjectDTO;

import com.meli.obterdiploma.repository.StudentDAO;
import static org.junit.jupiter.api.Assertions.*;

import com.meli.obterdiploma.service.ObterDiplomaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ObterDiplomaServiceTest {

    private List<SubjectDTO> subjects;
    private StudentDTO studentDTO;

    @BeforeEach
    public void preTestSetup() {
        subjects = new ArrayList<>();
        subjects.add(new SubjectDTO("Matemática", 10.0));
        subjects.add(new SubjectDTO("Física", 7.0));

        studentDTO = new StudentDTO();
        studentDTO.setId(1L);;
        studentDTO.setStudentName("John Doe");
        studentDTO.setSubjects(subjects);
    }

    @Test
    @DisplayName("Given a studentDTO mock containing two subjects with scores 10.0 and 7.0, when call analyzeScores method, then should return 8.5 as average score.")
    public void testAverageScoreResult() {
        StudentDAO studentDAO = Mockito.mock(StudentDAO.class);
        Mockito.when(studentDAO.findById(Mockito.any())).thenReturn(studentDTO);

        ObterDiplomaService obterDiplomaService = new ObterDiplomaService(studentDAO);
        StudentDTO result = obterDiplomaService.analyzeScores(1L);
        double expected = 8.5;
        assertEquals(result.getAverageScore(), expected);
    }

    @Test
    @DisplayName("Given a studentDTO mock that reaches greater than nine, then message should return O aluno John Doe obteve uma média de 10. Parabéns!")
    public void testMessageScoreGreaterThanNine() {
        subjects.remove(1);

        StudentDAO studentDAO = Mockito.mock(StudentDAO.class);
        Mockito.when(studentDAO.findById(Mockito.any())).thenReturn(studentDTO);

        ObterDiplomaService obterDiplomaService = new ObterDiplomaService(studentDAO);
        StudentDTO result = obterDiplomaService.analyzeScores(1L);
        assertEquals(result.getMessage(), "O aluno John Doe obteve uma média de 10. Parabéns!");
    }

    @Test
    @DisplayName("Given a studentDTO mock that reaches lower than nine, then message should return O aluno John Doe obteve uma média de 8.5. Você pode melhorar.")
    public void testMessageScoreLowerThanNine() {
        StudentDAO studentDAO = Mockito.mock(StudentDAO.class);
        Mockito.when(studentDAO.findById(Mockito.any())).thenReturn(studentDTO);

        ObterDiplomaService obterDiplomaService = new ObterDiplomaService(studentDAO);
        StudentDTO result = obterDiplomaService.analyzeScores(1L);
        assertEquals(result.getMessage(), "O aluno John Doe obteve uma média de 8.5. Você pode melhorar.");
    }

}
