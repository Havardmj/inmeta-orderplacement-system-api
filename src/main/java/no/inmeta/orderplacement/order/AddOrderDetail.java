package no.inmeta.orderplacement.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddOrderDetail {

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String emailAddress;

    private String movingFromAddress;

    private String movingToAddress;

    private TypeOfService typeOfService;
}
