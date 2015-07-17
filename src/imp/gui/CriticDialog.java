/**
 * This Java Class is part of the Impro-Visor Application
 *
 * Copyright (C) 2005-2012 Robert Keller and Harvey Mudd College
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

import imp.Constants;
import imp.ImproVisor;
import imp.com.PlayScoreCommand;
import imp.com.SetChordsCommand;
import imp.data.*;
import imp.util.Preferences;
import java.awt.Point;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import polya.Polylist;

/**
 * Created on August 2, 2006, 1:50 PM
 *
 * @author  Martin Hunt
 */

public class CriticDialog extends javax.swing.JDialog implements Constants {
 
    private CriticTableModel dataModel;
    private enum TCol {
        NAME (String.class, "Name"),
        NOTES (String.class, "Notes"),
        CHORDS (String.class, "Chords"),
        GRADE (Integer.class, "Grade", 50),
        LOADBTN (ImageIcon.class, "Load", 50),
        PLAYBTN (ImageIcon.class, "", 20);
        
        private final String name;
        private final int width;
        private final Class type;
        private TCol(Class type, String name) {
            this(type, name, -1);
        }
        private TCol(Class type, String name, int width) {
            this.type = type;
            this.name = name;
            this.width = width;
        }
        public int getWidth() {
            return width;
        }
        public Class getType() {
            return type;
        }
        
        @Override
        public String toString() {
            return name;
        }
    }
        
    /** Creates new form CriticDialog */
    public CriticDialog(java.awt.Frame parent) {
        super(parent, false);
        dataModel = new CriticTableModel();
        initComponents();
      
        
        TableColumn c;
        int width;
        
        for(TCol col : TCol.values()) {
            c = dataTable.getColumnModel().getColumn(col.ordinal());
            width = col.getWidth();
            if(width >= 0) {
                c.setMinWidth(width);
                c.setMaxWidth(width);
                c.setPreferredWidth(width);
            }
        }
    }
    
    public void updateLabel() {
        if(currentFile == null) {
            fileLabel.setText(null);
        } else {
            try {
                fileLabel.setText("file: " + currentFile.getCanonicalPath());
            } catch(IOException e) {
                errorLabel.setText("File IO Error: " + e.getMessage());
            }
        }
    }
    
    public void add(String data, int grade) {
        dataModel.add(data, grade);
    }
    
    public boolean rowExists(String data, int grade) {
        return dataModel.rowExists(data, grade);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        dataTablePopupMenu = new javax.swing.JPopupMenu();
        changeNameMenuItem = new javax.swing.JMenuItem();
        changeChordsMenuItem = new javax.swing.JMenuItem();
        changeGradeMenuItem = new javax.swing.JMenuItem();
        numRowsMenuItem = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        dataTable = new javax.swing.JTable();
        fileLabel = new javax.swing.JLabel();
        errorLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        saveFile = new javax.swing.JButton();
        saveAsFile = new javax.swing.JButton();
        openFile = new javax.swing.JButton();
        appendFile = new javax.swing.JButton();
        deleteSelected = new javax.swing.JButton();
        deleteAll = new javax.swing.JButton();

        changeNameMenuItem.setText("Change Name(s)");
        changeNameMenuItem.setToolTipText("Change the name of all the selected licks.");
        changeNameMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeNameMenuItemActionPerformed(evt);
            }
        });
        dataTablePopupMenu.add(changeNameMenuItem);

        changeChordsMenuItem.setText("Change Chords");
        changeChordsMenuItem.setToolTipText("Change the chords for all the selected licks.");
        changeChordsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeChordsMenuItemActionPerformed(evt);
            }
        });
        dataTablePopupMenu.add(changeChordsMenuItem);

        changeGradeMenuItem.setText("Change Grade(s)");
        changeGradeMenuItem.setToolTipText("Change the grade for all the selected licks.");
        changeGradeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeGradeMenuItemActionPerformed(evt);
            }
        });
        dataTablePopupMenu.add(changeGradeMenuItem);

        numRowsMenuItem.setText("Number of Licks");
        numRowsMenuItem.setToolTipText("Displays the number of selected licks.");
        numRowsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numRowsMenuItemActionPerformed(evt);
            }
        });
        dataTablePopupMenu.add(numRowsMenuItem);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        dataTable.setModel(dataModel);
        dataTable.getTableHeader().setReorderingAllowed(false);
        dataTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dataTableMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(dataTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jScrollPane1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 2, 0);
        getContentPane().add(fileLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 10, 0, 0);
        getContentPane().add(errorLabel, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        saveFile.setText("Save");
        saveFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveFileActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(saveFile, gridBagConstraints);

        saveAsFile.setText("Save As...");
        saveAsFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsFileActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel1.add(saveAsFile, gridBagConstraints);

        openFile.setText("Open");
        openFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jPanel1.add(openFile, gridBagConstraints);

        appendFile.setText("Append From File");
        appendFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appendFileActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(appendFile, gridBagConstraints);

        deleteSelected.setText("Delete Selected");
        deleteSelected.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteSelectedActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        jPanel1.add(deleteSelected, gridBagConstraints);

        deleteAll.setText("Delete All");
        deleteAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteAllActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        jPanel1.add(deleteAll, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        getContentPane().add(jPanel1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deleteAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteAllActionPerformed
        dataModel.clear();
    }//GEN-LAST:event_deleteAllActionPerformed

    private void deleteSelectedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteSelectedActionPerformed
        dataModel.deleteRows(dataTable.getSelectedRows());
    }//GEN-LAST:event_deleteSelectedActionPerformed

    private void dataTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataTableMousePressed
        if (evt.isPopupTrigger()) {
            if (dataTable.getSelectedRows().length == 0) {
                int r = dataTable.rowAtPoint(new Point(evt.getX(), evt.getY()));
                dataTable.setRowSelectionInterval(r, r);
            }
            dataTablePopupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
        }

        else if(dataTable.getSelectedColumn() == TCol.PLAYBTN.ordinal() ||
                dataTable.getSelectedColumn() == TCol.LOADBTN.ordinal()) {
            int row = dataTable.getSelectedRow();
            if(row == -1)
                return;
            
            Notate notate = ImproVisor.getCurrentWindow();
            int totalNumSlots = notate.getScoreLength();
            ChordPart chords = new ChordPart(totalNumSlots - 1);
            MelodyPart melody = new MelodyPart();    
            
            Polylist dataRow = dataModel.getRow(row);
            String name = (String) dataRow.nth(TCol.NAME.ordinal());
         
            Polylist combined = ((Polylist) (dataRow.nth(TCol.CHORDS.ordinal()))).append(
                                (Polylist) (dataRow.nth(TCol.NOTES.ordinal()))
                                );
            (new SetChordsCommand(0, combined, chords, melody)).execute();

            chords.setStyle(Preferences.getPreference(Preferences.DEFAULT_STYLE));
            
            Polylist notes = (Polylist) (dataRow.nth(TCol.NOTES.ordinal()));
            
            // Add all notes to the note list
            while(!notes.isEmpty()) {
                while(!notes.isEmpty() && notes.first() == null) {
                    notes = notes.rest();
                }

                if(!notes.isEmpty()) {
                    melody.addNote(NoteSymbol.toNote(notes.first().toString()));
                    notes = notes.rest();
                }
            }
            
            melody.setInstrument(notate.getCurrentStave().getMelodyPart().getInstrument());
            Score score = new Score();
            score.setChordProg(chords);
            score.addPart(melody);
            
            if (dataTable.getSelectedColumn() == TCol.PLAYBTN.ordinal()) { 
                new PlayScoreCommand(score, 
                                     0, 
                                     true, 
                                     ImproVisor.getLastMidiSynth(),
                                     ImproVisor.getCurrentWindow(),
                                     0, 
                                     0,
                                     false,
                                     BEAT * 4).execute();
            }
            
            if (dataTable.getSelectedColumn() == TCol.LOADBTN.ordinal()) {
                notate.getLickgenFrame().setSaveLickTextField(name);
                notate.getChordProg().newPasteOver(chords, notate.getCurrentSelectionStart());
                notate.putLickWithoutRectify(melody);
            }
        }
    }//GEN-LAST:event_dataTableMousePressed

    private void saveFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveFileActionPerformed
        save();
    }//GEN-LAST:event_saveFileActionPerformed

    private void appendFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appendFileActionPerformed
        addFromFile(false);
    }//GEN-LAST:event_appendFileActionPerformed

    private void openFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFileActionPerformed
        addFromFile(true);
    }//GEN-LAST:event_openFileActionPerformed

    private void saveAsFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsFileActionPerformed
        saveAs();
    }//GEN-LAST:event_saveAsFileActionPerformed

    private void changeNameMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeNameMenuItemActionPerformed
        String s = JOptionPane.showInputDialog("Set the name for the selected rows");
        if( s != null && s.length() > 0 )
        {
            int[] rows = dataTable.getSelectedRows();
            int col = TCol.NAME.ordinal();
            for (int row : rows)
                dataModel.setValueAt(s, row, col);
        }
    }//GEN-LAST:event_changeNameMenuItemActionPerformed

    private void changeChordsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeChordsMenuItemActionPerformed
        String s = JOptionPane.showInputDialog("Set the chords for the selected rows");
        if( s != null && s.length() > 0 )
        {
            int[] rows = dataTable.getSelectedRows();
            int col = TCol.CHORDS.ordinal();
            for (int row : rows)
                dataModel.setValueAt(s, row, col);
        }
    }//GEN-LAST:event_changeChordsMenuItemActionPerformed

    private void changeGradeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeGradeMenuItemActionPerformed
        int grade;
        String s = JOptionPane.showInputDialog("Set the grade for the selected rows");
        if( s != null && s.length() > 0 )
        {
            grade = Integer.parseInt(s);
            int[] rows = dataTable.getSelectedRows();
            int col = TCol.GRADE.ordinal();
            for (int row : rows)
                dataModel.setValueAt(grade, row, col);
        }
    }//GEN-LAST:event_changeGradeMenuItemActionPerformed

    private void numRowsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numRowsMenuItemActionPerformed
        JOptionPane.showMessageDialog(null, 
                new JLabel("<html><div style=\"text-align: center;\">"
                + "Number of Rows: " + dataTable.getSelectedRows().length), 
                  "Info", JOptionPane.PLAIN_MESSAGE);
    }//GEN-LAST:event_numRowsMenuItemActionPerformed

    // File to be read from or written to
    File currentFile = null;
    
    public void addFromFile(boolean overwrite) {
        JFileChooser openDialog = new JFileChooser(ImproVisor.getNNetDataDirectory());
        openDialog.setFileFilter(new FileFilter() {

            public boolean accept(File pathname) {
                if (pathname.isDirectory())
                    return true;
                return !pathname.getName().endsWith(".training.data");
            }

            public String getDescription() {
                return "Lick Files";
            }
        });
        
        openDialog.setDialogType(JFileChooser.OPEN_DIALOG);
        
        if(openDialog.showDialog(this, "Open") != JFileChooser.APPROVE_OPTION)
            return;
        
        File file = openDialog.getSelectedFile();
        if(file.exists()) {
            if(overwrite) {
                dataModel.clear();
                currentFile = file;
                updateLabel();
            }
            
            BufferedReader in;
            try {
                in = new BufferedReader(new FileReader(file));
            } catch(FileNotFoundException e) {
                errorLabel.setText("File Not Found: " + e.getMessage());
                return;
            }
            
            String line;
            
            try {
                while((line = in.readLine()) != null) {
                    int gradeStart = line.indexOf(" ");
                    int grade = (int) (10 * Double.valueOf(line.substring(0, gradeStart)));

                    int lickStart = line.indexOf("(");
                    String lick = line.substring(lickStart);
                    
                    if (!rowExists(lick, grade))
                        add(lick, grade);
                }
            } catch(IOException e) {
                errorLabel.setText("File IO Error: " + e.getMessage());
                return;
            }
        }
        
        errorLabel.setText(null);
    }
    
    public void save() {
        if(currentFile != null)
            save(currentFile);
        else
            saveAs();
    }
    
    public void save(File f) {
        BufferedWriter out;
        try {
            out = new BufferedWriter(new FileWriter(f));
        } catch(IOException e) {
            errorLabel.setText("File IO Error: " + e.getMessage());
            return;
        } 
        
        for(int i = 0; i < dataModel.getRowCount(); i++) {
            try {
                saveRow(out, i);
            } catch (IOException e) {
                errorLabel.setText("File IO Error: " + e.getMessage());
                return;
            }
        }
        
        try {
            out.flush();
        } catch(IOException e) {
            errorLabel.setText("File IO Error: " + e.getMessage());
            return;
        }
        
        Scanner in;
        BufferedWriter outTemp;
        BufferedWriter outWeight;
        try {
            in = new Scanner(f);
            
            String filePath = f.getAbsolutePath();
            
            // Truncate the suffix of the file name if it exists
            if (f.getName().lastIndexOf(".") > 0)
            {
                int pos = f.getAbsolutePath().lastIndexOf(".");
                filePath = f.getAbsolutePath().substring(0, pos);
            }
            
            File fileTemp = new File(filePath + ".temp");
            outTemp = new BufferedWriter(new FileWriter(fileTemp));
            File fileWeight = new File(filePath + ".training.data");
            outWeight = new BufferedWriter(new FileWriter(fileWeight));
            
            int maxSize = 0;
            while (in.hasNextLine()) {
                String line = in.nextLine();
                int index = line.indexOf("(lick");
                line = line.substring(0, index);
                if (line.length() > maxSize)
                    maxSize = line.length();
                outTemp.write(line);
                outTemp.newLine();
            }
            outTemp.flush();
            outTemp.close();
            in.close();

            // Write beginning info
            outWeight.write("1 ");
            int numInputs = (maxSize - 4) / 2;
            outWeight.write("" + numInputs);
            outWeight.newLine();
            
            in = new Scanner(fileTemp);
            while (in.hasNextLine()) {
                String data = in.nextLine();
                while (data.length() < maxSize)
                {
                    // Add a whole note rest landing on beat 1 
                    data += BitVectorGenerator.WHOLE_REST;
                }
                
                outWeight.write(data);
                outWeight.newLine();
            }
            outWeight.flush();
            in.close();
            fileTemp.delete();
            
            
        } catch(IOException e) {
            errorLabel.setText("File IO Error: " + e.getMessage());
            return;
        }
   
        errorLabel.setText(null);
    }
    
    public void saveAs() {
        JFileChooser saveDialog = new JFileChooser(ImproVisor.getNNetDataDirectory());
        saveDialog.setFileFilter(new FileFilter() {

            public boolean accept(File pathname) {
                if (pathname.isDirectory())
                    return true;
                return !pathname.getName().endsWith(".training.data");
            }

            public String getDescription() {
                return "Lick Files";
            }
        });
        
        saveDialog.setDialogType(JFileChooser.SAVE_DIALOG);
        if(saveDialog.showDialog(this, "Save") != JFileChooser.APPROVE_OPTION)
            return;
        currentFile = saveDialog.getSelectedFile();
        updateLabel();
        save(currentFile);
    }
    
    /**
     * Saves a lick for the critic, later to be used as input for a neural network.
     * Major changes include a smaller bit vector and a new method 
     * for representing the data.
     * Needs at least one chord from the leadsheet, assumes at most two chords at the moment.
     * Also needs only a two measure selection. 
     * @param out
     * @param row
     * @throws IOException 
     */
    private void saveRow(BufferedWriter out, int row) throws IOException {
        Polylist r = dataModel.getRow(row);
        Polylist name = Polylist.list("name", r.first());
        Polylist notes = (Polylist) r.second();
        Polylist chords = (Polylist) r.third();
        int grade = (int) (Integer) r.fourth();
        Polylist lick = Polylist.list("lick", notes.cons("notes"), chords.cons("sequence"), name, Polylist.list("grade", grade));

        // Prepare a score so that Chord lengths can be determined
        ChordPart chordsList = new ChordPart(ImproVisor.getCurrentWindow().getScoreLength() - 1);
        MelodyPart melody = new MelodyPart();
        Polylist combined = chords.append(notes);
        (new SetChordsCommand(0, combined, chordsList, melody)).execute();
        chordsList.setStyle(Preferences.getPreference(Preferences.DEFAULT_STYLE));
        Score score = new Score();
        score.setChordProg(chordsList);
        score.addPart(melody);
        ArrayList<ChordSymbol> symbols = score.getChordProg().getChordSymbols();
        ArrayList<Integer> durations = score.getChordProg().getChordDurations();
        
        ArrayList<Note> noteList = new ArrayList<Note>();
        ArrayList<Chord> chordList = new ArrayList<Chord>();
        
        // Add all chords to the chord list
        for (int i = 0; i < symbols.size(); i++)
            chordList.add(new Chord(symbols.get(i), durations.get(i)));
        
        // Add all notes to the note list
        while(!notes.isEmpty()) {
            while(!notes.isEmpty() && notes.first() == null) {
                notes = notes.rest();
            }
            
            if(!notes.isEmpty()) {
                noteList.add(NoteSymbol.toNote(notes.first().toString()));
                notes = notes.rest();
            }
        }
        
        MelodyPart melodyPart = new MelodyPart();
        ChordPart chordPart = new ChordPart();
        for (Note n : noteList)
            melodyPart.addNote(n);
        for (Chord c : chordList)
            chordPart.addChord(c);

        int [] classifications = Coloration.collectNoteColors(melodyPart, chordPart);
        
        // Boolean used atomically to track potential errors
        AtomicBoolean error = new AtomicBoolean(false);
        StringBuilder output = new StringBuilder();
        int beatPosition;
        int currStart = 0;
        int currEnd = BEAT * 8 - 1;
        int size = melodyPart.size() - 1;
        
        while (currEnd <= size && !error.get())
        {
            // Add the grade
            output.append(String.valueOf(grade / 10.0));
            output.append(' ');
            
            MelodyPart currMelody = melodyPart.extract(currStart, currEnd);
             
            ArrayList<Unit> unitList = currMelody.getUnitList();
            ArrayList<Note> currNoteList = new ArrayList<Note>();
            for (Unit u : unitList)
                currNoteList.add((Note) u);
           
            beatPosition = currStart;
            
            // Print all note data for all notes within one lick
            for (int index = 0; index < currNoteList.size(); index++)
            {
                int indexPrev = index - 1;
                int currNoteClassification = Coloration.getNoteClassification(classifications[beatPosition]);

                if (indexPrev < 0)
                {
                    if (currStart == 0)
                    {
                        beatPosition = BitVectorGenerator.printNoteData(output, null, currNoteList.get(index), 
                                currNoteClassification, beatPosition, error);
                    }
                    else
                    {   
                        // For correct distance to preceding note
                        Note prevNote = melodyPart.getPrevNote(currStart);
                        beatPosition = BitVectorGenerator.printNoteData(output, prevNote, currNoteList.get(index), 
                                currNoteClassification, beatPosition, error);
                    }
                }
                else
                {
                    beatPosition = BitVectorGenerator.printNoteData(output, currNoteList.get(indexPrev), 
                            currNoteList.get(index), currNoteClassification, beatPosition, error);
                }
            }
            
            output.append(lick.toString());
            output.append("\n");
                       
            // Move two beats ahead
            currStart += BEAT * 2;
            currEnd += BEAT * 2;     
        }
 
        if (!error.get())
        {
            out.write(output.toString());
        }
        else
        {
            System.out.println("Error from parsing data: Will not save this lick.");
        }
    } 
    
//    Commented out code is the old way of dealing with encoding a lick
//    a lick as a bit vector.
//
//    /**
//     * Previous way to save data for the critic. 
//     * Uses longer bit vectors comprised of chords and individual notes.
//     * Changed to accommodate the use of new printing methods.
//     * @param out
//     * @param row
//     * @throws IOException 
//     */
//    private void saveRowOld(BufferedWriter out, int row) throws IOException {
//        Polylist r = dataModel.getRow(row);
//        Polylist name = Polylist.list("name", r.first());
//        Polylist notes = (Polylist) r.second();
//        Polylist chords = (Polylist) r.third();
//        int grade = (int) (Integer) r.fourth();
//        Polylist lick = Polylist.list("lick", notes.cons("notes"), chords.cons("sequence"), name, Polylist.list("grade", grade));
//        
//        out.write(String.valueOf(grade / 10.0));
//        out.write(' ');
//        
//        while(!chords.isEmpty()) {
//            while(!chords.isEmpty() && chords.first() == null) {
//                chords = chords.rest();
//            }
//            
//            if(!chords.isEmpty()) {
//                printChord(out, ChordSymbol.makeChordSymbol((String) chords.first()));
//                chords = chords.rest();
//            }
//        }
//        
//        Polylist noteSymbols = NoteSymbol.makeNoteSymbolList(notes);
//        
//        while(noteSymbols.nonEmpty()) {
//            printNoteSymbol(out, (NoteSymbol) noteSymbols.first());
//            noteSymbols = noteSymbols.rest();
//        }
//        
//        out.write(lick.toString());
//    }
//    
//    /** Variables for printing a note symbol at a particular resolution. */
//    private final int BOTTOMNOTE = 60;
//    private final int TOPNOTE = 83;
//    //Changed from EIGHTH to THIRTYSECOND_TRIPLET in order to obtain a finer note resolution.
//    private final int MINDURATION = THIRTYSECOND_TRIPLET;
//    
//    /**
//     * Previous way of saving note data, as a bit vector.
//     * Resulted in very large inputs for the neural network, 
//     * making it hard to train. 
//     * Changed to a new bit vector input method.
//     * @param out
//     * @param note
//     * @throws IOException 
//     */
//    private void printNoteSymbol(BufferedWriter out, NoteSymbol note) throws IOException {
//        int pitch = note.getMIDI();
//
//        if(pitch > 0) {
//            if(pitch < BOTTOMNOTE)
//                pitch = BOTTOMNOTE;
//            if(pitch > TOPNOTE) 
//                pitch = TOPNOTE;
//        }
//            
//        // total number of bit rows to output
//        int totalRows = note.getDuration() / MINDURATION;
//        
//        // current bit row
//        int currentRow = 0;
//        
//        char[] bits = {'0', ' ', '0', ' ', '0', ' ', '0', ' ',
//                       '0', ' ', '0', ' ', '0', ' ', '0', ' ',
//                       '0', ' ', '0', ' ', '0', ' ', '0', ' ',
//                       '0', ' ', '0', ' ', '0', ' ', '0', ' ',
//                       '0', ' ', '0', ' ', '0', ' ', '0', ' ',
//                       '0', ' ', '0', ' ', '0', ' ', '0', ' '};
//        while(currentRow < totalRows) {
//            if(pitch == -1) {
//                out.write("0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 ");
//            } else {
//                // sustain bit
//                out.write( currentRow > 0 ? "1 " : "0 ");
//                int index = 2 * (pitch - BOTTOMNOTE);
//                bits[index] = '1';
//                out.write(bits);
//                bits[index] = '0';
//            }
//            currentRow++;
//        }
//    }
//    
//    /**
//     * Saves chord in a bit vector representation.
//     * 12 bits long, unique for every chord.
//     * @param out
//     * @param chord
//     * @throws IOException 
//     */
//    private void printChord(BufferedWriter out, ChordSymbol chord) throws IOException {
//        if(chord == null)
//            return;
//        
//        Polylist spelling = chord.getChordForm().getSpell(chord.getRootString());
//        
//        // 12 bits for a chord
//        char[] bitSpelling = {'0', ' ', '0', ' ', '0', ' ', '0', ' ', 
//                              '0', ' ', '0', ' ', '0', ' ', '0', ' ', 
//                              '0', ' ', '0', ' ', '0', ' ', '0', ' '};
//        
//        while(spelling.nonEmpty()) {
//            NoteSymbol n = (NoteSymbol) spelling.first();
//            bitSpelling[2 * (n.getMIDI() % 12)] = '1';
//            spelling = spelling.rest();
//        }
//        
//        out.write(bitSpelling);
//    }
    
    private class CriticTableModel extends AbstractTableModel {
        private ImageIcon playIcon = new ImageIcon(getClass().getResource("/imp/gui/graphics/icons/play.png"));
        private ImageIcon loadIcon = new ImageIcon(getClass().getResource("/imp/gui/graphics/icons/load.png"));

        private ArrayList<Polylist> data = new ArrayList<Polylist>(); //Changed from Vector

        public CriticTableModel() {
            
        }
        
        public void clear() {
            data.clear();
            fireTableDataChanged();
        }
        
        public void add(String lickStr, int grade) {
            Polylist lick = Notate.parseListFromString(lickStr);
            if(lick.length() == 1 && lick.first() instanceof Polylist && ((Polylist) lick.first()).length() > 1) {
                lick = (Polylist) (lick.first());
            }
            Polylist notes = Polylist.list();
            Polylist chords = Polylist.list();
            String name = "";
            while(lick.nonEmpty()) {
                Object o = lick.first();
                if(o instanceof Polylist) {
                    Polylist p = (Polylist) o;
                    String s = (String) p.first();
                    if(s.equals("notes")) {
                        notes = p.rest();
                    } else if(s.equals("sequence")) {
                        chords = p.rest();
                    } else if(s.equals("name")) {
                        name = (String) p.rest().implode(" ");
                    }
                }
                lick = lick.rest();
            }
            data.add(Polylist.list(name, notes, chords, grade));
            
            fireTableDataChanged();
        }
        
        public Polylist getRow(int row) {
            return data.get(row);
        }
        
        public boolean rowExists(String lickStr, int grade) {
            Polylist lick = Notate.parseListFromString(lickStr);
            if(lick.length() == 1 && lick.first() instanceof Polylist && ((Polylist) lick.first()).length() > 1) {
                lick = (Polylist) (lick.first());
            }
            Polylist notes = Polylist.list();
            Polylist chords = Polylist.list();
            String name = "";
            while(lick.nonEmpty()) {
                Object o = lick.first();
                if(o instanceof Polylist) {
                    Polylist p = (Polylist) o;
                    String s = (String) p.first();
                    if(s.equals("notes")) {
                        notes = p.rest();
                    } else if(s.equals("sequence")) {
                        chords = p.rest();
                    } else if(s.equals("name")) {
                        name = (String) p.rest().implode(" ");
                    }
                }
                lick = lick.rest();
            }
            
            return data.contains(Polylist.list(name, notes, chords, grade));
        }

        public int getColumnCount() {
            return TCol.values().length;
        }

        @Override
        public String getColumnName(int col) {
            return TCol.values()[col].toString();
        }
        
        @Override
        public Class getColumnClass(int col) {
            return TCol.values()[col].getType();
        }

        public int getRowCount() {
            return data.size();
        }

        public Object getValueAt(int row, int col) {
            if(col == TCol.PLAYBTN.ordinal())
                return playIcon;
            if (col == TCol.LOADBTN.ordinal())
                return loadIcon;
            Object o = data.get(row).nth(col);
            if(o instanceof Polylist && !((Polylist)o).nonEmpty())
                return "";
            else {
                return o;
            }
        }
        
        public void deleteRows(int[] rowsToDelete) {
            java.util.Arrays.sort(rowsToDelete, 0, rowsToDelete.length);
            for(int i = rowsToDelete.length - 1; i >= 0; --i) {
                deleteRow(rowsToDelete[i]);
            }
        }
        
        public void deleteRow(int i) {
            data.remove(i);
            
            fireTableDataChanged();
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex != TCol.PLAYBTN.ordinal() &&
                   columnIndex != TCol.LOADBTN.ordinal();
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            int name = TCol.NAME.ordinal();
            int grade = TCol.GRADE.ordinal();
            int notes = TCol.NOTES.ordinal();
            int chords = TCol.CHORDS.ordinal();
            if(columnIndex == name) {
                data.get(rowIndex).setNth(name, aValue.toString());
            } else if(columnIndex == grade) {
                data.get(rowIndex).setNth(grade, Integer.valueOf(aValue.toString()));
            } else if(columnIndex == notes) {
                data.get(rowIndex).setNth(notes, (Polylist) Notate.parseListFromString(aValue.toString()).first());
            } else if(columnIndex == chords) {
                data.get(rowIndex).setNth(chords, (Polylist) Notate.parseListFromString(aValue.toString()).first());
            }
           
            fireTableRowsUpdated(rowIndex,rowIndex);
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton appendFile;
    private javax.swing.JMenuItem changeChordsMenuItem;
    private javax.swing.JMenuItem changeGradeMenuItem;
    private javax.swing.JMenuItem changeNameMenuItem;
    private javax.swing.JTable dataTable;
    private javax.swing.JPopupMenu dataTablePopupMenu;
    private javax.swing.JButton deleteAll;
    private javax.swing.JButton deleteSelected;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JLabel fileLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem numRowsMenuItem;
    private javax.swing.JButton openFile;
    private javax.swing.JButton saveAsFile;
    private javax.swing.JButton saveFile;
    // End of variables declaration//GEN-END:variables
    
}
