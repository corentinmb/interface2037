package composants.entitees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    private QuestionDAO questionDAO;

    @RequestMapping(method = RequestMethod.GET)
    Collection<Question> getQuestions(){
        return questionDAO.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    Question getQuestion(@PathVariable Long id){
        return questionDAO.findOne(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/last")
    Question getLastQuestion(){
        return Collections.max(questionDAO.findAll(), Comparator.comparing(c -> c.getDatecreation()));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Question addQuestion(@RequestParam(value = "libelle", required = false) String libelle) {
        Question q = new Question(libelle);
        return questionDAO.saveAndFlush(q);
    }

    /*@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteQuestion(@PathVariable Long id) {
        questionDAO.delete(id);
    }*/
}
