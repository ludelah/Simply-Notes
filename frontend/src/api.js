const BASE = "http://localhost:8080/api";

let token = localStorage.getItem("userToken");
if (!token) {
  // site must be in HTTPS protocol for this to work
  token = crypto.randomUUID();
  localStorage.setItem("userToken", token);
}

async function request(path, opts = {}) {
  const res = await fetch(`${BASE}${path}`, {
    headers: {
      "Content-Type": "application/json",
      "X-User": token
    },
    ...opts,
  });
  return res.json();
}

export const api = {
  getNotes: () => request("/notes/active"),
  getArchive: () => request("/notes/archived"),
  addNote: (note) =>
    request("/notes/active", {
      method: "POST",
      body: JSON.stringify(note),
    }),
  deleteNote: (id) =>
    request(`/notes/${id}`, { method: "DELETE" }),
  archiveNote: (id) =>
    request(`/notes/${id}/archive`, { method: "POST" }),
  unarchiveNote: (id) =>
    request(`/notes/${id}/unarchive`, { method: "POST"} ),
  updateNote: (id, note) =>
    request(`/notes/${id}`, {
      method: "PUT",
      body: JSON.stringify(note)
    })
};
