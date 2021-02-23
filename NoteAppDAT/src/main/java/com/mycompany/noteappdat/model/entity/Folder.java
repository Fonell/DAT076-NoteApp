package com.mycompany.noteappdat.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NamedQuery(name="Folder.findByName",
    query="SELECT f FROM Folder f WHERE f.name LIKE :fName")

@Data
@Entity(name="Folder")
@NoArgsConstructor
public class Folder implements Serializable {
    @Id
    @NonNull
    //@Column(name="folder_name")
    private String name;
    
    @OneToMany(mappedBy = "folder")
    @EqualsAndHashCode.Exclude        
    private List<Note> notes;

    public Folder(String folderName) {
        this.name = folderName;
    }
}
