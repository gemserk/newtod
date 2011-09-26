package com.gemserk.games.newtod.templates;

public class EntityTemplates {


	public EntityTemplates(EntityTemplatesHelper entityTemplatesHelper) {
		creepTemplate = new CreepTemplate(entityTemplatesHelper);
	}
	
	public final CreepTemplate creepTemplate;	
	
}
