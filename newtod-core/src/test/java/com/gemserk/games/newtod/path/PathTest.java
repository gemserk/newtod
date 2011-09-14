package com.gemserk.games.newtod.path;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.math.Vector2;
import com.gemserk.games.newtod.path.Path.PathTraversal;

public class PathTest {

	private Path path;

	@Before
	public void setUp(){
		path = new Path();
	}
	
	@Test
	public void canAddOneSegment(){
		Segment segment = new Segment(new Vector2(0,0), new Vector2(1,1));
		path.addSegment(segment);
		
		Vector2 position = new Vector2();
		path.getStartPosition(position);
		assertThat((double)position.dst(0, 0), Matchers.closeTo(0, 0.1));
		path.getEndPosition(position);
		assertThat((double)position.dst(1, 1), Matchers.closeTo(0, 0.1));
		
	}
	
	@Test
	public void canAddTwoSegment(){
		Segment segment = new Segment(new Vector2(0,0), new Vector2(1,1));
		path.addSegment(segment);
		segment = new Segment(new Vector2(1,1), new Vector2(2,2));
		path.addSegment(segment);
		
		Vector2 position = new Vector2();
		path.getStartPosition(position);
		assertThat((double)position.dst(0, 0), Matchers.closeTo(0, 0.1));
		path.getEndPosition(position);
		assertThat((double)position.dst(2, 2), Matchers.closeTo(0, 0.1));	
	}
	
	
	@Test
	public void canAdvanceInOneSegment(){
		Segment segment = new Segment(new Vector2(0,0), new Vector2(1,0));
		path.addSegment(segment);
		
		PathTraversal traversal = path.getTraversal();
		
		traversal.advance(0.5f);
		assertThat((double)traversal.getPosition().dst(0.5f, 0), Matchers.closeTo(0, 0.1));	
	}
	
	@Test
	public void canAdvanceInTwoSegment(){
		Segment segment = new Segment(new Vector2(0,0), new Vector2(1,0));
		path.addSegment(segment);
		segment = new Segment(new Vector2(1,0), new Vector2(1,1));
		path.addSegment(segment);
		
		
		PathTraversal traversal = path.getTraversal();
		
		traversal.advance(1.5f);
		assertThat((double)traversal.getPosition().dst(1, 0.5f), Matchers.closeTo(0, 0.1));	
	}
	
	@Test
	public void ifAdvancingMoreThanTheLengthOfThePathItStaysInTheEndWithOneSegment(){
		Segment segment = new Segment(new Vector2(0,0), new Vector2(1,0));
		path.addSegment(segment);
		
		
		PathTraversal traversal = path.getTraversal();
		
		traversal.advance(10);
		
		Vector2 endPosition = new Vector2();
		path.getEndPosition(endPosition);
		assertThat((double)traversal.getPosition().dst(endPosition), Matchers.closeTo(0, 0.1));	
	}
	
	@Test
	public void ifAdvancingMoreThanTheLengthOfThePathItStaysInTheEndWithTwoSegments(){
		Segment segment = new Segment(new Vector2(0,0), new Vector2(1,0));
		path.addSegment(segment);
		segment = new Segment(new Vector2(1,0), new Vector2(1,1));
		path.addSegment(segment);
		
		PathTraversal traversal = path.getTraversal();
		
		traversal.advance(10);
		
		Vector2 endPosition = new Vector2();
		path.getEndPosition(endPosition);
		assertThat((double)traversal.getPosition().dst(endPosition), Matchers.closeTo(0, 0.1));	
	}
	
	
	
	
}
