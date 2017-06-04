package map;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import island.Island;

public class WAMap extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private List<Island> islands = new ArrayList<Island>();
	private JPanel panel;
	
	public WAMap() {
		this.setTitle("WA map");
	    this.setSize(500, 500);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    JMenuBar menuBar = new JMenuBar();
	    JMenu action = new JMenu("Actions");
	    menuBar.add(action);
	    
	    JMenuItem addIsland = new JMenuItem("Add island");
	    addIsland.setActionCommand("add island");
	    addIsland.addActionListener(this);
	    action.add(addIsland);
	    
	    JMenuItem addIslandRef = new JMenuItem("Add island by reference");
	    addIslandRef.setActionCommand("add island ref");
	    addIslandRef.addActionListener(this);
	    action.add(addIslandRef);
	    
	    this.setJMenuBar(menuBar);
	    
	    panel = new JPanel();
	    panel.setLayout(null);
	    setTestIsland();
	    draw();
	    //panel.setBackground(Color.ORANGE);
	    this.setContentPane(panel);
	    
	    this.setVisible(true);
	    this.repaint();
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
	    for(Island i : islands){
	    	panel.add(i);
	    }
	    this.repaint();
	}
	
	public void addIslandToMap(Island i){
		islands.add(i);
		this.draw();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if("add island".equals(e.getActionCommand())){
			new AddIsland(this);
		} else if("add island ref".equals(e.getActionCommand())){
			new AddIslandRef(this);
		}
		
	}
	
	/**
	 * Search an island by a name
	 * @param name
	 * @return the first island with the name, null if not found
	 */
	public Island getIslandByName(String name){
		boolean found = false;
		Island i = null;
		for(ListIterator<Island> it = islands.listIterator(0); it.hasNext() && !found; ){
			i = it.next();
			found = (name.toLowerCase()).equals( i.getName().toLowerCase() );
		}
		if(found){
			return i;
		} else {
			return null;
		}
	}

}
