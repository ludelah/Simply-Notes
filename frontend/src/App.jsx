import { useEffect, useState } from "react";
import { api } from "./api";
import "./App.css"

export default function App() {
  const [notes, setNotes] = useState([]);
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");

  // Load notes on start
  useEffect(() => {
    api.getNotes().then(setNotes);
  }, []);

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

  return (
    <div className="p-4 max-w-xl mx-auto grid notes">

      <div className="mb-4">
        <input
          className="border p-2 mr-2"
          placeholder="Title"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
        />
        <input
          className="border p-2 mr-2"
          placeholder="Content"
          value={content}
          onChange={(e) => setContent(e.target.value)}
        />
        <button
          className="bg-blue-500 text-white px-4 py-2 newNote"
          onClick={addNote}
        >
          Add
        </button>
      </div>

      <ul>
        {notes.map((note) => (
          <li
            key={note.id}
            className="border-b py-2 justify-between items-center"
          >
            <div>
              <strong>{note.title}</strong>: {note.content}
            </div>
            <button
              className="bg-red-500 text-white px-2 py-1 deleteNote"
              onClick={() => deleteNote(note.id)}
            >
              X
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
}
