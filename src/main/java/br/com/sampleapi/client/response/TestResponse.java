package br.com.sampleapi.client.response;

import lombok.Data;

@Data
public class TestResponse {

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;

}
