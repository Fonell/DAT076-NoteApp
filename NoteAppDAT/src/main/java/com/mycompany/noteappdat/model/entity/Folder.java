package com.mycompany.noteappdat.model.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

//@NamedQuery(name = "Folder.findFolderInFolderByName",
//        query = "SELECT f FROM Folder f WHERE f.name = :folderName AND f.parent.name = :parentName")

@NamedQuery(name = "Note.findAllFoldersWithoutFolder",
        query = "SELECT f FROM Folder f WHERE f.parent IS NULL")

@Data
@Entity(name="Folder")
@NoArgsConstructor
@RequiredArgsConstructor
public class Folder implements Serializable {
    
    @Id
    @NonNull
    private String name;
    
    @OneToMany(mappedBy = "parent")
    @EqualsAndHashCode.Exclude
    private List<Folder> childFolders;
    
    @OneToMany(mappedBy = "folder", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @EqualsAndHashCode.Exclude
    private List<Note> notes;
       
    @ManyToOne
    @JoinColumn(name = "childFolders")
    private Folder parent;
}
