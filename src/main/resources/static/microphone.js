var SpeechRecognition = SpeechRecognition || webkitSpeechRecognition;
var recognition = new SpeechRecognition();
var isListening = false;
var mediaRecorder;
var recordedBlobs = [];

recognition.onstart = function() {
    console.log('Voice recognition started. Try speaking into the microphone.');
    isListening = true;
    document.getElementById('speakButton').classList.add('listening');
    startEchoEffect();
}

recognition.onspeechend = function() {
    recognition.stop();
}

recognition.onresult = function(event) {
    var transcript = event.results[0][0].transcript;
    alert('You said: ' + transcript);

    fetch('/tweet', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: new URLSearchParams({
            'transcript': transcript
        })
    })
        .catch(error => console.error('Error:', error));
}

recognition.onend = function() {
    isListening = false;
    document.getElementById('speakButton').classList.remove('listening');
    stopEchoEffect();
}

document.getElementById('speakButton').addEventListener('click', function() {
    if (isListening) {
        recognition.stop();
        var blob = new Blob(recordedBlobs, {type: 'audio/wav'});
        var formData = new FormData();
        formData.append('file', blob);
        fetch('/audio', {
            method: 'POST',
            body: formData
        })
    } else {
        recognition.start();
    }
});

function startEchoEffect() {
    document.getElementById('speakButton').style.animation = 'echo 1s infinite';
}

function stopEchoEffect() {
    document.getElementById('speakButton').style.animation = '';
}