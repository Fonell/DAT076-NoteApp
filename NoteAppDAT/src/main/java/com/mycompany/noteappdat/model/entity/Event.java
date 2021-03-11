package com.mycompany.noteappdat.model.entity;

import com.mycompany.noteappdat.model.service.Almanac.DateInterface;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

@NamedQuery(
        name = "Event.findByName",
        query = "SELECT e FROM Event e WHERE e.name LIKE :name"
)

@NamedQuery(
        name = "Event.getEventsInPeriod",
        query = "SELECT e FROM Event e WHERE e.eventDate BETWEEN :from AND :to"
)


@Data
@Entity(name = "Event")
@NoArgsConstructor
@RequiredArgsConstructor
public class Event implements Serializable, DateInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NonNull
    private String name;

    @NonNull
    @Temporal(TemporalType.TIMESTAMP)
    private GregorianCalendar eventDate;

    @ManyToOne
    private Note note;

    @Override
    public int getYear() {
        return eventDate.get(Calendar.YEAR);
    }

    @Override
    public int getMonth() {
        return eventDate.get(Calendar.MONTH);
    }

    @Override
    public int getWeek() {
        return eventDate.get(Calendar.WEEK_OF_MONTH);
    }

    @Override
    public int getDay() {
        return eventDate.get(Calendar.DAY_OF_MONTH);
    }
    
    @Override
    public Calendar getDate() {
        return getEventDate();
    }
   
    @Override
    public int compareTo(DateInterface o) {
        return this.getDate().compareTo(o.getDate());
    }
}
