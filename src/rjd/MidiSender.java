package rjd;

import javax.sound.midi.*;

//  https://reintech.io/blog/java-midi-programming-creating-manipulating-midi-data
public class MidiSender {

    public static void main(String[] args) {
        try {
            // Get the default MIDI synthesizer
            Synthesizer synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();

            // Get the synthesizer's MIDI receiver
            Receiver receiver = synthesizer.getReceiver();

            // Create a note-on message and send it to the receiver
            ShortMessage noteOn = new ShortMessage();
            noteOn.setMessage(ShortMessage.NOTE_ON, 0, 60, 93);
            receiver.send(noteOn, -1);

            // Wait for 1 second
            Thread.sleep(10000);

            // Create a note-off message and send it to the receiver
            ShortMessage noteOff = new ShortMessage();
            noteOff.setMessage(ShortMessage.NOTE_OFF, 0, 60, 0);
            receiver.send(noteOff, -1);

            // Close the synthesizer
            synthesizer.close();

        } catch (MidiUnavailableException | InterruptedException | InvalidMidiDataException e ) {
            e.printStackTrace();
        }
    }
}
