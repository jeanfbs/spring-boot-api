package br.com.sampleapi.service;

import br.com.sampleapi.client.CepClient;
import br.com.sampleapi.client.response.TestResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class ClientService {

    private CepClient client;

    public TestResponse invokeTestClient(final String cep){
        log.info("calling external api client");
        return client.buscaEnderecoPor(cep);
    }

}
