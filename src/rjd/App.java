package rjd;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import java.io.IOException;

public class App {

    public static void main(String[] args)
            throws MidiUnavailableException, InvalidMidiDataException, InterruptedException, IOException {
        // TODO Auto-generated method stub

        int[] notes = { 60, 64, 67, 60, 65, 67, 55, 59, 62, 55, 60, 62, 53, 57, 60, 53, 58, 60 };
        long[] times = { 0, 0, 0, 1000, 1000, 1000, 2000, 2000, 2000, 3000, 3000, 3000, 4000, 4000, 4000, 5000, 5000, 5000};

        PlayMidi.playMidi(notes, times);

    }
}