package main;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfosBox extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private static JLabel mouseCoord;
	private static JLabel distance;
	private static JLabel name;
	private static InfosBox me;

	public InfosBox() {
		me = this;
		this.setOpaque(false);
		this.setLayout(null);
		this.setBounds(0, 0, 200, 60);
		
		mouseCoord = new JLabel();
	    mouseCoord.setBounds(0, 0 , 200, 20);
	    this.add(mouseCoord);
	    
	    distance = new JLabel();
	    distance.setBounds(0, 20 , 200, 20);
	    this.add(distance);
	    
	    name = new JLabel();
	    name.setBounds(0, 40 , 200, 20);
	    this.add(name);
	}
	
	public static void setMouseCoordLabel(String s){
		mouseCoord.setText(s);
		me.repaint();
	}
	
	public static void setDistanceLabel(String s){
		distance.setText(s);
		me.repaint();
	}
	
	public static void setNameLabel(String s){
		name.setText(s);
		me.repaint();
	}

}
