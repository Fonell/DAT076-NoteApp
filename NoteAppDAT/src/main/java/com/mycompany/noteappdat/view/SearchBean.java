package com.mycompany.noteappdat.view;

import com.mycompany.noteappdat.model.entity.Event;
import com.mycompany.noteappdat.model.entity.Folder;
import com.mycompany.noteappdat.model.entity.Note;
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

    private String searchString;

    public List<Note> getNotesByName() {
        return ss.findNoteByName(searchString);
    }

    public List<Note> getNotesByContent() {
        return ss.findNoteByContent(searchString);
    }

    public List<Folder> getFoldersByName() {
        return ss.findFolderByName(searchString);
    }

    public List<Event> getEventsByName() {
        return ss.findEventByName(searchString);
    }
}