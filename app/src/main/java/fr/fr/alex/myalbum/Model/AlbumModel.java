package fr.fr.alex.myalbum.Model;

public class AlbumModel {
    private String album_titre;
    private String album_image_url;

    public String getAlbum_titre() { return album_titre; }
    public String getAlbum_image_url() { return album_image_url; }


    public AlbumModel(String album_titre, String album_image_url){
        this.album_titre = album_titre;
        this.album_image_url = album_image_url;
    }
}
