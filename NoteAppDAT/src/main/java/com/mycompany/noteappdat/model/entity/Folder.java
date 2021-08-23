package com.mycompany.noteappdat.model.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

import lombok.*;

@NamedQuery(
        name = "Folder.findByName",
        query = "SELECT f FROM Folder f WHERE lower(f.name) LIKE :name"
)

@NamedQuery(
        name = "Folder.find",
        query = "SELECT f FROM Folder f WHERE f.parent IS NULL"
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NonNull
    private String name;

    @OneToMany(mappedBy = "parent")
    @EqualsAndHashCode.Exclude
    private List<Folder> childFolders;

    @ManyToOne
    @JoinColumn(name = "childFolders")
    private Folder parent;

    @OneToMany(mappedBy = "folder")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Note> notes; //Is there a good way to change this name only in the database in the note-table? Current naming is confusing. (NOTE has a column named NOTES referencing a folder)


}
