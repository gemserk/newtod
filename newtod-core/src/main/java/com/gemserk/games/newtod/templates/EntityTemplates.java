package com.gemserk.games.newtod.templates;

import com.artemis.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.gemserk.commons.artemis.EntityBuilder;
import com.gemserk.commons.artemis.components.PhysicsComponent;
import com.gemserk.commons.artemis.components.SpatialComponent;
import com.gemserk.commons.artemis.components.TagComponent;
import com.gemserk.commons.artemis.templates.EntityFactory;
import com.gemserk.commons.artemis.templates.EntityFactoryImpl;
import com.gemserk.commons.artemis.templates.EntityTemplate;
import com.gemserk.commons.artemis.templates.EntityTemplateImpl;
import com.gemserk.commons.gdx.box2d.BodyBuilder;
import com.gemserk.commons.gdx.games.PhysicsImpl;
import com.gemserk.commons.gdx.games.Spatial;
import com.gemserk.commons.gdx.games.SpatialPhysicsImpl;
import com.gemserk.commons.gdx.graphics.Mesh2dBuilder;
import com.gemserk.componentsengine.utils.Parameters;
import com.gemserk.componentsengine.utils.ParametersWrapper;
import com.gemserk.games.newtod.Tags;
import com.gemserk.resources.ResourceManager;

public class EntityTemplates {

	private final BodyBuilder bodyBuilder;
	private final ResourceManager<String> resourceManager;
	private final EntityBuilder entityBuilder;
	private final Mesh2dBuilder mesh2dBuilder;
	private final World physicsWorld;
	private final EntityFactory entityFactory;

	private final Parameters parameters = new ParametersWrapper();
	private final com.artemis.World world;

	public EntityTemplates(World physicsWorld, com.artemis.World world, ResourceManager<String> resourceManager, EntityBuilder entityBuilder, EntityFactoryImpl entityFactory) {
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
