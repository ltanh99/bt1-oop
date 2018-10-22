package sample;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.sound.sampled.AudioInputStream;

import marytts.LocalMaryInterface;
import marytts.MaryInterface;
import marytts.exceptions.MaryConfigurationException;
import marytts.exceptions.SynthesisException;
import marytts.modules.synthesis.Voice;
import marytts.signalproc.effects.AudioEffect;
import marytts.signalproc.effects.AudioEffects;

/**
 * @author GOXR3PLUS
 *
 */
public class TextToSpeech {

    private AudioPlayer tts;
    private MaryInterface marytts;

    /**
     * Constructor
     */
    public TextToSpeech() {
        try {
            marytts = new LocalMaryInterface();

        } catch (MaryConfigurationException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    //----------------------GENERAL METHODS---------------------------------------------------//

    /**
     * Transform text to speech
     *
     * @param text
     *            The text that will be transformed to speech
     * @param daemon
     *            <br>
     *            <b>True</b> The thread that will start the text to speech Player will be a daemon Thread <br>
     *            <b>False</b> The thread that will start the text to speech Player will be a normal non daemon Thread
     * @param join
     *            <br>
     *            <b>True</b> The current Thread calling this method will wait(blocked) until the Thread which is playing the Speech finish <br>
     *            <b>False</b> The current Thread calling this method will continue freely after calling this method
     */
    public void speak(String text , float gainValue , boolean daemon , boolean join) {

        stopSpeaking();

        try (AudioInputStream audio = marytts.generateAudio(text)) {
            tts = new AudioPlayer();
            tts.setAudio(audio);
            tts.setGain(gainValue);
            tts.setDaemon(daemon);
            tts.start();
            if (join)
                tts.join();

        } catch (SynthesisException ex) {
            Logger.getLogger(getClass().getName()).log(Level.WARNING, "Error saying phrase.", ex);
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.WARNING, "IO Exception", ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(getClass().getName()).log(Level.WARNING, "Interrupted ", ex);
            tts.interrupt();
        }
    }
    public void stopSpeaking() {
        // Stop the previous player
        if (tts != null)
            tts.cancel();
    }

    public Collection<Voice> getAvailableVoices() {
        return Voice.getAvailableVoices();
    }
    public MaryInterface getMarytts() {
        return marytts;
    }
    public List<AudioEffect> getAudioEffects() {
        return StreamSupport.stream(AudioEffects.getEffects().spliterator(), false).collect(Collectors.toList());
    }
    public void setVoice(String voice) {
        marytts.setVoice(voice);
    }

}
