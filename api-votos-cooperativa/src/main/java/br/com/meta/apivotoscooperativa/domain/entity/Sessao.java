package br.com.meta.apivotoscooperativa.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "sessao")
@Entity(name = "Sessao")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "numeroDaSessao")
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numeroDaSessao")
    private Long numeroDaSessao;

    private Boolean ativa;

    @OneToOne
    private Pauta pauta;

    private LocalDateTime abertaAte;

}
