package br.com.meta.apivotoscooperativa.domain.entity;

import br.com.meta.apivotoscooperativa.domain.dto.in.DadosCadastroAssociado;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "associados")
@Entity(name = "Associado")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Associado {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "nome")
        private String nome;

        @Column(name = "cpf")
        private String cpf;

        @OneToMany(mappedBy = "associado")
        private List<Votos> votos;

        public Associado(@Valid DadosCadastroAssociado dados) {
                this.cpf = dados.cpf();
                this.nome = dados.nome();
        }



}

