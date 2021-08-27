package com.mycompany.noteappdat.view;

import com.mycompany.noteappdat.model.entity.Event;
import com.mycompany.noteappdat.model.service.SearchService;
import lombok.Data;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Data
@Named
@ViewScoped
public class SearchBean implements Serializable {

    @Inject
    private SearchService ss;

    private String searchString = "";

    public List<Event> getEventsByName() {
        return ss.findEventByName(searchString);
    }
}