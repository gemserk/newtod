package com.gemserk.games.newtod.templates;

import com.artemis.World;
import com.gemserk.commons.artemis.components.ComponentMapperInitHelper;
import com.gemserk.commons.artemis.templates.EntityTemplate;
import com.gemserk.commons.reflection.Injector;

public class EntityTemplates {

	class TemplateInjector {
		World world;
		Injector injector;
		
		public <T> T getInstance(Class<? extends T> clazz) {
			T instance = injector.getInstance(clazz);
			ComponentMapperInitHelper.config(instance, world.getEntityManager());
			return instance;
		}
		
	}
	
	
	public EntityTemplates(Injector injector) {
		injector.bind("entityTemplates", this);
		
		TemplateInjector templateInjector = new TemplateInjector();
		injector.injectMembers(templateInjector);
		
		this.creepTemplate = templateInjector.getInstance(CreepTemplate.class);
		this.towerTemplate = templateInjector.getInstance(TowerTemplate.class);
		this.bulletTemplate = templateInjector.getInstance(BulletTemplate.class);
	}
	
	public final CreepTemplate creepTemplate;
	public final TowerTemplate towerTemplate;
	public final  BulletTemplate bulletTemplate;	
	
}
