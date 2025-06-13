import React, { useEffect, useState } from "react";
import { Story } from "./types/Story";

const API_URL = "http://localhost:8080/api/stories";

function App() {
	const [stories, setStories] = useState<Story[]>([]);
	const [title, setTitle] = useState("");
	const [content, setContent] = useState("");
	const [department, setDepartment] = useState("");

	// Fetch stories on load
	useEffect(() => {
		fetch(API_URL)
			.then((res) => {
				if (!res.ok) throw new Error("Failed to fetch stories");
				return res.json();
			})
			.then((data) => setStories(data))
			.catch((err) => alert("Error fetching stories: " + err.message));
	}, []);

	const handleSubmit = (e: React.FormEvent) => {
		e.preventDefault();
		const newStory: Omit<Story, "id"> = { title, content, department };
		fetch(API_URL, {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
			},
			body: JSON.stringify(newStory),
		})
			.then((res) => {
				if (!res.ok) throw new Error("Failed to add story");
				return res.json();
			})
			.then((addedStory) => setStories([...stories, addedStory]))
			.catch((err) => alert("Error adding story: " + err.message));

		setTitle("");
		setContent("");
		setDepartment("");
	};

	const handleDelete = (id?: number) => {
		if (!id) return;
		fetch(`${API_URL}/${id}`, { method: "DELETE" })
			.then((res) => {
				if (!res.ok) throw new Error("Failed to delete story");
				setStories(stories.filter((story) => story.id !== id));
			})
			.catch((err) => alert("Error deleting story: " + err.message));
	};

	return (
		<div style={{ maxWidth: 600, margin: "auto", padding: 24 }}>
			<h1>VIA Stories</h1>
			<form onSubmit={handleSubmit} style={{ marginBottom: 24 }}>
				<div>
					<input
						required
						placeholder="Title"
						value={title}
						onChange={(e) => setTitle(e.target.value)}
						style={{ width: "100%", marginBottom: 8 }}
					/>
				</div>
				<div>
					<textarea
						required
						placeholder="Content"
						value={content}
						onChange={(e) => setContent(e.target.value)}
						style={{ width: "100%", marginBottom: 8 }}
					/>
				</div>
				<div>
					<input
						required
						placeholder="Department"
						value={department}
						onChange={(e) => setDepartment(e.target.value)}
						style={{ width: "100%", marginBottom: 8 }}
					/>
				</div>
				<button type="submit">Add Story</button>
			</form>

			<h2>All Stories</h2>
			<ul>
				{stories.map((story) => (
					<li key={story.id}>
						<h3>
							{story.title} <small>({story.department})</small>
						</h3>
						<p>{story.content}</p>
						<button onClick={() => handleDelete(story.id)}>Delete</button>
					</li>
				))}
			</ul>
		</div>
	);
}

export default App;
