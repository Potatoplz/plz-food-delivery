package food.delivery.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import food.delivery.config.kafka.KafkaProcessor;
import food.delivery.domain.*;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PolicyHandler {

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @Autowired
    food.delivery.external.OrderService orderService;

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OrderAccepted'"
    )
    public void wheneverOrderAccepted_Notify(
        @Payload OrderAccepted orderAccepted
    ) {
        OrderAccepted event = orderAccepted;
        System.out.println(
            "\n\n##### listener Notify : " + orderAccepted + "\n\n"
        );
        // REST Request Sample

        // orderService.getOrder(/** mapping value needed */);

        // Sample Logic //

    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OrderRejected'"
    )
    public void wheneverOrderRejected_Notify(
        @Payload OrderRejected orderRejected
    ) {
        OrderRejected event = orderRejected;
        System.out.println(
            "\n\n##### listener Notify : " + orderRejected + "\n\n"
        );
        // REST Request Sample

        // orderService.getOrder(/** mapping value needed */);

        // Sample Logic //

    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='CookingStarted'"
    )
    public void wheneverCookingStarted_Notify(
        @Payload CookingStarted cookingStarted
    ) {
        CookingStarted event = cookingStarted;
        System.out.println(
            "\n\n##### listener Notify : " + cookingStarted + "\n\n"
        );
        // REST Request Sample

        // orderService.getOrder(/** mapping value needed */);

        // Sample Logic //

    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='CookingFinished'"
    )
    public void wheneverCookingFinished_Notify(
        @Payload CookingFinished cookingFinished
    ) {
        CookingFinished event = cookingFinished;
        System.out.println(
            "\n\n##### listener Notify : " + cookingFinished + "\n\n"
        );
        // REST Request Sample

        // orderService.getOrder(/** mapping value needed */);

        // Sample Logic //

    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Picked'"
    )
    public void wheneverPicked_Notify(@Payload Picked picked) {
        Picked event = picked;
        System.out.println("\n\n##### listener Notify : " + picked + "\n\n");
        // REST Request Sample

        // orderService.getOrder(/** mapping value needed */);

        // Sample Logic //

    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='DeliveryCompleted'"
    )
    public void wheneverDeliveryCompleted_Notify(
        @Payload DeliveryCompleted deliveryCompleted
    ) {
        DeliveryCompleted event = deliveryCompleted;
        System.out.println(
            "\n\n##### listener Notify : " + deliveryCompleted + "\n\n"
        );
        // REST Request Sample

        // orderService.getOrder(/** mapping value needed */);

        // Sample Logic //

    }
}
