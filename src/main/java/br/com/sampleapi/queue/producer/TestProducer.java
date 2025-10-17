package br.com.sampleapi.queue.producer;

import br.com.sampleapi.queue.message.TestMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;

//@Component
//@Slf4j
public class TestProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${spring.activemq.queue.name}")
    private String destination;

    public void sendMessage(TestMessage message) {
//        log.info("msg=Enviando nova mensagem para fila {}", destination);
//        log.info("mensagem={}", message);
        jmsTemplate.convertAndSend(destination, message);
    }
}