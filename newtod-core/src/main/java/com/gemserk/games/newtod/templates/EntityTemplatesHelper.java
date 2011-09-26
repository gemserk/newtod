package com.gemserk.games.newtod.templates;

import com.badlogic.gdx.physics.box2d.World;
import com.gemserk.commons.artemis.EntityBuilder;
import com.gemserk.commons.artemis.templates.EntityFactory;
import com.gemserk.commons.artemis.templates.EntityFactoryImpl;
import com.gemserk.commons.gdx.box2d.BodyBuilder;
import com.gemserk.commons.gdx.graphics.Mesh2dBuilder;
import com.gemserk.componentsengine.utils.Parameters;
import com.gemserk.componentsengine.utils.ParametersWrapper;
import com.gemserk.resources.ResourceManager;

public class EntityTemplatesHelper {

	public final BodyBuilder bodyBuilder;
	public final ResourceManager<String> resourceManager;
	public final EntityBuilder entityBuilder;
	public final Mesh2dBuilder mesh2dBuilder;
	public final World physicsWorld;
	public final EntityFactory entityFactory;

	public final Parameters parameters = new ParametersWrapper();
	public final com.artemis.World world;

	public EntityTemplatesHelper(World physicsWorld, com.artemis.World world, ResourceManager<String> resourceManager, EntityBuilder entityBuilder, EntityFactoryImpl entityFactory) {
		this.physicsWorld = physicsWorld;
		this.world = world;
		// TODO Auto-generated constructor stub
		this.resourceManager = resourceManager;
		this.entityBuilder = entityBuilder;
		this.entityFactory = entityFactory;
		this.bodyBuilder = new BodyBuilder(physicsWorld);
		this.mesh2dBuilder = new Mesh2dBuilder();
	}

	
	

}
