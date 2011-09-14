package com.gemserk.games.newtod.path;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Path {

	Array<Segment> segments = new Array<Segment>();
	
	public void addSegment(Segment segment){
		segments.add(segment);
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
				//que hago si estoy en el ultimo
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
		
//		float segmentLength = path.getDistanceToNextPoint(index);
//
//		while (segmentLength - innerDistance < distance) {
//			if (path.isLastSegment(index)) {
//				distance = 0;
//				break;
//			}
//			index++;
//			distanceFromOrigin += (segmentLength - innerDistance);
//			distance -= (segmentLength - innerDistance);
//			segmentLength = path.getDistanceToNextPoint(index);
//			innerDistance = 0;
//		}
//
//		innerDistance += distance;
//		distanceFromOrigin += distance;
//
//		return new PathTraversal(path, index, innerDistance, distanceFromOrigin);
		
		
		
//		float segmentLength = currentSegment.getLength();
//		float remainingDistance = currentDistanceInSegment + distance - segmentLength;
//		if(remainingDistance > 0){
//			if(segmentIndex + 1 == path.segments.size){
//				currentDistanceInSegment = segmentLength;
//				remainingDistance = 0;
//			} else {
//				segmentIndex++;
//				currentSegment = path.segments.get(segmentIndex);
//				currentDistanceInSegment = remainingDistance;
//			}
//		}
		
	}

	public PathTraversal getTraversal() {
		return new PathTraversal(this);
	}
}
