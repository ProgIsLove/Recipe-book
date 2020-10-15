package recipe.project.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import recipe.project.commands.RecipeCommand;
import recipe.project.converters.RecipeCommandToRecipe;
import recipe.project.converters.RecipeToRecipeCommand;
import recipe.project.domain.Recipe;
import recipe.project.repositories.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService {
	
	private final RecipeRepository recipeRepository;
	private final RecipeCommandToRecipe recipeCommandToRecipe;
	private final RecipeToRecipeCommand recipeToRecipeCommand;
	
	public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe,
			RecipeToRecipeCommand recipeToRecipeCommand) {
		this.recipeRepository = recipeRepository;
		this.recipeCommandToRecipe = recipeCommandToRecipe;
		this.recipeToRecipeCommand = recipeToRecipeCommand;
	}


	@Override
	public Set<Recipe> getRecipes() {
		// TODO Auto-generated method stub
		
		Set<Recipe> recipeSet = new HashSet<>();
		
		recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
		
		return recipeSet;
	}


	@Override
	public Recipe findById(Long l) {
		
		Optional<Recipe> recipeOptional = recipeRepository.findById(l);
		
		if(!recipeOptional.isPresent()) {
			throw new RuntimeException("Recipe not found");
		}
		
		return recipeOptional.get();
	}
	
	@Override
	@Transactional
	public RecipeCommand findCommandById(Long l) {
		// TODO Auto-generated method stub
		
		return recipeToRecipeCommand.convert(findById(l));
	}


	@Override
	@Transactional
	public RecipeCommand saveRecipeCommand(RecipeCommand command) {
		// TODO Auto-generated method stub
		Recipe detachedRecipe = recipeCommandToRecipe.convert(command);
		
		Recipe savedRecipe = recipeRepository.save(detachedRecipe);
	
		return recipeToRecipeCommand.convert(savedRecipe);
	}


	@Override
	public void deleteById(Long idToDelete) {
		// TODO Auto-generated method stub
		recipeRepository.deleteById(idToDelete);
		
	}
}
