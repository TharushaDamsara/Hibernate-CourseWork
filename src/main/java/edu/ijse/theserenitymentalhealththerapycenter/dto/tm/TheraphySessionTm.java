package edu.ijse.theserenitymentalhealththerapycenter.dto.tm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class TheraphySessionTm {

    private String id;
    private String date;
    private String time;
    private String status;
    private String therapistId;
    private String patientId;
    private String therapyProgramId;
}
