package com.mycompany.noteappdat.model.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NamedQuery(
        name = "Folder.findByName",
        query = "SELECT f FROM Folder f WHERE f.name LIKE :name"
)

@NamedQuery(
        name = "Folder.findAllInRoot",
        query = "SELECT f FROM Folder f WHERE f.parent IS NULL"
)

@Data
@Entity(name = "Folder")
@NoArgsConstructor
@RequiredArgsConstructor
public class Folder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

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
