package edu.ijse.theserenitymentalhealththerapycenter.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PatientHistoryDto {


    private String id;
    private double payment;
    private String status;
    private String theraphy;


}
