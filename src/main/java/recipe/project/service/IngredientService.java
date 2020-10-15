package recipe.project.service;

import recipe.project.commands.IngredientCommand;


public interface IngredientService {

	IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
	
	IngredientCommand saveIngredientCommand(IngredientCommand command);
	
	void deleteById(Long recipeId, Long idToDelete);
}
