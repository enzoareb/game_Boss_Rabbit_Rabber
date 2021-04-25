package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Conejo {
	private double x;
	private double y;
	private double alto;
	private double ancho;
	private int vidas;
	private double escala;
	private Image imagen;

	Conejo(double x, double y, double alto, double ancho) {
		this.x = x;
		this.y = y;
		this.alto = alto;
		this.ancho = ancho;
		this.vidas = 3;
		this.escala = 0.5;
		this.imagen = Herramientas.cargarImagen("conejo2.png");
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getAlto() {
		return alto;
	}

	public double getAncho() {
		return ancho;
	}

	public int getVidas() {
		return vidas;
	}

	public void descontarVida() {
		this.vidas--;
	}

	void moverArriba() {
		this.y = this.y - this.alto;
	}

	void moverDerecha() {
		this.x = this.x + this.ancho;
	}

	void moverIzquieda() {
		this.x = this.x - this.ancho;
	}

	void dibujar(Entorno entorno) {
		entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.yellow);
		entorno.dibujarImagen(this.imagen, this.x, this.y, 0, this.escala);
	}

	Disparo dispararPoder(double x, double y, double ancho, double alto) {
		Disparo disparo = new Disparo(x, y, ancho, alto);
		return disparo;
	}

}
