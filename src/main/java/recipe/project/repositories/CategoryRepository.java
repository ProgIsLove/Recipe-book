package recipe.project.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import recipe.project.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long>{
	
	Optional<Category> findByDescription(String description);
	
}
