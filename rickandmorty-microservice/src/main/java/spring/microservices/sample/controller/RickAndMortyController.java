package spring.microservices.sample.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import spring.microservices.sample.common.application.UseCaseExecutor;
import spring.microservices.sample.module.rickandmorty.application.GetRandomCharactersUseCase;
import spring.microservices.sample.module.rickandmorty.domain.Character;
import spring.microservices.sample.module.rickandmorty.domain.CharacterId;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static spring.microservices.sample.config.Constants.MAX_AMOUNT;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/characters")
@Slf4j
public final class RickAndMortyController {

    private final UseCaseExecutor useCaseExecutor;
    private final GetRandomCharactersUseCase getRandomCharactersUseCase;

    public RickAndMortyController(UseCaseExecutor useCaseExecutor, GetRandomCharactersUseCase getRandomCharactersUseCase) {
        this.useCaseExecutor = useCaseExecutor;
        this.getRandomCharactersUseCase = getRandomCharactersUseCase;
    }

    /**
     * Default handler, returns the maximum number of characters allowed at random.
     */
    @GetMapping (produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Character> getDefaultAmountRandomCharacters() {
        return getRandomCharacters(MAX_AMOUNT, Collections.emptyList());
    }

    /**
     * Basic handler, returns the number of characters specified at random.
     */
    @GetMapping (value ="/{amount}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Character> getRandomCharactersWithoutExcludes(@PathVariable("amount") @Valid @Min(1) @Max(5) Integer amount) {
        return getRandomCharacters(amount, Collections.emptyList());
    }

    /**
     * Advanced handler, returns the number of characters specified at random excluding characters with specified IDs.
     */
    @GetMapping (value ="/{amount}/{excludeCharacters}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Character> getRandomCharacters(@PathVariable("amount") @Valid @Min(1) @Max(5) Integer amount,
                                               @PathVariable("excludeCharacters") List<Long> excludeCharacters) {
        // Convert long values to CharacterID objects
        List<CharacterId> excludedCharactersIds = excludeCharacters.stream().map(CharacterId::new).collect(Collectors.toList());
        // Execute use case with specified input values in the request and returns a random list of characters
        return useCaseExecutor.execute(
            getRandomCharactersUseCase,
            new GetRandomCharactersUseCase.InputValues(amount, excludedCharactersIds),
            GetRandomCharactersUseCase.OutputValues::getCharacters
        );
    }

}
