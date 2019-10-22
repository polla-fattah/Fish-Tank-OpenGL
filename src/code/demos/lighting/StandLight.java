package code.demos.lighting;


import framework.engine.DisplayableObject;
import framework.engine.Scene;
import framework.interfaces.Lighting;

/**
 * This class is a modified version of what was provided in the framwark's demo light 
 * @author itxsa
 *
 */
public class StandLight extends DisplayableObject implements  Lighting {
	/* Lighting properties */
	float ambient[]  = new float[3];
	float diffuse[]  = new float[3];
	float specular[] = new float[3];
	boolean positional;
	/* Maintain runtime */
	float t;
	/* Light rotation radius */
	final float radius = 500.f;
	/**
	 * @param parent
	 */
	public StandLight(Scene parent, float r, float g, float b) {
		super(parent);
		ambient[0] = 38.f;
		ambient[1] = 38.f;
		ambient[2] = 26.f;
		
		diffuse[0] = r;
		diffuse[1] = g;
		diffuse[2] = b;
		
		specular[0] = 100.f;
		specular[1] = 100.f;
		specular[2] = 100.f;
		
		positional = true;
		t = 2.f;
	}

	/* (non-Javadoc)
	 * @see framework.interfaces.Lighting#setupLighting()
	 */
	@Override
	public void setupLighting() {
		// Sets up lighting (called whenever lights is setup)
		
		/* Sets the specular property of lights created
		 * IMPORTANT: light properties, such as lightSpecular affect ALL lights generated after
		 * so they must be reset at the end of the call.
		 */
		parent.lightSpecular(specular[0],specular[1],specular[2]); 
		
		// Create ambient light with ambient colour (position optional)
		parent.ambientLight(ambient[0],ambient[1],ambient[2],-pos.x,-pos.y,-pos.z);
		
		// Create positional or directional light
		if(positional)
			parent.pointLight(diffuse[0],diffuse[1],diffuse[2],-pos.x,-pos.y,-pos.z);
		else
			parent.directionalLight(diffuse[0],diffuse[1],diffuse[2],-pos.x,-pos.y,-pos.z);
		
		parent.lightSpecular(0,0,0); // Reset specular light property!
	}



	/* (non-Javadoc)
	 * @see framework.engine.DisplayableObject#display()
	 */
	@Override
	public void display() {
		// Some basic code to draw the position and direction of the light
		// Not suitable for a directional light source, as directional light has no positon.
		if (positional){
			// Disable lighting effects on this geometry
			parent.noLights();
				parent.pushMatrix();
				parent.pushStyle();
				// Style light source to match diffuse colour
					parent.fill((int)diffuse[0],(int)diffuse[1],(int)diffuse[2]);
					parent.noStroke();
					parent.translate(pos.x,pos.y,pos.z); // positon

			
				parent.popStyle();
				parent.popMatrix();
			parent.lights(); // !IMPORTANT! Renable lighting after this
			
			//parent.box(pos.x,pos.y,pos.z);
		}
	}
}
