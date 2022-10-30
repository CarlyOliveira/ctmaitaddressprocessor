package br.com.ctmait.addressprocessor.tech.rest.resources;

import br.com.ctmait.addressprocessor.abstraction.process.AddressCreateProcess;
import br.com.ctmait.addressprocessor.tech.rest.mapper.AddressMapper;
import br.com.ctmait.addressprocessor.tech.rest.payload.in.AddressPayloadIn;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class AddressResources {

    private final AddressCreateProcess addressCreateProcess;

    @GetMapping("/v1/addressprocessor/status")
    public ResponseEntity<String> on() {
        return ResponseEntity.ok("Address Processor está ON");
    }

    @PostMapping("/v1/address")
    public ResponseEntity<?> createAddress(@RequestBody AddressPayloadIn addressPayloadIn) {

        log.info("AR-CA-00 ", " Request ", addressPayloadIn);

        var address = AddressMapper.INSTANCE.map(addressPayloadIn);

        log.info("AR-CA-01 ", " Address ", address);

        address.visit(addressCreateProcess::execute);

        log.info("AR-CA-02 ", " Address ", address);

        var response = ResponseEntity.created(URI.create("/api/address/" + address.getId())).build();

        log.info("AR-CA-03 ", " Response ", response);

        return response;
    }
}
