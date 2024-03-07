package rjd;

import java.io.IOException;
import java.net.URL;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

// https://stackoverflow.com/questions/69909883/using-midi-in-java
public class PlayMidi {

    public static void playMidiSong() throws InvalidMidiDataException, IOException, MidiUnavailableException {

        URL url = new URL("http://www.vgmusic.com/music/computer/microsoft/windows/touhou_6_stage3_boss.mid");

        Sequence sequence = MidiSystem.getSequence(url);
        Sequencer sequencer = MidiSystem.getSequencer();

        sequencer.open();
        sequencer.setSequence(sequence);

        sequencer.start();

        // Thread.sleep(8000);

    }

    public static void playMidi(int[] notes, long[] times) {

        try {

            // A static method of MidiSystem that returns
            // a sequencer instance.
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            System.out.println("Sequencer is open.");

            int bpm = 220;
            int resolution = 4;

            // Creating a sequence.
            Sequence sequence = new Sequence(Sequence.PPQ, resolution);

            // PPQ(Pulse per ticks) is used to specify timing
            // type and 4 is the timing resolution.

            double ticksPerSecond = resolution * (bpm / 60.0);
            double tickSize = 1.0 / ticksPerSecond;


            // Creating a track on our sequence upon which
            // MIDI events would be placed
            Track track = sequence.createTrack();

            // Adding some events to the track
            for (int i = 0; i < notes.length; i++) {

                double timeFactor = (times[i] / 1000);
                // Add Note On event
                track.add(makeEvent(ShortMessage.NOTE_ON, 1, notes[i], 120, ticksPerSecond * timeFactor));  //ShortMessage.NOTE_ON 144

                // Add Note Off event
                track.add(makeEvent(ShortMessage.NOTE_OFF, 1, notes[i], 120, (ticksPerSecond * timeFactor) + ticksPerSecond)); //ShortMessage.NOTE_OFF 128
            }

            // Setting our sequence so that the sequencer can
            // run it on synthesizer
            sequencer.setSequence(sequence);

            // Specifies the beat rate in beats per minute.
            sequencer.setTempoInBPM(bpm);

            // Sequencer starts to play notes
            sequencer.start();

            while (true) {

                // Exit the program when sequencer has stopped playing.
                if (!sequencer.isRunning()) {
                    sequencer.close();
                    System.out.println("Sequencer is closed.");
                    System.exit(1);
                }
            }
        } catch (Exception ex) {

            ex.printStackTrace();
        }


    }

    public static MidiEvent makeEvent(int command, int channel, int note, int velocity, double tick) {

        MidiEvent event = null;

        try {

            // ShortMessage stores a note as command type, channel,
            // instrument it has to be played on and its speed.
            ShortMessage a = new ShortMessage();
            a.setMessage(command, channel, note, velocity);

            // A midi event is comprised of a short message(representing
            // a note) and the tick at which that note has to be played
            event = new MidiEvent(a, (int) tick);
        } catch (Exception ex) {

            ex.printStackTrace();
        }
        return event;
    }
}
