package code.objects;

import framework.engine.DisplayableObject;
import framework.engine.Scene;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
/**
 * This class creates the stage (room) using scale dimensions directly 
 * @author itxsa27
 *
 */
public class Room extends DisplayableObject {
	PImage wallTex, floorTex;
	public Room(Scene parent) {
		super(parent);
		wallTex = parent.loadImage("texture\\wall.png"); 
		floorTex = parent.loadImage("texture\\floor.png"); 
	}

	public void display() {
		parent.pushMatrix();						// Save state
		parent.pushStyle();							// Save style attributes
		parent.noLights();
		// Project from Object Space to World Space
		parent.translate(pos.x,pos.y,pos.z);    // Position
		
		// Set X, Y and Z orientation 
		parent.rotateY(rotation.y);             
		parent.rotateZ(rotation.z);             
		parent.rotateX(rotation.x);             

		parent.noFill();                    	// Transparent fill
		//Set stroke properties 
		parent.stroke(0.f);                 	
		parent.strokeWeight(2/scale.x);     	
		
		drawStage();                        	//Draw stage in Object Space directly from scale dimensions
		
		parent.lights();						//Restore lights
		parent.popStyle();						// Restore style attributes
		parent.popMatrix();						// Restore state
	}

	private void drawStage(){
		parent.textureWrap(PConstants.REPEAT); // To produce textile view
		
		// LEFT SIDE
		parent.beginShape(PApplet.QUADS);
		parent.texture(wallTex);
		parent.vertex(-scale.x, -scale.y, -scale.z, 0, 0); 
		parent.vertex(-scale.x, -scale.y, scale.z, 12, 0);
		parent.vertex(-scale.x, 0.f, scale.z, 12, 8);
		parent.vertex(-scale.x, 0.f, -scale.z, 0, 8);
		parent.endShape();
		
		//    RIGHT SIDE
		parent.beginShape(PApplet.QUAD);
		parent.texture(wallTex);
		parent.vertex(scale.x, -scale.y, scale.z, 0, 0);
		parent.vertex(scale.x, -scale.y, -scale.z, 12, 0);
		parent.vertex(scale.x, 0.f, -scale.z, 12, 8);
		parent.vertex(scale.x, 0.f, scale.z, 0, 8);
		parent.endShape();
		
		// FAR SIDE
		parent.beginShape(PApplet.QUAD);
		parent.texture(wallTex);
		parent.vertex(scale.x, -scale.y, -scale.z, 0, 0);
		parent.vertex(-scale.x, -scale.y, -scale.z, 12, 0);
		parent.vertex(-scale.x, 0.f, -scale.z, 12, 8);
		parent.vertex(scale.x, 0.f, -scale.z, 0, 8);
		parent.endShape();
		
		// NEAR SIDE
		parent.beginShape(PApplet.QUAD);
		parent.texture(wallTex);
		parent.vertex(-scale.x, -scale.y, scale.z, 0, 0);
		parent.vertex(scale.x, -scale.y, scale.z, 12, 0);
		parent.vertex(scale.x, 0.f, scale.z, 12, 8);
		parent.vertex(-scale.x, 0.f, scale.z, 0, 8);
		parent.endShape();
		
		// BOTTOM (floor)
		parent.beginShape(PApplet.QUAD);
		parent.texture(floorTex);
		parent.vertex(scale.x, 0.f, -scale.z, 0, 0);
		parent.vertex(-scale.x, 0.f, -scale.z, 16, 0);
		parent.vertex(-scale.x, 0.f, scale.z, 16, 24);
		parent.vertex(scale.x, 0.f, scale.z, 0, 24);
		parent.endShape();
		
		// TOP (ceiling)
		parent.fill(236, 203, 190);
		parent.beginShape(PApplet.QUAD);
		parent.vertex(-scale.x,-scale.y,-scale.z, 0, 0);
		parent.vertex(scale.x,-scale.y,-scale.z, 4, 0);
		parent.vertex(scale.x,-scale.y,scale.z, 4, 4);
		parent.vertex(-scale.x,-scale.y,scale.z, 0, 4);
		parent.endShape();
	}
}