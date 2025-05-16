package ch.cpnv.pumpthevolumeup.Controllers;

public class MusicNotFoundException extends RuntimeException{

    MusicNotFoundException(Long id){
        super("Could not find music " + id);
    }
}
