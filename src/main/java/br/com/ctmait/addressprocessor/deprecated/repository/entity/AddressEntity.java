package br.com.ctmait.addressprocessor.deprecated.repository.entity;

import br.com.ctmait.addressprocessor.deprecated.repository.converter.LocalDateConverter;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DynamoDBTable(tableName = "tb_address")
public class AddressEntity {

    @DynamoDBHashKey(attributeName = "id")
    private String id;
    @DynamoDBAttribute(attributeName = "cep")
    private String cep;
    @DynamoDBAttribute(attributeName = "logradouro")
    private String logradouro;
    @DynamoDBAttribute(attributeName = "complemento")
    private String complemento;
    @DynamoDBAttribute(attributeName = "bairro")
    private String bairro;
    @DynamoDBAttribute(attributeName = "cidade")
    private String cidade;
    @DynamoDBAttribute(attributeName = "numero")
    private String numero;
    @DynamoDBAttribute(attributeName = "uf")
    private String uf;
    @DynamoDBAttribute(attributeName = "data_inclusao")
    @DynamoDBTypeConverted(converter = LocalDateConverter.class)
    private LocalDate dataInclusao;

}