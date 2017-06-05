package map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import javax.swing.JLabel;
import javax.swing.JPanel;

import island.Island;

public class WAMap extends JPanel implements ActionListener,MouseMotionListener,MouseListener{
	private static final long serialVersionUID = 1L;
	private List<Island> islands = new ArrayList<Island>();
	private JLabel mouseCoord;
	private JLabel distance;
	private int x1, y1;
	
	public WAMap() {
	    this.setSize(500, 500);
	    this.setLayout(null);
	    
	    addMouseMotionListener(this);
	    mouseCoord = new JLabel();
	    mouseCoord.setBounds(0, 0 , 200, 20);
	    this.add(mouseCoord);
	    
	    addMouseListener(this);
	    distance = new JLabel();
	    distance.setBounds(0, 20 , 200, 20);
	    this.add(distance);
	    
	    this.setVisible(true);
	    this.repaint();
	}
	
	public void addIslandToMap(Island i){
		islands.add(i);
		this.add(i);
		this.repaint();
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
	
	@Override
	public void mouseMoved(MouseEvent e) {
		mouseCoord.setText("X : " + e.getX() + " | Y : " + e.getY() );
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int x2 = e.getX() - x1;
		int y2 = e.getY() - y1;
		double dist = Math.sqrt( x2*x2 + y2*y2 );
		distance.setText("Distance =" + Math.ceil(dist) );
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		x1 = e.getX();
		y1 = e.getY();
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public List<Island> getIslandsList() {
		return islands;
	}
	
	

}
