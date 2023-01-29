package no.inmeta.orderplacement.repository;

import no.inmeta.orderplacement.TestData;
import no.inmeta.orderplacement.order.AddOrder;
import no.inmeta.orderplacement.order.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles({"local", "testdata"})
@DirtiesContext
public class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void confirming_save_order_for_AddOrder() {
        AddOrder addOrder = TestData.aAddDetailTestCase();
        Order order = new Order(addOrder);

        Assertions.assertDoesNotThrow(() -> {
            orderRepository.save(order);
        });
    }

    @Test
    public void confirming_save_order() {
        Order order = TestData.oneConsultantTestOrder();

        Assertions.assertDoesNotThrow(() -> {
            orderRepository.save(order);
        });

    }
}
