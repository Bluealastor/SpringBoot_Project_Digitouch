package it.digitouch.videonoleggio.repository;

import it.digitouch.videonoleggio.model.NoleggioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoleggioRepository extends JpaRepository<NoleggioModel, Long>{
}
