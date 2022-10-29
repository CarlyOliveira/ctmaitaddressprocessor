package br.com.ctmait.addressprocessor.deprecated.repository.mapper;

import br.com.ctmait.addressprocessor.deprecated.domain.models.Address;
import br.com.ctmait.addressprocessor.deprecated.repository.entity.AddressEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface AddressEntityMapper {

    AddressEntityMapper INSTANCE = Mappers.getMapper( AddressEntityMapper.class );

    @Mapping(source = "address.cep", target = "cep")
    @Mapping(source = "address.logradouro", target = "logradouro")
    @Mapping(source = "address.complemento", target = "complemento")
    @Mapping(source = "address.bairro", target = "bairro")
    @Mapping(source = "address.cidade", target = "cidade")
    @Mapping(source = "address.uf", target = "uf")
    @Mapping(source = "address.numero", target = "numero")
    @Mapping(target = "id", expression = "java( uuidToId(address.getId()) )")
    AddressEntity AddressToAddressEntity (Address address);

    @Mapping(source = "addressEntity.cep", target = "cep")
    @Mapping(source = "addressEntity.logradouro", target = "logradouro")
    @Mapping(source = "addressEntity.complemento", target = "complemento")
    @Mapping(source = "addressEntity.bairro", target = "bairro")
    @Mapping(source = "addressEntity.cidade", target = "cidade")
    @Mapping(source = "addressEntity.uf", target = "uf")
    @Mapping(source = "addressEntity.numero", target = "numero")
    @Mapping(target = "id", expression = "java( idToUiid(addressEntity.getId()) )" )
    Address AddressEntityToAddress (AddressEntity addressEntity);

    default UUID idToUiid(String id){
        return id != null ? UUID.fromString(id) : null;
    };

    default String uuidToId(UUID id){
        return id != null ? id.toString() : null;
    };
}
