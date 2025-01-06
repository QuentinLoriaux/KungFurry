package com.ensta.myfilmlist;

import com.ensta.myfilmlist.dto.FilmDTO;
import com.ensta.myfilmlist.exception.ControllerException;
import com.ensta.myfilmlist.exception.ServiceException;
import com.ensta.myfilmlist.model.Page;
import com.ensta.myfilmlist.persistence.controller.impl.FilmResourceImpl;
import com.ensta.myfilmlist.service.MyFilmsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class MyFilmsControllerTests {

    @Mock
    private MyFilmsService myFilmsService;

    @InjectMocks
    private FilmResourceImpl myFilmsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetFilms() throws ServiceException {
        FilmDTO film1 = new FilmDTO();
        film1.setId(1);
        film1.setTitre("Film 1");
        film1.setDuree(120);
        FilmDTO film2 = new FilmDTO();
        film2.setId(2);
        film2.setTitre("Film 2");
        film2.setDuree(90);

        List<FilmDTO> mockFilms = Arrays.asList(film1, film2);
        Page<FilmDTO> mockPage = new Page<FilmDTO>(1,2,2, mockFilms);

        when(myFilmsService.findAllFilms(1, 2, "", "", "")).thenReturn(mockPage);

        try {
            ResponseEntity<Page<FilmDTO>> response = myFilmsController.getAllFilms(1, 2, "", "", "");
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(mockFilms.size(), response.getBody().getData().size());
            assertEquals("Film 1", response.getBody().getData().get(0).getTitre());
            assertEquals("Film 2", response.getBody().getData().get(1).getTitre());     
        } catch (ControllerException e) {
            e.printStackTrace();
        }
    }
}