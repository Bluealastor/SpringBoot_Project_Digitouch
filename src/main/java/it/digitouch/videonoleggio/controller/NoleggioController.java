package it.digitouch.videonoleggio.controller;

import it.digitouch.videonoleggio.dto.NoleggioDTO;
import it.digitouch.videonoleggio.service.NoleggioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/noleggio")
@RequiredArgsConstructor
public class NoleggioController {

    private final NoleggioService noleggioService;

    //TODO GODO PARTE 2
    @GetMapping()
    public ResponseEntity<List<NoleggioDTO>> getAllNoleggi(){
        return ResponseEntity.ok(noleggioService.getAllNoleggi()) ;
    }

    @PostMapping()
    public ResponseEntity<NoleggioDTO> createFilm(@Valid @RequestBody NoleggioDTO noleggioDTO){
        NoleggioDTO savedNoleggio = noleggioService.saveNoleggio(noleggioDTO);
        return ResponseEntity.ok(savedNoleggio);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoleggioDTO> noleggioFindById(@Valid @PathVariable Long id){
        NoleggioDTO noleggioDTO = noleggioService.getNoleggioById(id);
        return ResponseEntity.ok(noleggioDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFilm(@PathVariable Long id) {
        noleggioService.deleteNoleggioById(id);
        return ResponseEntity.ok("Film eliminato con successo");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<NoleggioDTO> updateNoleggio(@PathVariable Long id, @Valid @RequestBody NoleggioDTO noleggioDTO){
        NoleggioDTO updateNoleggio = noleggioService.updateNoleggio(id, noleggioDTO);
        return ResponseEntity.ok(updateNoleggio);
    }

}
