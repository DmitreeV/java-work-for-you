package pet.project.javaworkforyou.reaction.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pet.project.javaworkforyou.reaction.dto.ReactionDto;
import pet.project.javaworkforyou.reaction.model.Reaction;

@Mapper(componentModel = "spring")
public interface ReactionMapper {

    @Mapping(target = "vacancy", source = "vacancy.id")
    @Mapping(target = "resume", source = "resume.id")
    ReactionDto toReactionDto(Reaction reaction);
}
