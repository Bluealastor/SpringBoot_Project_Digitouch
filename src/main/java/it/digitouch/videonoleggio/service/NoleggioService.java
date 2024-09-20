package it.digitouch.videonoleggio.service;

import it.digitouch.videonoleggio.dto.FilmDTO;
import it.digitouch.videonoleggio.dto.NoleggioDTO;;
import it.digitouch.videonoleggio.exception.ElementNotFoundException;
import it.digitouch.videonoleggio.model.FilmModel;
import it.digitouch.videonoleggio.model.NoleggioModel;
import it.digitouch.videonoleggio.repository.NoleggioRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Data
public class NoleggioService {

    private final NoleggioRepository noleggioRepository;


    // GET ALL NOLEGGIO
    public List<NoleggioDTO> getAllNoleggi() {
        List<NoleggioModel> noleggiomodels = noleggioRepository.findAll();
        List<NoleggioDTO> noleggioDTOS = new ArrayList<>();

        for(NoleggioModel noleggioModel : noleggiomodels){
            NoleggioDTO noleggioDTO = new NoleggioDTO(
                    noleggioModel.getId(),
                    noleggioModel.getNome(),
                    noleggioModel.getTitolare(),
                    noleggioModel.getCitta(),
                    noleggioModel.getHashNoleggio()
            );
            noleggioDTOS.add(noleggioDTO);
    }
        return noleggioDTOS;
    }

    public NoleggioDTO saveNoleggio(NoleggioDTO noleggioDTO) {
        NoleggioModel noleggioModel = new NoleggioModel(
                noleggioDTO.getId(),
                noleggioDTO.getNome(),
                noleggioDTO.getTitolare(),
                noleggioDTO.getCitta(),
                noleggioDTO.getHashNoleggio()
        );

        NoleggioModel saveNoleggio = noleggioRepository.save(noleggioModel);
        return new NoleggioDTO(
                saveNoleggio.getId(),
                saveNoleggio.getNome(),
                saveNoleggio.getTitolare(),
                saveNoleggio.getCitta(),
                saveNoleggio.getHashNoleggio()
        );
    }

    public NoleggioDTO getNoleggioById(Long id) {
        NoleggioModel noleggioModel = noleggioRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("noleggio non trovato"));
        return new NoleggioDTO(
                noleggioModel.getId(),
                noleggioModel.getNome(),
                noleggioModel.getTitolare(),
                noleggioModel.getCitta(),
                noleggioModel.getHashNoleggio()
        );
    }

    public void deleteNoleggioById(Long id) {
        noleggioRepository.deleteById(id);
    }

    public NoleggioDTO updateNoleggio(Long id, NoleggioDTO noleggioDTO){
        NoleggioModel existingNoleggio = noleggioRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Noleggio non trovato"));
        existingNoleggio.setNome(noleggioDTO.getNome());
        existingNoleggio.setTitolare(noleggioDTO.getTitolare());
        existingNoleggio.setCitta(noleggioDTO.getCitta());
        existingNoleggio.setHashNoleggio(noleggioDTO.getHashNoleggio());

        noleggioRepository.save(existingNoleggio);
        return converterToDTO(existingNoleggio);
    }


    //TODO  per semplificare il codice basta scrivere una volta il converte
private NoleggioDTO converterToDTO(NoleggioModel noleggioModel){
        return new NoleggioDTO(
                noleggioModel.getId(),
                noleggioModel.getNome(),
                noleggioModel.getCitta(),
                noleggioModel.getCitta(),
                noleggioModel.getHashNoleggio()
        );
}







}

