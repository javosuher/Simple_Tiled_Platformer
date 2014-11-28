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
	
	public Map(Main main, SpriteBatch batch) {
		this.main = main;
		loader = new TmxMapLoader();
		map = loader.load("tiledMap.tmx"); // Inicializamos las variables
		this.batch = batch;
		renderer = new OrthogonalTiledMapRenderer(map, batch);
	}

	public void draw(OrthographicCamera camera) { // Metodo para dibujar y actualizar los valores del mapa
		renderer.setView(camera);
		renderer.render();
	}
	
	public void dispose() { // Destruye los elementos innecesarios
		map.dispose();
		renderer.dispose();
	}
	
	public TiledMap getMap() { // Devuelve el mapa
		return map;
	}
}
