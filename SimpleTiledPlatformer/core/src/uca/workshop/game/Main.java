package uca.workshop.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/*AplicationListener es una interfaz que proporciona metodos que se llaman cada vez que es necesario
* crear, pausar, continuar, renderizar o destruir una aplicacion, nos permite ademas manejar los graficos
*/
/* Game es una clase que implementa de AplicationListener y que permite delegar en una Screen,
* es decir, que permite a la alicacion tener y manejar facilmente varias ventanas
*/
// Main es la clase principal de nuestro juego, es decir, es la primera que se llama cuando se ejecuta.

public class Main extends Game {
	private static final int WIDTH = 800; // Ancho pantalla de juego
	private static final int HEIGHT = 700; // Alto pantalla de juego
	
	public AbstractScreen gameScreen; // Pantalla principal del juego
	public SpriteBatch batch; // "Grupo de Sprites (imagenes)" nos permite dibujar rectagulos como referencias a texturas, es necesario para mostrar todo por pantalla.
	public OrthographicCamera camera; // Camara con una proyección ortografica para ver el juego
	public Viewport viewport; // Permite que el juego se redimensione en diferentes pantallas de dispositivos. Tiene mucha importancia cuando desarrollamos el juego para android
	public boolean win; // Booleano que decidirá si se ha acabado el juego exitosamente o no.

	@Override
	public void create() { // Método que se llama cuando se ejecuta el juego
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		viewport = new FitViewport(WIDTH, HEIGHT, camera); // Inicializamos todo
		gameScreen = new GameScreen(this);
		win = false;
		setScreen(gameScreen); // Establecemos la pantalla del juego como principal
	}

	@Override
	public void dispose() { // Método para eliminar recursos.
		gameScreen.dispose();
		batch.dispose();
	}
}
