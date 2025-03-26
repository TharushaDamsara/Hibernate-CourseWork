package edu.ijse.theserenitymentalhealththerapycenter.dto.tm;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PatientTm {


    private String id;
    private String name;
    private String contactInfo;
    private String gender;
    private String birthDate;


}
