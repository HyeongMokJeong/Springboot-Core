package hello.order;

import java.util.concurrent.atomic.AtomicInteger;

public interface OrderService {
    void order();
    void cancel();
    AtomicInteger getStock(); // 멀티쓰레드 상황에서 안전성 보장하는 타입
}
