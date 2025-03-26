package edu.ijse.theserenitymentalhealththerapycenter.dto.tm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data

public class TheraphyPorgrammeTm {

    private String programId;
    private String name;
    private String duration;
    private double fee;


}
