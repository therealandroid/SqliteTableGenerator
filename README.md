# Generate Create Table SQL scripts for Android-Sqlite based applications

##Description
This library is used to help the creation of SQL scripts Java-based objects that represent the tables. With the use of annotations on the attributes is easier to organize and versioning tables.

##Table Example

  ```java
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
  ```
  
##Usage  

 ```java
 String CREATE_TABLE_ALBUM = TableCreator.createFrom(TableAlbum.class);
 ```
###Output

```java
CREATE TABLE album ( offline INTEGER , name TEXT , state INTEGER , id_alias INTEGER PRIMARY KEY ,
FOREIGN KEY ( artist_id ) REFERENCES artist ( id ) )
```

 
 
 
