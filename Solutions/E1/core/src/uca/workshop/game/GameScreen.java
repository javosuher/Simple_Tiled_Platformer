package uca.workshop.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class GameScreen extends AbstractScreen { // Pantalla principal del juego.
	private static final float CAMERA_SPEED = 300.0f;
	
	private Map map; // El mapa de nuestra pantalla
	private Vector2 direction;

	public GameScreen(Main main) {
		super(main);
		map = new Map(main, batch); // Creamos el mapa
		direction = new Vector2();
	}
	
	@Override
	public void render(float delta) { // Método que permite actualizar los valores del juego y dibujar el juego para que lo vea el usuario.
		Gdx.gl.glClearColor(0, 0, 0, 1);    //Gdx es una clase con la que podemos acceder a variables que hacen referencia a todos los subsitemas, como son graficos, audio, ficheros, entrada y aplicaciones
		                                    // gl es una variable de tipo GL, nos permite acceder a metodos de GL10, GL11 y GL20
		                                   //En este caso glClearColor es un bucle (game loop) que establecera el fondo de la pantalla negro (0,0,0) con transparencia 1
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Despues de la funcion anterior es necesario ejecutar esta, para que se lleve a cabo
		
		updateCamera();
		map.draw(camera); // Dibujamos el mapa
	}
	
	@Override
	public void dispose() { // Metodo que se encarga de destruir los valores del juego y liberar recursos.
		map.dispose();
	}
	
	private void updateCamera() { // Método que permite actualizar los valores de la camara para poder ver a nuestro protagonista.
		direction.set(0.0f, 0.0f); // Establecemos la posicion inicial
		
		int mouseX = Gdx.input.getX(); // Cogemos las coordenadas del raton al pinchar
		int mouseY = Gdx.input.getY();
		int width = Gdx.graphics.getWidth(); // Cogemos los parametros a usar
		int height = Gdx.graphics.getHeight();
		
		// Sirve para ver el escenario
		
		if (Gdx.input.isKeyPressed(Keys.LEFT) || (Gdx.input.isTouched() && mouseX < width * 0.25f)) { // Si presionamos en la parte izquierda o la tecla correspodiente
			direction.x = -1;
		}
		else if (Gdx.input.isKeyPressed(Keys.RIGHT) || (Gdx.input.isTouched() && mouseX > width * 0.75f)) { // Si presionamos en la parte derecha o la tecla correspodiente
			direction.x = 1;
		}
		
		if (Gdx.input.isKeyPressed(Keys.UP) || (Gdx.input.isTouched() && mouseY < height * 0.25f)) { // Si presionamos en la parte de arriba o la tecla correspodiente
			direction.y = 1;
		}
		else if (Gdx.input.isKeyPressed(Keys.DOWN) || (Gdx.input.isTouched() && mouseY > height * 0.75f)) { // Si presionamos en la parte de abajo o la tecla correspodiente
			direction.y = -1;
		}
		
		direction.nor().scl(CAMERA_SPEED * Gdx.graphics.getDeltaTime());; // Normalizamos
		
		camera.position.x += direction.x;
		camera.position.y += direction.y;

		TiledMapTileLayer layer = (TiledMapTileLayer)map.getMap().getLayers().get(0); // Cogemos una capa del mapa

		float cameraMinX = viewport.getWorldWidth() * 0.5f; // Minimo punto en el eje x
		float cameraMinY = viewport.getWorldHeight() * 0.5f; // Minimo punto en el eje y
		float cameraMaxX = layer.getWidth() * layer.getTileWidth() - cameraMinX; // Maximo punto en el eje x
		float cameraMaxY = layer.getHeight() * layer.getTileHeight() - cameraMinY; // Maximo punto en el eje y

		camera.position.x = MathUtils.clamp(camera.position.x, cameraMinX, cameraMaxX); // Escogemos camera.posicion.x si es mayor de cameraMinX o menor que cameraMaxX, en cualquier otro caso que 
		camera.position.y= MathUtils.clamp(camera.position.y, cameraMinY, cameraMaxY); // no se cumpla la condicion se coge cameraMinX o cameraMaxX, en ese orden. El segundo es igual pero con el eje Y
                                                                                       // Asi conseguimos que la camara no se salga del escenario.
		camera.update(); // Actualizamos la camara
	}
}
