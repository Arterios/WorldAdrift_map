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

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import island.Island;
import island.ModifyIsland;
import main.InfosBox;
import main.Utils;

public class WAMap extends JPanel implements ActionListener,MouseMotionListener,MouseListener{
	private static final long serialVersionUID = 1L;
	private List<Island> islands = new ArrayList<Island>();
	private JPopupMenu popup;
	
	/* temporary field for event */
	private int x1, y1;
	private Island quickAddRef;
	
	public WAMap() {
	    this.setLayout(null);
	    this.setAutoscrolls(true);
	    this.setPreferredSize(new Dimension( 500,500));
	    
	    addMouseMotionListener(this);
	    addMouseListener(this);
	    
	    popup = new JPopupMenu();
		
		JMenuItem modify = new JMenuItem("Mofify island");
		modify.setActionCommand("modify");
		modify.addActionListener(this);
		popup.add(modify);
		
		JMenuItem delete = new JMenuItem("Delete island");
		delete.setActionCommand("delete");
		delete.addActionListener(this);
		popup.add(delete);
	    
	    this.setVisible(true);
	    this.repaint();
	}
	
	public void addIslandToMap(Island i){
		double[] coord = i.getCoord();
		if((coord[0] + i.getIslandSize()) > this.getWidth()){
			this.setWidht((int)coord[0] + i.getIslandSize());
		}
		if((coord[1] + i.getIslandSize()) > this.getHeight()){
			this.setHeight((int)coord[1] + i.getIslandSize());
		}
		islands.add(i);
		this.add(i);
		this.repaint();
	}
	
	/**
	 * Remove an island from the map, can check if the island is referenced by at least
	 * one other island.
	 * @param island the island to remove
	 * @param ignoreError True to ignore possible reference
	 */
	public void removeIsland(Island island, boolean ignoreError){
		if(isReferenced(island)){
			JOptionPane.showMessageDialog(null, "Cannot remove \""+island.getName()+"\" : it is referenced by other(s) island(s).");
		} else {
			islands.remove(island);
			this.remove(island);
			this.revalidate();
			this.repaint();
		}
	}
	
	public void removeIsland(Island island){
		removeIsland(island, false);
	}
	
	public boolean isReferenced(Island island){
		boolean found = false;
		Island i = null;
		for(ListIterator<Island> it = islands.listIterator(0); it.hasNext() && !found; ){
			i = it.next();
			found = (island == i.getRefIsland());
		}
		return found;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Island island = null;
		try{
			island = (Island)getComponentAt(x1, y1);
		} catch (Exception ex) {
			// TODO: handle exception
		}
		switch(e.getActionCommand()){
			case "add island" :
				new AddIsland(this);
				break;
			case "add island ref" :
				new AddIslandRef(this);
				break;
			case "modify" :
				//System.out.println(island);
				new ModifyIsland(island,this);
				break;
			case "delete" :
				removeIsland(island);;
				break;
			default :
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
		int x = e.getX() - x1;
		int y = e.getY() - y1;
		double dist = Math.sqrt( x*x + y*y );
		InfosBox.setDistanceLabel("Distance = " + Math.ceil(dist));
		double acosX = Math.acos(x/dist);
		double asinY = Math.asin(y/dist);
		double angle;
		if(asinY>=0){
			angle = acosX;
		} else {
			angle = 2*Math.PI - acosX;
		}
		angle = Double.parseDouble(Utils.DF.format(angle));
		InfosBox.setAngleLabel("Angle = " + angle);
		if(e.isShiftDown()){
			if(quickAddRef != null){
				quickAddRef.setAngle(angle);
				quickAddRef.setDistance(Math.ceil(dist));
				//System.out.println(quickAddRef);
				this.repaint(e.getX()-50,e.getY()-50,100,100);
			}
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(SwingUtilities.isRightMouseButton(e)){
			if("island.Island".equals( findComponentAt(getMousePosition()).getClass().getName()) )
				popup.show(e.getComponent(),e.getX(), e.getY());
		}

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
		//System.out.println(x1 + " " + y1);
		if(e.isShiftDown()){
			Component c = null;
			Island originIsland = null;
			try{
				c = findComponentAt(getMousePosition());
				originIsland = (Island)c;
			} catch(Exception e1) {
				originIsland = null;
			} 
			//System.out.println(originIsland);
			if(originIsland != null){
				quickAddRef = new Island("Island"+ islands.size(), originIsland, 0,0 );
				addIslandToMap(quickAddRef);
			}
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		InfosBox.setAngleLabel("");
		InfosBox.setDistanceLabel("");
		quickAddRef = null;
		
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
