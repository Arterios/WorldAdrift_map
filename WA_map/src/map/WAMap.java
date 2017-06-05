package map;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import javax.swing.JPanel;

import island.Island;
import main.InfosBox;

public class WAMap extends JPanel implements ActionListener,MouseMotionListener,MouseListener{
	private static final long serialVersionUID = 1L;
	private List<Island> islands = new ArrayList<Island>();
	private int x1, y1;
	
	public WAMap() {
	    this.setLayout(null);
	    this.setAutoscrolls(true);
	    this.setPreferredSize(new Dimension( 500,500));
	    
	    addMouseMotionListener(this);
	    addMouseListener(this);
	    
	    this.setVisible(true);
	    this.repaint();
	}
	
	public void addIslandToMap(Island i){
		double[] coord = i.getCoord();
		if(coord[0] > this.getWidth()){
			this.setWidht((int)coord[0] + i.getIslandSize());
		}
		if(coord[1] > this.getHeight()){
			this.setHeight((int)coord[1] + i.getIslandSize());
		}
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
	
	private void updateInfosBox(){
		Component c = null;
		try{
			c = findComponentAt(getMousePosition());
		} catch(NullPointerException e) {
			//do nothing
		}
		if(c == this || c == null)
			InfosBox.setNameLabel("The void");
		else
			InfosBox.setNameLabel(c.getName());
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		InfosBox.setMouseCoordLabel("X : " + e.getX() + " | Y : " + e.getY() );
		updateInfosBox();
		//System.out.println(getWidth() + " | " + getHeight());
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int x2 = e.getX() - x1;
		int y2 = e.getY() - y1;
		double dist = Math.sqrt( x2*x2 + y2*y2 );
		InfosBox.setDistanceLabel("Distance =" + Math.ceil(dist));
		
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
	
	public void setWidht(int x){
		Dimension dim = this.getPreferredSize();
		this.setPreferredSize(new Dimension( x, dim.height));
	}
	
	public void setHeight(int x){
		Dimension dim = this.getPreferredSize();
		this.setPreferredSize(new Dimension( dim.width, x));
	}


}
