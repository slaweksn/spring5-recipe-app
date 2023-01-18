package guru.springframework.services.impl;

import java.util.HashSet;
import java.util.Set;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import org.springframework.stereotype.Service;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service("recipeService")
public class RecipeServiceImpl implements RecipeService {

	private final RecipeRepository recipeRepository;
	private final RecipeCommandToRecipe recipeCommandToRecipe;
	private final RecipeToRecipeCommand recipeToRecipeCommand;
	
	public RecipeServiceImpl(RecipeRepository recipeRepository,
							 RecipeCommandToRecipe recipeCommandToRecipe,
							 RecipeToRecipeCommand recipeToRecipeCommand) {
		super();
		this.recipeRepository = recipeRepository;
		this.recipeCommandToRecipe = recipeCommandToRecipe;
		this.recipeToRecipeCommand = recipeToRecipeCommand;
	}

	@Override
	public Set<Recipe> getRecipes() {
		
		log.debug("I'm in the service");
		Set<Recipe> recipes = new HashSet<>();
		recipeRepository.findAll().forEach(recipes::add);
		
		return recipes;
	}

	@Override
	public Recipe findById(Long l) {

		return recipeRepository.findById(l).orElseThrow(() -> new RuntimeException("Recipe not found"));
	}

	@Transactional
	@Override
	public RecipeCommand saveRecipeCommand(RecipeCommand command) {

		Recipe recipe = recipeCommandToRecipe.convert(command);

		Recipe saveRecipe = recipeRepository.save(recipe);

		return recipeToRecipeCommand.convert(saveRecipe);
	}
}
