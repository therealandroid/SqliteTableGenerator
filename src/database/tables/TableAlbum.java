package database.tables;


import database.annotations.Column;
import database.annotations.ForeignKey;
import database.annotations.PrimaryKey;
import database.annotations.Table;

/**
 * Created by diogojayme on 8/4/16.
 */

@Table(name = "album")
public class TableAlbum {

    @PrimaryKey(name = "id_alias")
    private long id;

    @Column(name = "name", size = 200)
    private String name;

    @Column(name = "offline")
    private boolean offline;

    @Column(name = "state", size = 1)
    private int state;

    @ForeignKey(name = "artist_id", references = TableArtist.class)
    private int artistId;
}
