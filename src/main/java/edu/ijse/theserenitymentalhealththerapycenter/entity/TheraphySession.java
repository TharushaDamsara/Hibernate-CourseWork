package edu.ijse.theserenitymentalhealththerapycenter.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "therapy_sessions")

public class TheraphySession implements SuperEntity{
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String date;
    private String time;
    private String status;
    @ManyToOne
    @JoinColumn(name = "therapist_id")
    private Theraphist therapist;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private TheraphyPorgramme therapyProgram;

    public Optional<Object> findById(String therapySession) {
        return null;
    }
}
