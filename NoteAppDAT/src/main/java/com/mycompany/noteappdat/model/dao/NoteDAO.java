package com.mycompany.noteappdat.model.dao;

import com.mycompany.noteappdat.model.dao.key.NotePK;
import com.mycompany.noteappdat.model.entity.Note;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Getter;

@Stateless
public class NoteDAO extends AbstractDAO<Note> {
    @Getter @PersistenceContext(unitName = "db")
    private EntityManager entityManager;

    public NoteDAO() {
        super(Note.class);
    }
    
    public Note findNoteMatchingPK(NotePK pk) {
        return entityManager.find(Note.class, pk);
    }
    
   

    /*
    public Note findNoteMatchingCIDAndTitle(Client owner, String title) {
        NotePK pk = new NotePK(owner, title);
        return entityManager.find(Note.class, pk);
    }
*/
}
