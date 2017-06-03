package map;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import island.Island;

public class WAMap {
	private List<Island> islands = new ArrayList<Island>();
	
	public WAMap() {
		// TODO Auto-generated constructor stub
	}
	
	private void setTestIsland(){
		Island ref = new Island("ref", 250.0,  250.0);
		ref.setIslandSize(30);
		
		Island t1 = new Island("t1", ref, 3.14, 50);
		t1.setColor(Color.GREEN);
		t1.setIslandSize(15);
		Island t11 = new Island("t11", t1, 2, 100);
		t11.setColor(Color.GREEN);
		Island t12 = new Island("t12", t1, 4, 50);
		t12.setColor(Color.GREEN);
		
		Island t2 = new Island("t2", ref, 0, 50);
		t2.setColor(Color.YELLOW);
		t2.setIslandSize(15);
		Island t21 = new Island("t21", t2, 1, 50);
		t21.setColor(Color.RED);
		Island t22 = new Island("t22", t2, 5, 100);
		t22.setColor(Color.RED);
		
		islands.add(ref);
		islands.add(t1);
		islands.add(t2);
		islands.add(t11);
		islands.add(t12);
		islands.add(t21);
		islands.add(t22);
		
	}
	
	public void draw(){
		JFrame window = new JFrame();
		window.setTitle("WA map");
	    window.setSize(500, 500);
	    window.setLocationRelativeTo(null);
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    JPanel panel = new JPanel();
	    panel.setLayout(null);
	    setTestIsland();
	    for(Island i : islands){
	    	panel.add(i);
	    }
	    //panel.setBackground(Color.ORANGE);
	    window.setContentPane(panel);
	    //window.pack();
	    
	    window.setVisible(true);
	    window.repaint();
	}

}
