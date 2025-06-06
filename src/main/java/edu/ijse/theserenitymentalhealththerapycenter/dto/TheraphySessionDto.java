package edu.ijse.theserenitymentalhealththerapycenter.dto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class TheraphySessionDto  {

    private String id;
    private String date;
    private String time;
    private String status;
    private TheraphistDto therapist;
    private PatientDto patient;
    private TheraphyPorgrammeDto therapyProgram;
    private double fee;
}
