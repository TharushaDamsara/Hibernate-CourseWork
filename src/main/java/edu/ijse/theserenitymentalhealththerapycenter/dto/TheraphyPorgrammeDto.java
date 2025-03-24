package edu.ijse.theserenitymentalhealththerapycenter.dto;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data

public class TheraphyPorgrammeDto {

    private String programId;
    private String name;
    private String duration;
    private double fee;


}
