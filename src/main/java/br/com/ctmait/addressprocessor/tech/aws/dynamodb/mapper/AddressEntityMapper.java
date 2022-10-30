package br.com.ctmait.addressprocessor.tech.aws.dynamodb.mapper;

import br.com.ctmait.addressprocessor.domain.models.Address;
import br.com.ctmait.addressprocessor.tech.aws.dynamodb.entity.AddressEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper
public interface AddressEntityMapper {

    AddressEntityMapper INSTANCE = Mappers.getMapper( AddressEntityMapper.class );

    @Mapping(source = "source.cep", target = "cep")
    @Mapping(source = "source.logradouro", target = "logradouro")
    @Mapping(source = "source.complemento", target = "complemento")
    @Mapping(source = "source.bairro", target = "bairro")
    @Mapping(source = "source.cidade", target = "cidade")
    @Mapping(source = "source.uf", target = "uf")
    @Mapping(source = "source.numero", target = "numero")
    @Mapping(target = "id", expression = "java( uuidToId(source.getId()) )")
    @Mapping(target = "dataInclusao", ignore = true)
    AddressEntity map (Address source);

    @Mapping(source = "source.cep", target = "cep")
    @Mapping(source = "source.logradouro", target = "logradouro")
    @Mapping(source = "source.complemento", target = "complemento")
    @Mapping(source = "source.bairro", target = "bairro")
    @Mapping(source = "source.cidade", target = "cidade")
    @Mapping(source = "source.uf", target = "uf")
    @Mapping(source = "source.numero", target = "numero")
    @Mapping(target = "id", expression = "java( idToUiid(source.getId()) )" )
    Address map (AddressEntity source);

    default UUID idToUiid(String id){
        return id != null ? UUID.fromString(id) : null;
    };

    default String uuidToId(UUID id){
        return id != null ? id.toString() : null;
    };
}
