package br.com.ctmait.addressprocessor.tech.rest.mapper;

import br.com.ctmait.addressprocessor.domain.models.Address;
import br.com.ctmait.addressprocessor.domain.models.Provider;
import br.com.ctmait.addressprocessor.tech.rest.payload.in.AddressPayloadIn;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper( AddressMapper.class );

    @Mapping(source = "source.cep", target = "cep")
    @Mapping(source = "source.logradouro", target = "logradouro")
    @Mapping(source = "source.complemento", target = "complemento")
    @Mapping(source = "source.bairro", target = "bairro")
    @Mapping(source = "source.cidade", target = "cidade")
    @Mapping(source = "source.uf", target = "uf")
    @Mapping(source = "source.numero", target = "numero")
    @Mapping(source = "transactionId", target = "id")
    @Mapping(source = "source.provider", target = "provider", qualifiedByName = "ProviderEnum")
    Address map (AddressPayloadIn source, String transactionId);

    @Named("ProviderEnum")
    default Provider getByCode(final String code) {
        return Provider.getByCode(code);
    }
}
