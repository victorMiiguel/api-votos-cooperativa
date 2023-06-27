package br.com.meta.apivotoscooperativa.infra.springdoc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {
    @Bean

    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API votos cooperativa")
                        .description("API Rest votos cooperativa para votação de pautas em sessão, contendo as funcionalidades de CRUD, associados e pautas, com funções de abrir uma sessão para uma pauta especifica e iniciar uma votação para o aceitamento da ideia preescrita na pauta.\n " +
                                "Ao cadastrar um novo associado é verificado a existência do CPF, então é necessário que cadastre com um CPF válido.\n" +
                                "Na requisição abrir uma sessão, se não for especificado pelo usuário o tempo default de cada sessão é de de 1 min. " +
                                "podendo ser atribuido pelo usuário no campo 'duraçãoEmMinutos' ao abrir a sessão.\n " +
                                "Ao iniciar a requisição de votação o usuário só pode votar uma unica vez em cada sessão.")
                        .contact(new Contact()
                                .name("Time Backend")
                                .email("brenocesarsrv@gmail.com;"+
                                        " miguel.vamaral@meta.com.br"))
                        .license(new License()
                                .name("Apache 1.0")
                                .url("")));
    }
}
