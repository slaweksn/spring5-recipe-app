package guru.springframework.services.impl;

import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.services.RecipeService;
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

//import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    RecipeService recipeService;
    @Mock
    RecipeRepository recipeRepository;
    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;
    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;
    @BeforeAll
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks((this));

        this.recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    public void getRecipesTest() {
        //given
        Recipe recipe = new Recipe();
        Set<Recipe> recipesDate = new HashSet<>();
        recipesDate.add(recipe);
        given(this.recipeRepository.findAll()).willReturn((recipesDate));
        //given(this.recipeService.getRecipes()).willReturn((recipesDate);
        //when(this.recipeService.getRecipes()).thenReturn(recipesDate);
        //when(this.recipeRepository.findAll()).thenReturn(recipesDate);

        // when
        Set<Recipe> recipes = this.recipeService.getRecipes();
        System.out.println("recipes: " + recipes);

        // then
        assertEquals(recipes.size(), 1, 0);
        verify(recipeRepository, times(1));
    }
}