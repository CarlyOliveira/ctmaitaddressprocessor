package br.com.ctmait.addressprocessor.tech.aws.sqs.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class AddressPayload {
    private UUID id;
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String numero;
    private String provider;
}
