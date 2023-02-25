package kg.edu.manas.obis.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LessonMark {
    @NotEmpty(message = "Это полья не должен быть пустым!")
    private String grades;
}
