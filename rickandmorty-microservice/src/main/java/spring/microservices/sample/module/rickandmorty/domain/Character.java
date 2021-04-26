package spring.microservices.sample.module.rickandmorty.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.*;
import lombok.experimental.FieldDefaults;
import spring.microservices.sample.common.domain.AbstractEntity;
import spring.microservices.sample.common.infrastructure.annotation.Default;

import java.time.Instant;
import java.util.List;

import static spring.microservices.sample.config.Constants.TOTAL_EPISODES;

/**
 * Character entity.
 *
 * @author Miguel A. Vila
 */
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class Character extends AbstractEntity<CharacterId> {

    private static final long serialVersionUID = -6832992196197662831L;

    String name;
    String status;
    String species;
    String type;
    String gender;
    Location origin;
    Location location;
    String image;
    List<Episode> episodes;
    String url;
    Instant created;
    String popularity;

    @JsonCreator
    @Default
    @Builder
    public Character(@NonNull CharacterId id, @NonNull String name, @NonNull String status, @NonNull String species,
                     @NonNull String type, @NonNull String gender, Location origin, Location location,
                     @NonNull String image, List<Episode> episodes, @NonNull String url, @NonNull Instant created) {
        super(id);
        this.name = name;
        this.status = status;
        this.species = species;
        this.type = type;
        this.gender = gender;
        this.origin = origin;
        this.location = location;
        this.image = image;
        this.episodes = episodes;
        this.url = url;
        this.created = created;
        this.popularity = calculatePopularityIndex();
    }

    /**
     * Calculates the character's popularity index based on the number of episodes in which the character appears divided by the total number of episodes in the series.
     *
     * @return the string with the popularity index in the range from 0 to 100 and including two decimal places of precision
     */
    private String calculatePopularityIndex() {
        if (episodes != null) {
            return String.format("%.2f", (episodes.size() / (double) TOTAL_EPISODES) * 100);
        }
        return String.format("%.2f", 0d);
    }

}
