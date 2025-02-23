package guru.springframework.msscbeerservice.services;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repository.BeerRepository;
import guru.springframework.msscbeerservice.web.controller.NotFoundException;
import guru.springframework.msscbeerservice.web.mappers.BeerMapper;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerMapper beerMapper;
    private final BeerRepository beerRepository;

   @Override
    public BeerDto getById(UUID beerId) {
        return beerMapper.beerToBeerDto(beerRepository.
                findById(beerId).orElseThrow(NotFoundException::new));
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {

       return beerMapper.beerToBeerDto(beerRepository.save
               (beerMapper.beerDtoToBeer(beerDto)));

    }

    @Override
    public BeerDto updateBeerById(UUID beerId, BeerDto beerDto) {

       Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);

       beer.setBeerName(beer.getBeerName());
       beer.setBeerStyle(beer.getBeerStyle());
       beer.setPrice(beer.getPrice());
       beer.setUpc(beer.getUpc());

       return beerMapper.beerToBeerDto(beerRepository.save(beer));


    }
}
