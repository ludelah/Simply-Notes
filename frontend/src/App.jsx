import { useEffect, useState } from "react";
import { api } from "./api";
import "./App.css"

export default function App() {
  const [notes, setNotes] = useState([]);
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [showArchived, setShowArchived] = useState(false);

  // Load notes on start
  useEffect(() => {
    if(showArchived){
      api.getArchive().then(setNotes);
    } else {
    api.getNotes().then(setNotes);
    }
  }, [showArchived]);

  const addNote = async () => {
    const newNote = await api.addNote({ title, content });
    setNotes([...notes, newNote]);
    setTitle("");
    setContent("");
  };

  const deleteNote = async (id) => {
    await api.deleteNote(id);
    setNotes(notes.filter((n) => n.id !== id));
  };

  const archiveNote = async (id) => {
    await api.archiveNote(id);
    setNotes(notes.filter((n) => n.id !== id));
  };



  return (
    <ul className="notesContainer">

      <div className="newNote">
        <input
          placeholder="Title"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          id="titleInput"
          name="titleInput"
        />
        <textarea
          value={content}
          onChange={(e) => setContent(e.target.value)}
          id="contentInput"
          name="contentInput"
        />
        <button
          className="newNote"
          onClick={addNote}
        >
          Add
        </button>
        
        <button
          id="archiveButton"
          onClick={() => setShowArchived((prev) => !prev)}
        >
          {showArchived ? "Show Active Notes" : "Show Archived Notes"}
        </button>

      </div>

        {notes.map((note) => (
          <li
            key={note.id}
            className={`${note.archived ? "archivedNote" : "noteContainer"}`}
          >
            <div class="note">
              
              <textarea 
              rows="1"
              className="noteTitle"
              disabled={note.archived}
              onChange={e => {
                const newNotes = [...notes];
                newNotes[idx] = {...note, title: e.target.value};
                setNotes(newNotes);
              }}
              onBlur={e => {
                api.updateNote(note.id, {...note, title: e.target.value, content: note.content })
              }}
              >{note.title}
              </textarea>
              
              <textarea 
              class="noteContent"
              disabled={note.archived}
              onChange={e => {
                const newNotes = [...notes];
                newNotes[idx] = {...note, content: e.target.value };
                setNotes(newNotes);
              }}
              onBlur={e => {
                api.updateNote(note.id, {...note, title: note.title, content: e.target.value });
              }}
              >{note.content}
              </textarea>
            </div>

            <button
              className="buttonNote"
              id="delete"
              onClick={() => deleteNote(note.id)}
            >
              X
            </button>

            <button
              className="buttonNote"
              id="archive"
              onClick={async () => {
                if (note.archived) {
                  await api.unarchiveNote(note.id);
                  setNotes(notes.filter((n) => n.id !== note.id));
                } else {
                  archiveNote(note.id);
                }
              }}
            >
              {note.archived ? "↑" : "↓"}
            </button>
            
          </li>
        ))}
      
    </ul>
  );
}
