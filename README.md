# Voice To Tweet

![VoiceToTweet](https://github.com/user-attachments/assets/e11859d7-4522-49a1-add2-38beb844672d)

**Voice To Tweet** is a web-based application that transcribes spoken words into text using OpenAI's Whisper Speech-to-Text model. The transcribed text is then posted as a tweet on X (formerly Twitter) via the Twitter4J API.

### Technologies

- **OpenAI Whisper**: Converts speech to text.
- **Twitter4J**: Posts tweets to X (Twitter).
- **JavaScript**: Captures microphone input as a `.wav` file and handles front-end logic.
- **Spring Boot**: Powers the back-end server.
- **Thymeleaf**: Template engine for rendering HTML.

### How It Works

1. The user records speech via the web interface.
2. JavaScript converts the microphone input into a `.wav` file.
3. The `.wav` file is sent to Whisper, which transcribes the speech into text.
4. The transcribed text is posted to X using the Twitter4J API.

The back-end is built using **Spring Boot**, with **Thymeleaf** for rendering the front-end. **JavaScript** handles the front-end logic, particularly converting the audio and sending it to the back-end.

This project was created for the **Web Programming** course at the **Faculty of Computer Science and Engineering**, Skopje.

---
