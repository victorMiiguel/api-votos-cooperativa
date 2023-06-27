package br.com.meta.apivotoscooperativa.domain.repository;

import br.com.meta.apivotoscooperativa.domain.entity.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessaoRepository extends JpaRepository<Sessao, Long> {
    boolean existsByPautaId(Long idPauta);

}
