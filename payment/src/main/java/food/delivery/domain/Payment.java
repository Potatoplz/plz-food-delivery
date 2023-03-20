package food.delivery.domain;

import food.delivery.PaymentApplication;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Payment_table")
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long orderId;

    private String status;

    private String customerId;

    private String storeId;

    @PostPersist
    public void onPostPersist() {}

    public static PaymentRepository repository() {
        PaymentRepository paymentRepository = PaymentApplication.applicationContext.getBean(
            PaymentRepository.class
        );
        return paymentRepository;
    }

    public void pay() {
        Paid paid = new Paid(this);
    //    paid.setStatus("결제완료");

        repository().findByOrderId(paid.getId()).ifPresent(payment->{
            payment.setStatus("결제완료");
            repository().save(payment);
         });
        paid.publishAfterCommit();
    }

    public static void updateStatus(OrderPlaced orderPlaced) {
        /** Example 1:  new item  */
            Payment payment = new Payment();
            payment.setOrderId(orderPlaced.getId());
            payment.setCustomerId(orderPlaced.getCustomerId());
            payment.setStoreId(orderPlaced.getStoreId());
            payment.setStatus("결제대기");
            repository().save(payment);
       

        /** Example 2:  finding and process
        
        repository().findById(orderPlaced.get???()).ifPresent(payment->{
            
            payment // do something
            repository().save(payment);


         });
        */

    }
}
