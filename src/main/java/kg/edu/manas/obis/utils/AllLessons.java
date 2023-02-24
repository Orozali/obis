package kg.edu.manas.obis.utils;

import kg.edu.manas.obis.models.Lessons;
import org.springframework.stereotype.Component;

import java.util.List;
public class AllLessons {
    public List<Lessons> lessons;
    public AllLessons(){
        lessons.add(new Lessons("MANTIKSAL TASARIM"));
        lessons.add(new Lessons("SINYALLER VE SISTEMLER"));
        lessons.add(new Lessons("NESNE YONELIMLI PROGRAMLAMA"));
        lessons.add(new Lessons("KIRGIZISTAN CORAFYASI"));
        lessons.add(new Lessons("OLASILIK VE ISTATISTIK"));
        lessons.add(new Lessons("ATAMEKEN TARIHI"));
    }
}
