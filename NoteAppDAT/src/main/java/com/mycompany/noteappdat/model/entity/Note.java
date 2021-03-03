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

@NamedQuery(
        name = "Note.findAllInFolder",
        query = "SELECT n FROM Note n WHERE n.folder LIKE :name"
)

@Data
@Entity(name = "Note")
@NoArgsConstructor
@RequiredArgsConstructor
public class Note implements Serializable {

    /*
    QueryDSL

    List<Tuple> userTitleCounts = queryFactory.select(
          blogPost.title, blogPost.id.count().as(count))
          .from(blogPost)
          .groupBy(blogPost.title)
          .orderBy(count.desc())
          .fetch();
    List<Person> persons = query.from(person)
        .where(person.firstname.eq(firstname))
        .orderBy(person.surname.desc())
        .list(person);
     */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NonNull
    private String name;

    private String text;

    @ManyToOne
    //@Column(name = "test")
    @JoinColumn(name = "notes")
    private Folder folder;

    /*
    @OneToMany(mappedBy = "note", cascade = {CascadeType.ALL})
    @EqualsAndHashCode.Exclude
    private List<Event> events = new ArrayList<>();
    */
}
