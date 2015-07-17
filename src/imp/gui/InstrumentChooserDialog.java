/**
 * This Java Class is part of the Impro-Visor Application
 *
 * Copyright (C) 2005-2009 Robert Keller and Harvey Mudd College
 *
 * Impro-Visor is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * Impro-Visor is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * merchantability or fitness for a particular purpose.  See the
 * GNU General Public License for more details.
 *

 * You should have received a copy of the GNU General Public License
 * along with Impro-Visor; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */

package imp.gui;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.*;

/**
 *
 * @author  Martin
 */
public class InstrumentChooserDialog extends javax.swing.JDialog {
    
    private InstrumentPanel instruments;
    
    /** Creates new form InstrumentChooser2 */
    public InstrumentChooserDialog(java.awt.Dialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        instruments = new InstrumentPanel();
        jScrollPane1.setViewportView(instruments);
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(16);
        establishDefaultButton();  // This button only highlights the first time.
        pack();
    }
    
    public void establishDefaultButton()
    {
    getRootPane().setDefaultButton(okButton);
    }
    
    public String doLookup(int number) {
        return instruments.doLookup(number);
    }
    
    public void setValue(int number) {
        setValue(number, instruments.doLookup(number));
    }
    
    public void setValue(int number, String name) {
        selectedNumber.setText(String.valueOf(number));
        selectedName.setText(name);
        instruments.setValue(number);
    }
    
    public int getValue() {
        try {
            return Integer.parseInt(selectedNumber.getText());
        } catch (NumberFormatException e) {
        }
        return 0;
    }
    
    public void setTarget(JTextField targetNumber, JButton targetName) {
        this.targetNumber = targetNumber;
        this.targetName = targetName;
    }
    
    private void updateSelectionFromText() {
        if(selectedNumber.getText().length() > 0) {
            int value = getValue();
            instruments.setValue(value);
            selectedName.setText(instruments.doLookup(value));
        }         
    }
    
     /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane1 = new javax.swing.JScrollPane();
        buttonPanel = new javax.swing.JPanel();
        cancelButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();
        selectedPanel = new javax.swing.JPanel();
        selectedNumber = new javax.swing.JTextField();
        selectedName = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(400, 1540));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(400, 400));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jScrollPane1, gridBagConstraints);

        buttonPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        cancelButton.setText("Cancel");
        cancelButton.setDefaultCapable(false);
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(cancelButton);

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(okButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(buttonPanel, gridBagConstraints);

        selectedPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("selected instrument"));
        selectedPanel.setLayout(new java.awt.GridBagLayout());

        selectedNumber.setMinimumSize(new java.awt.Dimension(11, 22));
        selectedNumber.setPreferredSize(new java.awt.Dimension(30, 22));
        selectedNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectedNumberActionPerformed(evt);
            }
        });
        selectedNumber.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                selectedNumberFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                selectedNumberFocusLost(evt);
            }
        });
        selectedNumber.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                selectedNumberCaretPositionChanged(evt);
            }
        });
        selectedNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                selectedNumberKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        selectedPanel.add(selectedNumber, gridBagConstraints);

        selectedName.setText("nothing selected");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 6, 0, 0);
        selectedPanel.add(selectedName, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 20);
        getContentPane().add(selectedPanel, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private JTextField targetNumber = null;
    private JButton targetName = null;
    
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        if(targetNumber != null)
            targetNumber.setText(selectedNumber.getText());
        if(targetName != null)
            targetName.setText(selectedName.getText());
        dispose();
    }//GEN-LAST:event_okButtonActionPerformed

    private void selectedNumberKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_selectedNumberKeyReleased
        updateSelectionFromText();
    }//GEN-LAST:event_selectedNumberKeyReleased

    private void selectedNumberFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_selectedNumberFocusLost
        updateSelectionFromText();
    }//GEN-LAST:event_selectedNumberFocusLost

    private void selectedNumberFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_selectedNumberFocusGained
        updateSelectionFromText();
    }//GEN-LAST:event_selectedNumberFocusGained

    private void selectedNumberCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_selectedNumberCaretPositionChanged
        updateSelectionFromText();
    }//GEN-LAST:event_selectedNumberCaretPositionChanged

    private void selectedNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectedNumberActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_selectedNumberActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton okButton;
    private javax.swing.JLabel selectedName;
    private javax.swing.JTextField selectedNumber;
    private javax.swing.JPanel selectedPanel;
    // End of variables declaration//GEN-END:variables
    
    private class InstrumentPanel extends JPanel {
    
        /** Creates new form InstrumentChooser */
        public InstrumentPanel() {
            initInstruments();
            initComponents();
        }

        ButtonGroup buttons = new ButtonGroup();
        JTextField value = new JTextField();
        Font controlFont = new Font("Tahoma", 1, 10);
        Font groupFont = new Font("Tahoma", 1, 11);

        public void setValue(int num) {
            value.setText(String.valueOf(num));
            Instrument inst = getInstrument(num);
            if(inst.control.getParent() != null) {
                getInstrument(num).control.setSelected(true);
                scrollRectToVisible(getInstrument(num).control.getParent().getBounds());
            }
        }

        public String doLookup(int num) {
            return getInstrument(num).name;
        }
        
        private Instrument getInstrument(int num) {
            for(Group g : group) {
                if(num > g.indexEnd || num < g.indexStart)
                    continue;
                for(int i = 0; i < g.instrument.size(); i++) {
                    if(g.instrument.get(i).number == num) {
                        return g.instrument.get(i);
                    }
                }
            }
            
            return new Instrument(num, unknownInstrument);
        }

        public String getValue() {
            return value.getText();
        }

        private int globalInitCounter;

        private void initComponents() {
            int numRows = (group.size() + (other.instrument.size() > 0 ? 1 : 0) + 1) / 2;
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0; c.gridy = 0; c.anchor = c.NORTHWEST; c.fill = c.HORIZONTAL; c.weightx = 1.0;

            GridBagLayout layout = new GridBagLayout();
            setLayout(layout);
            setOpaque(false);

            for(Group g : group) {
                add(initGroup(g), c);

                c.gridy++;
    //            if(c.gridy >= numRows) {
    //                c.gridy = 0;
    //                c.gridx++;
    //            }
            }

            if(other.instrument.size() > 0) {
                add(initGroup(other), c);
            }
        }

        private JPanel initGroup(Group g) {

            int numRows = (g.instrument.size() + 1)/2;
            GridBagLayout layout = new GridBagLayout();
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0; c.gridy = 0; c.anchor = c.NORTHWEST;

            JPanel panel = new JPanel(layout);
            panel.setBorder(
                    BorderFactory.createTitledBorder(
                        null, g.name, TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION, 
                        groupFont
                    )
                );

            for(int i = 0; i < g.instrument.size(); i++) {
                JRadioButton instrumentButton = g.instrument.get(i).control;

                buttons.add(instrumentButton);
                panel.add(instrumentButton, c);

                c.gridy++;
                if(c.gridy >= numRows) {
                    c.gridy = 0;
                    c.gridx++;
                }
            }

            return panel;
        }

        Vector<String> instrument = new Vector<String>(129, 128);
        Vector<Group> group = new Vector<Group>();
        Group other = new Group(0, 0, "Other");

        static private final String unknownInstrument = "<unknown instrument>";

        private void initInstruments() {
            addGroup(	1	,	8	,"Piano");
            addGroup(	9	,	16	,"Chromatic Percussion");
            addGroup(	17	,	24	,"Organ");
            addGroup(	25	,	32	,"Guitar");
            addGroup(	33	,	40	,"Bass");
            addGroup(	41	,	48	,"Strings");
            addGroup(	49	,	56	,"Ensemble");
            addGroup(	57	,	64	,"Brass");
            addGroup(	65	,	72	,"Reed");
            addGroup(	73	,	80	,"Pipe");
            addGroup(	81	,	88	,"Synth Lead");
            addGroup(	89	,	96	,"Synth Pad");
            addGroup(	97	,	104	,"Synth Effects");
            addGroup(	105	,	112	,"Ethnic");
            addGroup(	113	,	120	,"Percussive");
            addGroup(	121	,	128	,"Sound Effects");

            addInstrument(	1	, "Acoustic Grand Piano");
            addInstrument(	2	, "Bright Acoustic Piano");
            addInstrument(	3	, "Electric Grand Piano");
            addInstrument(	4	, "Honky-tonk Piano");
            addInstrument(	5	, "Electric Piano 1");
            addInstrument(	6	, "Electric Piano 2");
            addInstrument(	7	, "Harpsichord");
            addInstrument(	8	, "Clavi");
            addInstrument(	9	, "Celesta");
            addInstrument(	10	, "Glockenspiel");
            addInstrument(	11	, "Music Box");
            addInstrument(	12	, "Vibraphone");
            addInstrument(	13	, "Marimba");
            addInstrument(	14	, "Xylophone");
            addInstrument(	15	, "Tubular Bells");
            addInstrument(	16	, "Dulcimer");
            addInstrument(	17	, "Drawbar Organ");
            addInstrument(	18	, "Percussive Organ");
            addInstrument(	19	, "Rock Organ");
            addInstrument(	20	, "Church Organ");
            addInstrument(	21	, "Reed Organ");
            addInstrument(	22	, "Accordion");
            addInstrument(	23	, "Harmonica");
            addInstrument(	24	, "Tango Accordion");
            addInstrument(	25	, "Acoustic Guitar (nylon)");
            addInstrument(	26	, "Acoustic Guitar (steel)");
            addInstrument(	27	, "Electric Guitar (jazz)");
            addInstrument(	28	, "Electric Guitar (clean)");
            addInstrument(	29	, "Electric Guitar (muted)");
            addInstrument(	30	, "Overdriven Guitar");
            addInstrument(	31	, "Distortion Guitar");
            addInstrument(	32	, "Guitar harmonics");
            addInstrument(	33	, "Acoustic Bass");
            addInstrument(	34	, "Electric Bass (finger)");
            addInstrument(	35	, "Electric Bass (pick)");
            addInstrument(	36	, "Fretless Bass");
            addInstrument(	37	, "Slap Bass 1");
            addInstrument(	38	, "Slap Bass 2");
            addInstrument(	39	, "Synth Bass 1");
            addInstrument(	40	, "Synth Bass 2");
            addInstrument(	41	, "Violin");
            addInstrument(	42	, "Viola");
            addInstrument(	43	, "Cello");
            addInstrument(	44	, "Contrabass");
            addInstrument(	45	, "Tremolo Strings");
            addInstrument(	46	, "Pizzicato Strings");
            addInstrument(	47	, "Orchestral Harp");
            addInstrument(	48	, "Timpani");
            addInstrument(	49	, "String Ensemble 1");
            addInstrument(	50	, "String Ensemble 2");
            addInstrument(	51	, "SynthStrings 1");
            addInstrument(	52	, "SynthStrings 2");
            addInstrument(	53	, "Choir Aahs");
            addInstrument(	54	, "Voice Oohs");
            addInstrument(	55	, "Synth Voice");
            addInstrument(	56	, "Orchestra Hit");
            addInstrument(	57	, "Trumpet");
            addInstrument(	58	, "Trombone");
            addInstrument(	59	, "Tuba");
            addInstrument(	60	, "Muted Trumpet");
            addInstrument(	61	, "French Horn");
            addInstrument(	62	, "Brass Section");
            addInstrument(	63	, "SynthBrass 1");
            addInstrument(	64	, "SynthBrass 2");
            addInstrument(	65	, "Soprano Sax");
            addInstrument(	66	, "Alto Sax");
            addInstrument(	67	, "Tenor Sax");
            addInstrument(	68	, "Baritone Sax");
            addInstrument(	69	, "Oboe");
            addInstrument(	70	, "English Horn");
            addInstrument(	71	, "Bassoon");
            addInstrument(	72	, "Clarinet");
            addInstrument(	73	, "Piccolo");
            addInstrument(	74	, "Flute");
            addInstrument(	75	, "Recorder");
            addInstrument(	76	, "Pan Flute");
            addInstrument(	77	, "Blown Bottle");
            addInstrument(	78	, "Shakuhachi");
            addInstrument(	79	, "Whistle");
            addInstrument(	80	, "Ocarina");
            addInstrument(	81	, "Lead 1 (square)");
            addInstrument(	82	, "Lead 2 (sawtooth)");
            addInstrument(	83	, "Lead 3 (calliope)");
            addInstrument(	84	, "Lead 4 (chiff)");
            addInstrument(	85	, "Lead 5 (charang)");
            addInstrument(	86	, "Lead 6 (voice)");
            addInstrument(	87	, "Lead 7 (fifths)");
            addInstrument(	88	, "Lead 8 (bass + lead)");
            addInstrument(	89	, "Pad 1 (new age)");
            addInstrument(	90	, "Pad 2 (warm)");
            addInstrument(	91	, "Pad 3 (polysynth)");
            addInstrument(	92	, "Pad 4 (choir)");
            addInstrument(	93	, "Pad 5 (bowed)");
            addInstrument(	94	, "Pad 6 (metallic)");
            addInstrument(	95	, "Pad 7 (halo)");
            addInstrument(	96	, "Pad 8 (sweep)");
            addInstrument(	97	, "FX 1 (rain)");
            addInstrument(	98	, "FX 2 (soundtrack)");
            addInstrument(	99	, "FX 3 (crystal)");
            addInstrument(	100	, "FX 4 (atmosphere)");
            addInstrument(	101	, "FX 5 (brightness)");
            addInstrument(	102	, "FX 6 (goblins)");
            addInstrument(	103	, "FX 7 (echoes)");
            addInstrument(	104	, "FX 8 (sci-fi)");
            addInstrument(	105	, "Sitar");
            addInstrument(	106	, "Banjo");
            addInstrument(	107	, "Shamisen");
            addInstrument(	108	, "Koto");
            addInstrument(	109	, "Kalimba");
            addInstrument(	110	, "Bag pipe");
            addInstrument(	111	, "Fiddle");
            addInstrument(	112	, "Shanai");
            addInstrument(	113	, "Tinkle Bell");
            addInstrument(	114	, "Agogo");
            addInstrument(	115	, "Steel Drums");
            addInstrument(	116	, "Woodblock");
            addInstrument(	117	, "Taiko Drum");
            addInstrument(	118	, "Melodic Tom");
            addInstrument(	119	, "Synth Drum");
            addInstrument(	120	, "Reverse Cymbal");
            addInstrument(	121	, "Guitar Fret Noise");
            addInstrument(	122	, "Breath Noise");
            addInstrument(	123	, "Seashore");
            addInstrument(	124	, "Bird Tweet");
            addInstrument(	125	, "Telephone Ring");
            addInstrument(	126	, "Helicopter");
            addInstrument(	127	, "Applause");
            addInstrument(	128	, "Gunshot");
        }

        private void addGroup(int indexStart, int indexEnd, String name) {
            group.add(new Group(indexStart, indexEnd, name));
        }

        private class Group {
            public int indexStart = 0;
            public int indexEnd = 0;
            public String name;
            public Vector<Instrument> instrument;
            public Group(int indexStart, int indexEnd, String name) {
                this.indexStart = indexStart;
                this.indexEnd = indexEnd;
                this.name = name;
                this.instrument = new Vector<Instrument>(indexEnd - indexStart + 1);
            }
        }

        private class Instrument {
            public int number;
            public String name;
            public JRadioButton control;
            public Instrument(int number, String name) {
                this.number = number;
                this.name = name;
                this.control = new JRadioButton(number + ": " + name);
                this.control.setFont(controlFont);
                this.control.setPreferredSize(new Dimension(180, 16));
                this.control.setToolTipText(name);

                final int instrumentNumber = number;
                final String instrumentName = name;
                this.control.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        InstrumentChooserDialog.this.setValue(instrumentNumber, instrumentName);
                    }
                });
            }
        }

        private void addInstrument(int number, String name) {
            boolean found = false;
            for(Group g : group) {
                if(number >= g.indexStart && number <= g.indexEnd) {
                    g.instrument.add(new Instrument(number, name));
                    found = true;
                    break;
                }
            }

            // if an instrument doesn't go into any group, put it in the other group
            if(!found) {
                other.instrument.add(new Instrument(number, name));
            }
        }
    }

}
