package kg.edu.manas.obis.repository;

import kg.edu.manas.obis.models.Lessons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lessons,Integer> {

}
