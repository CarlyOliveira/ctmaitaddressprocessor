package br.com.ctmait.addressprocessor.tech.rest.resources;

import br.com.ctmait.addressprocessor.abstraction.process.AddressCreateProcess;
import br.com.ctmait.addressprocessor.abstraction.process.AddressDeleteProcess;
import br.com.ctmait.addressprocessor.abstraction.process.AddressUpdateProcess;
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
    private final AddressUpdateProcess addressUpdateProcess;
    private final AddressDeleteProcess addressDeleteProcess;

    public AddressResources(AddressCreateProcess addressCreateProcess, AddressUpdateProcess addressUpdateProcess, AddressDeleteProcess addressDeleteProcess) {
        this.addressCreateProcess = addressCreateProcess;
        this.addressUpdateProcess = addressUpdateProcess;
        this.addressDeleteProcess = addressDeleteProcess;
    }

    @GetMapping("/v1/addressprocessor/status")
    public ResponseEntity<String> on() {
        return ResponseEntity.ok("Address Processor está ON");
    }

    @PostMapping("/v1/address")
    public ResponseEntity<?> createAddress(@RequestBody AddressPayloadIn addressPayloadIn,
                                           @RequestHeader(value = "transactionId", required = true) String transactionId) {

        log.info("AR-CA-00 create addres request {} transactionId {}", addressPayloadIn, transactionId);

        var address = AddressMapper.INSTANCE.map(addressPayloadIn, transactionId);

        log.info("AR-CA-01 create address address {}", address);

        addressCreateProcess.execute(address);

        log.info("AR-CA-02 create address address {}", address);

        var response = ResponseEntity.created(URI.create(RESPONSE_CREATE_BASE_PATH_URI + "/" + address.getId())).build();

        log.info("AR-CA-03 create address response {}", response);

        return response;
    }

    @PutMapping("/v1/address")
    public ResponseEntity<?> updateAddress(@RequestBody AddressPayloadIn addressPayloadIn,
                                           @RequestHeader(value = "transactionId", required = true) String transactionId) {

        log.info("AR-UA-00 update address request {} transactionId {}", addressPayloadIn, transactionId);

        var address = AddressMapper.INSTANCE.map(addressPayloadIn);

        log.info("AR-UA-01  update address {}", address);

        addressUpdateProcess.execute(address);

        log.info("AR-UA-02 updated address sucess {}", address);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/v1/address/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable String id,
                                           @RequestHeader(value = "transactionId", required = true) String transactionId) {

        log.info("AR-DA-00 delete address id {} transactionId {}", id, transactionId);

        var address = AddressMapper.INSTANCE.map(id);

        log.info("AR-DA-01  delete address {}", address);

        addressDeleteProcess.execute(address);

        log.info("AR-DA-02 deleted address sucess {}", address);

        return ResponseEntity.noContent().build();
    }
}
