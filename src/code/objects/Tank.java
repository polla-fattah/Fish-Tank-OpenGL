package code.objects;


import processing.core.PConstants;
import processing.core.PImage;
import framework.engine.DisplayableObject;
import framework.engine.Scene;
/**
 * Creates tank with semi-transparent quads and Material class
 * @author itxsa27
 *
 */
public class Tank extends DisplayableObject {
	private PImage sand = parent.loadImage("texture\\sand.jpg");
	//constructors
	public Tank(Scene parent) {
		super(parent);
		
	}
	public Tank(Scene parent, int dim) {
		super(parent);
		size(dim);
	}
	public Tank(Scene parent, int l, int h, int w) {
		super(parent);
		size(l, w, h);
	}


	@Override
	public void display() {
		//This Aluminium textured material is used to create corners of the tank
		Material m = new Material(parent, Material.ALUMINUM);
		m.size(0.028f, 0.4f , 0.028f);
		
		// Save status of seen before starting
		parent.pushMatrix();
		parent.pushStyle();
		
        parent.strokeWeight(0.2f/scale.x);
        parent.stroke(228,217,187);
		
        parent.translate(pos.x,pos.y,pos.z);    // move to position
        parent.scale(scale.x,scale.y,scale.z);  // scale to size

        parent.pushMatrix();
        parent.pushStyle();
        parent.fill(100, 100, 200, 150);
        
        // Tank Floor 
        parent.translate(0, 0, 0);
        parent.fill(228, 217, 187) ;

        parent.beginShape(PConstants.QUADS);
        parent.texture(sand);
        parent.vertex(0.49f, -0.0125f, 0.5f, 0, 0);
        parent.vertex(0.49f, -0.0125f, -0.5f, 0, 8);
        parent.vertex(-0.49f, -0.0125f, -0.5f, 8, 8);
        parent.vertex(-0.49f, -0.0125f, 0.5f, 8, 0);
        parent.endShape();
		//parent.box(1, 0.025f , 1);
        parent.translate(-0.5f, 0.0125f, 0.5f);
		 m.size(1, 0.023f , 1);
		 m.display();
		 parent.popStyle();
		parent.popMatrix(); parent.pushMatrix();// reset the Scene
        
		// Tank water
		parent.pushStyle();

        parent.fill(0, 0, 255, 90);
        parent.translate(0, -0.35f, 0);
		parent.box(1, 0.005f , 1);
		parent.popStyle();
		parent.popMatrix(); parent.pushMatrix();// reset the Scene
        
        parent.fill(100, 100, 200, 50);
        m.size(0.028f, 0.4f , 0.028f);
		// near side
		parent.translate(0, -0.19f, 0.49f);
		parent.box(1, 0.4f , 0.025f);
		parent.translate(0.473f, 0.2f, 0.0125f);
		m.display();
		parent.popMatrix(); parent.pushMatrix();// reset the Scene
		
		//far side
		parent.translate(0, -0.19f, -0.485f);
		parent.box(1, 0.4f , 0.025f);
		parent.translate(0.473f, 0.2f, 0.0125f);

		m.display();

		parent.popMatrix(); parent.pushMatrix();// reset the Scene
		
		//Left side
		parent.translate(-0.485f, -0.19f, 0);
		parent.box(0.025f, 0.4f , 1);
		parent.translate(-0.0138f, 0.2f,0.503f);

		m.display();

		parent.popMatrix(); parent.pushMatrix();// reset the Scene

		//Right side
		parent.translate(0.485f, -0.19f, 0);
		parent.box(0.025f, 0.4f , 1);
		parent.translate(-0.98f, 0.2f, -0.47f);

		m.display();

		parent.popMatrix();// reset the Scene
		
		parent.popStyle();
		parent.popMatrix();
		
	}

}
