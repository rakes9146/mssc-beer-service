package guru.springframework.msscbeerservice.bootstrap;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repository.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BeerLoader implements CommandLineRunner {

    private final BeerRepository beerRepository;

    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBeerObject();
    }

    private void loadBeerObject() {

        if(beerRepository.count() == 0 ){

                beerRepository.save(Beer.builder()
                        .beerName("Mango Bobs")
                        .beerStyle("IPA")
                        .quantityToBrew(200)
                        .minOnHand(12)
                        .upc(383838382222L)
                        .price(new BigDecimal("12.95"))
                        .build());

            beerRepository.save(Beer.builder()
                    .beerName("Galaxy Cat")
                    .beerStyle("PALE_ALE")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(98938938832L)
                    .price(new BigDecimal("11.95"))
                    .build());

            System.out.println("Loaded Beers "+beerRepository.count());

        }

    }
}
