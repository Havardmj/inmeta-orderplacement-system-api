package no.inmeta.orderplacement.Consultant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddConsultant {

    private String firstName;

    private String lastName;

    private String emailAddress;

    private String consultantPassword;

    private String phoneNumber;
}
