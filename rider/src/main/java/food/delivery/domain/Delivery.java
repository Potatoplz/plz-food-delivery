package food.delivery.domain;

import food.delivery.RiderApplication;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Delivery_table")
@Data
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long orderId;

    private String address;

    private String status;

    private String customerId;

    private String storeId;

    // @PostPersist
    // public void onPostPersist() {}

    public static DeliveryRepository repository() {
        DeliveryRepository deliveryRepository = RiderApplication.applicationContext.getBean(
            DeliveryRepository.class
        );
        return deliveryRepository;
    }

    public void pickUp() {
        setStatus("배달시작");
        Picked picked = new Picked(this);
        picked.publishAfterCommit();
    }
    
    public void confirmDelivery() {
        setStatus("배달완료");
        DeliveryCompleted deliveryCompleted = new DeliveryCompleted(this);
        deliveryCompleted.publishAfterCommit();
    }

    public static void updateStatus(CookingFinished cookingFinished) {
        /** Example 1:  new item 
        Delivery delivery = new Delivery();
        repository().save(delivery);

        */

        /** Example 2:  finding and process */
        
        repository().findByOrderId(cookingFinished.getOrderId()).ifPresent(delivery->{
            delivery.setStatus("조리완료");
            repository().save(delivery);
        });
        

    }

    public static void copyOrder(OrderPlaced orderPlaced) {
        /** Example 1:  new item */
         
        Delivery delivery = new Delivery();
        delivery.setOrderId(orderPlaced.getId());
        delivery.setStoreId(orderPlaced.getStoreId());
        delivery.setCustomerId(orderPlaced.getCustomerId());
        delivery.setAddress(orderPlaced.getAddress());
        delivery.setStatus("결제완료");
        repository().save(delivery);


        /** Example 2:  finding and process
        
        repository().findById(orderPlaced.get???()).ifPresent(delivery->{
            
            delivery // do something
            repository().save(delivery);

         });
        */

    }
}
