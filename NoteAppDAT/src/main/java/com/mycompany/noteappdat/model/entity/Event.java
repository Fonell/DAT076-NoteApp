package com.mycompany.noteappdat.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@NamedQuery(
        name = "Event.findByName",
        query = "SELECT e FROM Event e WHERE e.name LIKE :name"
)

@NamedQuery(
        name = "Event.findByDate",
        query = "SELECT e FROM Event e WHERE e.name LIKE :name"
)

@Data
@Entity(name = "Event")
@NoArgsConstructor
@RequiredArgsConstructor
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @NonNull
    private String name;

    @NonNull
    private Calendar eventDate;

    @ManyToOne
    private Note note;
}
