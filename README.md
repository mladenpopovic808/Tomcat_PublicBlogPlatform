# Public Blog Platform

This project is a public blog platform that allows registered users to post articles, view, and comment on posts. The platform is open to all registered users, enabling them to contribute posts and interact with content through comments.

## Features

- **Login Form**: Users can log in using their username and password. Upon successful login, a token is issued that must be sent with each subsequent request (viewing posts, adding posts, commenting).
  
- **View Posts**: The main page displays a list of all existing posts. Users can click on a post to view its full content, including comments and a form for adding new comments.
  
- **Create New Post**: The "New Post" button opens a form for creating a new post. The author field is automatically populated with the logged-in user's username. Upon submission, the form disappears, and the new post appears in the list of posts.

- **Comment on Posts**: Users can view a specific post, which includes its title, publication date, author, content, and all existing comments. The comment author field is automatically filled with the logged-in user's username. After submitting a comment, the form fields are cleared, and the new comment is added to the list of displayed comments.

## Scenario

### Login Form

- A login form with fields for username and password is required for users to access existing posts and add new ones.
- Upon successful login, users receive a token for subsequent requests.

### Main Page (List of Posts)

- The main page displays all published posts.
- Each post is clickable, allowing users to view the complete content and its comments.
- The "New Post" button allows users to create a new post.

### Post Creation Form

- When the "New Post" button is clicked, a form appears for the user to enter the title and content of the post.
- The author field is automatically filled with the logged-in user's username.
- After submission, the form disappears, and the new post is added to the list of posts.

### Post Content View

- Clicking on a post shows the complete content, including:
  - Title
  - Publication date
  - Author
  - Content of the post
  - Comments
- The comment form automatically fills the author field with the logged-in user's username.
- After submitting a new comment, the form fields are cleared, and the new comment is added to the list of displayed comments.

## Technical Requirements

- The application is a **Single Page Application (SPA)**, meaning all interactions after the initial page load (`posts.html`) are handled via **AJAX** requests.
- On the backend, the RESTful API is implemented using the **JAX-RS specification**.
- Posts and their comments are stored in a **relational database**.



