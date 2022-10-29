package br.com.ctmait.addressprocessor.deprecated.rest.resources;

import br.com.ctmait.addressprocessor.deprecated.domain.core.usecases.AddressCreateUseCase;
import br.com.ctmait.addressprocessor.deprecated.domain.models.Address;
import br.com.ctmait.addressprocessor.deprecated.rest.mapper.AddressRequestMapper;
import br.com.ctmait.addressprocessor.deprecated.rest.resources.request.AddressResquest;
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

    private final AddressCreateUseCase<Address> addressAddressCreateUseCase;

    @PostMapping("/v1/addressprocessor/status")
    public ResponseEntity<String> on() {
        return ResponseEntity.ok("Address Processor está ON");
    }

    @PostMapping("/v1/address")
    public ResponseEntity<?> createAddress(@RequestBody AddressResquest addressResquest) {

        log.info("AR-CA-00 ", " Request ", addressResquest);

        var address = AddressRequestMapper.INSTANCE.AddressRequestToAddress(addressResquest);

        log.info("AR-CA-01 ", " Address ", address);

        address.accept(addressAddressCreateUseCase::execute);

        log.info("AR-CA-02 ", " Address ", address);

        var response = ResponseEntity.created(URI.create("/api/address/" + address.getId())).build();

        log.info("AR-CA-03 ", " Response ", response);

        return response;
    }
}
