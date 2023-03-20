package food.delivery.domain;

import food.delivery.StoreApplication;
import food.delivery.domain.OrderRejected;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Cooking_table")
@Data
public class Cooking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String foodId;

    private Long orderId;

    private String address;

    private String status;

    private String storeId;

    private String customerId;

    // @PostPersist
    // public void onPostPersist() {
    //     OrderRejected orderRejected = new OrderRejected(this);
    //     orderRejected.publishAfterCommit();
    // }

    @PreUpdate
    public void onPreUpdate() {}

    public static CookingRepository repository() {
        CookingRepository cookingRepository = StoreApplication.applicationContext.getBean(
            CookingRepository.class
        );
        return cookingRepository;
    }

    public void accept(AcceptCommand acceptCommand) {
        if(acceptCommand.getAccept()){
            OrderAccepted orderAccepted = new OrderAccepted(this);
            orderAccepted.publishAfterCommit();
            setStatus("주문수락");
        }else{
            OrderRejected orderRejected = new OrderRejected(this);
            orderRejected.publishAfterCommit();
            setStatus("주문거절");
        }
    }

    public void startCooking() {
        CookingStarted cookingStarted = new CookingStarted(this);
        cookingStarted.publishAfterCommit();
        setStatus("조리시작");
    }
    
    public void finishCooking() {
        CookingFinished cookingFinished = new CookingFinished(this);
        cookingFinished.publishAfterCommit();
        setStatus("조리완료");
    }

    public static void updateStatus(Paid paid) {
        /** Example 1:  new item 
        Cooking cooking = new Cooking();
        repository().save(cooking);

        */

        /** Example 2:  finding and process
        
        repository().findById(paid.get???()).ifPresent(cooking->{
            
            cooking // do something
            repository().save(cooking);


         });
        */

    }

    public static void copyOrder(OrderPlaced orderPlaced) {
        /** Example 1:  new item */
        Cooking cooking = new Cooking();
        cooking.setOrderId(orderPlaced.getId());
        cooking.setStoreId(orderPlaced.getStoreId());
        cooking.setCustomerId(orderPlaced.getCustomerId());
        cooking.setFoodId(orderPlaced.getFoodId());
        cooking.setAddress(orderPlaced.getAddress());
        cooking.setStatus("주문접수");
        
        repository().save(cooking);

        /** Example 2:  finding and process
         
        repository().findById(orderPlaced.get???()).ifPresent(cooking->{
            
            cooking // do something
            repository().save(cooking);
            });
        */

    }
}
