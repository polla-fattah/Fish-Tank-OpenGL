package code.objects;

import processing.core.PVector;
import ddf.minim.*;
import framework.engine.DisplayableObject;
import framework.engine.Scene;
/**
 * This Class produces sound effects the bubbles inside the tank and music outside it.
 * 
 * @author itxsa27
 *
 */
public class Sound extends DisplayableObject {
	private AudioPlayer  melody, bubbles;
	
	public Sound(Scene parent){
		super(parent);
		Minim minim = new Minim(parent);	// Initialise the players
		
		melody = minim.loadFile("sounds//melody.mp3", 2048);	//Room's music
		bubbles = minim.loadFile("sounds//bubbles1.mp3", 2048); //air bubble sound
	}
	//plays both audio at the start and sets initial value for the sounds 
	public void play(){
		melody.play();			//start playing
		melody.loop();			//sets property to repeat forever
		melody.setGain(-13f);	// sets the sound gain

		bubbles.play();			//start playing
		bubbles.loop();			//sets property to repeat forever
		bubbles.setGain(1000f);	// sets the sound gain
	}
	
	// the method display which is repeated with the graphics loop tracks the location of the 
	// camera to adjust sound accordingly
	@Override
	public void display(){
		PVector eye = parent.getCameraLocation();
		if(eye.z < 202 && eye.z > -241.6  && eye.x > -192.5f && eye.x < 242.3f){ //inside tank
			bubbles.setGain(1000f);	// raise the voice 
			melody.setGain(-13f); 	// lower the voice
		}
		else{ // outside tank
			melody.setGain(1000f);	// raise the voice
			bubbles.setGain(-9f);	// lower the voice
		}
	}
}
