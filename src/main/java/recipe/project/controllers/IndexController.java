package recipe.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import recipe.project.service.RecipeService;

@Controller
public class IndexController {
	
	private final RecipeService recipeService;
	
	public IndexController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}
	
	@RequestMapping({"","/"})
	public ModelAndView getIndex() {
		ModelAndView nav = new ModelAndView("index");
		nav.addObject("recipes", recipeService.getRecipes());
		return nav;
	}
}
