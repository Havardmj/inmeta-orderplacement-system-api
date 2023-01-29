package no.inmeta.orderplacement.repository;

import no.inmeta.orderplacement.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID>, JpaSpecificationExecutor {

    Order save(Order order);

    List<Order> findAllByConsultantId(UUID consultantId);
}
