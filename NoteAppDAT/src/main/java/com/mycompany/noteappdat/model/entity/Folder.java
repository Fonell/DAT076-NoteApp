package com.mycompany.noteappdat.model.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NamedQuery(name = "Folder.findFolderInFolderByName",
        query = "SELECT f FROM Folder f WHERE f.name = :folderName AND f.parent.name = :parentName")

@Data
@Entity(name="Folder")
@NoArgsConstructor
@RequiredArgsConstructor
public class Folder implements Serializable {
    @Id
    @NonNull
    private String name;
    
    @OneToMany(mappedBy = "folder")
    private List<Note> notes;
    
    @ManyToOne
    @JoinColumn(name = "children")
    private Folder parent;
    
    @OneToMany(mappedBy = "parent")
    private List<Folder> children;
}
