package ch.cpnv.pumpthevolumeup.Repositories;

import ch.cpnv.pumpthevolumeup.Entities.Music;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(MusicRepository repository){
        return args->{
            log.info("Preloading " + repository.save(new Music("Song 1", "Artist 1")));
            log.info("Preloading " + repository.save(new Music("Song 2", "Artist 2")));
            log.info("Preloading " + repository.save(new Music("Song 3", "Artist 3")));
            log.info("Preloading " + repository.save(new Music("Song 4", "Artist 4")));
        };
    }
}
