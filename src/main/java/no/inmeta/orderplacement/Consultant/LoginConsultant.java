package no.inmeta.orderplacement.Consultant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginConsultant {
    private UUID id;

    private String firstName;

    private String lastName;

    private String emailAddress;

    private String phoneNumber;
}
