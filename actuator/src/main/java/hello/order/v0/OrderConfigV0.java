package hello.order.v0;

import hello.order.OrderService;
import org.springframework.context.annotation.Bean;

public class OrderConfigV0 {
    @Bean
    OrderService orderService() {
        return new OrderServiceV0();
    }
}
