package kg.edu.manas.obis.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    @NotEmpty(message = "Это полья не должен быть пустым!")
    private String name;
    @Column(name = "surname")
    @NotEmpty(message = "Это полья не должен быть пустым!")
    private String surname;

    @OneToMany(mappedBy = "teacher")
    private List<Lessons> lessons;
}
