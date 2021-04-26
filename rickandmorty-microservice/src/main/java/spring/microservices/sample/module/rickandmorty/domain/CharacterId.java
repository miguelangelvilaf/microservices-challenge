package spring.microservices.sample.module.rickandmorty.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.NonNull;
import spring.microservices.sample.common.domain.LongEntityId;

/**
 * Character ID value object.
 *
 * @author Miguel A. Vila
 */
public final class CharacterId extends LongEntityId {

    private static final long serialVersionUID = 8402000317424692774L;

    @JsonCreator
    public CharacterId(@NonNull Long id) {
        super(id);
    }

}
