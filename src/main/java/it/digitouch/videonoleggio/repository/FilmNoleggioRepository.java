package it.digitouch.videonoleggio.repository;

import it.digitouch.videonoleggio.model.FilmNoleggioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmNoleggioRepository extends JpaRepository<FilmNoleggioModel, Long> {
}

