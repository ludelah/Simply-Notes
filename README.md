# Notes.io - Simple web app for taking notes
## Technologies used
* Frontend:
	1. React 
	1. Vite v7.1.4 
	1. NodeJS v22.19.0

* Backend:
	1. Java 21
	1. Spring Boot v3.5.5
	1. PostgreSQL
	1. Docker-compose v1.29.2

## How to run
- Install node 22.19.0 + npm 10.9.3 to run the frontend
- Install Java Development Kit v21 to build and run the backend
- Install the docker-compose tool to build the database
- Start run.sh

It will setup the DB with docker and extract the credentials from the .env file in the repo (should not be used in prod)
Then, it will build and install all dependencies required for the backend with the maven wrapper tool inside the /backend folder.
After that, the script will install all dependencies required for the frontend with npm install and then run it.
