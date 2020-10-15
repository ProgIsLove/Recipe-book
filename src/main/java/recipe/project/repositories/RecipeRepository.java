package recipe.project.repositories;

import org.springframework.data.repository.CrudRepository;

import recipe.project.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
