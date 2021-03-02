package com.mycompany.noteappdat.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NamedQuery(
        name = "Note.findByName",
        query = "SELECT n FROM Note n WHERE n.name LIKE :name"
)

@NamedQuery(
        name = "Note.findAllInRoot",
        query = "SELECT n FROM Note n WHERE n.folder IS NULL"
)

@Data
@Entity(name = "Note")
@NoArgsConstructor
@RequiredArgsConstructor
public class Note implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @NonNull
    private String name;

    private String text;

    @ManyToOne
    @JoinColumn(name = "notes")
    private Folder folder;

    /*
    @OneToMany(mappedBy = "note")
    @EqualsAndHashCode.Exclude
    private List<Event> events = new ArrayList<>();
     */
}
