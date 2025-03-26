package edu.ijse.theserenitymentalhealththerapycenter.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class UserTm {

    private String id;
    private String username;
    private String password;
    private String role;

}