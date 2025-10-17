package br.com.sampleapi.resource;

import br.com.sampleapi.dto.PaginationResponse;
import br.com.sampleapi.dto.User;
import br.com.sampleapi.client.response.TestResponse;
import br.com.sampleapi.resource.api.BaseResource;
import br.com.sampleapi.resource.api.UserApi;
import br.com.sampleapi.service.ClientService;
import br.com.sampleapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@AllArgsConstructor
public class UserResource implements BaseResource, UserApi {

    private ClientService clientService;
    private UserService service;
//    private TestProducer producer;

    @Override
    public ResponseEntity<?> create(final User request) {
        var response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<PaginationResponse<User>> find(User request, Integer page, Integer size) {
        var response = service.find(request, page, size);
        ResponseEntity.BodyBuilder builder = !response.last() ? ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                : ResponseEntity.status(HttpStatus.OK);
        return builder.body(response);
    }

    @Override
    public ResponseEntity<?> findById(UUID id) {
        var response = service.findById(id);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> update(UUID id, User request) {
        var response = service.update(id, request);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> delete(UUID id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @GetMapping(value = "/testApiClient")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public TestResponse testApiClient(){
        return clientService.invokeTestClient("38401216");
    }

    //    @GetMapping(value = "/testQueue", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public ResponseEntity testQueue(){
//        TestMessage message = new TestMessage();
//        message.setBody("teste mensagem");
//        message.setId(400);
//        producer.sendMessage(message);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }

}
