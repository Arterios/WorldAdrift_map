package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import loadSave.LoadJson;
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
	    
	    JMenu preferences = new JMenu("Preferences");
	    menuBar.add(preferences);
	    
	    JMenuItem addIsland = new JMenuItem("Add island");
	    addIsland.setActionCommand("add island");
	    addIsland.addActionListener(this);
	    action.add(addIsland);
	    
	    JMenuItem addIslandRef = new JMenuItem("Add island by reference");
	    addIslandRef.setActionCommand("add island ref");
	    addIslandRef.addActionListener(this);
	    action.add(addIslandRef);
	    
	    JMenuItem refresh = new JMenuItem("Refresh");
	    refresh.setActionCommand("refresh");
	    refresh.addActionListener(this);
	    action.add(refresh);
	    
	    JMenuItem load = new JMenuItem("Load");
	    load.setActionCommand("load");
	    load.addActionListener(this);
	    action.add(load);
	    
	    JMenuItem setFolder = new JMenuItem("Set data folder");
	    setFolder.setActionCommand("set folder");
	    setFolder.addActionListener(this);
	    preferences.add(setFolder);
	    
	    this.setJMenuBar(menuBar);
	    this.setContentPane(map);
	    this.setVisible(true);
	    this.repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
			case "add island" :
				new AddIsland(map);
				break;
			case "add island ref" :
				new AddIslandRef(map);
				break;
			case "set folder" :
				Preferences.setFolderDialog();
				break;
			case "refresh" :
				map.repaint();
				break;
			case "load":
				String file = (String)JOptionPane.showInputDialog(null, "Enter file name :",
		                "Load", JOptionPane.PLAIN_MESSAGE, null, null, "islands.json");
				LoadJson l = new LoadJson(file);
				l.load(map);
				break;
			default :
		}
		
	}

}
