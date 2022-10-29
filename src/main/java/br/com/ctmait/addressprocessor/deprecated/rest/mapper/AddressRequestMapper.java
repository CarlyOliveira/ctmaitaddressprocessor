package br.com.ctmait.addressprocessor.deprecated.rest.mapper;

import br.com.ctmait.addressprocessor.deprecated.domain.models.Address;
import br.com.ctmait.addressprocessor.deprecated.rest.resources.request.AddressResquest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AddressRequestMapper {

    AddressRequestMapper INSTANCE = Mappers.getMapper( AddressRequestMapper.class );

    @Mapping(source = "addressResquest.cep", target = "cep")
    @Mapping(source = "addressResquest.logradouro", target = "logradouro")
    @Mapping(source = "addressResquest.complemento", target = "complemento")
    @Mapping(source = "addressResquest.bairro", target = "bairro")
    @Mapping(source = "addressResquest.cidade", target = "cidade")
    @Mapping(source = "addressResquest.uf", target = "uf")
    @Mapping(source = "addressResquest.numero", target = "numero")
    Address AddressRequestToAddress (AddressResquest addressResquest);

}
