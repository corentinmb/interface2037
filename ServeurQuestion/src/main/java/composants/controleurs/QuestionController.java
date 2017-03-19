package composants.controleurs;

import composants.entitees.Question;
import composants.entitees.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    private QuestionRepository questionRepository;

    @RequestMapping(method = RequestMethod.GET)
    Collection<Question> getQuestions(){
        return questionRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    Question getQuestion(@PathVariable Long id){
        return questionRepository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/last")
    Question getLastQuestion(){
        return Collections.max(questionRepository.findAll(), Comparator.comparing(c -> c.getDatecreation()));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Question addQuestion(@RequestParam(value = "libelle", required = false) String libelle) {
        Question q = new Question(libelle);
        return questionRepository.saveAndFlush(q);
    }

    /*@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteQuestion(@PathVariable Long id) {
        questionDAO.delete(id);
    }*/
}
