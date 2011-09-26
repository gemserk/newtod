package com.gemserk.games.newtod.systems.components;

import com.artemis.Component;
import com.gemserk.games.newtod.path.Path.PathTraversal;

public class CreepDataComponent extends Component{

	public float speed;
	public PathTraversal pathTraversal;
	
	public CreepDataComponent(float speed, PathTraversal pathTraversal) {
		this.speed = speed;
		this.pathTraversal = pathTraversal;
	}
}
