package br.com.ctmait.addressprocessor.tech.rest.resources;

import br.com.ctmait.addressprocessor.abstraction.process.AddressCreateProcess;
import br.com.ctmait.addressprocessor.tech.rest.mapper.AddressMapper;
import br.com.ctmait.addressprocessor.tech.rest.payload.in.AddressPayloadIn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api")
@Slf4j
public class AddressResources {

    @Value("${rest.resources.address.response.base-path-location}")
    private String RESPONSE_CREATE_BASE_PATH_URI;

    private final AddressCreateProcess addressCreateProcess;

    public AddressResources(AddressCreateProcess addressCreateProcess) {
        this.addressCreateProcess = addressCreateProcess;
    }

    @GetMapping("/v1/addressprocessor/status")
    public ResponseEntity<String> on() {
        return ResponseEntity.ok("Address Processor está ON");
    }

    @PostMapping("/v1/address")
    public ResponseEntity<?> createAddress(@RequestBody AddressPayloadIn addressPayloadIn,
                                           @RequestHeader(value = "transactionId", required = true) String transactionId) {

        log.info("AR-CA-00 Request {} transactionId {}", addressPayloadIn, transactionId);

        var address = AddressMapper.INSTANCE.map(addressPayloadIn, transactionId);

        log.info("AR-CA-01  Address {}", address);

        addressCreateProcess.execute(address);

        log.info("AR-CA-02 Address {}", address);

        var response = ResponseEntity.created(URI.create(RESPONSE_CREATE_BASE_PATH_URI + "/" + address.getId())).build();

        log.info("AR-CA-03 Response {}", response);

        return response;
    }

    //TODO construir endpoint PATCH e implementar seu processo e suas ações

    //TODO construir endpoint DELETE e implementar seu processo e suas ações
}
