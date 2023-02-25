package kg.edu.manas.obis.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import kg.edu.manas.obis.models.Teacher;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LessonDTO {
    @NotEmpty(message = "Это полья не должен быть пустым!")
    private String lesson_name;
    @NotEmpty(message = "Это полья не должен быть пустым!")
    private String lesson_code;
    private int kredi;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher teacher;
}
