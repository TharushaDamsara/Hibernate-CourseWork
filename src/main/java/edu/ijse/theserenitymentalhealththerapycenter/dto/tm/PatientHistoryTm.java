package edu.ijse.theserenitymentalhealththerapycenter.dto.tm;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PatientHistoryTm {


    private String id;
    private double payment;
    private String status;
    private String theraphy;


}
