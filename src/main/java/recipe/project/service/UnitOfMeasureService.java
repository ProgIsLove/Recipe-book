package recipe.project.service;

import java.util.Set;

import recipe.project.commands.UnitOfMeasureCommand;

public interface UnitOfMeasureService {
	
	Set<UnitOfMeasureCommand> listAllUoms();
}
