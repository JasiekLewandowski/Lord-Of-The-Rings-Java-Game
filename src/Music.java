import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music {
    int trackIndex;
    String audioPath;
    File audioFile;
    Clip audioClip;

    public Music(int trackIndex) {
        try {
            audioPath = "Music/" + trackIndex + ".wav";
            audioFile = new File(audioPath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioStream);

        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            System.out.println("File not found");
        }
        audioClip.start();
    }

    public void stopMusic() {
        audioClip.stop();
    }
}
