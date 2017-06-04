package map;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import island.Island;

public class AddIsland extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private WAMap map;
	private JPanel panel;
	
	private JLabel xLabel = new JLabel("x coordinate");
	private JLabel yLabel = new JLabel("y coordinate");
	private JLabel sizeLabel = new JLabel("size of the island");
	private JLabel nameLabel = new JLabel("name the island");
	
	private JFormattedTextField xField;
	private JFormattedTextField yField;
	private JFormattedTextField sizeField;
	private JTextField nameField;
	private JComboBox<String> colorList;

	public AddIsland(WAMap map) {
		this.map = map;
		this.setTitle("Add island");
	    this.setSize(400, 300);
	    this.setLocationRelativeTo(null);
	    
	    JButton b1 = new JButton("Add island");
	    b1.setActionCommand("add island");
	    b1.addActionListener(this);
	    
	    NumberFormat coordFormat = DecimalFormat.getNumberInstance();
	    coordFormat.setMinimumFractionDigits(1);
	    coordFormat.setMaximumFractionDigits(3);
	    coordFormat.setRoundingMode(RoundingMode.HALF_EVEN);
	    
	    NumberFormat sizeFormat = NumberFormat.getNumberInstance();
	    
	    xField = new JFormattedTextField(coordFormat);
	    xField.setValue(new Double(0.0));
	    xField.setColumns(10);
	    xLabel.setLabelFor(xField);
	    
	    yField = new JFormattedTextField(coordFormat);
	    yField.setValue(new Double(0.0));
	    yField.setColumns(10);
	    yLabel.setLabelFor(yField);
	    
	    sizeField = new JFormattedTextField(sizeFormat);
	    sizeField.setValue(new Integer(7));
	    sizeField.setColumns(5);
	    sizeLabel.setLabelFor(sizeField);
	    
	    nameField = new JTextField(20);
	    nameLabel.setLabelFor(nameField);
	    
	    String[] color = {"Green", "Red", "Blue", "Yellow"};
	    colorList = new JComboBox<String>(color);
	    
	    JPanel xPane = new JPanel(new GridLayout(1,0));
	    xPane.add(xLabel);
	    xPane.add(xField);
	    
	    JPanel yPane = new JPanel(new GridLayout(1,0));
	    yPane.add(yLabel);
	    yPane.add(yField);
	    
	    JPanel sizePane = new JPanel(new GridLayout(1,0));
	    sizePane.add(sizeLabel);
	    sizePane.add(sizeField);
	    
	    JPanel namePane = new JPanel(new GridLayout(1,0));
	    namePane.add(nameLabel);
	    namePane.add(nameField);
	    
	    panel = new JPanel(new GridLayout(0,1));
	    panel.add(xPane);
	    panel.add(yPane);
	    panel.add(sizePane);
	    panel.add(namePane);
	    panel.add(colorList);
	    panel.add(b1);
	    
	    this.setContentPane(panel);
	    this.setVisible(true);
	}

	public Island newIsland() {
		return new Island("test add", 100, 100);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Island i;
		if("add island".equals(e.getActionCommand())){
			double x = ((Number)xField.getValue()).doubleValue();
			double y = ((Number)yField.getValue()).doubleValue();
			int size = ((Number)sizeField.getValue()).intValue();
			String name = nameField.getText();
			i = new Island(name, x, y);
			i.setIslandSize(size);
			switch((String)colorList.getSelectedItem()){
				case "Green" : 
					i.setColor(Color.GREEN);
					break;
				case "Red" : 
					i.setColor(Color.RED);
					break;
				case "Blue" : 
					i.setColor(Color.BLUE);
					break;
				case "Yellow" : 
					i.setColor(Color.YELLOW);
					break;
				default : 
					i.setColor(Color.PINK);
			}
			map.addIslandToMap(i);
		}
		
	}

}
