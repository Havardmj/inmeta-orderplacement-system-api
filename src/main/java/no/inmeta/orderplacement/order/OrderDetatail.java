package no.inmeta.orderplacement.order;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Data
@Builder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_detail")
public class OrderDetatail {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String emailAddress;

    private String movingFromAddress;

    private String movingToAddress;

    @Enumerated(EnumType.STRING)
    private TypeOfService typeOfService;

    public OrderDetatail(
            String firstName,
            String lastName,
            String phoneNumber,
            String emailAddress,
            String movingFromAddress,
            String movingToAddress,
            TypeOfService typeOfService
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.movingFromAddress = movingFromAddress;
        this.movingToAddress = movingToAddress;
        this.typeOfService = typeOfService;
    }

    public static OrderDetatail newOrderDetailContent(AddOrderDetail addDetail) {
        OrderDetatail orderDetatail = new OrderDetatail();

        orderDetatail.setFirstName(addDetail.getFirstName());
        orderDetatail.setLastName(addDetail.getLastName());
        orderDetatail.setPhoneNumber(addDetail.getPhoneNumber());
        orderDetatail.setEmailAddress(addDetail.getEmailAddress());
        orderDetatail.setMovingFromAddress(addDetail.getMovingFromAddress());
        orderDetatail.setMovingToAddress(addDetail.getMovingToAddress());
        orderDetatail.setTypeOfService(addDetail.getTypeOfService());

        return orderDetatail;
    }

}
