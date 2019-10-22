package code.objects;

import framework.engine.DisplayableObject;
import framework.engine.Scene;
import framework.interfaces.Animation;
import processing.core.PApplet;
import processing.core.PConstants;
/**
 * This class creates the ferries wheel and the air bubbles 
 * @author itxsa27
 *
 */
public class Ferris  extends DisplayableObject implements Animation{
	private float rotation = 0;
	// radios, speed and location of air bubbles
	private float[][] risingBubbles = new float[30][4];
	private float[][] surfaceBubbles = new float[300][4];
	
	public Ferris(Scene parent) {
		super(parent);
		//Initialising air bubbles 
		for(int i = 0; i < risingBubbles.length; i++){
			risingBubbles[i][0] = parent.random(-3f, 3f);
			risingBubbles[i][1] = parent.random(-150, 0);
			risingBubbles[i][2] = parent.random(-3f, 3f);
			risingBubbles[i][3] = parent.random(0.6f, 2f);
		}
		for(int i = 0; i < surfaceBubbles.length; i++){
			surfaceBubbles[i][0] = parent.random(-60f, 60f);
			surfaceBubbles[i][1] = parent.random(-0.2f, 0.2f);
			surfaceBubbles[i][2] = parent.random(-60f, 60f);
			surfaceBubbles[i][3] = parent.random(-0.2f, 0.2f);
		}
	}

	@Override
	public void display() {
		//save the state of the seen
		parent.pushMatrix();
		parent.pushStyle();
        parent.translate(pos.x,pos.y,pos.z);    // move to position
		
        parent.noStroke();
		
        //draw ferries parts
		rotatingEye();
		baseMotor();
		bubbles();
		
		//return the state of the seen
		parent.popStyle();
		parent.popMatrix();
	}
	//Draws both kind of bubbles (rising and floating)
	private void bubbles(){
		parent.pushMatrix();
		parent.pushStyle();
		parent.fill(3, 155, 255, 80);	//set the colour and transparency
		parent.translate(-35, 30, 5);	// set the position
		// draws rising bubbles
		for(int i = 0; i < risingBubbles.length; i++){
			parent.pushMatrix();
			parent.translate(risingBubbles[i][0], risingBubbles[i][1], risingBubbles[i][2]);// move the bubble to its position
			parent.sphere(risingBubbles[i][3]);//draw it
			parent.popMatrix();

		}
		// draws floating bubbles
		for(int i = 0; i < surfaceBubbles.length; i++){
			parent.pushMatrix();
			parent.translate(surfaceBubbles[i][0], pos.y -170, surfaceBubbles[i][2]);		// move the bubble to its position
			parent.sphere((Math.abs(surfaceBubbles[i][3])+Math.abs(surfaceBubbles[i][1])) * 5); //draw it
			parent.popMatrix();

		}
		parent.popStyle();
		parent.popMatrix();
	}
	// creates base of the ferries wheel and the motor of bubble maker
	private void baseMotor(){
		// left side legs of the wheel
		parent.fill(100, 100, 100);
		parent.pushMatrix();
		parent.rotateX(PApplet.radians(-20));
		parent.rotateZ(PApplet.radians(-20));
		cylinder(50, 1);
		parent.rotateZ(PApplet.radians(40));
		cylinder(50, 1);
		parent.popMatrix();
		parent.pushMatrix();
		// right side legs of the wheel
		parent.translate(0, 0, 10);
		parent.rotateX(PApplet.radians(20));
		parent.rotateZ(PApplet.radians(-20));
		cylinder(50, 1);
		parent.rotateZ(PApplet.radians(40));
		cylinder(50, 1);
		parent.popMatrix();
		
		//The metal base of the ferries wheel and bubble maker with the same object 
		parent.pushMatrix();
		parent.pushStyle();
		parent.fill(60, 60, 100);
		parent.translate(-30, 50, 35);
		Material m = new Material(parent, Material.METAL);
		m.size(60,4,60);
		m.display();		//Ferries base
		parent.translate(-12, 0, -20);
		m.size(20,7,20);
		m.display();	//bubble makers  base
		parent.translate(9, 0, -10);
		parent.rotateX(PConstants.PI);
		//Cylinders of the bubble maker because the inside each cylinder is invisible so 
		//double cylinders with different directions are created to over come this
		for (int i = 0; i < 5; i++){
			cylinder(8 + i * 2, 10 - i* 2);
			cylinderR(8 + i * 2, 10 - i * 2);
		}
		parent.popStyle();
		parent.popMatrix();
	}
	// creates ferries rotating wheel
	private void rotatingEye(){
		parent.pushMatrix();
		parent.pushStyle();
				
		parent.fill(100);
		parent.rotateZ(rotation);
		
		//Far side 
		for(int i = 0; i < 10; i++){
			// far side
			
			cylinder(40, 0.5f);
			parent.pushMatrix();
			parent.rotateX(PConstants.PI / 2);
		
			
			//mid Links
			parent.translate(0, 0, 27f);
			cylinder(10, 0.5f);			
			
			//outer links
			parent.translate(0, 0, 9);
			cylinder(10, 0.5f);
			// The bucket
			
			parent.translate(0, 5.2f, 2);
			parent.box(4, 9, 4);
			
			parent.translate(-2.6f, 0f, 2);
			parent.box(7, 9, 1);

			parent.popMatrix();
			
			joint();
			
			//near side
	        parent.translate(0,0,10);
			cylinder(40, 0.5f);
			joint();
	        parent.translate(0,0,-10);
	        
			parent.rotateZ(PConstants.PI / 5);
		}
		//inner big link
		parent.rotateX(PConstants.PI / 2);
		cylinder(10, 2f);	
		
		parent.popStyle();
		parent.popMatrix();
	}
	//creates joint cylinders between main cylinders of the eye
	private void joint(){
		parent.pushMatrix();
		parent.translate(0, 30, 0);

		parent.rotateZ(PApplet.radians(110));
		cylinder(18, 0.5f);
		parent.popMatrix();
	}
	
	@Override
	public void update(float dT) {
		rotation += 0.04;	// the rotation of the ferries wheel
		
		// rising bubble increases y and small random move or x.
		//if bubble gets to the top of water return it to base
		for(int i = 0; i < risingBubbles.length; i++){
			risingBubbles[i][1]-= parent.random(0.3f, 2)/2;
			if(Math.floor(parent.random(risingBubbles.length)) > i ){
				risingBubbles[i][0] = parent.random(-0.3f, 0.3f);
				risingBubbles[i][2] = parent.random(-0.3f, 0.3f);
			}
			if(risingBubbles[i][1] < pos.y -170)
				risingBubbles[i][1] = 0;
		}
		
		//floating bubbles on the surface, moves to direction of x, and z
		// if reaches on of the edges of the tank return to the start point with different 
		//speed and direction
		for(int i = 0; i < surfaceBubbles.length; i++){
			surfaceBubbles[i][0] += surfaceBubbles[i][1];		// move x
			surfaceBubbles[i][2] += surfaceBubbles[i][3];		//move x
			if(surfaceBubbles[i][0] > 300 || surfaceBubbles[i][0] < -110 ||
					surfaceBubbles[i][2] > 300 || surfaceBubbles[i][2] < -120	){
				surfaceBubbles[i][0] = 0;				//reset x
				surfaceBubbles[i][2] = 0;				//reset z
				surfaceBubbles[i][1] = parent.random(-0.2f, 0.2f);	//new x speed and direction the magnitude is their speed and sign creates direction
				surfaceBubbles[i][3] = parent.random(-0.2f, 0.2f);	//new z speed and direction the magnitude is their speed and sign creates direction
			}
		}
	}
	
	// helper function to create cylinder copied form lab codes 
	private void cylinder(float h, float r){
		float res = PConstants.PI * 0.1f;            // resolution (in radians: equivalent to 18 degrees)
		float x = r, z = 0.f;           // initialise x and z on right of cylinder centre
		float t = 0.f;                  // initialise angle as 0
		
		do{
			parent.beginShape(PConstants.QUADS);          // new QUAD
			// Create first points
			parent.normal(x,h,z);          
			parent.vertex(x,h,z);          // top
			parent.vertex(x,0,z);          // bottom
			// Iterate around circle
			t += res;               // add increment to angle
			x = r*PApplet.cos(t);           // move x and z around circle
			z = r*PApplet.sin(t);
			// Close quad
			
			parent.normal(x,h,z);          
			parent.vertex(x,0,z);          // bottom
			
			parent.vertex(x,h,z);          // top
			parent.endShape();                 // end shape
		
		}while(t <= 2*PConstants.PI);              // for a full circle (360 degrees)
	}
	
	//creates reverse cylinder to overcome the problem of transparent side 
	private void cylinderR(float h, float r){
		float res = PConstants.PI*0.1f;            // resolution (in radians: equivalent to 18 degrees)
		float x = r, z = 0.f;           // initialise x and z on right of cylinder centre
		float t = 0.f;                  // initialise angle as 0
		
		do{
			parent.beginShape(PConstants.QUADS);          // new QUAD
			// Create first points
			parent.normal(x,0,z);          
			parent.vertex(x,0,z);          // top
			
			parent.normal(x,h,z);          
			parent.vertex(x,h,z);          // bottom
			// Iterate around circle
			t += res;               // add increment to angle
			x = r*PApplet.cos(t);           // move x and z around circle
			z = r*PApplet.sin(t);
			// Close quad
			
			parent.normal(x,h,z);          			
			parent.vertex(x,h,z);          // bottom
			
			parent.normal(x,0,z);          
			parent.vertex(x,0,z);          // top
			
			parent.endShape();                 // end shape
		
		}while(t <= 2*PConstants.PI);              // for a full circle (360 degrees)
	}

}
