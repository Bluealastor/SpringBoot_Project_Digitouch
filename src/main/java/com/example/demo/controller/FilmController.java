package com.example.demo.controller;

import com.example.demo.model.FilmModel;
import com.example.demo.repository.FilmRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/film")
public class FilmController {

    @Autowired
    FilmRepository filmRepository;

    @GetMapping()
    public List<FilmModel> getAllFilm(){
        List<FilmModel> filmList = filmRepository.findAll();
       return filmList;
    }

    @GetMapping("/{id}")
    public FilmModel filmFindById(@PathVariable Long id){
        FilmModel filmById = filmRepository.findById(id).orElse(null);
        return filmById;
    }

    @DeleteMapping("/{id}")
    public void filmDelete(@PathVariable Long id){
        filmRepository.deleteById(id);
    }

    @PostMapping()
    public void filmInsert(@Valid @RequestBody FilmModel film){
        filmRepository.save(film);
    }

}
