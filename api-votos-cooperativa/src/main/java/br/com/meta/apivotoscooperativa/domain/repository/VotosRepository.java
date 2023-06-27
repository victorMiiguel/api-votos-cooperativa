package br.com.meta.apivotoscooperativa.domain.repository;

import br.com.meta.apivotoscooperativa.domain.entity.Votos;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VotosRepository extends JpaRepository<Votos, Long> {
    @Query(value = "SELECT COUNT(*) > 0 FROM votos v WHERE v.associado_id = :id_associado AND v.numero_da_sessao = :numero_da_sessao", nativeQuery = true)
    Long existsByAssociadoIdAndSessaoId(@Param("id_associado") Long associado_id, @Param("numero_da_sessao") Long numero_da_sessao);
    @Transactional
    @Modifying
    @Query(value = "UPDATE pautas SET votos_sim = votos_sim + 1 WHERE id = (SELECT pauta_id FROM sessao WHERE numero_da_sessao = :numeroDaSessao)", nativeQuery = true)
    void atualizarVotosSim(@Param("numeroDaSessao") Long numeroDaSessao);

    @Transactional
    @Modifying
    @Query(value = "UPDATE pautas SET votos_nao = votos_nao + 1 WHERE id = (SELECT pauta_id FROM sessao WHERE numero_da_sessao = :numeroDaSessao)", nativeQuery = true)
    void atualizarVotosNao(@Param("numeroDaSessao") Long numeroDaSessao);

}


