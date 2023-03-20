package food.delivery.domain;

import food.delivery.infra.AbstractEvent;
import java.util.*;
import lombok.Data;

@Data
public class Paid extends AbstractEvent {

    private Long id;
    private Long orderId;
    private String status;
    private String customerId;
    private String storeId;
}
