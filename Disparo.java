package juego;

import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Disparo {
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private double escala;
	private Image imagen;

	public Disparo(double x, double y, double ancho, double alto) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.escala = 0.1;
		this.imagen = Herramientas.cargarImagen("poder.png");
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getAncho() {
		return ancho;
	}

	public double getAlto() {
		return alto;
	}
	public void mover() {
		this.y = this.y-1;
	}

	void dibujar(Entorno entorno) {
		entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.white);
	//	entorno.dibujarImagen(this.imagen, this.x, this.y, 0, this.escala);
	}

}
