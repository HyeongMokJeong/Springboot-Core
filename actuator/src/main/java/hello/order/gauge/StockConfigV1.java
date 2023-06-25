package hello.order.gauge;

import hello.order.OrderService;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StockConfigV1 {

    @Bean
    public MyStockMetric myStockMetric(OrderService orderService, MeterRegistry meterRegistry) {
        return new MyStockMetric(orderService, meterRegistry);
    }

    @Slf4j
    static class MyStockMetric {
        private OrderService orderService;

        public MyStockMetric(OrderService orderService, MeterRegistry meterRegistry) {
            this.orderService = orderService;
            this.meterRegistry = meterRegistry;
        }

        private MeterRegistry meterRegistry;

        // my.stock 이라는 이름으로 gauge 등록
        // 람다식으로 전달된 함수는 외부에서 메트릭 확인할 때 호출됨
        // return 값이 게이지 값
        @PostConstruct
        public void init() {
            Gauge.builder("my.stock", orderService, service -> {
                log.info("stock gauge call");
                return service.getStock().get();
            }).register(meterRegistry);
        }
    }
}
