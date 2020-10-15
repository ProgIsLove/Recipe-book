package recipe.project.converters;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.sun.istack.Nullable;

import recipe.project.commands.CategoryCommand;
import recipe.project.domain.Category;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category>{
	
	
	@Nullable
	@Override
	public synchronized Category convert(CategoryCommand source) {
		// TODO Auto-generated method stub
		if(source == null) {
			return null;
		}
		
		final Category category = new Category();
		category.setId(source.getId());
		category.setDescription(source.getDescription());
		return category;
	}

}
