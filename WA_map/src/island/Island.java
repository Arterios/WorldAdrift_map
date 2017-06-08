package island;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import main.Preferences;

public class Island extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private static final int SCALE = 1; //number of pixels for 1 meter
	
	private int size;
	private Island refIsland;
	private double angle; //in radiant
	private double distance;
	private String name;
	private String description;
	private double[] coord;
	private Color c;

	public Island(String name, double x, double y){
		this.name = name;
		this.coord = new double[2];
		coord[0] = x;
		coord[1] = y;
		this.refIsland = null;
		angle = 0;
		distance = 0;
		setIslandSize(7);
		c = Color.BLUE;
	}

	public Island(String name, Island refIsland, double angle, double distance) {
		this.name = name;
		this.refIsland = refIsland;
		this.angle = angle;
		this.distance = distance;
		calcAbsoluteCoord();
		setIslandSize(7);
		c = Color.RED;
	}
	
	private void setCoordInPanel(){
		if(this.refIsland != null)
			calcAbsoluteCoord();
		Dimension psize = this.getPreferredSize();
		this.setBounds((SCALE*(int)coord[0] - size/2) ,
				(SCALE*(int)coord[1] - size/2) , psize.width, psize.height);
		
	}

	/**
	 * Calculate coordinate relatively to his reference island
	 * @return coordinates, index 0 is x, index 1 is y
	 */
	public double[] coordFromRef(){
		double[] ret = {0.0, 0.0};
		ret[0] = Math.cos(angle) * distance;
		ret[1] = Math.sin(angle) * distance;
		return ret;
	}
	
	/**
	 * Set absolute coordinate of the island
	 */
	private void calcAbsoluteCoord(){
		double[] refOffset = coordFromRef();
		double[] refCoord = refIsland.getCoord(); 
		double[] coord = {0.0, 0.0};
		coord[0] = refCoord[0] + refOffset[0];
		coord[1] = refCoord[1] + refOffset[1];
		this.coord = coord;
	}


	public double[] getCoord() {
		return coord;
	}
	
	public void setCoord(double[] c){
		coord = c;
	}
	
	
	public Island getRefIsland() {
		return refIsland;
	}
	
	public void setRefIsland(Island i){
		refIsland = i;
	}


	public double getAngle() {
		return angle;
	}


	public double getDistance() {
		return distance;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name){
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String desc){
		this.description = desc;
	}
	
    public Dimension getPreferredSize() {
        return new Dimension(size,size);
    }
    
    public void setColor(Color c){
    	this.c = c;
    }
	
	public void setAngle(double angle) {
		this.angle = angle;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
    
    public void setColor(String c){
    	switch(c.toLowerCase()){
    		case "green" :
    			setColor(Color.GREEN);
    			break;
    		case "red" :
    			setColor(Color.RED);
    			break;
    		case "blue" :
    			setColor(Color.BLUE);
    			break;
    		case "yellow" :
    			setColor(Color.YELLOW);
    			break;
    		default :
    			setColor(Color.PINK);
    	}
    }
    
    public String getIslandColor(){
    	if(c == Color.GREEN){
    		return "green";
    	}
    	if(c == Color.RED){
    		return "red";
    	}
    	if(c == Color.BLUE){
    		return "blue";
    	}
    	if(c == Color.YELLOW){
    		return "yellow";
    	}
    	return "pink";
    }
    
    public void setIslandSize(int t){
    	if(t > 7)
    		size = t;
    	else
    		size = 7;
    	setCoordInPanel();
    }
    
    public int getIslandSize(){
    	return size;
    }
	
	public void paintComponent(Graphics g){
		setCoordInPanel();
		super.paintComponent(g);
		try {
			File f = new File(Preferences.folder + "/images/" + name + ".png");
		    Image img = ImageIO.read(f);
		    g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		} catch (IOException e) {
			g.setColor(c);
			g.fillOval(0, 0, size, size);
		}
	}
}
