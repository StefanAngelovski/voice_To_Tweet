package voiceToTweet.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import com.theokanning.openai.audio.CreateTranscriptionRequest;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import twitter4j.auth.AccessToken;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Controller
public class PostController {
    private String apiKey = System.getenv("OPENAI_API_KEY");
    private String text;

    @PostMapping("/audio")
    public String audio(@RequestParam("file") MultipartFile file, HttpSession session) {
        try {
            // Save the file locally
            Path tempFile = Files.createTempFile("audio-", ".wav");
            file.transferTo(tempFile.toFile());

            // Transcribe the audio file
            OpenAiService service = new OpenAiService(apiKey);
            CreateTranscriptionRequest request = new CreateTranscriptionRequest();
            request.setModel("whisper-1");
            text = service.createTranscription(request, tempFile.toString()).getText();

            return text;
        } catch (IOException e) {
            e.printStackTrace();
            // Print the transcribed text to the console
            System.out.println(text);
            return "Failed to process audio file: " + e.getMessage();
        }
    }

    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        AccessToken accessToken = (AccessToken) session.getAttribute("accessToken");
        if (accessToken != null) {
            model.addAttribute("user", accessToken.getScreenName());
        }
        return "index";
    }
}