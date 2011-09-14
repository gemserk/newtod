package com.gemserk.games.newtod.path;

import java.security.InvalidParameterException;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Path {

	Array<Segment> segments = new Array<Segment>();
	
	public Path(Segment...segments) {
		if(segments.length<1)
			throw new InvalidParameterException("You have to pass at least one segment");
		
		for (int i = 0; i < segments.length; i++) {
			this.segments.add(segments[i]);
		}
	}
	
	public void getStartPosition(Vector2 position){
		position.set(segments.get(0).getStart());
	}
	
	public void getEndPosition(Vector2 position){
		position.set(segments.get(segments.size -1).getEnd());
	}
	
	static class PathTraversal {
		Path path;
		
		Vector2 currentPosition = new Vector2();
		float currentDistanceInSegment = 0;
		int segmentIndex = 0;
		Segment currentSegment;
		
		
		public PathTraversal(Path path){
			this.path = path;
			this.currentSegment = path.segments.get(this.segmentIndex);
		}
		
		public void advance(float distance){
			float segmentLength = currentSegment.getLength();
			while(segmentLength - currentDistanceInSegment < distance){
				if(segmentIndex==path.segments.size -1){
					currentDistanceInSegment = currentSegment.getLength();
					distance = 0;
					break;
				}
				distance -= (segmentLength - currentDistanceInSegment);
				segmentIndex++;
				currentSegment = path.segments.get(segmentIndex);
				segmentLength = currentSegment.getLength();
				currentDistanceInSegment = 0;
			}
			
			currentDistanceInSegment += distance;
			currentPosition.set(currentSegment.getDirection()).mul(currentDistanceInSegment).add(currentSegment.getStart());
			
			
		}
		
		public Vector2 getPosition(){
			return currentPosition;
		}
	}

	public PathTraversal getTraversal() {
		return new PathTraversal(this);
	}
}
