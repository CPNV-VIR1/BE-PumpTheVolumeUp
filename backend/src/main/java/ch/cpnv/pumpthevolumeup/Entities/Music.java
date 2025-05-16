package ch.cpnv.pumpthevolumeup.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Music {

    private @Id
    @GeneratedValue Long id;
    private String name;
    private String artist;

    public Music(){}

    public Music(String name, String artist){
        this.setName(name);
        this.setArtist(artist);
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getArtist(){
        return this.artist;
    }

    public void setArtist(String artist){
        this.artist = artist;
    }

    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(!(o instanceof Music employee))
            return false;
        return  Objects.equals(this.id, employee.id) &&
                Objects.equals(this.name, employee.name) &&
                Objects.equals(this.artist, employee.artist);
    }

    @Override
    public int hashCode(){
        return Objects.hash(
                this.id,
                this.name,
                this.artist);
    }

    @Override
    public String toString(){
        return "Music{" + "id=" +
                this.getId() + ", name='" +
                this.getName() + '\'' + ", role='" +
                this.getArtist() + '\'' +
                '}';
    }
}
