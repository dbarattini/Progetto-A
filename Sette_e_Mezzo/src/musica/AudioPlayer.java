package musica;


import eccezioni.CaricamentoCanzoneException;
import eccezioni.CanzoneNonTrovataException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.*;


public class AudioPlayer {
    
    private Map<String, Clip> musica;
    private static boolean muto = false;
	
    public AudioPlayer() {
	musica = new HashMap<>();
    }
	
    public void riproduci(String s) throws CanzoneNonTrovataException {
        if(!muto) {
            try{
                musica.get(s).start();
            } catch(NullPointerException e){
                throw new CanzoneNonTrovataException(s);
            }
        }
    }
    
    public void riavvia(String s) throws CanzoneNonTrovataException{
        if(!muto) {
            try{
                riavvolgi(s);
                riproduci(s);
            } catch(NullPointerException e){
                throw new CanzoneNonTrovataException(s);
            }
        }
    }
    
    private void riavvolgi(String s) throws CanzoneNonTrovataException{
        if(!muto) {
            try{
                musica.get(s).setFramePosition(0);
            } catch(NullPointerException e){
                throw new CanzoneNonTrovataException(s);
            }
        }
    }
    
    public void riproduci_in_loop(String s) throws CanzoneNonTrovataException {
        if(!muto) {
            try{
                musica.get(s).loop(Clip.LOOP_CONTINUOUSLY);
            } catch(NullPointerException e){
                throw new CanzoneNonTrovataException(s);
            }
        }
    }
	
    public void ferma(String s) throws CanzoneNonTrovataException {
        try{
            musica.get(s).stop();
        } catch(NullPointerException e){
            throw new CanzoneNonTrovataException(s);
        }
    }
	
    public void chiudi(String s) throws CanzoneNonTrovataException {
        try{
            musica.get(s).close();
        } catch(NullPointerException e){
            throw new CanzoneNonTrovataException(s);
        }
    }
    
    public void carica(String canzone, String nome_canzone) throws CaricamentoCanzoneException {
        try {
            BufferedInputStream stream = new BufferedInputStream(getClass().getResourceAsStream(canzone));
            AudioInputStream ais = AudioSystem.getAudioInputStream(stream);
            AudioFormat formato_base = ais.getFormat();
            AudioFormat formato_decodifica = new AudioFormat(
                                            AudioFormat.Encoding.PCM_SIGNED,
                                            formato_base.getSampleRate(),
                                            16, formato_base.getChannels(),
                                            formato_base.getChannels() * 2,
                                            formato_base.getSampleRate(),
                                            false);
            AudioInputStream ais_finale = AudioSystem.getAudioInputStream(formato_decodifica, ais);
            Clip clip = AudioSystem.getClip();
            clip.open(ais_finale);
            musica.put(nome_canzone, clip);
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            throw new CaricamentoCanzoneException(canzone);
	}
    }
}