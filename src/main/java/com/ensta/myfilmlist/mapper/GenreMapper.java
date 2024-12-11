package com.ensta.myfilmlist.mapper;

import com.ensta.myfilmlist.dto.GenreDTO;
import com.ensta.myfilmlist.model.Genre;

import java.util.List;
import java.util.stream.Collectors;

public class GenreMapper {
    public static GenreDTO convertGenreToGenreDTO(Genre genre) {
        GenreDTO genreDto = new GenreDTO();
        genreDto.setId(genre.getId());
        genreDto.setName(genre.getName());
        return genreDto;
    }

    public static Genre convertGenreDTOToGenre(GenreDTO genreDto) {
        Genre genre = new Genre();
        genre.setId(genreDto.getId());
        genre.setName(genreDto.getName());
        return genre;
    }

    public static List<GenreDTO> convertListGenreToListGenreDTO(List<Genre> genres) {
        return genres.stream().map(GenreMapper::convertGenreToGenreDTO).collect(Collectors.toList());
    }
}
