package spring.microservices.sample.common.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.NonNull;

import java.util.Objects;

/**
 * Base class for value objects that are used as identifiers for {@link Entity}s. These are
 * essentially Long-wrappers.
 *
 * @author Miguel A. Vila
 */
public abstract class LongEntityId implements ValueObject<LongEntityId>, EntityId<Long> {

    private static final long serialVersionUID = -7378403862594829416L;

    private final Long id;

    @JsonCreator
    public LongEntityId(@NonNull Long id) {
        this.id = id;
    }

    @Override
    @NonNull
    public Long getValue() {
        return id;
    }

    @Override
    public boolean sameValueAs(LongEntityId other) {
        return other != null && this.id.equals(other.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LongEntityId that = (LongEntityId) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("%s[%s]", getClass().getSimpleName(), id);
    }

}
