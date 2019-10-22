package code.objects;

import framework.engine.DisplayableObject;
import framework.engine.Scene;
import framework.interfaces.Animation;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;
/**
 *  This is an abstract class defines the moves of the fishes 
 * @author itxsa27
 *
 */
public abstract  class Fish extends DisplayableObject implements Animation{
	protected PVector move;
	protected float tailMove = 0, angle;	

	//constructor sets initial values 
	public Fish(Scene parent) {
		super(parent);
		move = new PVector(-1f, 0,  0);


	}
	
	@Override
	public void update(float dT) {
		//the animation of the fishes body 
		tailMove = PApplet.sin(PApplet.radians(parent.frameCount*5)) * 4;
		
		// the combination of x and z direction dictates the path of the fish
		pos.x += move.x;
		pos.z += move.z;
		
		// if a fish approaches limits of the tank or ferries then change direction
		if(pos.x < -155 || ((int)parent.random(5) == 3 && pos.z == 0)  || isFerris() && move.x < 0 ){
			moveAwayOrRight();
		}
		else if(pos.x > 200 || ((int)parent.random(5) == 3 && pos.z == 0) || isFerris() && move.x > 0){
			moveLeftOrForward();
		}
		else if(pos.z < -200 || ((int)parent.random(5) == 3 && pos.x == 0) || isFerris() || isFerris() && move.z < 0){
			moveForwardOrLeft();
		}
		else if(pos.z >155 || ((int)parent.random(5) == 3 && pos.x == 0) || isFerris() && move.z > 0){
			moveRightOrAway();
		}
		
	}
	//ferries position to be avoided 
	private boolean isFerris(){
		return (pos.z > -121 &&  pos.z < -70 && 
				pos.x > -90  && pos.x <  -20 &&
				pos.y < 110 );

	}
	
	// multiple functions as predetermined paths which any fish can take
	private void moveAwayOrRight(){
		pos.x++;
		if(parent.random(1) > 0.5){
			move.z = -1f;
			move.x = 0;
			angle = PConstants.PI * 1.5f;
		}
		else{
			move.z = -0.5f;
			move.x = 0.5f;
			angle = PApplet.radians(-135);
		}
	}

	public void moveRightOrAway(){
		pos.z--;
		if(parent.random(1) > 0.5){
			move.z = -1f;
			move.x = 0f;
			angle = -PConstants.PI * 0.5f;
		}
		else{
			move.z = -0.5f;
			move.x = -0.5f;
			angle = PApplet.radians(-45);
		}
	}	
	public void moveLeftOrForward(){
		pos.x--;
		if(parent.random(1) > 0.5){
			move.z = 0f;
			move.x = -1f;
			angle = 0;
		}
		else{
			move.z = 0.5f;
			move.x = -0.5f;
			angle = PApplet.radians(45);
		}
	}
	public void moveForwardOrLeft(){
		pos.z++;
		if(parent.random(1) > 0.5){
			move.z = 0f;
			move.x = 1;
			angle = PConstants.PI;
		}
		else{
			move.z = 0.5f;
			move.x = 0.5f;
			angle = PApplet.radians(135);
		}
	}

}
