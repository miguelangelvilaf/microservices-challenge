package spring.microservices.sample.module.rickandmorty.domain;

import java.util.List;

/**
 * Character repository.
 *
 * @author Miguel A. Vila
 */
public interface CharacterRepository {

    List<Character> findByIds(List<CharacterId> ids);

}
