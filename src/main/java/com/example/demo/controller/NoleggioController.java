package com.example.demo.controller;

import com.example.demo.repository.NoleggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/noleggio")
public class NoleggioController {

    @Autowired
    NoleggioRepository noleggioRepository;

    

}
