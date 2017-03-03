package composants.entitees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    private QuestionDAO questionDAO;

    @RequestMapping(method = RequestMethod.GET)
    Collection<Question> getQuestions(){
        return this.questionDAO.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    Question getQuestion(@PathVariable Long id){
        return this.questionDAO.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Question addQuestion(@RequestBody Question question) {
        return questionDAO.saveAndFlush(question);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Question updateQuestion(@RequestBody Question question) {
        return questionDAO.saveAndFlush(question);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteQuestion(@PathVariable Long id) {
        questionDAO.delete(id);
    }
}