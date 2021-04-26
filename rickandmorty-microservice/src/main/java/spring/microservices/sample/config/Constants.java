package spring.microservices.sample.config;

/**
 * Constants.
 *
 * @author Miguel A. Vila
 */
public interface Constants {

    Integer MAX_AMOUNT = 5;
    Long MIN_CHARACTER_ID = 1L;
    Long MAX_CHARACTER_ID = 671L;
    Integer TOTAL_EPISODES = 41;
    String API_BASE_URL = "https://rickandmortyapi.com/api";
    String API_CHARACTER_ENDPOINT = API_BASE_URL + "/character";

}
