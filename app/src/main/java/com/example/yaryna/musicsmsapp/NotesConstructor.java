package com.example.yaryna.musicsmsapp;

import java.util.ArrayList;

/**
 * Created by Yaryna on 22/11/2015.
 */
public class NotesConstructor {
        ArrayList<NoteInstance> notes;

        public NotesConstructor(ArrayList<String> notesAsStrings){
            notes = constructArrayOfNotes(notesAsStrings);
        }

        private ArrayList<NoteInstance> constructArrayOfNotes( ArrayList<String> notesAsString){
            ArrayList<NoteInstance> notes = new ArrayList<>();
            for (String s : notesAsString) {
                NoteInstance note = new NoteInstance(s);
                notes.add(note);
            }
            return notes;
        }

        public ArrayList<NoteInstance> getNotes(){return this.notes;}
}
