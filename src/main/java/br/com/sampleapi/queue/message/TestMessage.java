package br.com.sampleapi.queue.message;

import lombok.Data;

@Data
public class TestMessage {

    private Integer id;
    private String body;

    public TestMessage() {
        // default construtor
    }
}
