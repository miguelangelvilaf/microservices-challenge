package spring.microservices.sample.common.domain;

import lombok.NonNull;

/**
 * Interface for entity ID.
 *
 * @author Miguel A. Vila
 * @param <T> the T type of the entity ID.
 */
public interface EntityId<T> extends DomainObject {

    /**
     * Returns the value of ID.
     *
     * @return the ID value of type T.
     */
    @NonNull
    T getValue();

}
