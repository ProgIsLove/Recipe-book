 package recipe.project.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import recipe.project.commands.IngredientCommand;
import recipe.project.converters.IngredientCommandToIngredient;
import recipe.project.converters.IngredientToIngredientCommand;
import recipe.project.domain.Ingredient;
import recipe.project.domain.Recipe;
import recipe.project.repositories.RecipeRepository;
import recipe.project.repositories.UnitOfMeasureRepository;

@Service
public class IngredientServiceImpl implements IngredientService {
	
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand,
                                 IngredientCommandToIngredient ingredientCommandToIngredient,
                                 RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

	@Override
	public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
		// TODO Auto-generated method stub
		
		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
		
		if(!recipeOptional.isPresent()) {
			throw new RuntimeException("recipe id not found id: " + recipeId);
		}
		
		Recipe recipe = recipeOptional.get();
		
		Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredient().stream()
				.filter(ingredient -> ingredient.getId().equals(ingredientId))
				.map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();
		
		if(!ingredientCommandOptional.isPresent()) {
			throw new RuntimeException("Ingredient id not found: " + ingredientId);

		}
				
		return ingredientCommandOptional.get();
	}

	@Override
	@Transactional
	public IngredientCommand saveIngredientCommand(IngredientCommand command) {
		// TODO Auto-generated method stub
		Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());
		
		if(!recipeOptional.isPresent()) {
			
			throw new RuntimeException("Recipe not found for id: " + command.getRecipeId());
		}else {
			Recipe recipe = recipeOptional.get();
			
			Optional<Ingredient> ingredientOptional = recipe.getIngredient()
					.stream()
					.filter(ingredient -> ingredient.getId().equals(command.getId()))
					.findFirst();
			
			if(ingredientOptional.isPresent()) {
				Ingredient ingredientFound = ingredientOptional.get();
				ingredientFound.setDescription(command.getDescription());
				ingredientFound.setAmount(command.getAmount());
				ingredientFound.setAmount(command.getAmount());
				ingredientFound.setUom(unitOfMeasureRepository
						.findById(command.getUom().getId())
						.orElseThrow(()-> new RuntimeException("UOM NOT FOUND")));
			}else {
                Ingredient ingredient = ingredientCommandToIngredient.convert(command);
                ingredient.setRecipe(recipe);
                recipe.addIngredient(ingredient);
			}
			
            Recipe savedRecipe = recipeRepository.save(recipe);

            Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredient().stream()
                    .filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
                    .findFirst();

            
            if(!savedIngredientOptional.isPresent()){
                savedIngredientOptional = savedRecipe.getIngredient().stream()
                        .filter(recipeIngredients -> recipeIngredients.getDescription().equals(command.getDescription()))
                        .filter(recipeIngredients -> recipeIngredients.getAmount().equals(command.getAmount()))
                        .filter(recipeIngredients -> recipeIngredients.getUom().getId().equals(command.getUom().getId()))
                        .findFirst();
            }

            
            return ingredientToIngredientCommand.convert(savedIngredientOptional.get());
			
		}
		
		
	}

	@Override
	public void deleteById(Long recipeId, Long idToDelete) {
		// TODO Auto-generated method stub
		
		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
		
		if(recipeOptional.isPresent()) {
			Recipe recipe = recipeOptional.get();
			
			Optional<Ingredient> ingredientOptional = recipe
					.getIngredient()
					.stream()
					.filter(ingredient -> ingredient.getId().equals(idToDelete))
					.findFirst();
			
			if(ingredientOptional.isPresent()) {
				Ingredient ingredientToDelete = ingredientOptional.get();
				ingredientToDelete.setRecipe(null);
				recipe.getIngredient().remove(ingredientOptional.get());
				recipeRepository.save(recipe);
			}else {
				throw new RuntimeException("Recipe Id Not found. Id: " + recipeId);
			}
		}
		
	}
	
	

}
