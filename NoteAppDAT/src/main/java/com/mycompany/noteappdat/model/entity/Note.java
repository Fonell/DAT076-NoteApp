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


@NamedQuery(name="Note.findNoteByNameAndFolder",
    query="SELECT n FROM Note n WHERE n.name = :nName AND n.folder.name = :fName")

@NamedQuery(name="Note.addFolderToNote",
    query="SELECT n FROM Note n WHERE n.name = :nName AND n.folder.name = :fName")

@Data
@Entity(name ="Note")
@NoArgsConstructor
@RequiredArgsConstructor
public class Note implements Serializable {
    
    @Id
    @NonNull
    //@Column(name="note_name")
    private String name;
    
    //@Column(name="note_text")
    private String text;
    
    @ManyToOne
    @JoinColumn(name="notes")
    private Folder folder;
    
    //@OneToMany(mappedBy = "note")
    //@Column(name="NOTE_EVENT")
    //private List<Event> events = new ArrayList<>();
}
