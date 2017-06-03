package map;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import island.Island;

public class WAMap {
	
	public WAMap() {
		// TODO Auto-generated constructor stub
	}
	
	public void draw(){
		JFrame window = new JFrame();
		window.setTitle("WA map");
	    window.setSize(500, 500);
	    window.setLocationRelativeTo(null);
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    JPanel panel = new JPanel();
	    panel.setLayout(null);
	    Island ref = new Island("test", 250.0,  250.0);
	    ref.setIslandSize(15);
	    panel.add(ref);
	    panel.add(new Island("testFromRef", ref, 1.57, 50));
	    panel.add(new Island("testFromRef", ref, 3.14, 50));
	    panel.add(new Island("testFromRef", ref, 0, 50));
	    panel.add(new Island("testFromRef", ref, 4.70, 50));
	    //panel.setBackground(Color.ORANGE);
	    window.setContentPane(panel);
	    //window.pack();
	    
	    window.setVisible(true);
	}

}
