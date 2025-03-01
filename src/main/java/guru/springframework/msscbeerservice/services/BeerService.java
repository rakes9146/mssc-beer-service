package guru.springframework.msscbeerservice.services;

import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.model.BeerPagedList;
import guru.springframework.msscbeerservice.web.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {

    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyleEnum, PageRequest pageRequest, Boolean showInventoryOnHand);

    public BeerDto getById(UUID beerId, Boolean showInventoryOnHand);

    public BeerDto saveNewBeer(BeerDto beerDto);

    public BeerDto updateBeerById(UUID beerId, BeerDto beerDto);

    BeerDto getByUpc(String beerUpc);
}
