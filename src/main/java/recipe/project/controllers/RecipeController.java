package recipe.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import recipe.project.commands.RecipeCommand;
import recipe.project.service.RecipeService;

@RequestMapping("recipe")
@Controller
public class RecipeController {
	
	private final RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}
	
	@GetMapping("/show/{id}")
	public ModelAndView showById(@PathVariable String id) {
		ModelAndView nav = new ModelAndView("recipe/show");
		nav.addObject("recipe", recipeService.findById(Long.valueOf(id)));
		return nav;
	}
		
	@GetMapping("/new")
	public ModelAndView newRecipe() {
		ModelAndView nav = new ModelAndView("recipe/recipeform");
		
		nav.addObject("recipe",new RecipeCommand());
		return nav;
	}
	
	@GetMapping("/update/{id}")
	public ModelAndView updateRecipe(@PathVariable String id) {
		ModelAndView nav = new ModelAndView("recipe/recipeform");
		nav.addObject("recipe",recipeService.findCommandById(Long.valueOf(id)));
		return nav;
	}
	
	@PostMapping
	public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
		RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
		
		return "redirect:/recipe/show/" + savedCommand.getId();
	}
	
	@GetMapping("/delete/{id}")
	public String deleteById(@PathVariable String id) {
		
		recipeService.deleteById(Long.valueOf(id));
		
		return "redirect:/";
	}
	
}
