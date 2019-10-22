package code;

import framework.engine.*;		// import framework classes
import code.objects.*;			// import your objects package
import code.demos.lighting.*;

/**
 * Your main Coursework class. Inherits {@code framework.}{@link Scene}. Edit this class.  
 * @author {Sanar F. Abduljabbar}
 * 
 * @version 1.0.0
 */
public class MyScene extends Scene {
	
	@Override
	public void initialise(){
		textureMode(NORMAL);
		setBackgroundColour(0.f,0.f,0.f,0.f);	// set clear colour to black
		
		//Create and set the stage
		Room room = new Room(this);
		room.size(1000);
		room.position(0.f,250.f,0.f);
		
		//Setting the lights 
		StandLight m1 = new StandLight(this, 255, 255, 255);
		m1.position(100, -400, 100);
		StandLight m2 = new StandLight(this, 200, 200, 200);
		m2.position(100, -50, 100);
		
		//Creates the table which holds fish tank
		Table t1 = new Table(this, 500, 500, 140);
		t1.position(0, 180, 0);
		
		//Fish tank
		Tank tank = new Tank(this, 470);
		tank.position(25, 100, -20);
		
		//The ferries 
		Ferris  f = new Ferris(this);
		f.position(-50.f,40.f,-100.f);

		//Plants 
		Plants  plant1 = new Plants(this, 50, 70, 50);
		plant1.position(0f,50.f,0f);

		//two kind of fishes 
		TexturedTringledFish fish1 = new TexturedTringledFish(this, loadImage("texture\\f4.jpg"));
		fish1.position(0, -20, 0);

		ColouredCurvedFish fish2 = new ColouredCurvedFish(this, 10, 20, 200);
		fish2.position(0, 40, 0);
		
		ColouredCurvedFish fish3 = new ColouredCurvedFish(this, 200, 20, 20);
		fish3.position(0, 20, 0);
	
		ColouredCurvedFish fish4 = new ColouredCurvedFish(this, 100, 160, 110);
		fish4.position(0, 150, 0);
		
		TexturedTringledFish fish5 = new TexturedTringledFish(this, loadImage("texture\\f1.jpg"));
		fish5.position(0, 30, 0);
		
		// The sound effect
		Sound sound = new Sound(this);
		sound.play();		
		
		//adding created objects to the seen 
		addObjectToScene(f);
		addObjectToScene(m1);
		addObjectToScene(t1);
		addObjectToScene(room);
		addObjectToScene(plant1);
		addObjectToScene(m2);
		addObjectToScene(fish1);
		addObjectToScene(fish2);
		addObjectToScene(fish3);
		addObjectToScene(tank);
		addObjectToScene(fish4);
		addObjectToScene(fish5);
		addObjectToScene(sound);
		
		
		super.initialise();						// call default initialisation for camera and projection. 
		
		
	}
	
	/**
	 * Override default global lighting.
	 * @see #lights()
	 */
	@Override
	protected void globalLighting(){
	//	super.globalLighting();		// DISABLE GLOBAL LIGHTING WHEN IMPLEMENTING OWN
		//ambientLight(255.f,255.f,255.f);
	}
	
	/**
	 * Override default reshape function. Called during every iteration of {@link #draw()}.
	 * Use this method to handle resizing objects based on your window size.
	 * @see #getObject(String)
	 * @see #projection()
	 */
	protected void reshape(){
		super.reshape();
	}
	
	/**
	 * Override default initial window size (600x400). Adjust variables in {@code super} class to change values.
	 */
	@Override
	protected void setInitWindowSize(){
		super.initWidth = 1200;	// must override variables in super class to affect size
		super.initHeight = 700;
	}
	
	/**
	 * Override projection properties here. Remove call to {@code super.projection()} and replace with
	 * perspective mode.
	 * @see #perspective(float, float, float, float)
	 * @see #ortho(float, float, float, float, float, float)
	 * @see #frustum(float, float, float, float, float, float)
	 */
	@Override
	protected void projection(){
		//super.projection();	// calls default projection setup in Scene (orthographic)
		perspective(radians(60.f),(float)width/(float)height, 1.f, 4000.f);
	}

}
