package ch.cpnv.pumpthevolumeup.Controllers;

import ch.cpnv.pumpthevolumeup.Entities.Music;
import ch.cpnv.pumpthevolumeup.Repositories.MusicRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/music")
public class MusicController {

    private final MusicRepository repository;

    MusicController(MusicRepository repository){
        this.repository = repository;
    }

    /* curl sample :
    curl -i localhost:8080/music
    */
    @GetMapping
    List<Music> all(){
        return repository.findAll();
    }

    /* curl sample :
    curl -i -X POST localhost:8080/music \
        -H "Content-type:application/json" \
        -d "{\"name\": \"Russel George\", \"artist\": \"gardener\"}"
    */
    @PostMapping
    Music newmusic(@RequestBody Music newmusic){
        return repository.save(newmusic);
    }

    /* curl sample :
    curl -i localhost:8080/music/1
    */
    @GetMapping("{id}")
    Music one(@PathVariable Long id){
        return repository.findById(id)
                .orElseThrow(() -> new MusicNotFoundException(id));
    }

    /* curl sample :
    curl -i -X PUT localhost:8080/music/2 \
        -H "Content-type:application/json" \
        -d "{\"name\": \"Samwise Bing\", \"artist\": \"peer-to-peer\"}"
     */
    @PutMapping("{id}")
    Music replacemusic(@RequestBody Music newMusic, @PathVariable Long id) {
        return repository.findById(id)
                .map(music -> {
                    music.setName(newMusic.getName());
                    music.setArtist(newMusic.getArtist());
                    return repository.save(music);
                })
                .orElseGet(() -> {
                    newMusic.setId(id);
                    return repository.save(newMusic);
                });
    }

    /* curl sample :
    curl -i -X DELETE localhost:8080/music/2
    */
    @DeleteMapping("{id}")
    void deletemusic(@PathVariable Long id){
        repository.deleteById(id);
    }
}
