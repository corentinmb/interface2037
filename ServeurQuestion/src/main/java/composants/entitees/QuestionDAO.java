package composants.entitees;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface QuestionDAO extends JpaRepository<Question, Long> {

}
