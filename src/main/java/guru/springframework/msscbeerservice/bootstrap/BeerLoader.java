package guru.springframework.msscbeerservice.bootstrap;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repository.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;
@RequiredArgsConstructor
@Component
public class BeerLoader implements CommandLineRunner {

   public static final String BEER_1_UPC = "0631234200036";
   public static final String BEER_2_UPC = "0631234300019";
   public static final String BEER_3_UPC = "0083783375213";

    private final BeerRepository beerRepository;

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
                        .upc(BEER_1_UPC)
                        .price(new BigDecimal("12.95"))
                        .build());
            beerRepository.save(Beer.builder()
                    .beerName("Galaxy Cat")
                    .beerStyle("PALE_ALE")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(BEER_2_UPC)
                    .price(new BigDecimal("11.95"))
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("Third New Beer")
                    .beerStyle("PALE_ALE")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(BEER_3_UPC)
                    .price(new BigDecimal("11.95"))
                    .build());

            System.out.println("Loaded Beers "+beerRepository.count());

        }

    }
}
