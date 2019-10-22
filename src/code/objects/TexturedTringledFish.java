package code.objects;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import framework.engine.Scene;
/**
 *
 * @author itxsa27
 *
 */
public class TexturedTringledFish extends Fish {

	private PImage texture;
	
	public TexturedTringledFish(Scene p, PImage tex) { 
		super(p);
		this.texture = tex;
	}
	
	public void display(){
		// Save status of seen before starting
		parent.pushMatrix();
		parent.pushStyle();
		
		parent.translate(pos.x, pos.y ,pos.z);
		parent.rotateY(angle);
		
		parent.scale(scale.x, scale.y, scale.z);
		
		//draw fish's head and body
		body();
		head();

		//return the state of the seen back
		parent.popStyle();
		parent.popMatrix();

	}
	
	private void body(){
		parent.pushMatrix();

		float y, z, m1y, m2y;
		int res = 20;
		PVector p1 = new PVector(0f, 6.0f, 3.0f);
		PVector p2 = new PVector(25.0f, 2f,  tailMove);
		

		parent.noStroke();
		parent.fill(140);
		for( int i = 0; i <= 360; i+= res){
			parent.beginShape();
			parent.texture(texture);
			y = PApplet.cos(PApplet.radians(i))* p1.y;
			z = PApplet.sin(PApplet.radians(i)) * p1.z;
			parent.normal(p1.x, y, z);
			parent.vertex(p1.x, y, z, 0, 0 );
			
			
			m1y = PApplet.cos(PApplet.radians(i))* p2.y;
			parent.normal(p2.x, m1y, p2.z);
			parent.vertex(p2.x, m1y, p2.z, 1, 0);// end tip
			
			m2y = PApplet.cos(PApplet.radians(i+res))* p2.y;
			parent.normal(p2.x , m2y, p2.z);
	
			parent.vertex(p2.x , m2y, p2.z, 1, 1);// end tip
			
			
			//parent.vertex(p2.x, p2.y, p2.z, 1, 0);// end tip
			
			y = PApplet.cos(PApplet.radians(i+res))* p1.y;
			z = PApplet.sin(PApplet.radians(i+res)) * p1.z;
			parent.normal(p1.x, y, z);
			parent.vertex(p1.x, y, z, 0, 1 );
			parent.endShape();
			//break;
		}
	  rearFins();
	  backFin();
	  sideFins();
	  tummyFins();
	  parent.popMatrix();
	}
	private void rearFins(){
		parent.pushStyle();
		PVector p1 = new PVector(25.0f, 2f,  tailMove);
		parent.stroke(22,20,20);
		parent.strokeWeight(2);
		for(int i = 0; i < 100; i ++){
			parent.curve(p1.x, p1.y, p1.z, 
						p1.x, p1.y-4f*i/100f, p1.z, 
						p1.x+ 5 - PApplet.sin(PApplet.radians(i*2)) *1.2f - parent.noise(i,5), p1.y+ 5f -(i/6.5f), p1.z*2,
						33, 0, -p1.z*2);
			parent.strokeWeight(2-1.9f/(i+1));
		}
		parent.popStyle();
	}
	private void backFin(){
		parent.pushStyle();
		parent.stroke(22,20,20);
		parent.strokeWeight(2);
		
		PVector p1 = new PVector(2.5f, -5.5f, 0.0f);

		for(int i = 0; i < 100; i ++){
			parent.curve(p1.x+ i/10f, p1.y + i/50f, p1.z + tailMove* (i+50)/200f, 
						p1.x + i/10f, p1.y+ i/50f, p1.z + tailMove* i/200f, 
						p1.x+ 3f + i/10f , p1.y  - parent.noise(i,2) , p1.z + tailMove * (i+10)/200f,
						p1.x+ 6f + i/10f, p1.y   - parent.noise(i,2)*2, p1.z + tailMove* (i+50)/200f);
		}
		
		parent.popStyle();

	}
	
	private void sideFins(){
		parent.pushStyle();
		parent.stroke(22,20,20);
		parent.strokeWeight(2);		
		PVector p1 = new PVector(0f, 2.5f, 2.6f);
		
		for(int i = 0; i < 100; i ++){
			//Right
			parent.curve(p1.x, p1.y , p1.z , 
						p1.x , p1.y -(i/700f), p1.z , 
						p1.x+ 4f + i/30f - parent.noise(i,3) , p1.y  - i/50f, p1.z + tailMove/3f +1 ,
						p1.x+ 3f - i/30, p1.y  - i/50f , p1.z + tailMove/2f);
			//Left
			parent.curve(p1.x, p1.y , -p1.z , 
					p1.x , p1.y -(i/700f), -p1.z , 
					p1.x+ 4f + i/30f - parent.noise(i,3) , p1.y  - i/50f, -(p1.z - tailMove/3f +1) ,
					p1.x+ 3f - i/30, p1.y  - i/50f , -(p1.z - tailMove/3f));

		}
		

		
		parent.popStyle();
	}
	
	private void tummyFins(){
		parent.pushStyle();
		parent.stroke(22,20,20);
		parent.strokeWeight(2);
		
		PVector p1 = new PVector(16f, 2.5f, 1.2f);

		for(int i = 0; i < 100; i ++){
			//Right
			parent.curve(p1.x, p1.y , p1.z + tailMove/1.5f , 
						p1.x , p1.y -(i/700f), p1.z+ tailMove/1.5f , 
						p1.x+ 6f - i/30f + parent.noise(i,3) , p1.y  + i/30f - tailMove * 0.1f, p1.z + tailMove  ,
						p1.x+ 6f - i/30, p1.y - i/30f - tailMove, p1.z + tailMove*3);
			//Left
			parent.curve(p1.x, p1.y , -p1.z + tailMove/1.5f , 
						p1.x , p1.y -(i/700f), -p1.z+ tailMove/1.5f , 
						p1.x+ 6f - i/30f + parent.noise(i,3) , p1.y  + i/30f - tailMove * 0.1f, -p1.z + tailMove  ,
						p1.x+ 6f - i/30, p1.y - i/30f - tailMove, -p1.z + tailMove*3);
		}
		
		parent.popStyle();
	}
	private void head(){
		parent.pushMatrix();

		float y, z, m1y, m1z, m2y, m2z;
		int res = 20;
		PVector p1 = new PVector(0f, 6.0f, 3.0f);
		PVector p2 = new PVector(-5.0f, 1.0f, 1.0f);
		
		parent.noStroke();
		float move = PApplet.abs(PApplet.cos(PApplet.radians(parent.frameCount/5f)));
		
		  for( int i = 0; i <= 360; i+= res){
			parent.beginShape();
			parent.texture(texture);
			y = PApplet.cos(PApplet.radians(i+res))* p1.y;
			z = PApplet.sin(PApplet.radians(i+res)) * p1.z;
			
			parent.normal(p1.x, y, z);
			parent.vertex(p1.x, y, z, 0, 0 );
			
			m1y = PApplet.cos(PApplet.radians(i+res))* p2.y * move  ;
			m1z = PApplet.sin(PApplet.radians(i+res)) * p2.z;
			parent.normal(p2.x, m1y, m1z);
			parent.vertex(p2.x, m1y, m1z, 1, 0);// end tip
			
			m2y = PApplet.cos(PApplet.radians(i))* p2.y* move;
			m2z = PApplet.sin(PApplet.radians(i)) * p2.z;
			parent.normal(p2.x , m2y, m2z);
			parent.vertex(p2.x , m2y, m2z, 1, 1);// end tip

			y = PApplet.cos(PApplet.radians(i))* p1.y;
			z = PApplet.sin(PApplet.radians(i)) * p1.z;
			parent.normal(p1.x, y, z);
			parent.vertex(p1.x, y, z, 0, 1 );
			
			parent.endShape();
			
			//Mouth
			parent.pushStyle();
			parent.fill(0);
			parent.beginShape();		
			parent.vertex(p2.x, m1y, m1z);// end tip

			parent.vertex(-1, 0, 0);// end tip
			parent.vertex(p2.x, m2y, m2z);// end tip
			parent.endShape();
			parent.stroke(200);
			parent.strokeWeight(3);
			parent.line(p2.x, m1y, m1z, p2.x, m2y, m2z);
			parent.popStyle();

		  }
		
		  eye();
		parent.popMatrix();

	}
	private void eye(){
		//Left 
		parent.noStroke();
		parent.fill(250, 250, 250);
		parent.translate(-1, -1.5f, 1.7f);

		parent.pushMatrix();
		parent.scale(-1.f,-1.f,-1.f);
		parent.sphere(1f);
		parent.popMatrix();

		parent.fill(36,233,32);
		parent.translate(-0.3f, 0.f, 0.7f);

		parent.pushMatrix();
		parent.scale(-1.f,-1.f,-1.f);
		parent.sphere(0.5f);
		parent.popMatrix();

		//Right 
		parent.fill(250, 250, 250);		
		parent.translate(0.3f, 0.f, -4.1f);
		
		parent.pushMatrix();
		parent.scale(-1.f,-1.f,-1.f);
		parent.sphere(1f);
		parent.popMatrix();
		
		parent.fill(36,233,32);
		parent.translate(-0.3f, 0.f, -0.7f);		
		
		parent.pushMatrix();
		parent.scale(-1.f,-1.f,-1.f);
		parent.sphere(0.5f);
		parent.popMatrix();

	}

}



