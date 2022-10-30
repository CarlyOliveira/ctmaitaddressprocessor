package br.com.ctmait.addressprocessor.tech.aws.dynamodb.repository;

import br.com.ctmait.addressprocessor.domain.models.Address;
import br.com.ctmait.addressprocessor.tech.aws.dynamodb.entity.AddressEntity;
import br.com.ctmait.addressprocessor.tech.aws.dynamodb.mapper.AddressEntityMapper;
import br.com.ctmait.addressprocessor.tech.infrastructure.repository.AddressRepository;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTableMapper;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class AddressRepositoryImpl implements AddressRepository {

    private final DynamoDBMapper dynamoDBMapper;

    @Override
    public void save(Address address) {

        try {
            address.setId(UUID.randomUUID());
            var addressEntity = AddressEntityMapper.INSTANCE.map(address);
            addressEntity.setDataInclusao(LocalDate.now());
            DynamoDBTableMapper<AddressEntity, String, ?> dynamoDBTableMapper = dynamoDBMapper.newTableMapper(AddressEntity.class);
            dynamoDBTableMapper.saveIfNotExists(addressEntity);
        }catch (ConditionalCheckFailedException conditionalCheckFailedException){
            // TODO Implementar tratamento para endereço duplicado
        }catch (Exception exception){
            // TODO Implementar tratamento para erros gerais
        }
    }
}
