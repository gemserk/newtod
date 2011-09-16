package com.gemserk.games.newtod.path;

import static org.junit.Assert.assertThat;

import java.security.InvalidParameterException;

import org.hamcrest.Matchers;
import org.junit.Test;

import com.badlogic.gdx.math.Vector2;
import com.gemserk.games.newtod.path.Path.PathTraversal;

public class PathTest {

	@Test(expected=InvalidParameterException.class)
	public void cantCreateAPathWithoutSegmentsNoParameter(){
		Path path = new Path();
	}
	
	@Test(expected=InvalidParameterException.class)
	public void cantCreateAPathWithoutSegmentsEmptyArrayParameter(){
		Path path = new Path(new Segment[]{});
	}
	
	@Test
	public void canAddOneSegment(){
		Path path = new Path(new Segment(new Vector2(0,0), new Vector2(1,1)));
		
		assertThat((double)path.getStartPosition().dst(0, 0), Matchers.closeTo(0, 0.1));
		assertThat((double)path.getEndPosition().dst(1, 1), Matchers.closeTo(0, 0.1));
		
	}
	
	@Test
	public void canAddTwoSegment(){
		Path path = new Path(new Segment(new Vector2(0,0), new Vector2(1,1)), new Segment(new Vector2(1,1), new Vector2(2,2)));
		
		assertThat((double)path.getStartPosition().dst(0, 0), Matchers.closeTo(0, 0.1));
		assertThat((double)path.getEndPosition().dst(2, 2), Matchers.closeTo(0, 0.1));	
	}
	
	
	@Test
	public void canAdvanceInOneSegment(){
		Path path = new Path(new Segment(new Vector2(0,0), new Vector2(1,0)));
		
		PathTraversal traversal = path.getTraversal();
		
		traversal.advance(0.5f);
		assertThat((double)traversal.getPosition().dst(0.5f, 0), Matchers.closeTo(0, 0.1));	
	}
	
	@Test
	public void canAdvanceInTwoSegment(){
		Path path = new Path(new Segment(new Vector2(0,0), new Vector2(1,0)), new Segment(new Vector2(1,0), new Vector2(1,1)));
		
		PathTraversal traversal = path.getTraversal();
		
		traversal.advance(1.5f);
		assertThat((double)traversal.getPosition().dst(1, 0.5f), Matchers.closeTo(0, 0.1));	
	}
	
	@Test
	public void ifAdvancingMoreThanTheLengthOfThePathItStaysInTheEndWithOneSegment(){
		Path path = new Path(new Segment(new Vector2(0,0), new Vector2(1,0)));
		
		
		PathTraversal traversal = path.getTraversal();
		
		traversal.advance(10);
		
		assertThat((double)traversal.getPosition().dst(path.getEndPosition()), Matchers.closeTo(0, 0.1));	
	}
	
	@Test
	public void ifAdvancingMoreThanTheLengthOfThePathItStaysInTheEndWithTwoSegments(){
		Path path = new Path(new Segment(new Vector2(0,0), new Vector2(1,0)), new Segment(new Vector2(1,0), new Vector2(1,1)));
		
		PathTraversal traversal = path.getTraversal();
		
		traversal.advance(10);
		
		assertThat((double)traversal.getPosition().dst(path.getEndPosition()), Matchers.closeTo(0, 0.1));	
	}
	
	
	
	
}
