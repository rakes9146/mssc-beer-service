package guru.springframework.msscbeerservice.event;

import guru.springframework.msscbeerservice.web.model.BeerDto;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerEvent implements Serializable {

    static final long serialVersionUID = -234334343443L;

    private BeerDto beerDto;



}
