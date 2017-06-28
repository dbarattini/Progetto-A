package dominio.musica;

import dominio.eccezioni.CaricamentoCanzoneException;
import dominio.eccezioni.CanzoneNonTrovataException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.*;

public class AudioPlayer {

    private Map<String, Clip> musica;
    public static boolean muto = false;

    public AudioPlayer() {
        musica = new HashMap<>();
    }

    public void riproduci(String nome_canzone) throws CanzoneNonTrovataException {
        if (!muto) {
            try {
                musica.get(nome_canzone).start();
            } catch (NullPointerException e) {
                throw new CanzoneNonTrovataException(nome_canzone);
            }
        }
    }

    public void riavvia(String nome_canzone) throws CanzoneNonTrovataException {
        if (!muto) {
            try {
                riavvolgi(nome_canzone);
                riproduci(nome_canzone);
            } catch (NullPointerException e) {
                throw new CanzoneNonTrovataException(nome_canzone);
            }
        }
    }

    public void riavvolgi(String nome_canzone) throws CanzoneNonTrovataException {
        if (!muto) {
            try {
                musica.get(nome_canzone).setFramePosition(0);
            } catch (NullPointerException e) {
                throw new CanzoneNonTrovataException(nome_canzone);
            }
        }
    }

    public void riproduciInLoop(String nome_canzone) throws CanzoneNonTrovataException {
        if (!muto) {
            try {
                musica.get(nome_canzone).loop(Clip.LOOP_CONTINUOUSLY);
            } catch (NullPointerException e) {
                throw new CanzoneNonTrovataException(nome_canzone);
            }
        }
    }

    public void riavviaInLoop(String nome_canzone) throws CanzoneNonTrovataException {
        if (!muto) {
            try {
                riavvolgi(nome_canzone);
                riproduciInLoop(nome_canzone);
            } catch (NullPointerException e) {
                throw new CanzoneNonTrovataException(nome_canzone);
            }
        }
    }

    public void ferma(String nome_canzone) throws CanzoneNonTrovataException {
        try {
            musica.get(nome_canzone).stop();
        } catch (NullPointerException e) {
            throw new CanzoneNonTrovataException(nome_canzone);
        }
    }

    public void chiudi(String nome_canzone) throws CanzoneNonTrovataException {
        try {
            musica.get(nome_canzone).close();
        } catch (NullPointerException e) {
            throw new CanzoneNonTrovataException(nome_canzone);
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
