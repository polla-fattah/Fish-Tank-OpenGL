package code.objects;

import processing.core.PConstants;
import processing.core.PImage;
import framework.engine.DisplayableObject;
import framework.engine.Scene;
/**
 * This class creates a box with different textures TIMBER, METAL, PLASTIC and ALUMINUM
 * @author itxsa27
 *
 */
public class Material extends DisplayableObject {
	private PImage tex;	//The texture 
	
	//Texture type that can be used to create box
	public static final int TIMBER = 0;
	public static final int METAL = 1;
	public static final int PLASTIC = 2;
	public static final int ALUMINUM = 3;
	
	//default constructor loads timber texture
	public Material(Scene parent) {
		super(parent);
		loadMaterial(TIMBER);
	}
	//
	public Material(Scene parent, int type) {
		super(parent);
		loadMaterial(type);

	}
	
	//loading the material as specified by the constructor
	private void loadMaterial(int type){
		switch(type){
		case TIMBER:
			tex = parent.loadImage("texture\\LW.jpg"); 
			break;
			
		case METAL:
			tex = parent.loadImage("texture\\ME.jpg"); 
			break;
			
		case PLASTIC:
			tex = parent.loadImage("texture\\PL.png"); 
			break;
			
		case ALUMINUM:
			tex = parent.loadImage("texture\\AL.png"); 
			break;
		default:
			tex = parent.loadImage("texture\\LW.jpg"); 
			break;
			
		}
	}
	
	//Creates the box with the specified material and scale dimensions  
	@Override
	public void display() {
		parent.pushMatrix();
		parent.pushStyle();
		
        parent.translate(pos.x,pos.y,pos.z);    // move to position

		//Front
		parent.beginShape(PConstants.QUADS);
		parent.texture(tex);
		parent.normal(1, 0, 1);
		parent.vertex(0, 0, 0, 0, 0);
		parent.vertex(scale.x, 0, 0, 0, 4);
		
		parent.normal(1, 0, 1);
		parent.vertex(scale.x, -scale.y, 0, 4, 4);
		parent.vertex(0, -scale.y, 0, 4, 0);
		parent.endShape();
		
		//Back
		parent.beginShape(PConstants.QUADS);
		parent.texture(tex);
		parent.normal(0, 0, -scale.z);
		parent.vertex(0, 0, -scale.z, 0, 0);
		parent.normal(0, -scale.y, -scale.z);

		parent.vertex(0, -scale.y, -scale.z, 0, 1);
		
		parent.normal(scale.x, -scale.y, -scale.z);
		parent.vertex(scale.x, -scale.y, -scale.z, 1, 1);
		parent.normal(scale.x, 0, -scale.z);
		parent.vertex(scale.x, 0, -scale.z, 1, 0);
		parent.endShape();

		//Right
		parent.beginShape(PConstants.QUADS);
		parent.texture(tex);
		parent.normal(scale.x, 0, -1);
		parent.vertex(scale.x, 0, 0, 0, 0);
		parent.vertex(scale.x, 0, -scale.z, 0, 1);
		
		parent.normal(scale.x, -scale.y, -1);
		parent.vertex(scale.x, -scale.y, -scale.z, 1, 1);
		parent.vertex(scale.x, -scale.y, 0, 1, 0);
		parent.endShape();
		
		//Left
		parent.beginShape(PConstants.QUADS);
		parent.texture(tex);
		parent.normal(0, 0, -1);
		parent.vertex(0, 0, 0, 0, 0);
		parent.vertex(0, -scale.y, 0, 0, 1);
		
		parent.normal(0, -scale.y, -1);
		parent.vertex(0, -scale.y, -scale.z, 1, 1);
		parent.vertex(0, 0, -scale.z, 1, 0);
		parent.endShape();
		
		//Top
		parent.beginShape(PConstants.QUADS);
		parent.texture(tex);
		parent.normal(0, -scale.y, -1);
		parent.vertex(0, -scale.y, 0, 0, 0);
		parent.normal(scale.x, -scale.y, 0);

		parent.vertex(scale.x, -scale.y, 0, 0, 1);
		
		parent.normal(scale.x, -scale.y, -scale.z);
		parent.vertex(scale.x, -scale.y, -scale.z, 1, 1);
		parent.normal(0, -scale.y, -scale.z);
		parent.vertex(0, -scale.y, -scale.z, 1, 0);
		parent.endShape();	
		
		//Top
		parent.beginShape(PConstants.QUADS);
		parent.texture(tex);
		parent.normal(0, 0, -1);
		parent.vertex(0, 0, 0, 0, 0);
		parent.vertex(0, 0, -scale.z, 0, 1);
		
		parent.normal(scale.x, 0, -scale.z);
		parent.vertex(scale.x, 0, -scale.z, 1, 1);
		parent.vertex(scale.x, 0, 0, 1, 0);
		parent.endShape();
		
		
		
		parent.popStyle();
		parent.popMatrix();
	}

}
