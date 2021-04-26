package spring.microservices.sample.module.rickandmorty.infrastructure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import spring.microservices.sample.module.rickandmorty.domain.Character;
import spring.microservices.sample.module.rickandmorty.domain.CharacterId;
import spring.microservices.sample.module.rickandmorty.domain.CharacterRepository;
import spring.microservices.sample.module.rickandmorty.infrastructure.api.CharacterResource;
import spring.microservices.sample.module.rickandmorty.infrastructure.mapper.CharacterMapper;

import java.util.*;
import java.util.stream.Collectors;

import static spring.microservices.sample.config.Constants.API_CHARACTER_ENDPOINT;

/**
 * Character repository implementation.
 *
 * @author Miguel A. Vila
 */
@Repository
@RequiredArgsConstructor
@Slf4j
public class CharacterRepositoryImpl implements CharacterRepository {

    private final RestTemplate apiRestTemplate;
    private final CharacterMapper characterMapper;

    @Override
    public List<Character> findByIds(List<CharacterId> ids) {
        // Convert list to string
        String joinedIds = ids.stream().map(id -> id.getValue().toString()).collect(Collectors.joining(","));
        String url = API_CHARACTER_ENDPOINT + "/" + joinedIds;
        log.info("Requested URL: " + url);
        CharacterResource[] characterResources = apiRestTemplate.getForObject(url, CharacterResource[].class);
        if (Objects.nonNull(characterResources)) {
            List<CharacterResource> charactersList = Arrays.asList(characterResources);
            return charactersList.stream().map(characterMapper::toCharacter).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

}
