package kg.edu.manas.obis.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false,name = "id")
    private int id;
    @Column(name = "name")
    @NotEmpty(message = "Это полья не должен быть пустым!")
    private String name;
    @Column(name = "surname")
    @NotEmpty(message = "Это полья не должен быть пустым!")
    private String surname;
    @Column(name = "student_number")
    @NotEmpty(message = "Это полья не должен быть пустым!")
    private String student_number;
    @Column(name = "tel_number")
    private int tel_number;
    @Column(name = "father_name")
    @NotEmpty(message = "Это полья не должен быть пустым!")
    private String father_name;
    @Column(name = "mother_name")
    @NotEmpty(message = "Это полья не должен быть пустым!")
    private String mother_name;
    @Column(name = "password")
    @NotEmpty(message = "Это полья не должен быть пустым!")
    @Size(min = 6, message = "Пароль должен быть между 6 и 16")
    private String password;
    @Column(name = "place_of_birth")
    @NotEmpty(message = "Это полья не должен быть пустым!")
    private String place_of_birth;
    @Column(name = "nationality")
    @NotEmpty(message = "Это полья не должен быть пустым!")
    private String nationality;
    @Column(name = "role")
    private String role;
    @Column(name = "faculty")
    @NotEmpty(message = "Это полья не должен быть пустым!")
    private String faculty;
    @Column(name = "profession")
    @NotEmpty(message = "Это полья не должен быть пустым!")
    private String profession;

    @Column(name = "email")
    @NotEmpty(message = "Это полья не должен быть пустым!")
    @Email(message = "Это полья должен быть email (например: example@gmail.com)")
    private String email;

    @OneToMany(mappedBy = "student",fetch = FetchType.EAGER)
    private List<Lessons> lessons;


    public List<Lessons> getLessons(){
        Collections.sort(this.lessons, Comparator.comparing(Lessons::getId));
        return this.lessons;
    }
}
