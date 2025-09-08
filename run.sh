#!/usr/bin/env bash
set -e
# start postgre
docker-compose up -d db

echo "Waiting for Postgres to be ready..."
# simple wait loop
until docker exec $(docker ps -qf "name=postgres") pg_isready -U notes_user >/dev/null 2>&1; do
  sleep 1
done || true

# NOTE: above tries to detect container; if it fails, just sleep few secs
sleep 3

# build backend
(cd backend && mvn -DskipTests package)

# run backend
java -jar backend/target/notes-backend-0.0.1-SNAPSHOT.jar &

# start frontend
(cd frontend && npm install && npm run dev)
