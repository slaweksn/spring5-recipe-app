package guru.springframework.services.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.services.RecipeService;

@Service
public class RecipeServiceImpl implements RecipeService {

	private final RecipeRepository recipeRepository;
	
	
	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		super();
		this.recipeRepository = recipeRepository;
	}

	@Override
	public Set<Recipe> getRecipes() {
		// TODO Auto-generated method stub
		Set<Recipe> recipes = new HashSet<>();
		//recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
		recipeRepository.findAll().forEach(recipes::add);
		
		return recipes;
	}

}
