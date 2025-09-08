#!/bin/bash
# Run backend
cd backend
./mvnw spring-boot:run &
BACK_PID=$!

# Run frontend
cd ../frontend
npm install
npm run dev &

wait $BACK_PID
