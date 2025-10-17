package br.com.sampleapi.queue.consumer;

import br.com.sampleapi.queue.message.TestMessage;
import org.springframework.jms.annotation.JmsListener;

//@Component
//@Slf4j
public class TestConsumer {


    @JmsListener(destination = "${spring.activemq.queue.name}")
    public void receiveQueue(TestMessage message) {
//        log.info("msg=Mensagem recebid da fila ");
//        log.info("mensagem={}", message);
    }
}
