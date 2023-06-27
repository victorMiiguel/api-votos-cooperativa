package br.com.meta.apivotoscooperativa.domain.repository;

import br.com.meta.apivotoscooperativa.domain.entity.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {

}
