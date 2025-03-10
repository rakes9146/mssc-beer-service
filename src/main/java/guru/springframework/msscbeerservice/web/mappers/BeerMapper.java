package guru.springframework.msscbeerservice.web.mappers;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {

    Beer beerDtoToBeer(BeerDto beerDto);

    BeerDto beerToBeerDtoWithInventory(Beer beer);

    BeerDto beerToBeerDto(Beer beer);

}
