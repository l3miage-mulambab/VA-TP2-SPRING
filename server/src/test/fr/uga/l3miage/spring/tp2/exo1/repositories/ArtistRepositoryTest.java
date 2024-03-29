package fr.uga.l3miage.spring.tp2.exo1.repositories;

import fr.uga.l3miage.exo1.enums.GenreMusical;
import fr.uga.l3miage.spring.tp2.exo1.models.ArtistEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")
public class ArtistRepositoryTest {
    @Autowired
    private ArtistRepository artistRepository;
    @Test
    void testRequestCountByGenreMusicalEquals(){
        ArtistEntity artist = ArtistEntity
                .builder()
                .name("Blaste")
                .biography("The Goat of Modern Rap")
                .genreMusical(GenreMusical.RAP)
                .build();
        ArtistEntity artist2 = ArtistEntity
                .builder()
                .name("Hamza")
                .biography("Un peu éclaté mais ça va")
                .genreMusical(GenreMusical.RANDB)
                .build();

        artistRepository.save(artist);
        artistRepository.save(artist2);

        // when
        int nbGenres = artistRepository.countByGenreMusicalEquals(GenreMusical.SLAM);

        // then
        assertThat(nbGenres == 0);
    }
}
