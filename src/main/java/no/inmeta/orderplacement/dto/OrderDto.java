package no.inmeta.orderplacement.dto;


import lombok.RequiredArgsConstructor;
import no.inmeta.orderplacement.order.AddOrder;
import no.inmeta.orderplacement.order.Order;
import no.inmeta.orderplacement.repository.OrderRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderDto {

    private final OrderRepository orderRepository;

    public void addConsultantOrder(AddOrder addOrder) {
        Order order = new Order(addOrder);
        orderRepository.save(order);
    }

    public void findConsultantOrder(UUID consultantId) {

    }

    public List<Order> findAllConsultantOrders(UUID consultantId) {
        return orderRepository.findAllByConsultantId(consultantId);
    }
}
