package edu.ijse.theserenitymentalhealththerapycenter.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data

public class PaymentTm {

    private String id;
    private double amount;
    private String date;
    private String patient;
    private String therapySessionId;

}