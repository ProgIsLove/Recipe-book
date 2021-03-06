package recipe.project.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import recipe.project.domain.Category;
import recipe.project.domain.Difficulty;
import recipe.project.domain.Ingredient;
import recipe.project.domain.Notes;
import recipe.project.domain.Recipe;
import recipe.project.domain.UnitOfMeasure;
import recipe.project.repositories.CategoryRepository;
import recipe.project.repositories.RecipeRepository;
import recipe.project.repositories.UnitOfMeasureRepository;

@Component
@Profile("default")
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {
	
	private final CategoryRepository categoryRepository;
	private final RecipeRepository recipeRepository;
	private final UnitOfMeasureRepository uomRepository;
	
	public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository,
			UnitOfMeasureRepository uomRepository) {
		this.categoryRepository = categoryRepository;
		this.recipeRepository = recipeRepository;
		this.uomRepository = uomRepository;
	}
	
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		recipeRepository.saveAll(getRecipes());
		
	}


	private List<Recipe> getRecipes(){
		
		List<Recipe> recipes = new ArrayList<>(2);
		
		Optional<UnitOfMeasure> eachUomOptional = uomRepository.findByDescription("Each");
		
		if(!eachUomOptional.isPresent()) {
			throw new RuntimeException("Excepted UOM Not found");
		}
		
		Optional<UnitOfMeasure> tableSpoonUomOptional = uomRepository.findByDescription("Tablespoon");
		
		if(!tableSpoonUomOptional.isPresent()) {
			throw new RuntimeException("Excepted UOM Not found");
			
		}
		
		Optional<UnitOfMeasure> dashUomOptional = uomRepository.findByDescription("Dash");
		
		if(!dashUomOptional.isPresent()) {
			throw new RuntimeException("Excepted UOM not found");
		}
		
		Optional<UnitOfMeasure> teaSpoonUomOptional = uomRepository.findByDescription("Teaspoon");
		
		if(!teaSpoonUomOptional.isPresent()) {
			throw new RuntimeException("Excepted UOM not found");
		}
		
		Optional<UnitOfMeasure> pintUomOptional = uomRepository.findByDescription("Pint");
		
		if(!pintUomOptional.isPresent()) {
			throw new RuntimeException("Excepted UOM not found");
		}
		
		Optional<UnitOfMeasure> cupUomOptional = uomRepository.findByDescription("Cup");
		
		if(!cupUomOptional.isPresent()) {
			throw new RuntimeException("Excepted UOM not found");
		}
		
		UnitOfMeasure eachUom = eachUomOptional.get();
		UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
		UnitOfMeasure dashUom = dashUomOptional.get();
		UnitOfMeasure teaSpoonUom = teaSpoonUomOptional.get();
		UnitOfMeasure pintUom = pintUomOptional.get();
		UnitOfMeasure cupUom = cupUomOptional.get();
		
		Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
		
		if(!americanCategoryOptional.isPresent()) {
			throw new RuntimeException("Excepted Category not found");
		}
		
		Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
		
		if(!mexicanCategoryOptional.isPresent()) {
			throw new RuntimeException("Excepted Category not found");
		}
		
		Category americanCategory = americanCategoryOptional.get();
		Category mexicanCategory = mexicanCategoryOptional.get();
		
		Recipe guacRecipe = new Recipe();
		guacRecipe.setDescription("Perfect Guacamole");
		guacRecipe.setPrepTime(10);
		guacRecipe.setDifficulty(Difficulty.EASY);
		guacRecipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
		guacRecipe.setCookTime(0);
		guacRecipe.setServings(4);
		guacRecipe.setSource("Simply recipes");
		guacRecipe.setDirections("1 Cut the avocado, remove flesh: Cut the avocados in half. "
				+ "Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. "
				+ "(See How to Cut and Peel an Avocado.) Place in a bowl." 
				+ "\n" 
				+"2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" 
				+ "\n" 
				+ "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\r\n" + 
				"\r\n" + 
				"Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\r\n" + 
				"\r\n" + 
				"Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\r\n" + 
				"\r\n" + 
				"Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving." +"\n" 
				+ "4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.");
		
		Notes gueNotes = new Notes();
		gueNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" + 
		"feel free to experiment with variations including strawberries, peaches, pineapple, mangoes, even watermelon. "
		+ "One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). You can get creative with homemade guacamole!");
		
		guacRecipe.setNotes(gueNotes);
		//gueNotes.setRecipe(guacRecipe);
		
		guacRecipe.addIngredient(new Ingredient("2 ripe avocados", new BigDecimal(2),eachUom));
		guacRecipe.addIngredient(new Ingredient("salt", new BigDecimal(0.5), teaSpoonUom));
		guacRecipe.addIngredient(new Ingredient("fresh lime or lemon juice", new BigDecimal(2), eachUom));
		guacRecipe.addIngredient(new Ingredient("minced red onion", new BigDecimal(2),tableSpoonUom));
		guacRecipe.addIngredient(new Ingredient("serrano chiles, steams and seeds removed, minced", new BigDecimal(2),eachUom));
		guacRecipe.addIngredient(new Ingredient("cilantro (leaves and tender stems), finely chopped ", new BigDecimal(2), tableSpoonUom));
		guacRecipe.addIngredient(new Ingredient("A dash of freshly grated black pepper", new BigDecimal(1), dashUom));
		guacRecipe.addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(0.5),eachUom));
		
		guacRecipe.getCategories().add(americanCategory);
		guacRecipe.getCategories().add(mexicanCategory);
		
		recipes.add(guacRecipe);
		
		Recipe tacosRecipe = new Recipe();
		tacosRecipe.setDescription("Spicy Grilled Chicken Taco");
		tacosRecipe.setCookTime(9);
		tacosRecipe.setPrepTime(20);
		tacosRecipe.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
		tacosRecipe.setSource("Simply recipes");
		tacosRecipe.setServings(4);
		tacosRecipe.setDifficulty(Difficulty.MODERATE);
		
		
		tacosRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n");
		
		Notes tacoNotes = new Notes();
		tacoNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n");
		tacoNotes.setRecipe(tacosRecipe);
		tacosRecipe.setNotes(tacoNotes);
		
		tacosRecipe.getIngredient().add(new Ingredient("Ancho Chili Powder", new BigDecimal(2), tableSpoonUom, tacosRecipe));
		tacosRecipe.getIngredient().add(new Ingredient("Dried Oregano", new BigDecimal(1), teaSpoonUom, tacosRecipe));
		tacosRecipe.getIngredient().add(new Ingredient("Dried Cumin", new BigDecimal(1), teaSpoonUom, tacosRecipe));
		tacosRecipe.getIngredient().add(new Ingredient("Sugar", new BigDecimal(1), teaSpoonUom, tacosRecipe));
		tacosRecipe.getIngredient().add(new Ingredient("Salt", new BigDecimal(".5"), teaSpoonUom, tacosRecipe));
		tacosRecipe.getIngredient().add(new Ingredient("Clove of Garlic, Choppedr", new BigDecimal(1), eachUom, tacosRecipe));
		tacosRecipe.getIngredient().add(new Ingredient("finely grated orange zestr", new BigDecimal(1), tableSpoonUom, tacosRecipe));
		tacosRecipe.getIngredient().add(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), tableSpoonUom, tacosRecipe));
		tacosRecipe.getIngredient().add(new Ingredient("Olive Oil", new BigDecimal(2), tableSpoonUom, tacosRecipe));
		tacosRecipe.getIngredient().add(new Ingredient("boneless chicken thighs", new BigDecimal(4), tableSpoonUom, tacosRecipe));
		tacosRecipe.getIngredient().add(new Ingredient("small corn tortillasr", new BigDecimal(8), eachUom, tacosRecipe));
		tacosRecipe.getIngredient().add(new Ingredient("packed baby arugula", new BigDecimal(3), cupUom, tacosRecipe));
		tacosRecipe.getIngredient().add(new Ingredient("medium ripe avocados, slic", new BigDecimal(2), eachUom, tacosRecipe));
		tacosRecipe.getIngredient().add(new Ingredient("radishes, thinly slicked", new BigDecimal(4), eachUom, tacosRecipe));
		tacosRecipe.getIngredient().add(new Ingredient("cherry tomatoes, halved", new BigDecimal(".5"), pintUom, tacosRecipe));
		tacosRecipe.getIngredient().add(new Ingredient("red onion, thinly sliced", new BigDecimal(".25"), eachUom, tacosRecipe));
		tacosRecipe.getIngredient().add(new Ingredient("Roughly chopped cilantro", new BigDecimal(4), eachUom, tacosRecipe));
		tacosRecipe.getIngredient().add(new Ingredient("cup sour cream thinned with 1/4 cup milk", new BigDecimal(4), cupUom, tacosRecipe));
		tacosRecipe.getIngredient().add(new Ingredient("lime, cut into wedges", new BigDecimal(4), eachUom, tacosRecipe));
		
		tacosRecipe.getCategories().add(americanCategory);
		tacosRecipe.getCategories().add(mexicanCategory);
		recipes.add(tacosRecipe);
		
		return recipes;
		
	}
}
