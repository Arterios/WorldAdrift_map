package main;

import javax.swing.JOptionPane;

public class Preferences {
	public static String folder = "F:/hugor/desktop/";
	public Preferences() {
		// TODO Auto-generated constructor stub
	}
	
	public static void setFolderDialog(){
		folder = (String)JOptionPane.showInputDialog(null, "Enter new folder path (use / and not \\) :",
                "Set folder", JOptionPane.PLAIN_MESSAGE, null, null, folder);
	}

}
