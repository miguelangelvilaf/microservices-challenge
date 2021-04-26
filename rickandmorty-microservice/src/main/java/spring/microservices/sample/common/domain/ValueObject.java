package spring.microservices.sample.common.domain;

/**
 * Marker interface for value objects.
 *
 * @author Miguel A. Vila
 * @param <T> the T type.
 */
public interface ValueObject<T> extends DomainObject {

    /**
     * Value objects compare by the values of their attributes, they don't have an identity.
     *
     * @param other The other value object.
     * @return <code>true</code> if the given value object's and this value object's attributes are the same.
     */
    boolean sameValueAs(T other);

}
