package com.mycompany.noteappdat.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@NoArgsConstructor
public class Folder implements Serializable {
    @Id
    @NonNull
    private String name;
    
    @OneToMany(mappedBy = "folder")
    private List<Note> notes = new ArrayList<>();

    public Folder(String folderName) {
        this.name = folderName;
    }
}
