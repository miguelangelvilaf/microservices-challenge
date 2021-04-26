package spring.microservices.sample.common.domain;

import java.io.Serializable;

/**
 * Interface for domain objects that can be uniquely identified.
 *
 * @author Miguel A. Vila
 * @param <ID> the ID type.
 */
public interface Entity<ID extends Serializable> extends DomainObject {

    /**
     * Returns the ID of this domain object.
     *
     * @return the ID or {@code null} if an ID has not been assigned yet.
     */
    ID getId();

}
