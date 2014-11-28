package uca.workshop.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Enemy extends Entity { // Clase que representa a los enemigos del juego
	public static final float WIDTH = 72; // Ancho
	public static final float HEIGHT = 97; // Alto
	
	private Texture frame1, frame2; // Texturas del enemigo
	private Animation animation; // Permite animar los sprites en el juego y que parezca que el enemigo se mueve.
	private float stateTime; // Permite devolver el frame indicado

	public Enemy() {
		super(WIDTH, HEIGHT, new Rectangle());
		
		stateTime = 0;
		setPosition(0, 0); // Inicializamos los valores necesarios
		
		frame1 = new Texture("Enemies/barnacle.png"); 
		frame2 = new Texture("Enemies/barnacle_bite.png"); // Cargamos las texturas
		animation = new Animation(0.5f, new TextureRegion(frame1), new TextureRegion(frame2)); // Las intoducimos en la animacion con una velocidad indicada en el primer parametro
		animation.setPlayMode(PlayMode.LOOP); // Ponemos que se ejecute en bucle.
	}
	
	@Override
	public void draw(SpriteBatch batch) { // Permite dibujar al enemigo
		
		// MAKE: Cambiar al frame adecuado y dibujar.
		
	}
	
	public void update() { // Actualizamos los valores del enemigo
		stateTime += Gdx.graphics.getDeltaTime(); // Aumentamos statetime para cambiar la textura del enemigo.
	}
	
	public void dispose() { // Eliminamos los recursos innecesarios.
		frame1.dispose();
		frame2.dispose();
	}
}
