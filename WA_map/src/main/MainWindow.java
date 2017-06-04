package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import map.AddIsland;
import map.AddIslandRef;
import map.WAMap;

public class MainWindow extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	WAMap map;

	public MainWindow() {
		this.setTitle("WA map");
	    this.setSize(500, 500);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    map = new WAMap();
	    
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
	    this.setContentPane(map);
	    this.setVisible(true);
	    this.repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if("add island".equals(e.getActionCommand())){
			new AddIsland(map);
		} else if("add island ref".equals(e.getActionCommand())){
			new AddIslandRef(map);
		}
		
	}

}
