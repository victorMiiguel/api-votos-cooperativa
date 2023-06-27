package br.com.meta.apivotoscooperativa.domain.service;

import br.com.meta.apivotoscooperativa.infra.Messages;
import br.com.meta.apivotoscooperativa.infra.exceptions.CpfInvalidoException;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
public class ValidaCpfService {
    public boolean validarCpf(String cpf) {
        DadosCpf dadosCpf;
        if(cpf.length() < 11 || cpf.length() > 11){
            throw new CpfInvalidoException(Messages.CPF_INVALIDO);
        }
        var request =  new HttpGet("https://api.nfse.io/validate/NaturalPeople/taxNumber/"+cpf);
        try(CloseableHttpClient httpClient = HttpClientBuilder.create().disableRedirectHandling().build();
            CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                var result = EntityUtils.toString(entity);
                Gson gson = new Gson();
                dadosCpf = gson.fromJson(result, DadosCpf.class);
                return Objects.equals(dadosCpf.valid, "true");
            }
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private class DadosCpf {
        private Long cpf;
        private String valid;

    }
}
