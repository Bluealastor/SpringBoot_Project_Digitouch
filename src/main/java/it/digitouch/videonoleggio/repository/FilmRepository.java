package it.digitouch.videonoleggio.repository;

import it.digitouch.videonoleggio.model.FilmModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<FilmModel, Long> {
}
