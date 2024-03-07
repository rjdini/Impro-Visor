package rjd;


import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Line;
import javax.sound.sampled.Mixer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * A little example showing how to play a tune in Java.
 *
 * Inputs are not sanitized or checked, this is just to show how simple it is.
 *
 * @author Peter
 */
public class Synth {

    private static List<String> notes = Arrays.asList("C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B");
    private static MidiChannel[] channels;
    private static int INSTRUMENT = 0 ; // 0 is a piano, 9 is percussion, other channels are for other instruments
    private static int VOLUME = 80; // between 0 et 127
    private static Instrument[] instruments ;

    public static void main( String[] args ) {

        try {
            Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();
            for (Mixer.Info info : mixerInfos) {
                Mixer m = AudioSystem.getMixer(info);
                Line.Info[] lineInfos = m.getSourceLineInfo();
                for (Line.Info lineInfo : lineInfos) {
                    System.out.println(info.getName() + "---" + lineInfo);
                    Line line = m.getLine(lineInfo);
                    System.out.println("\t-----" + line);
                }
                lineInfos = m.getTargetLineInfo();
                for (Line.Info lineInfo : lineInfos) {
                    System.out.println(m + "---" + lineInfo);
                    Line line = m.getLine(lineInfo);
                    System.out.println("\t-----" + line);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }



        try {
            // * Open a synthesizer
            Synthesizer synth = MidiSystem.getSynthesizer();
            synth.open();
            synth.getDefaultSoundbank();
            instruments = synth.getAvailableInstruments();
            Iterator iter = Arrays.stream(instruments).iterator();
            while (iter.hasNext()){
                String instrumentName = iter.next().toString();
                System.out.println(instrumentName);
            }
            channels = synth.getChannels();
            channels[1].programChange(19);


            System.out.print("soundbank loaded " + instruments);

            // Check for null; maybe not all 16 channels exist.
            if (channels[1] != null) {
                channels[1].noteOn(100, 93);
            }
            rest(500);

        //    channels[1].noteOn(id("4C"), VOLUME );
         //   rest(500);

            play("6D",  100);
            rest(500);

            // * Play some notes
            play("6D",  300);

            System.out.println("********");
            play("6C#", 300);
            play("6D",  1000);
             rest(500);

            play("6D",  300);
            play("6C#", 300);
            play("6D",  1000);
            play("6E",  300);
            play("6E",  600);
            play("6G",  300);
            play("6G",  600);
            rest(500);

            // * finish up
            synth.close();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

/**
 * Plays the given note for the given duration
 */
private static void play(String note, int duration) throws InterruptedException
{
    // * start playing a note
    channels[INSTRUMENT].noteOn(id(note), 64 );
    // * wait

    Thread.sleep( duration );
    // * stop playing a note
    channels[INSTRUMENT].noteOff(id(note));
}

    /**
     * Plays nothing for the given duration
     */
    private static void rest(int duration) throws InterruptedException
    {
        Thread.sleep(duration);
    }

    /**
     * Returns the MIDI id for a given note: eg. 4C -> 60
     * @return
     */
    private static int id(String note)
    {
        int octave = Integer.parseInt(note.substring(0, 1));
        return notes.indexOf(note.substring(1)) + 12 * octave + 12;
    }
}