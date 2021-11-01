package dev.chuckbot.ui;

import dev.chuckbot.persistence.DatasourceFactory;
import dev.chuckbot.persistence.JokeEntity;
import dev.chuckbot.persistence.PreparedStatementJokeDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller()
public class JokeController {

    private PreparedStatementJokeDao preparedStatementJokeDao;

    public JokeController() {
        try {
            DatasourceFactory dsFactory = new DatasourceFactory();
            preparedStatementJokeDao = new PreparedStatementJokeDao(dsFactory.getConnection());
        } catch (Exception ex) {
            System.err.println("Cannot create JokeDAO: " + ex.getMessage());
        }
    }

    @GetMapping("/view/jokes")
    public String main(Model model) {
        try {
            List<JokeEntity> jokeEntities = preparedStatementJokeDao.getAll();
            model.addAttribute("jokes", jokeEntities);
        } catch (Exception ex) {
            System.err.println("Wow! That's sad: " + ex.getMessage());
        }
        return "jokes"; //view
    }

}