package br.com.meta.apivotoscooperativa.domain.repository;

import br.com.meta.apivotoscooperativa.domain.entity.Associado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long> {

    boolean existsByCpf(String cpf);

}
