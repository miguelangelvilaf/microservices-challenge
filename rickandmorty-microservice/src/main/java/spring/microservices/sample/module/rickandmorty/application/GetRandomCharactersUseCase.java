package spring.microservices.sample.module.rickandmorty.application;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import spring.microservices.sample.common.application.UseCase;
import spring.microservices.sample.module.rickandmorty.domain.Character;
import spring.microservices.sample.module.rickandmorty.domain.CharacterId;
import spring.microservices.sample.module.rickandmorty.domain.CharacterRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static spring.microservices.sample.config.Constants.MIN_CHARACTER_ID;
import static spring.microservices.sample.config.Constants.MAX_CHARACTER_ID;

/**
 * Get random characters from Rick and Morty series.
 *
 * @author Miguel A. Vila
 */
@RequiredArgsConstructor
@Slf4j
public class GetRandomCharactersUseCase extends UseCase<GetRandomCharactersUseCase.InputValues, GetRandomCharactersUseCase.OutputValues> {

    private final CharacterRepository characterRepository;

    @Override
    public OutputValues execute(InputValues input) {
        final Integer amountOfCharacters = input.getAmountOfCharacters();
        final List<CharacterId> excludedCharactersIds = input.getExcludedCharactersIds();
        // Get the list of random CharacterId based on the specified number of characters and the list of identifiers to exclude
        final List<CharacterId> randomCharactersIds = getCharacterIds(amountOfCharacters, excludedCharactersIds);
        log.info("Get characters wih IDs: " + randomCharactersIds);
        final List<Character> characters = characterRepository.findByIds(randomCharactersIds);
        return new OutputValues(characters);
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        Integer amountOfCharacters;
        List<CharacterId> excludedCharactersIds;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        List<Character> characters;
    }

    /**
     * Get the number of characters IDs specified in the call.
     *
     * @param amount the amount of IDs to obtain
     * @param excludedIds the list of IDs to exclude
     * @return the list of characters IDs to be retrieved from the API
     */
    private List<CharacterId> getCharacterIds(Integer amount, List<CharacterId> excludedIds) {
        List<CharacterId> characterIds = new ArrayList<>();
        Random rnd = new Random(System.nanoTime());
        IntStream.rangeClosed(1, amount)
            .forEach(index -> {
                excludedIds.sort((characterId1, characterId2) -> (int) (characterId1.getValue() - characterId2.getValue()));
                log.info("Character IDs to exclude in iteration {}: {}", index, excludedIds);
                // Obtain a new random character ID between the minimum and maximum ID; and excluding the specified IDs
                CharacterId characterId = getRandomCharacterId(rnd, new CharacterId(MIN_CHARACTER_ID), new CharacterId(MAX_CHARACTER_ID), excludedIds);
                // Add obtained CharacterID to the final list
                characterIds.add(characterId);
                // Add obtained CharacterID to the excluded list
                excludedIds.add(characterId);
            });
        return characterIds;
    }

    /**
     * Returns a random CharacterId between a minimum ID and a maximum ID and which does not coincide with any of the IDs to be excluded.
     *
     * @param random the pseudorandom generator to use
     * @param min the min character ID to generate
     * @param max the max character ID to generate
     * @param exclusionsSorted the characters IDs to exclude from generation
     * @return the random character ID obtained
     */
    private CharacterId getRandomCharacterId(Random random, CharacterId min, CharacterId max, List<CharacterId> exclusionsSorted) {
        CharacterId logicalMaxId = new CharacterId(max.getValue() - exclusionsSorted.size());
        CharacterId randomCharacterId = new CharacterId(Math.round(random.nextDouble() * (logicalMaxId.getValue() - min.getValue() + 1)) + min.getValue());
        long randomId = randomCharacterId.getValue();
        randomId += exclusionsSorted.stream().filter(excludedId -> randomCharacterId.getValue() >= excludedId.getValue()).count();
        return new CharacterId(randomId);
    }

}
