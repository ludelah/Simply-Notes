#!/bin/bash
set -e

echo "Starting database..."
sudo docker-compose up -d db  # adjust service name if needed

echo "Starting backend..."
cd backend
./mvnw spring-boot:run &
BACK_PID=$!
cd ..

echo "Starting frontend..."
cd frontend
npm install
npm run dev &
FRONT_PID=$!
cd ..

# Wait for both
wait $BACK_PID $FRONT_PID

