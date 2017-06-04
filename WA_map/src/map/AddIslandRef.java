package map;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import island.Island;

/**
 * @author Hugo
 * Add an island by reference to another island
 */
public class AddIslandRef extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private WAMap map;
	private JPanel panel;
	
	private JLabel angleLabel = new JLabel("angle from reference");
	private JLabel distanceLabel = new JLabel("distance from reference");
	private JLabel sizeLabel = new JLabel("size of the island");
	private JLabel nameLabel = new JLabel("name the island");
	private JLabel nameRefLabel = new JLabel("name of the reference island");
	
	private JFormattedTextField angleField;
	private JFormattedTextField distanceField;
	private JFormattedTextField sizeField;
	private JTextField nameField;
	private JTextField nameRefField;
	private JComboBox<String> colorList;

	public AddIslandRef(WAMap map) {
		this.map = map;
		this.setTitle("Add island by reference");
	    this.setSize(400, 300);
	    this.setLocationRelativeTo(null);
	    
	    JButton b1 = new JButton("Add island");
	    b1.setActionCommand("add island");
	    b1.addActionListener(this);
	    
	    NumberFormat decFormat = DecimalFormat.getNumberInstance();
	    decFormat.setMinimumFractionDigits(1);
	    decFormat.setMaximumFractionDigits(4);
	    
	    NumberFormat sizeFormat = NumberFormat.getNumberInstance();
	    
	    angleField = new JFormattedTextField(decFormat);
	    angleField.setValue(new Double(0.0));
	    angleField.setColumns(10);
	    angleLabel.setLabelFor(angleField);
	    
	    distanceField = new JFormattedTextField(decFormat);
	    distanceField.setValue(new Double(0.0));
	    distanceField.setColumns(10);
	    distanceLabel.setLabelFor(distanceField);
	    
	    sizeField = new JFormattedTextField(sizeFormat);
	    sizeField.setValue(new Integer(7));
	    sizeField.setColumns(5);
	    sizeLabel.setLabelFor(sizeField);
	    
	    nameField = new JTextField(20);
	    nameLabel.setLabelFor(nameField);
	    
	    nameRefField = new JTextField(20);
	    nameRefLabel.setLabelFor(nameRefField);
	    
	    String[] color = {"Green", "Red", "Blue", "Yellow"};
	    colorList = new JComboBox<String>(color);
	    
	    JPanel anglePane = new JPanel(new GridLayout(1,0));
	    anglePane.add(angleLabel);
	    anglePane.add(angleField);
	    
	    JPanel distancePane = new JPanel(new GridLayout(1,0));
	    distancePane.add(distanceLabel);
	    distancePane.add(distanceField);
	    
	    JPanel sizePane = new JPanel(new GridLayout(1,0));
	    sizePane.add(sizeLabel);
	    sizePane.add(sizeField);
	    
	    JPanel namePane = new JPanel(new GridLayout(1,0));
	    namePane.add(nameLabel);
	    namePane.add(nameField);
	    
	    JPanel nameRefPane = new JPanel(new GridLayout(1,0));
	    nameRefPane.add(nameRefLabel);
	    nameRefPane.add(nameRefField);
	    
	    panel = new JPanel(new GridLayout(0,1));
	    panel.add(anglePane);
	    panel.add(distancePane);
	    panel.add(sizePane);
	    panel.add(namePane);
	    panel.add(nameRefPane);
	    panel.add(colorList);
	    panel.add(b1);
	    
	    this.setContentPane(panel);
	    this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Island i;
		if("add island".equals(e.getActionCommand())){
			double angle = ((Number)angleField.getValue()).doubleValue();
			double distance = ((Number)distanceField.getValue()).doubleValue();
			int size = ((Number)sizeField.getValue()).intValue();
			String name = nameField.getText();
			String nameRef = nameRefField.getText();
			if(map.getIslandByName(name) != null){
				JOptionPane.showMessageDialog(null, "Island \""+name+"\" already exist.");
			} else {
				Island ref = map.getIslandByName(nameRef);
				if(ref != null){
					i = new Island(name, ref, angle, distance);
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
				} else {
					JOptionPane.showMessageDialog(null, "Island \""+name+"\" does not exist.");
				}
			}
		}
		
	}

}
