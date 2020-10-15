package recipe.project.service;

import java.util.Set;

import recipe.project.commands.RecipeCommand;
import recipe.project.domain.Recipe;

public interface RecipeService {

	Set<Recipe> getRecipes();
	
	Recipe findById(Long l);
	
	RecipeCommand saveRecipeCommand(RecipeCommand command);
	
	RecipeCommand findCommandById(Long l);
	
	void deleteById(Long idToDelete);
}
