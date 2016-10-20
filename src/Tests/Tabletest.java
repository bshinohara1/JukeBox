package Tests;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


import model.SongLibrary;

public class Tabletest extends JFrame {
	
	public static void main(String args[]) {
	    new Tabletest().setVisible(true);
	  }

	  // Need a TableModel to set as the model for a JTabel
	  private TableModel model;

	  // A JTable displays rows and columns of data in a graphical manner
	  private JTable table;

	  /**
	   * The constructor for a StudentTable. Sets up the GUI and the JTable
	   */
	  public Tabletest() {
	    // set up the JFrame
	    setTitle("Sort Table Demo");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(700, 140);
	    setLocation(30, 30);

	    SongLibrary a =null;
	    // TODO: 2) Need a new StudentCollection as our model
	    model = a.getSongLibrary();

	    // TODO: 3) Construct the JTable (table) with our model as an argument (could use setModel)
	    table = new JTable(model);

	    // TODO: 4) Construct a JScrollPane to decorate table so that if the data exceeds the 
	    // side of the table in the  GUI, then it automatically becomes scrollable.
	    JScrollPane scrollPane = new JScrollPane(table);

	    // TODO: 5) Add scrollpane to this JFrame
	    // this.add(scrollPane);

	    // TODO: Run this code to see if the JTable appears (no code to write)

	    // TODO: 6) Construct a new RowSorter<TableModel> to be a TableRowSorter
	    // while setting its model to model
	    RowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(model);

	    // TODO: 7) Link up table and the sorter
	    table.setRowSorter(rowSorter);

	    // Layout the GUI
	    add(scrollPane, BorderLayout.CENTER);
	    JButton button = new JButton("Select Highlighted Row");
	    JPanel panel = new JPanel();
	    panel.setMaximumSize(new Dimension(30, 30));
	    panel.add(button);
	    add(panel, BorderLayout.WEST);

	    // Listen to the button click
	    button.addActionListener(new ButtonListener());
	  }

	  private class ButtonListener implements ActionListener {

	    @Override
	    public void actionPerformed(ActionEvent arg0) {
	      // TODO: 8) Show the name of the student on the currently selected
	      // row need table's getSelectedRow and convertRowIndexToModel as
	      // well as model's getValueAt(rowIndex, columnIndex). See the API
	      // for details.

	      int selectedRow = table.convertRowIndexToModel(table.getSelectedRow());
	      // We use the model here because the order of the rows and the order
	      // of the columns never changes.
	      System.out.println(model.getValueAt(selectedRow, 0));
	    }

	  }
}
