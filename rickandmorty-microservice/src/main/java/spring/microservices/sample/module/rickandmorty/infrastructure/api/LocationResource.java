package spring.microservices.sample.module.rickandmorty.infrastructure.api;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

/**
 * Location resource.
 *
 * @author Miguel A. Vila
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class LocationResource implements Serializable {

    private static final long serialVersionUID = 5628150329700043877L;

    String name;
    String url;

    public LocationResource() {
        super();
    }

}
