package com.gemserk.games.newtod.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.gemserk.animation4j.transitions.sync.Synchronizers;
import com.gemserk.commons.artemis.EntityBuilder;
import com.gemserk.commons.artemis.WorldWrapper;
import com.gemserk.commons.artemis.render.RenderLayers;
import com.gemserk.commons.artemis.systems.ContainerSystem;
import com.gemserk.commons.artemis.systems.OwnerSystem;
import com.gemserk.commons.artemis.systems.PhysicsSystem;
import com.gemserk.commons.artemis.systems.RenderLayerSpriteBatchImpl;
import com.gemserk.commons.artemis.systems.RenderableSystem;
import com.gemserk.commons.artemis.systems.ScriptSystem;
import com.gemserk.commons.artemis.systems.SpriteUpdateSystem;
import com.gemserk.commons.artemis.systems.TagSystem;
import com.gemserk.commons.artemis.templates.EntityFactoryImpl;
import com.gemserk.commons.gdx.GameStateImpl;
import com.gemserk.commons.gdx.box2d.Box2DCustomDebugRenderer;
import com.gemserk.commons.gdx.box2d.JointBuilder;
import com.gemserk.commons.gdx.camera.Libgdx2dCamera;
import com.gemserk.commons.gdx.camera.Libgdx2dCameraTransformImpl;
import com.gemserk.componentsengine.input.InputDevicesMonitorImpl;
import com.gemserk.componentsengine.input.LibgdxInputMappingBuilder;
import com.gemserk.games.newtod.Game;
import com.gemserk.games.newtod.Layers;
import com.gemserk.games.newtod.templates.EntityTemplates;
import com.gemserk.resources.ResourceManager;

public class PlayGameState extends GameStateImpl {

	private final Game game;
	private ResourceManager<String> resourceManager;
	private SpriteBatch spriteBatch;
	private World physicsWorld;
	private JointBuilder jointBuilder;
	private Libgdx2dCameraTransformImpl worldCamera;
	private Libgdx2dCameraTransformImpl guiCamera;
	private RenderLayers renderLayers;
	private com.artemis.World world;
	private EntityFactoryImpl entityFactory;
	private WorldWrapper worldWrapper;
	private EntityBuilder entityBuilder;
	private Box2DCustomDebugRenderer box2dCustomDebugRenderer;
	private com.gemserk.games.newtod.templates.EntityTemplates entityTemplates;
	private InputDevicesMonitorImpl<String> inputDevicesMonitor;

	public PlayGameState(Game game) {
		this.game = game;
	}

	public void setResourceManager(ResourceManager<String> resourceManager) {
		this.resourceManager = resourceManager;
	}

	@Override
	public void init() {
		super.init();

		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();

		float centerX = width / 2;
		float centerY = height / 2;
		spriteBatch = new SpriteBatch();

		physicsWorld = new World(new Vector2(0, 0), false);

		jointBuilder = new JointBuilder(physicsWorld);

		worldCamera = new Libgdx2dCameraTransformImpl();
		worldCamera.center(centerX, centerY);
		float zoom = 100;
		worldCamera.zoom(zoom);
		worldCamera.move(centerX / zoom, centerY / zoom);

		guiCamera = new Libgdx2dCameraTransformImpl();

		Libgdx2dCamera backgroundLayerCamera = new Libgdx2dCameraTransformImpl();

		renderLayers = new RenderLayers();

		renderLayers.add(Layers.Background, new RenderLayerSpriteBatchImpl(-10000, -500, backgroundLayerCamera, spriteBatch));
		renderLayers.add(Layers.World, new RenderLayerSpriteBatchImpl(-500, 100, worldCamera));

		world = new com.artemis.World();
		entityFactory = new EntityFactoryImpl(world);
		worldWrapper = new WorldWrapper(world);

		worldWrapper.addUpdateSystem(new PhysicsSystem(physicsWorld));
		worldWrapper.addUpdateSystem(new ScriptSystem());
		worldWrapper.addUpdateSystem(new TagSystem());
		worldWrapper.addUpdateSystem(new ContainerSystem());
		worldWrapper.addUpdateSystem(new OwnerSystem());

		worldWrapper.addRenderSystem(new SpriteUpdateSystem());
		worldWrapper.addRenderSystem(new RenderableSystem(renderLayers));

		worldWrapper.init();

		entityBuilder = new EntityBuilder(world);

		box2dCustomDebugRenderer = new Box2DCustomDebugRenderer((Libgdx2dCameraTransformImpl) worldCamera, physicsWorld);
		entityTemplates = new EntityTemplates(physicsWorld, world, resourceManager, entityBuilder, entityFactory);

		inputDevicesMonitor = new InputDevicesMonitorImpl<String>();
		new LibgdxInputMappingBuilder<String>(inputDevicesMonitor, Gdx.input) {
			{
				monitorKeys("pause", Keys.BACK, Keys.ESCAPE);
			}
		};

		loadLevel();
	}

	private void loadLevel() {
	}

	@Override
	public void render() {
		Gdx.graphics.getGL10().glClear(GL10.GL_COLOR_BUFFER_BIT);

		worldWrapper.render();

		 if (Game.isShowBox2dDebug())
			 box2dCustomDebugRenderer.render();
	}

	@Override
	public void update() {

		inputDevicesMonitor.update();
		Synchronizers.synchronize(getDelta());
		worldWrapper.update(getDeltaInMs());
	}

	@Override
	public void dispose() {
		worldWrapper.dispose();
		spriteBatch.dispose();
	}
}
