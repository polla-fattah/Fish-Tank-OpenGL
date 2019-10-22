package code.objects;

import framework.engine.DisplayableObject;
import framework.engine.Scene;
import processing.core.PApplet;

/**
 * Creates the plant at the floor of the tank 
 * @author itxsa27
 *
 */
public class Plants  extends DisplayableObject{
	private static final int  LEAVES = 50;		//number of leaves in one plant
	
	// Each leave's x,y,z end tip location location 
	private int[] x = new int[LEAVES];
	private int[] y = new int[LEAVES];
	private int[] z = new int[LEAVES];
	
	//Initialises size the location of each plant
	public Plants(Scene parent, float xx, float yy, float zz) {
		super(parent);
		size(xx, yy, zz);
		// Creates the location of each plants end tip
		for(int l = 0; l<LEAVES; l++) {
			x[l] = PApplet.floor(parent.random(-scale.x, scale.x));
			y[l] = PApplet.floor(parent.random(-scale.y, 0));
			z[l] = PApplet.floor(parent.random(-scale.z, scale.z));
		}
	}
	
	// Creates multiple plants on the bet of the tank
	@Override
	public void display() {
		parent.pushMatrix();
		parent.pushStyle();
		parent.lights();
        parent.translate(pos.x,pos.y,pos.z);    // move to position

		plant(80.f,50.f,100.f, 0, 200, 0);
		plant(-80.f,50.f,150.f, 0, 50, 0);
		plant(180.f,50.f,-100.f, 0, 150, 0);
		plant(100.f,50.f,-120.f, 0, 150, 0);
		plant(110.f,50.f,140.f, 0, 10, 0);
		plant(90.f,50.f,60.f, 0, 50, 0);

		  parent.popStyle();
		  parent.popMatrix();
		
	}
	//Creates one plant at the specified location
	private void plant(float px, float py, float pz, float cr, float cg, float cb){
		parent.pushMatrix();
		parent.pushStyle();
		parent.noFill();
        parent.translate(px,py,pz);    // move to position

		parent.stroke(cr, cg, cb);
		parent.smooth();
		  for(int l = 0; l < LEAVES; l++) {
			  parent.strokeWeight(1);
			  parent.normal(pos.x, pos.y, pos.z);
			  // All leaves have same base with different end points
			  parent.bezier(0, 0, 0, 0, -scale.x, 0, x[l], y[l], z[l], x[l], y[l], z[l]);
			  parent.strokeWeight(4);
			  parent.point(x[l], y[l], z[l]);
		  }
		  parent.popStyle();
		  parent.popMatrix();
	}

}
