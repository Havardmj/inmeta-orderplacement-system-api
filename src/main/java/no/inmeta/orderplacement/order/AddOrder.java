package no.inmeta.orderplacement.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddOrder {

    private UUID consultantId;

    private String consultantName;

    private LocalDate appointmentDate;

    private AddOrderDetail addDetail;




}
