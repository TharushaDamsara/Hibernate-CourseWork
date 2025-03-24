package edu.ijse.theserenitymentalhealththerapycenter.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PatientDto  {


    private String id;
    private String name;
    private String contactInfo;
    private String gender;
    private String birthDate;


}
