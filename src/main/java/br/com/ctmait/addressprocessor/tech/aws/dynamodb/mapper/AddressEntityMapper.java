package br.com.ctmait.addressprocessor.tech.aws.dynamodb.mapper;

import br.com.ctmait.addressprocessor.domain.models.Address;
import br.com.ctmait.addressprocessor.domain.models.Provider;
import br.com.ctmait.addressprocessor.domain.models.State;
import br.com.ctmait.addressprocessor.tech.aws.dynamodb.entity.AddressEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;


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
    @Mapping(source = "source.id", target = "id" )
    @Mapping(target = "dataInclusao", ignore = true)
    @Mapping(source = "source.state", target = "state", qualifiedByName = "StateString")
    @Mapping(source = "source.provider", target = "provider", qualifiedByName = "ProviderString")
    AddressEntity map (Address source);

    @Mapping(source = "source.cep", target = "cep")
    @Mapping(source = "source.logradouro", target = "logradouro")
    @Mapping(source = "source.complemento", target = "complemento")
    @Mapping(source = "source.bairro", target = "bairro")
    @Mapping(source = "source.cidade", target = "cidade")
    @Mapping(source = "source.uf", target = "uf")
    @Mapping(source = "source.numero", target = "numero")
    @Mapping(source = "source.id", target = "id" )
    @Mapping(source = "source.state", target = "state", qualifiedByName = "StateEnum")
    @Mapping(source = "source.provider", target = "provider", qualifiedByName = "ProviderEnum")
    Address map (AddressEntity source);

    @Named("ProviderEnum")
    default Provider getProviderByCode(final String code) {
        return Provider.getByCode(code);
    }

    @Named("ProviderString")
    default String getProviderCode(final Provider provider) {
        return provider.getCode();
    }

    @Named("StateEnum")
    default State getStateByCode(final String code) {
        return State.getByCode(code);
    }

    @Named("StateString")
    default String getStateCode(final State state) {
        return state.getCode();
    }
}
