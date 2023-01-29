package no.inmeta.orderplacement.Consultant;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "consultant")
public class Consultant {

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

    private String emailAddress;

    private String consultantPassword;

    private String phoneNumber;

    private LocalDate consultantAdded;

    public Consultant(
            UUID id,
            String firstName,
            String lastName,
            String emailAddress,
            String userPassword,
            String phoneNumber
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress.toLowerCase();
        this.consultantPassword = userPassword;
        this.phoneNumber = phoneNumber;
        this.consultantAdded = LocalDate.now();
    }

    public Consultant(String firstName, String lastName, String emailAddress, String userPassword, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress.toLowerCase();
        this.consultantPassword = userPassword;
        this.phoneNumber = phoneNumber;
        this.consultantAdded = LocalDate.now();
    }
}
