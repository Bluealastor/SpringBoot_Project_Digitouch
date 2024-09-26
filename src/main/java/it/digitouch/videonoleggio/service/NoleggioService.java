package it.digitouch.videonoleggio.service;

import it.digitouch.videonoleggio.dto.NoleggioDTO;
import it.digitouch.videonoleggio.exception.ElementAlreadyFoundException;
import it.digitouch.videonoleggio.exception.ElementNotFoundException;
import it.digitouch.videonoleggio.model.NoleggioModel;
import it.digitouch.videonoleggio.repository.NoleggioRepository;

import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;

@Service
@Data @Slf4j
@RequiredArgsConstructor
public class NoleggioService {

    private final NoleggioRepository noleggioRepository;

    private final ModelMapper modelMapper;

    //TODO GODURIA
     /*************************************************************************************************************
     *     UTILIZZO MODELMAPPER (ESTENZIONE MAVEN) per trasformare i dati da model a dto con stream e map         *
     *         PRIMO DATO DI MAP PARTENA dopo la (,) dato a cui voglio arrivare                                   *
     *************************************************************************************************************/

    public List<NoleggioDTO> getAllNoleggi() {
    /**************************************************************************************************************
    *         1) creo la list dei dati e la cerco nel DB                                                          *
    *        2) trasfomo i model in dto(ATTRAVERSO MODELMAPPER) e con stream e map mi faccio tornare la lista     *
    *             (con todoList trasformo la stream in lista)                                                     *
    **************************************************************************************************************/
        List<NoleggioModel> noleggioList = noleggioRepository.findAll();
        return noleggioList.stream().map(((noleggio) -> modelMapper.map(noleggio, NoleggioDTO.class))).toList();
    }

    public NoleggioDTO saveNoleggio(NoleggioDTO noleggioDTO) {
        noleggioRepository.findByhashNoleggio(noleggioDTO.getHashNoleggio()) .ifPresent(film -> { throw new ElementAlreadyFoundException("Film " + noleggioDTO.getHashNoleggio() + " esiste già");});
        NoleggioModel noleggio = modelMapper.map(noleggioDTO, NoleggioModel.class);
        noleggioRepository.save(noleggio);
        return modelMapper.map(noleggio, NoleggioDTO.class);
    }

    public NoleggioDTO getNoleggioById(Long id) {
        NoleggioModel noleggioModel = noleggioRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Noleggio: "+ id + " Not Found"));
        return modelMapper.map(noleggioModel, NoleggioDTO.class);
    }

    public void deleteNoleggioById(Long id) {
        noleggioRepository.deleteById(id);
    }

    @Transactional
    public void deleteNoleggioByHash(String hash){
        noleggioRepository.deleteByHashNoleggio(hash);
    }

    public NoleggioDTO updateNoleggio(Long id, NoleggioDTO noleggioDTO){
        NoleggioModel noleggioModel = noleggioRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Noleggio: "+ id + " Not Found"));
        noleggioModel.setNome(noleggioDTO.getNome());
        noleggioModel.setTitolare(noleggioDTO.getTitolare());
        noleggioModel.setCitta(noleggioDTO.getCitta());
        noleggioModel.setHashNoleggio(noleggioDTO.getHashNoleggio());

        noleggioRepository.findByhashNoleggio(noleggioDTO.getHashNoleggio())
                .ifPresent(film -> { throw new ElementAlreadyFoundException("Film " + noleggioDTO.getHashNoleggio() + " esiste già");});
        noleggioRepository.save(noleggioModel);
        return modelMapper.map(noleggioModel, NoleggioDTO.class);
    }
}