package no.inmeta.orderplacement.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.time.LocalDate;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "consultant_order")
public class Order extends AbstractAggregateRoot<Order> {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private UUID consultantId;

    private String consultantName;

     @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    private OrderDetatail orderDetail;

    private LocalDate orderRegister;

    private LocalDate appointmentDate;

    public Order(AddOrder addOrder) {
        this.id = UUID.randomUUID();
        this.orderDetail = OrderDetatail.newOrderDetailContent(addOrder.getAddDetail());
        this.consultantId = addOrder.getConsultantId();
        this.orderRegister = LocalDate.now();
        this.appointmentDate = addOrder.getAppointmentDate();
    }

    public Order(
            UUID consultantId,
            String consultantName,
            OrderDetatail orderDetail,
            LocalDate appointmentDate
    ) {
        this.consultantId = consultantId;
        this.consultantName = consultantName;
        this.orderDetail = orderDetail;
        this.orderRegister = LocalDate.now();
        this.appointmentDate = appointmentDate;

    }
}
