// Wrapper for my api
const BASE = "http://localhost:8080/api";

// Ensure the token exists
let token = localStorage.getItem("userToken");
if (!token) {
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
  addNote: (note) =>
    request("/notes/active", {
      method: "POST",
      body: JSON.stringify(note),
    }),
  deleteNote: (id) =>
    request(`/notes/${id}`, { method: "DELETE" }),
};
