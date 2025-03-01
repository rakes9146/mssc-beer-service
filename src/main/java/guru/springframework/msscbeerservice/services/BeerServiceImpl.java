package guru.springframework.msscbeerservice.services;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repository.BeerRepository;
import guru.springframework.msscbeerservice.web.controller.NotFoundException;
import guru.springframework.msscbeerservice.web.mappers.BeerMapper;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.model.BeerPagedList;
import guru.springframework.msscbeerservice.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerMapper beerMapper;
    private final BeerRepository beerRepository;

    @Override
    public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyleEnum, PageRequest pageRequest, Boolean showInventoryOnHand) {

        BeerPagedList beerPagedList;
        Page<Beer> beerPage;

        if(!StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyleEnum)){
            //search both
            beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyleEnum, pageRequest);
        }else if(!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(beerStyleEnum)){
            //search by beer name
            beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
        }else if(StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyleEnum)){
        //search by beer style
            beerPage = beerRepository.findAllByBeerStyle( beerStyleEnum, pageRequest);
        }else {
            //search all
            beerPage = beerRepository.findAll(pageRequest);
        }

        if(showInventoryOnHand) {

            beerPagedList = new BeerPagedList(
                    beerPage.getContent()
                            .stream()
                            .map(beerMapper::beerToBeerDtoWithInventory)
                            .collect(Collectors.toList()),

                    PageRequest.of(beerPage.getPageable().getPageNumber(), beerPage.getPageable().getPageSize())
                    ,
                    beerPage.getTotalElements()
            );
        }else {

            beerPagedList = new BeerPagedList(
                    beerPage.getContent()
                            .stream()
                            .map(beerMapper::beerToBeerDto)
                            .collect(Collectors.toList()),

                    PageRequest.of(beerPage.getPageable().getPageNumber(), beerPage.getPageable().getPageSize())
                    ,
                    beerPage.getTotalElements()
            );
        }

        return beerPagedList;
    }

    @Override
    public BeerDto getById(UUID beerId, Boolean showInventoryOnHand) {
        if(showInventoryOnHand){
            return beerMapper.beerToBeerDtoWithInventory(beerRepository.
                    findById(beerId).orElseThrow(NotFoundException::new));
        }else {
            return beerMapper.beerToBeerDto(beerRepository.
                    findById(beerId).orElseThrow(NotFoundException::new));
        }
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
