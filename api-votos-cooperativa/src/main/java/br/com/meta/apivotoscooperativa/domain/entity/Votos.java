package br.com.meta.apivotoscooperativa.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name = "votos")
@Entity(name = "Voto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Votos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "numeroDaSessao")
    private Sessao sessao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "associadoId")
    private Associado associado;

    @Column(name = "voto")
    private String voto;

}

