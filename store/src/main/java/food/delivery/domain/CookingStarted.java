package food.delivery.domain;

import food.delivery.domain.*;
import food.delivery.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class CookingStarted extends AbstractEvent {

    private Long id;
    private String foodId;
    private Long orderId;
    private String address;
    private String status;
    private String storeId;
    private String customerId;

    public CookingStarted(Cooking aggregate) {
        super(aggregate);
    }

    public CookingStarted() {
        super();
    }
}
