package spring.course.brewery.monolith.brewerymonolith.events;

import org.springframework.context.ApplicationEvent;
import spring.course.brewery.monolith.brewerymonolith.domain.BeerOrder;

public class NewBeerOrderEvent extends ApplicationEvent {

    public NewBeerOrderEvent(BeerOrder source) {
        super(source);
    }

    public BeerOrder getBeerOrder(){
        return (BeerOrder) this.source;
    }
}
