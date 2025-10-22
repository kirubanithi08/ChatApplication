Description: Private Chat Application Using Spring Boot

This project is a real-time private messaging application built using Spring Boot. It enables secure one-to-one communication between users, similar to features found in modern messaging platforms.

✅ Key Features

User Authentication & Authorization

Users can register, log in, and create unique profiles.

Secure authentication using Spring Security and JWT (JSON Web Token).

Real-Time Messaging

Uses WebSocket and STOMP protocols to enable instant message delivery.

Users can send and receive messages in real time without refreshing the page.

Private 1-on-1 Chat

Messages are sent directly between two users.

Chat rooms or unique channels are dynamically created for each user pair.

Message Storage

All messages are saved in a database (MySQL).

Users can view past conversations from chat history.

Online/Offline Status

Displays real-time user presence (online).

Notifications

Push notifications for new messages when users are offline.

🛠 Tech Stack

Backend	Spring Boot, Spring Web
Real-time Comm.	WebSocket, STOMP
Security	Spring Security, JWT
Database	MySQL / PostgreSQL / MongoDB
Frontend (optional)	React / Angular / Vue or Thymeleaf
Build Tool	Maven or Gradle
