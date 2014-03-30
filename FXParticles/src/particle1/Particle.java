// Rachael Colley 2014

package particle1;

import javafx.scene.paint.Paint;

public class Particle {

	private double posy;
	private double posx;
	private double targetx;
	private double targety;
	private double width;
	private double height;
	private Paint color;
	private double randomEase;
	private double travelRate;

	public Particle(double posx, double posy, double targetx, double targety,
			int width, int height, Paint color) {

		this.posy = posy;
		this.posx = posx;
		this.targetx = targetx;
		this.targety = targety;
		this.width = width;
		this.height = height;
		this.color = color;
		randomEase = Math.random() * 0.03;
		travelRate = 0.03;
	}

	public void update() {
		posx += calculateXDistance(targetx) * (travelRate);
		posy += calculateYDistance(targety) * (travelRate + randomEase);
	}

	private double  calculateYDistance(double yTarget) {
		return yTarget - posy;
	}

	private double  calculateXDistance(double xTarget) {
		return xTarget - posx;
	}
	
	// Getters / setters....

	public double getPosy() {
		return posy;
	}

	public double getPosx() {
		return posx;
	}

	public double getTargetx() {
		return targetx;
	}

	public double getTargety() {
		return targety;
	}

	public double getWidth() {
		return width;
	}
	
	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}

	public Paint getColor() {
		return color;
	}

	public double getRandomEase() {
		return randomEase;
	}

	public double getTravelRate() {
		return travelRate;
	}
	
	
	
}