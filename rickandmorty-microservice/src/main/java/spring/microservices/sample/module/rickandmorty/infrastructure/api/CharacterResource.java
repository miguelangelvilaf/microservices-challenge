package spring.microservices.sample.module.rickandmorty.infrastructure.api;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

/**
 * Character data returned by API.
 *
 * @author Miguel A. Vila
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CharacterResource implements Serializable {

    private static final long serialVersionUID = 469452311968389653L;

    Long id;
    String name;
    String status;
    String species;
    String type;
    String gender;
    LocationResource origin;
    LocationResource location;
    String image;
    String[] episode;
    String url;
    String created;

    public CharacterResource() {
        super();
    }

}
