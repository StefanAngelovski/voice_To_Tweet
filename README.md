# Voice To Tweet

![VoiceToTweet](https://github.com/user-attachments/assets/e11859d7-4522-49a1-add2-38beb844672d)

## Project Overview

**Voice To Tweet** is a web-based application that allows users to transcribe their spoken words into text using OpenAI's Whisper Speech-to-Text model. The transcribed text is then posted as a tweet on X (formerly Twitter) using the Twitter4J API.

## Technologies Used

- **OpenAI Whisper**: Used for converting speech to text.
- **Twitter4J**: An API that enables posting tweets to X (Twitter).
- **JavaScript**: Handles front-end logic, including converting microphone input into a `.wav` file.
- **Spring Boot**: The framework used for back-end development.
- **Thymeleaf**: A template engine used in the back-end to render HTML pages.

## How It Works

1. **Speech Input**: The user records their speech through the web interface.
2. **File Conversion**: The microphone input is captured as a `.wav` file using JavaScript.
3. **Transcription**: The `.wav` file is sent to OpenAI's Whisper for transcription into text.
4. **Tweet Posting**: The transcribed text is then sent to the back-end, where it is posted to X (Twitter) via the Twitter4J API.

## Backend

- Built with **Spring Boot**, providing the API endpoints and server-side logic.
- Uses **Thymeleaf** for rendering the front-end components.

## Frontend

- **JavaScript** is used to capture audio and handle communication with the back-end.

## Purpose

This project was developed as part of the course **Web Programming** at the **Faculty of Computer Science and Engineering**, Skopje.

---

