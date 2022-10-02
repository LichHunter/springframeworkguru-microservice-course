package spring.course.brewery.monolith.brewerymonolith.events;

import org.springframework.context.ApplicationEvent;
import spring.course.brewery.monolith.brewerymonolith.domain.Beer;

public class NewInventoryEvent extends ApplicationEvent {

    public NewInventoryEvent(Beer source) {
        super(source);
    }

    public Beer getBeer(){
        return (Beer) this.source;
    }
}
