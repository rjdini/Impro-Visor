package rjd;
import rjd.SimpleSynth;

public class Synth2 {

    public static void main( String[] args ) {
    SimpleSynth synth = new SimpleSynth();
    synth.setInstrument(SimpleSynth.PIANO);
    synth.setVolume(100);
    synth.noteOn(30);
    System.out.println(synth.isNoteOn(30));

    }

}
