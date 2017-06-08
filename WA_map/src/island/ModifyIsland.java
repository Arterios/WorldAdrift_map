package island;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import map.WAMap;

public class ModifyIsland extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private Island island;
	private boolean isByRef;
	private WAMap map;
	
	private JLabel angleLabel = new JLabel("angle from reference");
	private JLabel distanceLabel = new JLabel("distance from reference");
	private JLabel sizeLabel = new JLabel("size of the island");
	private JLabel nameLabel = new JLabel("name the island");
	private JLabel nameRefLabel = new JLabel("name of the reference island");
	private JLabel xLabel = new JLabel("x coordinate");
	private JLabel yLabel = new JLabel("y coordinate");
	
	private JFormattedTextField xField;
	private JFormattedTextField yField;
	private JFormattedTextField angleField;
	private JFormattedTextField distanceField;
	private JFormattedTextField sizeField;
	private JTextField nameField;
	private JTextField nameRefField;
	private JComboBox<String> colorList;
	private JCheckBox degree;

	public ModifyIsland(Island island, WAMap map) {
		this.setTitle("Modify island " + island.getName());
	    this.setSize(400, 300);
	    this.setLocationRelativeTo(null);
	    this.island = island;
	    this.map = map;
	    this.panel = new JPanel(new GridLayout(0,1));
	    
	    if(island.getRefIsland() == null){
	    	setPanelOrigin();
	    } else {
	    	setPanelByRef();
	    }
	    
	    this.setContentPane(panel);
	    this.setVisible(true);
	}
	
	private void setPanelByRef(){
		isByRef = true;
		
		JButton convertO = new JButton("Convert to origin");
		convertO.setActionCommand("convertO");
		convertO.addActionListener(this);
		
		JButton accept = new JButton("accept");
	    accept.setActionCommand("accept");
	    accept.addActionListener(this);
	    
	    JButton cancel = new JButton("cancel");
	    cancel.setActionCommand("cancel");
	    cancel.addActionListener(this);
	    
	    NumberFormat decFormat = DecimalFormat.getNumberInstance();
	    decFormat.setMinimumFractionDigits(1);
	    decFormat.setMaximumFractionDigits(4);
	    
	    NumberFormat sizeFormat = NumberFormat.getNumberInstance();
	    
	    angleField = new JFormattedTextField(decFormat);
	    angleField.setValue(island.getAngle());
	    angleField.setColumns(10);
	    angleLabel.setLabelFor(angleField);
	    degree = new JCheckBox("Degree");
	    degree.setSelected(false);
	    
	    
	    distanceField = new JFormattedTextField(decFormat);
	    distanceField.setValue(island.getDistance());
	    distanceField.setColumns(10);
	    distanceLabel.setLabelFor(distanceField);
	    
	    sizeField = new JFormattedTextField(sizeFormat);
	    sizeField.setValue(island.getIslandSize());
	    sizeField.setColumns(5);
	    sizeLabel.setLabelFor(sizeField);
	    
	    nameField = new JTextField(20);
	    nameField.setText(island.getName());
	    nameLabel.setLabelFor(nameField);
	    
	    nameRefField = new JTextField(20);
	    if(island.getRefIsland() == null){
	    	nameRefField.setText("Enter a name");
	    } else {
	    	nameRefField.setText(island.getRefIsland().getName());
	    }
	    nameRefLabel.setLabelFor(nameRefField);
	    
	    String[] color = {"green", "red", "blue", "yellow"};
	    colorList = new JComboBox<String>(color);
	    colorList.setSelectedItem(island.getIslandColor());
	    
	    JPanel anglePane = new JPanel(new GridLayout(1,0));
	    anglePane.add(angleLabel);
	    anglePane.add(angleField);
	    anglePane.add(degree);
	    
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
	    
	    JPanel buttonPane = new JPanel(new GridLayout(1, 0));
	    buttonPane.add(accept);
	    buttonPane.add(cancel);
	    
	    panel.removeAll();
	    panel.add(convertO);
	    panel.add(anglePane);
	    panel.add(distancePane);
	    panel.add(sizePane);
	    panel.add(namePane);
	    panel.add(nameRefPane);
	    panel.add(colorList);
	    panel.add(buttonPane);
	    panel.revalidate();
	    this.repaint();
	}
	
	private void setPanelOrigin(){
		isByRef = false;
		
		JButton convertR = new JButton("Convert by reference");
		convertR.setActionCommand("convertR");
		convertR.addActionListener(this);
		
		JButton accept = new JButton("accept");
	    accept.setActionCommand("accept");
	    accept.addActionListener(this);
	    
	    JButton cancel = new JButton("cancel");
	    cancel.setActionCommand("cancel");
	    cancel.addActionListener(this);
	    
	    NumberFormat coordFormat = DecimalFormat.getNumberInstance();
	    coordFormat.setMinimumFractionDigits(1);
	    coordFormat.setMaximumFractionDigits(3);
	    coordFormat.setRoundingMode(RoundingMode.HALF_EVEN);
	    
	    NumberFormat sizeFormat = NumberFormat.getNumberInstance();
	    
	    double[] coord = island.getCoord();
	    
	    xField = new JFormattedTextField(coordFormat);
	    xField.setValue(coord[0]);
	    xField.setColumns(10);
	    xLabel.setLabelFor(xField);
	    
	    yField = new JFormattedTextField(coordFormat);
	    yField.setValue(coord[1]);
	    yField.setColumns(10);
	    yLabel.setLabelFor(yField);
	    
	    sizeField = new JFormattedTextField(sizeFormat);
	    sizeField.setValue(island.getIslandSize());
	    sizeField.setColumns(5);
	    sizeLabel.setLabelFor(sizeField);
	    
	    nameField = new JTextField(20);
	    nameField.setText(island.getName());
	    nameLabel.setLabelFor(nameField);
	    
	    String[] color = {"green", "red", "blue", "yellow"};
	    colorList = new JComboBox<String>(color);
	    colorList.setSelectedItem(island.getIslandColor());
	    
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
	    
	    JPanel buttonPane = new JPanel(new GridLayout(1, 0));
	    buttonPane.add(accept);
	    buttonPane.add(cancel);
	    
	    panel.removeAll();
	    panel.add(convertR);
	    panel.add(xPane);
	    panel.add(yPane);
	    panel.add(sizePane);
	    panel.add(namePane);
	    panel.add(colorList);
	    panel.add(buttonPane);
	    panel.revalidate();
	    this.repaint();
	}
	
	private boolean updateIsland(){
		String name = nameField.getText();
		if(!name.equals(island.getName()) && map.getIslandByName(name) != null){
			JOptionPane.showMessageDialog(null, "Island \""+name+"\" already exist.");
			return false;
		}
		
		if(isByRef){
			String nameRef = nameRefField.getText();
			Island ref = map.getIslandByName(nameRef);
			if(ref != null){
				island.setRefIsland(ref);
				
				double angle = 0;
				if(degree.isSelected()){
					angle = Math.toRadians(((Number)angleField.getValue()).doubleValue());
				} else {
					angle = ((Number)angleField.getValue()).doubleValue();
				}
				island.setAngle(angle);
				
				double distance = ((Number)distanceField.getValue()).doubleValue();
				island.setDistance(distance);
			} else {
				JOptionPane.showMessageDialog(null, "Island \""+name+"\" does not exist.");
				return false;
			}
		} else {
			double[] coord = {0,0};
			coord[0] = ((Number)xField.getValue()).doubleValue();
			coord[1] = ((Number)yField.getValue()).doubleValue();
			island.setCoord(coord);
			island.setRefIsland(null);
			island.setAngle(0);
			island.setDistance(0);
		}
		
		island.setName(name);
		
		int size = ((Number)sizeField.getValue()).intValue();
		island.setIslandSize(size);
		
		island.setColor((String)colorList.getSelectedItem());
		
		return true;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case "convertO":
				setPanelOrigin();
				break;
			case "convertR" :
				setPanelByRef();
				break;
			case "cancel" :
				this.dispose();
				break;
			case "accept" :
				if(updateIsland()){
					map.repaint();
					this.dispose();
				}
				break;
			default:
				break;
		}
		
	}

}
