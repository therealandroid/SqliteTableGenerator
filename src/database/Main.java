package database;


import br.com.augustoccesar.querybuilder.configurations.Configuration;
import br.com.augustoccesar.querybuilder.configurations.Database;
import database.processor.TableCreator;
import database.tables.TableAlbum;
import database.tables.TableArtist;
import database.tables.TableTrack;

/**
 * Created by diogojayme on 8/4/16.
 */
public class Main {

    public static void main(String[] args){
        Configuration.setDatabase(Database.SQLITE);

        String CREATE_TABLE_ALBUM = TableCreator.createFrom(TableAlbum.class);
        String CREATE_TABLE_ARTIST = TableCreator.createFrom(TableArtist.class);
        String CREATE_TABLE_TRACK = TableCreator.createFrom(TableTrack.class);

        System.out.println(CREATE_TABLE_ALBUM);
        System.out.println(CREATE_TABLE_ARTIST);
        System.out.println(CREATE_TABLE_TRACK);
    }


}
