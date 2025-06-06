package ch.cpnv.pumpthevolumeup.Controllers;

import ch.cpnv.pumpthevolumeup.Entities.Music;
import ch.cpnv.pumpthevolumeup.Repositories.MusicRepository;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping()
    Music newmusic(
        @RequestParam("name") String name,
        @RequestParam("artist") String artist,
        @RequestParam("file") MultipartFile file
    ){
        Music music = new Music();
        music.setName(name);
        music.setArtist(artist);
        music.setPath(file);

        return repository.save(music);
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
    Music replacemusic(
        @RequestParam("name") String name,
        @RequestParam("artist") String artist,
        @RequestParam("file") MultipartFile file, @PathVariable Long id
    ) {
        return repository.findById(id)
                .map(music -> {
                    music.setName(name);
                    music.setArtist(artist);
                    music.setPath(file);
                    return repository.save(music);
                })
                .orElseGet(() -> {
                    Music newMusic = new Music();
                    newMusic.setId(id);
                    newMusic.setName(name);
                    newMusic.setArtist(artist);
                    newMusic.setPath(file);
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
