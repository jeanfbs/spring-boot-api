package br.com.sampleapi.client;

import br.com.sampleapi.client.response.TestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cep", url = "${clients.endpoints.cep-client.url}")
public interface CepClient {

    @GetMapping("{cep}/json")
    TestResponse buscaEnderecoPor(@PathVariable("cep") String cep);

}
