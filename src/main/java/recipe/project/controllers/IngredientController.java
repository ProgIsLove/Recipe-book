package recipe.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import recipe.project.commands.IngredientCommand;
import recipe.project.commands.RecipeCommand;
import recipe.project.commands.UnitOfMeasureCommand;
import recipe.project.service.IngredientService;
import recipe.project.service.RecipeService;
import recipe.project.service.UnitOfMeasureService;

@RequestMapping("recipe/{recipeId}")
@Controller
public class IngredientController {

	private final RecipeService recipeService;
	private final IngredientService ingredientService;
	private final UnitOfMeasureService unitOfMeasureService;

	public IngredientController(RecipeService recipeService, IngredientService ingredientService,
			UnitOfMeasureService unitOfMeasureService) {
		this.recipeService = recipeService;
		this.ingredientService = ingredientService;
		this.unitOfMeasureService = unitOfMeasureService;
	}

	@GetMapping("/ingredients")
	public ModelAndView listIngredients(@PathVariable String recipeId) {
		ModelAndView nav = new ModelAndView("recipe/ingredient/list");
		
		nav.addObject("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));
		
		return nav;
	}
	
	
	@GetMapping("/ingredient/{id}/show")
	public ModelAndView showRecipeIngredient(@PathVariable String recipeId, @PathVariable String id) {
		ModelAndView nav = new ModelAndView("recipe/ingredient/show");
		nav.addObject("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));
		return nav;
	}
	
    @GetMapping("/ingredient/new")
    public String newIngredient(@PathVariable String recipeId, Model model){
    	
    	RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));
    	
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));
        model.addAttribute("ingredient", ingredientCommand);

        ingredientCommand.setUom(new UnitOfMeasureCommand());

        model.addAttribute("uomList",  unitOfMeasureService.listAllUoms());

        return "recipe/ingredient/ingredientform";
    }

	@GetMapping("/ingredient/{id}/update")
	public String updateRecipeIngredient(@PathVariable String recipeId, @PathVariable String id, Model model) {
		model.addAttribute("ingredient",
				ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));

		model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
		return "recipe/ingredient/ingredientform";
	}

	@PostMapping("/ingredient")
	public String saveOrUpdate(@ModelAttribute IngredientCommand command) {

		IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

		return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredients/";
	}
	
	@GetMapping("/ingredient/{id}/delete")
	public String deleteIngredient(@PathVariable String recipeId,
									@PathVariable String id) {
		ingredientService.deleteById(Long.valueOf(recipeId), Long.valueOf(id));
		
		return "redirect:/recipe/" + recipeId + "/ingredients";
	}
}
