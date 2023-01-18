package guru.springframework.services;

import guru.springframework.Spring5RecipeAppApplication;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@SpringBootTest
//@BootstrapWith
//@ExtendWith(SpringExtension.class)
//@DataJpaTest
//@AutoConfigureMockMvc
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = {RecipeService.class})
//@DataJpaTest
//@TestPropertySource(locations = "classpath:application.properties")
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = Spring5RecipeAppApplication.class)
@SpringBootTest
//@SpringBootTest(classes = Spring5RecipeAppApplication.class)
//@DataJpaTest
public class RecipeServiceIT {
    public static final String NEW_DESCRIPTION = "New Description";

    @Autowired
    @Qualifier("recipeService")
    RecipeService recipeService;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Transactional
    //@Rollback(false)
    @Test
    public void testSaveOfDescription() throws Exception {
        //given
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe testRecipe = recipes.iterator().next();
        RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(testRecipe);
        System.out.println("testRecipeCommand  " + testRecipeCommand.getId() + " " + testRecipeCommand.getDescription());
        //when
        testRecipeCommand.setDescription(NEW_DESCRIPTION);
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(testRecipeCommand);
        System.out.println("savedRecipeCommand " + testRecipeCommand.getId() + " " +  savedRecipeCommand.getDescription());
        //then
        assertEquals(NEW_DESCRIPTION, savedRecipeCommand.getDescription());
        assertEquals(testRecipe.getId(), savedRecipeCommand.getId());
        assertEquals(testRecipe.getCategories().size(), savedRecipeCommand.getCategories().size());
        assertEquals(testRecipe.getIngredients().size(), savedRecipeCommand.getIngredients().size());

        System.out.println("-----------------------------------------------------------------------------------------");

    }

    @AfterEach
    public void testEnd(){
        System.out.println("testEnd");
        Iterable<Recipe> recipes2 = recipeRepository.findAll();
        recipes2.forEach(p->{
            System.out.println("savedRecipeCommand " + p.getId() + " " +  p.getDescription());
        });
    }
}
