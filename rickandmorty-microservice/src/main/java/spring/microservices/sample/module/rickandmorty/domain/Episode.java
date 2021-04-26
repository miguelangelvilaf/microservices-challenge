package spring.microservices.sample.module.rickandmorty.domain;

import lombok.Value;
import spring.microservices.sample.common.domain.ValueObject;

/**
 * Episode.
 *
 * @author Miguel A. Vila
 */
@Value
public class Episode implements ValueObject<Episode> {

    private static final long serialVersionUID = 4660067766177775938L;

    String url;

    @Override
    public boolean sameValueAs(Episode other) {
        return false;
    }

}
