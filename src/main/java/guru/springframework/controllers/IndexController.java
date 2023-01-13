package guru.springframework.controllers;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.domain.Category;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.services.RecipeService;

/**
 * Created by jt on 6/1/17.
 */
@Controller
public class IndexController {
	/*-
	private final CategoryRepository categoryRepository;
	private final UnitOfMeasureRepository measureRepository;
	
	public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository measureRepository) {
		super();
		this.categoryRepository = categoryRepository;
		this.measureRepository = measureRepository;
	}
	
	@RequestMapping({ "", "/", "/index" })
	public String getIndexPage(Model model) {
	
		Optional<Category> findByDescriptionCategory = categoryRepository.findByDescription("American");
	
		Optional<UnitOfMeasure> findByDescriptionUnitOfMeasure = measureRepository.findByDescription("Teaspoon");
	
		findByDescriptionCategory.ifPresent(p -> {
			System.out.println("Category: id=" + p.getId() + ", description=" + p.getDescription());
		});
	
		findByDescriptionUnitOfMeasure.ifPresent(p -> {
			System.out.println("UnitOfMeasure: id=" + p.getId() + ", description=" + p.getDescription());
		});
	
		return "index";
	}
	*/
	private final RecipeService recipeService;

	public IndexController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}

	@RequestMapping({ "", "/", "/index" })
	public String getIndexPage(Model model) {

		Set<Recipe> recipes = recipeService.getRecipes();
		
		model.addAttribute("recipes", recipes);
		
		return "index";
	}
}
