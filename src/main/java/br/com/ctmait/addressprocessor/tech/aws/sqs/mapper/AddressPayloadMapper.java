package br.com.ctmait.addressprocessor.tech.aws.sqs.mapper;

import br.com.ctmait.addressprocessor.domain.models.Address;
import br.com.ctmait.addressprocessor.domain.models.Provider;
import br.com.ctmait.addressprocessor.tech.aws.sqs.payload.AddressPayload;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressPayloadMapper {

    AddressPayloadMapper INSTANCE = Mappers.getMapper( AddressPayloadMapper.class );

    @Mapping(source = "source.cep", target = "cep")
    @Mapping(source = "source.logradouro", target = "logradouro")
    @Mapping(source = "source.complemento", target = "complemento")
    @Mapping(source = "source.bairro", target = "bairro")
    @Mapping(source = "source.cidade", target = "cidade")
    @Mapping(source = "source.uf", target = "uf")
    @Mapping(source = "source.numero", target = "numero")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "source.provider", target = "provider", qualifiedByName = "ProviderString")
    AddressPayload map (Address source);

    @Named("ProviderString")
    default String getProviderCode(final Provider provider) {
        return provider.getCode();
    }

}
