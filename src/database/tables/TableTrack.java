package database.tables;


import database.annotations.Column;
import database.annotations.ForeignKey;
import database.annotations.PrimaryKey;
import database.annotations.Table;

/**
 * Created by diogojayme on 8/4/16.
 */
@Table(name = "track")
public class TableTrack {

    @PrimaryKey
    private long id;

    @Column(name = "name", size = 200)
    private String name;

    @ForeignKey(name = "album_id", references = TableAlbum.class, foreignKey = "id_alias") // ID have to be the same as the declared in the referenced table
    private int albumId;

}
