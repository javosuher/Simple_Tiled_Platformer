package uca.workshop.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Map { // Clase que representa el nivel de nuestro juego con todos los objetos de el.
	private Main main;
	private SpriteBatch batch; // "Grupo de Sprites (imagenes)" nos permite dibujar rectagulos como referencias a texturas, es necesario para mostrar todo por pantalla.
	private TiledMap map; // El mapa importado de fichero tmx que se genera con tiled
	private TmxMapLoader loader; // Permite cargar el fichero tmx
	private OrthogonalTiledMapRenderer renderer; // El mapa contenido en un escenario ortogonal (Se le da las propiedades necesarias) para que lo pueda ver, usar y modificar el usuario
	
	private Player player; // El jugador del juego
	private Array<Rectangle> platforms; // Un array que contiene todas las plataformas del juego
	private Rectangle start, goal, returnStart; // La casilla de salida, la casilla de meta, y una casilla que si caes te devuelve al inicio, respectivamente.
	
	public Map(Main main, SpriteBatch batch) {
		this.main = main;
		loader = new TmxMapLoader();
		map = loader.load("tiledMap.tmx"); // Inicializamos las variables
		this.batch = batch;
		renderer = new OrthogonalTiledMapRenderer(map, batch);
		
		processMapMetadata(); // Este metodo inicializa los elementos del mapa.
	}

	public void draw(OrthographicCamera camera) { // Metodo para dibujar y actualizar los valores del mapa
		renderer.setView(camera);
		renderer.render();
		
		update();
		
		batch.begin();
		player.draw(batch);
		batch.end();
	}
	
	private void update() { // Actualiza los valores de los personajes del juego
		player.update(platforms); // Acualizamos los valores de player y comprobamos las colisiones con las plataformas
		if(player.getBody().overlaps(returnStart)) // Si se cae del escenario
			reset();
		
	}
	
	private void reset() { // Este metodo hace que el jugador vuelva a su posicion inicial
		player.setPosition(start.x, start.y);
	}
	
	public void dispose() { // Destruye los elementos innecesarios
		map.dispose();
		renderer.dispose();
		player.dispose();
	}
	
	public TiledMap getMap() { // Devuelve el mapa
		return map;
	}

	public Player getPlayer() { // Devuelve el jugador del mapa
		return player;
	}

	private void processMapMetadata() { // Este metodo inicializa los elementos del mapa.
		platforms = new Array<Rectangle>(); // Inicializamos los arrays

		MapObjects objects = map.getLayers().get("Objects").getObjects(); // Cogemos del mapa los objetos llamados "Objects"

		for (MapObject object : objects) {
			String name = object.getName(); // Cogemos su nombre
			RectangleMapObject rectangleObject = (RectangleMapObject) object; // Lo transformamos en rectangulo
			Rectangle rectangle = rectangleObject.getRectangle();

			if(name.equals("PlayerStart")) // Si se llama "PlayerStart"
				start = rectangle;
			if(name.equals("PlayerGoal")) // Si se llama "PlayerGoal"
				goal = rectangle;
			if(name.equals("returnStart")) // Si se llama "returnStart"
				returnStart = rectangle;
		}
		
		objects = map.getLayers().get("Physics").getObjects(); // Cogemos del mapa los objetos llamados "Physics"
		
		for (MapObject object : objects) {
			RectangleMapObject rectangleObject = (RectangleMapObject) object; // Lo transformamos en rectangulo
			Rectangle rectangle = rectangleObject.getRectangle();
			platforms.add(rectangle); // Las añadimos al array
		} 			
		
		player = new Player(); // Creamos al jugador
		player.setPosition(start.x, start.y); // Lo ponemos en la casilla salida.
	}
}
