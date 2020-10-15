package recipe.project.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import recipe.project.domain.Recipe;
import recipe.project.repositories.RecipeRepository;

@Service
public class ImageServiceImpl implements ImageService {
	
	private final RecipeRepository recipeRepository;
	
	public ImageServiceImpl(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}
	
	@Override
	@Transactional
	public void saveImageFile(Long recipeId, MultipartFile file) {
		// TODO Auto-generated method stub
		
		try {
			Recipe recipe = recipeRepository.findById(recipeId).get();
			
			Byte[] byteObjects = new Byte[file.getBytes().length];
			
			int i = 0;
			
			for(byte b : file.getBytes()) {
				byteObjects[i++] = b;
			}
			
			recipe.setImage(byteObjects);
			
			recipeRepository.save(recipe);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}

}
