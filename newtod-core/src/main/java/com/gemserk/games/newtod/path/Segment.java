package com.gemserk.games.newtod.path;

import com.badlogic.gdx.math.Vector2;

public class Segment {

	Vector2 start;
	Vector2 end;
	float length;
	Vector2 direction = new Vector2();
	
	public Segment(Vector2 start, Vector2 end) {
		this.start = start;
		this.end = end;
		this.length = start.dst(end);
		this.direction.set(end).sub(start).nor();
	}
	
	public Vector2 getStart() {
		return start;
	}
	
	public Vector2 getEnd() {
		return end;
	}
	
	public float getLength() {
		return length;
	}
	
	public Vector2 getDirection() {
		return direction;
	}
}
