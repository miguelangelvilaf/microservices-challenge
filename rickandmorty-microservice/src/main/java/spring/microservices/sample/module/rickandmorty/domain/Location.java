package spring.microservices.sample.module.rickandmorty.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import spring.microservices.sample.common.domain.ValueObject;

import java.io.Serializable;

/**
 * Location.
 *
 * @author Miguel A. Vila
 */
@Value
public class Location implements ValueObject<Location> {

    private static final long serialVersionUID = 5165301439430891533L;

    String name;
    String url;

    @Override
    public boolean sameValueAs(Location other) {
        return false;
    }

}
