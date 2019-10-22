package code.objects;

import processing.core.PApplet;
import processing.core.PVector;
import framework.engine.Scene;
/**
 * 
 * @author itxsa27
 *
 */
public class ColouredCurvedFish extends Fish {

	int r, g, b;
	public ColouredCurvedFish(Scene p, int r, int g, int b) {
		super(p);
		this.r = r;
		this.g = g;
		this.b = b;
	}
	public void display(){
		// Save status of seen before starting
		parent.pushMatrix();
		parent.pushStyle();

		parent.scale(scale.x, scale.y, scale.z);
		parent.translate(pos.x, pos.y ,pos.z);
		parent.rotateY(angle);

		parent.fill(r, g, b);

		body();
		head();
		
		//return the state of the seen back
		parent.popStyle();
		parent.popMatrix();

	}
	private void body(){
		PVector p1 = new PVector(1.0f, 0.0f, 3.0f);
		PVector p2 = new PVector(0f, 3.0f, 2.0f);
		PVector p3 = new PVector(20.0f, 0.0f, tailMove);
		parent.noStroke();
		
		parent.beginShape();
		parent.curveVertex(p1.x, -p1.y, p1.z);//control point
		
		parent.normal(p2.x, p2.y, p2.z);
		parent.curveVertex(p2.x, p2.y, p2.z);
		
		parent.normal(p3.x, p3.y, p3.z);
		parent.curveVertex(p3.x, p3.y, p3.z);// end tip
		
		parent.normal(p2.x, -p2.y, p2.z);
		parent.curveVertex(p2.x, -p2.y, p2.z);
		parent.curveVertex(p1.x, -p1.y, p1.z);//control point
		parent.endShape();
		
		parent.beginShape();
		parent.curveVertex(p1.x, p1.y, -p1.z);//control point
		
		parent.normal(p2.x, -p2.y, -p2.z);
		parent.curveVertex(p2.x, -p2.y, -p2.z);
		
		parent.curveVertex(p3.x, p3.y, p3.z);// end tip
		
		parent.normal(p2.x, p2.y, -p2.z);
		parent.curveVertex(p2.x, p2.y, -p2.z);
		parent.curveVertex(p1.x, p1.y, -p1.z);//control point
		parent.endShape();
		
		//Bottom 1
		parent.beginShape();
		parent.curveVertex(p1.x, p1.y, -p1.z);//control point
		
		parent.normal(p2.x, p2.y, -p2.z);
		parent.curveVertex(p2.x, p2.y, -p2.z);
		
		parent.curveVertex(p3.x, p3.y, p3.z);// end tip
		
		parent.normal(p2.x, p2.y* 1.3f, 0);
		parent.curveVertex(p2.x, p2.y* 1.3f, 0);
		
		parent.curveVertex(p1.x, p1.y, -p1.z);//control point
		parent.endShape();		


		//Bottom 2
		parent.beginShape();
		parent.curveVertex(p1.x, p1.y, p1.z);//control point
		
		parent.normal(p2.x, p2.y* 1.3f, 0);
		parent.curveVertex(p2.x, p2.y* 1.3f, 0);
		
		parent.curveVertex(p3.x, p3.y, p3.z);// end tip
		
		parent.normal(p2.x, p2.y, p2.z);
		parent.curveVertex(p2.x, p2.y, p2.z);
		
		parent.curveVertex(p1.x, p1.y, p1.z);//control point
		parent.endShape();		

		//Top1
		parent.beginShape();
		parent.curveVertex(p1.x, p1.y, p1.z); //control point
		
		parent.normal(p2.x, -p2.y , p2.z);
		parent.curveVertex(p2.x, -p2.y , p2.z);
		
		parent.curveVertex(p3.x, p3.y, p3.z); // end tip 
		
		parent.normal(p2.x, -p2.y * 1.7f, 0f);
		parent.curveVertex(p2.x, -p2.y * 1.7f, 0f);
		
		parent.curveVertex(p1.x, p1.y, p1.z);//control point
		parent.endShape();
		
		//Top2
		parent.beginShape();
		parent.curveVertex(p1.x, p1.y, -p1.z);//control point
		
		parent.normal(p2.x, -p2.y * 1.7f, 0f);
		parent.curveVertex(p2.x, -p2.y * 1.7f, 0f);
		
		parent.curveVertex(p3.x, p3.y, p3.z);// end tip
		
		parent.normal(p2.x, -p2.y, -p2.z);
		parent.curveVertex(p2.x, -p2.y, -p2.z);
		
		parent.curveVertex(p1.x, p1.y, -p1.z);//control point
		parent.endShape();
		
		  rearFins();
		  backFin();
		  sideFins();
		  tummyFins();

	}
	private void rearFins(){
		parent.pushStyle();
		PVector p1 = new PVector(20.0f, 0f,  tailMove);
		parent.stroke(190,190,190);
		parent.strokeWeight(2);
		for(int i = 0; i < 100; i ++){
			parent.curve(p1.x, p1.y, p1.z, 
						p1.x, p1.y, p1.z, 
						p1.x+ 5 + PApplet.sin(PApplet.radians(i*2))  + parent.noise(i,5), p1.y+ 5f -(i/6.5f), p1.z*2,
						33, 0, -p1.z*2);
			parent.strokeWeight(2-1.9f/(i+1));
		}
		parent.popStyle();
	}
	private void backFin(){
		parent.pushStyle();
		parent.stroke(190,190,190);
		parent.strokeWeight(2);
		
		PVector p1 = new PVector(2.5f, -4.3f, 0.0f);

		for(int i = 0; i < 100; i ++){
			parent.curve(p1.x+ i/10f, p1.y + i/50f, p1.z + tailMove* (i+50)/200f, 
						p1.x + i/10f, p1.y+ i/50f, p1.z + tailMove* i/200f, 
						p1.x+ 3f + i/10f , p1.y - PApplet.sin(PApplet.radians(i*2)) * 3 - parent.noise(i,2) , p1.z + tailMove * (i+10)/200f,
						p1.x+ 6f + i/10f, p1.y   - parent.noise(i,2)*2, p1.z + tailMove* (i+50)/200f);
		}
		
		parent.popStyle();

	}
	
	private void sideFins(){
		parent.pushStyle();
		parent.stroke(190,190,190);
		parent.strokeWeight(2);		
		PVector p1 = new PVector(0f, 2f, 2.2f);
		
		for(int i = 0; i < 100; i ++){
			//Right
			parent.curve(p1.x, p1.y , p1.z , 
						p1.x , p1.y -(i/70f), p1.z , 
						p1.x+ 4f + i/30f - parent.noise(i,3) , p1.y  - i/50f, p1.z + tailMove/3f +1 ,
						p1.x+ 3f - i/30, p1.y  - i/50f , p1.z + tailMove/2f);
			//Left
			parent.curve(p1.x, p1.y , -p1.z , 
					p1.x , p1.y -(i/70f), -p1.z , 
					p1.x+ 4f + i/30f - parent.noise(i,3) , p1.y  - i/50f, -(p1.z - tailMove/3f +1) ,
					p1.x+ 3f - i/30, p1.y  - i/50f , -(p1.z - tailMove/3f));

		}
		

		
		parent.popStyle();
	}
	
	private void tummyFins(){
		parent.pushStyle();
		parent.stroke(190,190,190);
		parent.strokeWeight(2);
		
		PVector p1 = new PVector(9f, 2f, 1.2f);

		for(int i = 0; i < 100; i ++){
			//Right
			float t = PApplet.sin(PApplet.radians(i)) * 3f;
			parent.curve(p1.x, p1.y , p1.z + tailMove/1.5f , 
						p1.x , p1.y -(i/700f), p1.z+ tailMove/1.5f , 
						p1.x+ 6f - i/30f + t + parent.noise(i,3) , p1.y  + i/30f - tailMove * 0.1f, p1.z + tailMove  + t ,
						p1.x+ 6f - i/30, p1.y - i/30f - tailMove, p1.z + tailMove*3);
			//Left
			parent.curve(p1.x, p1.y , -p1.z + tailMove/1.5f , 
						p1.x , p1.y -(i/700f), -p1.z+ tailMove/1.5f , 
						p1.x+ 6f - i/30f  +  t + parent.noise(i,3) , p1.y  + i/30f - tailMove * 0.1f, -p1.z + tailMove - t  ,
						p1.x+ 6f - i/30, p1.y - i/30f - tailMove, -p1.z + tailMove*3);
		}
		
		
		parent.popStyle();
	}
	private void head(){
		PVector p1 = new PVector(1.0f, 0.0f, 3.0f);
		PVector p2 = new PVector(0f, 3.0f, 2.0f);
		PVector p3 = new PVector(-7.0f, 1.0f, 0.0f);
		
		parent.noStroke();
		parent.beginShape();
		parent.normal(p2.x, -p2.y, p2.z);
		parent.curveVertex(p1.x, -p1.y, p1.z);//control point
		parent.curveVertex(p2.x, -p2.y, p2.z);
		
		parent.normal(p3.x, p3.y, p3.z);
		parent.curveVertex(p3.x, p3.y, p3.z);// end tip
		
		parent.normal(p2.x, p2.y, p2.z); 
		parent.curveVertex(p2.x, p2.y, p2.z);
		parent.curveVertex(p1.x, -p1.y, p1.z);//control point
		parent.endShape();
		
		parent.beginShape();
		parent.normal(p2.x, p2.y, -p2.z); 
		parent.curveVertex(p1.x, p1.y, -p1.z);//control point
		parent.curveVertex(p2.x, p2.y, -p2.z);
		
		parent.normal(p3.x, p3.y, p3.z); 
		parent.curveVertex(p3.x, p3.y, p3.z);// end tip
		
		parent.normal(p2.x, -p2.y, -p2.z); 
		parent.curveVertex(p2.x, -p2.y, -p2.z);
		parent.curveVertex(p1.x, p1.y, -p1.z);//control point
		parent.endShape();
		
		//Bottom 1
		parent.beginShape();
		parent.normal(p2.x, p2.y* 1.3f, 0); 
		parent.curveVertex(p1.x, p1.y, -p1.z);//control point
		parent.curveVertex(p2.x, p2.y* 1.3f, 0);
		parent.normal(p3.x, p3.y, p3.z); 

		parent.curveVertex(p3.x, p3.y, p3.z);// end tip
		
		parent.normal(p2.x, p2.y, -p2.z); 
		parent.curveVertex(p2.x, p2.y, -p2.z);

		parent.curveVertex(p1.x, p1.y, -p1.z);//control point
		parent.endShape();		


		//Bottom 2
		parent.beginShape();
		parent.curveVertex(p1.x, p1.y, p1.z);//control point
		
		parent.normal(p2.x, p2.y, p2.z); 
		parent.curveVertex(p2.x, p2.y, p2.z);
		
		parent.normal(p3.x, p3.y, p3.z); 
		parent.curveVertex(p3.x, p3.y, p3.z);// end tip
		
		parent.normal(p2.x, p2.y* 1.3f, 0); 
		parent.curveVertex(p2.x, p2.y* 1.3f, 0);
		parent.curveVertex(p1.x, p1.y, p1.z);//control point
		parent.endShape();		


		//Top1
		parent.beginShape();
		parent.curveVertex(p1.x, p1.y, p1.z); //control point
		
		parent.normal(p2.x, -p2.y * 1.7f, 0f); 
		parent.curveVertex(p2.x, -p2.y * 1.7f, 0f);
		
		parent.normal(p3.x, p3.y, p3.z); 
		parent.curveVertex(p3.x, p3.y, p3.z); // end tip 
		
		parent.normal(p2.x, -p2.y , p2.z); 
		parent.curveVertex(p2.x, -p2.y , p2.z);
		
		parent.curveVertex(p1.x, p1.y, p1.z);//control point
		parent.endShape();
		
		//Top2  		parent.normal(); 

		parent.beginShape();
		parent.curveVertex(p1.x, p1.y, -p1.z);//control point
		
		parent.normal(p2.x, -p2.y, -p2.z);
		parent.curveVertex(p2.x, -p2.y, -p2.z);
		
		parent.normal(p3.x, p3.y, p3.z);
		parent.curveVertex(p3.x, p3.y, p3.z);// end tip
		parent.normal(p2.x, -p2.y * 1.7f, 0f);
		parent.curveVertex(p2.x, -p2.y * 1.7f, 0f);
		
		parent.curveVertex(p1.x, p1.y, -p1.z);//control point
		parent.endShape();		
		
		eyes();

	}
	private void eyes(){
		parent.noStroke();

		//Left
		parent.fill(250, 250, 250);
		parent.translate(-1,-1.5f,1f);
		
		parent.pushMatrix();
		parent.scale(-1.f,-1.f,-1.f);
		parent.sphere(1f);
		parent.popMatrix();
		
		parent.fill(20, 20, 20);
		parent.translate(-0.2f,-0.2f,0.7f);

		parent.pushMatrix();
		parent.scale(-1.f,-1.f,-1.f);
		parent.sphere(0.5f);
		parent.popMatrix();
		
		//Right
		parent.fill(250, 250, 250);
		parent.translate(0.2f,0.2f,-2.7f);
		
		parent.pushMatrix();
		parent.scale(-1.f,-1.f,-1.f);
		parent.sphere(1f);
		parent.popMatrix();
		
		parent.fill(20, 20, 20);
		parent.translate(-0.2f, -0.2f,-0.7f);
		
		parent.pushMatrix();
		parent.scale(-1.f,-1.f,-1.f);
		parent.sphere(0.5f);
		parent.popMatrix();
	}




}



