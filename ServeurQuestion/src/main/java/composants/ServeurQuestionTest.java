package composants;


import cucumber.api.CucumberOptions;
import cucumber.api.PendingException;
import cucumber.api.java.fr.Alors;
import cucumber.api.java.fr.Etantdonné;
import cucumber.api.java.fr.Quand;
import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;

/**
 * Created by corentin on 27/04/2017.
 */
@RunWith(Cucumber.class)
@CucumberOptions(features={"classpath:features/"})
public class ServeurQuestionTest {
}
