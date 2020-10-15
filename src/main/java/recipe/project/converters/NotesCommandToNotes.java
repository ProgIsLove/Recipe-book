package recipe.project.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import recipe.project.commands.NotesCommand;
import recipe.project.domain.Notes;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {
	
    @Nullable
    @Override
    public synchronized Notes convert(NotesCommand source) {
        if(source == null) {
            return null;
        }

        final Notes notes = new Notes();
        notes.setId(source.getId());
        notes.setRecipeNotes(source.getRecipeNotes());
        return notes;
    }
	
}
