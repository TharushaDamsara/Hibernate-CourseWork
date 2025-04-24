package edu.ijse.theserenitymentalhealththerapycenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data

public class PaymentDto{

    private String id;
    private double amount;
    private String date;
    private PatientDto patient;
    private TheraphySessionDto therapySession;
    private String status;

}