package spring.microservices.sample.common.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;

/**
 * Base class for entities.
 *
 * @author Miguel A. Vila
 * @param <ID> the entity ID type.
 */
public abstract class AbstractEntity<ID extends EntityId<?>> implements Entity<ID> {

    private static final long serialVersionUID = -7451020895388342066L;

    private ID id;

    /**
     * Default constructor
     */
    protected AbstractEntity() {
    }

    /**
     * Copy constructor
     *
     * @param source the entity to copy from.
     */
    protected AbstractEntity(@NonNull AbstractEntity<ID> source) {
        this.id = source.id;
    }

    /**
     * Constructor for creating new entities.
     *
     * @param id the ID to assign to the entity.
     */
    protected AbstractEntity(@NonNull ID id) {
        this.id = id;
    }

    @Override
    @NonNull
    public ID getId() {
        return id;
    }

    /**
     * Java Bean style method to get the flat value of the entity ID.
     *
     * @return the flat value of the entity ID.
     */
    @NonNull
    @JsonProperty("id")
    public Object getIdValue() {
        return getId().getValue();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !getClass().equals(obj.getClass())) {
            return false;
        }

        AbstractEntity<?> other = (AbstractEntity<?>) obj;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id == null ? super.hashCode() : id.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s[%s]", getClass().getSimpleName(), id);
    }

}
