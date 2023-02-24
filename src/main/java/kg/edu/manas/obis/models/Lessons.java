package kg.edu.manas.obis.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Lessons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lessons {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "lesson_name")
    private String lesson_name;
    @Column(name = "grades")
    private String grades;
    @Column(name = "lesson_code")
    private String lesson_code;
    @Column(name = "kredi")
    private int kredi;
    @Column(name = "attendance")
    private Double attendance;
    @ManyToOne
    @JoinColumn(name = "student_id",referencedColumnName = "id")
    private Student student;

    public Lessons(String lesson_name){
        this.lesson_name = lesson_name;
    }
}
