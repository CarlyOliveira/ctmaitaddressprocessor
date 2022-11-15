package br.com.ctmait.addressprocessor.tech.rest.payload.in;


import lombok.*;

@Getter
@Setter
@ToString
public class AddressPayloadIn {
    private String id;
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String numero;
    private String provider;
}
