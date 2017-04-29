package musica;

import java.io.BufferedInputStream;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.*;

public class AudioPlayer {
    
    private Clip clip;
    private Map<String, Clip> musica;
	
    public AudioPlayer() {
	musica = new HashMap<>();
        carica("LoungeBeat.wav", "soundTrack");
    }
	
    public void play(String s) {
        if(musica.get(s) == null)
            return;
        stop(s);
        musica.get(s).setFramePosition(0);
        musica.get(s).start();
    }
    
    public void loop(String s) {
        if(musica.get(s) == null)
            return;
        stop(s);
        musica.get(s).setFramePosition(0);
        musica.get(s).loop(Clip.LOOP_CONTINUOUSLY);
    }
	
    public void stop(String s) {
        if(musica.get(s).isRunning())
            musica.get(s).stop();
    }
	
    public void chiudi(String s) {
        stop(s);
        musica.get(s).close();
    }
    
    public void carica(String fileSong, String nomeSong) {
        try {
            BufferedInputStream stream = new BufferedInputStream(getClass().getResourceAsStream(fileSong));
            AudioInputStream ais = AudioSystem.getAudioInputStream(stream);
            AudioFormat formatoBase = ais.getFormat();
            AudioFormat formatoDecodifica = new AudioFormat(
                                            AudioFormat.Encoding.PCM_SIGNED,
                                            formatoBase.getSampleRate(),
                                            16, formatoBase.getChannels(),
                                            formatoBase.getChannels() * 2,
                                            formatoBase.getSampleRate(),
                                            false);
            AudioInputStream aisFinale = AudioSystem.getAudioInputStream(formatoDecodifica, ais);
            clip = AudioSystem.getClip();
            clip.open(aisFinale);
            musica.put(nomeSong, clip);
        } catch (Exception e) {
            e.printStackTrace();
	}
    }
}