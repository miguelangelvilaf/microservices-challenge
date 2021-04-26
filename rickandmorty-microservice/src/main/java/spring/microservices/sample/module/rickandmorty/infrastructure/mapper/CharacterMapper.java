package spring.microservices.sample.module.rickandmorty.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import spring.microservices.sample.module.rickandmorty.domain.Character;
import spring.microservices.sample.module.rickandmorty.domain.CharacterId;
import spring.microservices.sample.module.rickandmorty.domain.Episode;
import spring.microservices.sample.module.rickandmorty.infrastructure.api.CharacterResource;

/**
 * Character mapper.
 *
 * @author Miguel A. Vila
 */
@Mapper
public interface CharacterMapper {

    @Mappings({
        @Mapping(target = "origin", source = "origin"),
        @Mapping(target = "location", source = "location"),
        @Mapping(target = "episodes", source = "episode"),
        @Mapping(target = "created", dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    })
    Character toCharacter(CharacterResource characterResource);

    default Episode fromEpisode(String episode) {
        return new Episode(episode);
    }

    default CharacterId fromId(Long id) {
        return new CharacterId(id);
    }

}
