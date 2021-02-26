package com.mycompany.noteappdat.model.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


//@NamedQuery(name="Note.findNoteByNameAndFolder",
//    query="SELECT n FROM Note n WHERE n.name = :noteName AND n.folder.name = :folderName")

@NamedQuery(name="Note.findAllNotesWithoutFolder",
    query="SELECT n FROM Note n WHERE n.folder IS NULL")

@Data
@Entity(name ="Note")
@NoArgsConstructor
@RequiredArgsConstructor
//@AddIndex
public class Note implements Serializable {
    
    @Id
    @NonNull
    private String name;
    
    private String text;
    
    @ManyToOne
    @JoinColumn(name="notes")
    private Folder folder;
    
    //@OneToMany(mappedBy = "note")
    //@EqualsAndHashCode.Exclude
    //private List<Event> events = new ArrayList<>();
}
