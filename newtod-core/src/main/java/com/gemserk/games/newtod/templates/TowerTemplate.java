package com.gemserk.games.newtod.templates;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.World;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.gemserk.commons.artemis.components.PhysicsComponent;
import com.gemserk.commons.artemis.components.ScriptComponent;
import com.gemserk.commons.artemis.components.SpatialComponent;
import com.gemserk.commons.artemis.scripts.ScriptJavaImpl;
import com.gemserk.commons.artemis.templates.EntityFactory;
import com.gemserk.commons.artemis.templates.EntityTemplateImpl;
import com.gemserk.commons.gdx.box2d.BodyBuilder;
import com.gemserk.commons.gdx.games.SpatialPhysicsImpl;
import com.gemserk.componentsengine.utils.ParametersWrapper;
import com.gemserk.games.newtod.Tags;
import com.gemserk.games.newtod.systems.components.CreepDataComponent;
import com.gemserk.games.newtod.systems.components.TowerDataComponent;

public class TowerTemplate extends EntityTemplateImpl{

	ComponentMapper<TowerDataComponent> towerDataComponent;
	ComponentMapper<SpatialComponent> spatialMapper;
	BodyBuilder bodyBuilder;
	World world;
	EntityFactory entityFactory;
	EntityTemplates entityTemplates;


	@Override
	public void apply(Entity entity) {
		
		Vector2 position = parameters.get("position");
		
		
		
		ScriptComponent scriptComponent = new ScriptComponent(new ScriptJavaImpl() {
			@Override
			public void update(World world, Entity e) {
				TowerDataComponent creepDataComponent  = towerDataComponent.get(e);
				
				ImmutableBag<Entity> creeps = world.getGroupManager().getEntities(Tags.CREEPS);
				if(creeps.isEmpty())
					return;
				
				Entity creep = creeps.get(0);
				
				SpatialComponent creepSpatial = spatialMapper.get(creep);
				SpatialComponent towerSpatial = spatialMapper.get(e);
				
				ParametersWrapper parametersWrapper = new ParametersWrapper();
				parametersWrapper.put("position", towerSpatial.getPosition());
				parametersWrapper.put("direction",creepSpatial.getPosition().cpy().sub(towerSpatial.getPosition()).nor());
				parametersWrapper.put("speed", 10000f);
				entityFactory.instantiate(entityTemplates.bulletTemplate, parametersWrapper);
			}
		});
		
		entity.addComponent(scriptComponent);
		
		Body body = bodyBuilder.fixture(//
				bodyBuilder.fixtureDefBuilder().circleShape(10f)).type(BodyType.StaticBody).position(position.x,position.y).build();
		
		entity.addComponent(new PhysicsComponent(body));
		entity.addComponent(new SpatialComponent(new SpatialPhysicsImpl(body, 20, 20)));
		world.getGroupManager().set(Tags.TOWERS, entity);
	}
	
	/**
	 * speed
	 * pathtraversal
	 * hitpoints
	 */
}
