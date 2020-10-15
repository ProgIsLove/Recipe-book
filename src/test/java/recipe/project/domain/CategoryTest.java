package recipe.project.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CategoryTest {
	
	Category category;
	
	@BeforeEach
	public void setUp() {
		category = new Category();
	}

	@Test
	void testGetId() throws Exception {
		Long idValue = 2L;
		
		category.setId(idValue);
		
		System.out.println(category.getId());
		
		assertEquals(idValue, category.getId());
	}

	@Test
	void testSetDescription() {
		fail("Not yet implemented");
	}

	@Test
	void testGetRecipe() {
		fail("Not yet implemented");
	}

}
