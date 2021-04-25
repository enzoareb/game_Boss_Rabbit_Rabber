package juego;

import java.awt.Color;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	// Variables
	private Pantalla pantalla;
	private Pantalla cesped1;
	private Pantalla cesped2;
	private Pantalla cesped3;
	private Pantalla vias;
	private Conejo conejo;
	private int puntos = 0;
	//private Disparo[] disparos = new Disparo[4];
	private Disparo disparo;
	private Auto[][] calle1 = new Auto[8][3];
	private Tren tren;
	private int tiempo = 0;

	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Boss Rabbit Rabber - Grupo ... - v1", 800, 600);

		// Inicializacion de objetos
		this.pantalla = new Pantalla(400, 300, 800, 600);
		this.cesped1 = new Pantalla(400, 565, this.entorno.ancho(), 50);
		this.cesped2 = new Pantalla(400, 300, this.entorno.ancho(), 80);
		this.cesped3 = new Pantalla(400, 35, this.entorno.ancho(), 50);
		this.vias = new Pantalla(400, -5, this.entorno.ancho(), 40);
		this.tren = new Tren(-100, -5, 40, 370);
		this.conejo = new Conejo(400, 545, 30, 28);
		this.disparo = new Disparo(this.conejo.getX(),
				this.conejo.getY() - ((this.conejo.getAlto() / 2) + this.conejo.getAlto() * 3 / 2),
				this.conejo.getAncho(), this.conejo.getAlto() * 3);

		this.calle1[0][0] = new Auto(350, 500, 40, 50);
		this.calle1[0][1] = new Auto(500, 500, 40, 50);
		this.calle1[0][2] = new Auto(700, 500, 40, 50);
		this.calle1[1][0] = new Auto(150, 460, 40, 50);
		this.calle1[1][1] = new Auto(550, 460, 40, 50);
		this.calle1[1][2] = new Auto(750, 460, 40, 50);
		this.calle1[2][0] = new Auto(200, 420, 40, 50);
		this.calle1[2][1] = new Auto(450, 420, 40, 50);
		this.calle1[2][2] = new Auto(800, 420, 40, 50);
		this.calle1[3][0] = new Auto(250, 380, 40, 50);
		this.calle1[3][1] = new Auto(500, 380, 40, 50);
		this.calle1[3][2] = new Auto(650, 380, 40, 50);
		this.calle1[4][0] = new Auto(250, 220, 40, 50);
		this.calle1[4][1] = new Auto(400, 220, 40, 50);
		this.calle1[4][2] = new Auto(560, 220, 40, 50);
		this.calle1[5][0] = new Auto(300, 180, 40, 50);
		this.calle1[5][1] = new Auto(450, 180, 40, 50);
		this.calle1[5][2] = new Auto(550, 180, 40, 50);
		this.calle1[6][0] = new Auto(100, 140, 40, 50);
		this.calle1[6][1] = new Auto(250, 140, 40, 50);
		this.calle1[6][2] = new Auto(500, 140, 40, 50);
		this.calle1[7][0] = new Auto(50, 100, 40, 50);
		this.calle1[7][1] = new Auto(200, 100, 40, 50);
		this.calle1[7][2] = new Auto(400, 100, 40, 50);
		// Inicia el juego!
		this.entorno.iniciar();
	}

	// verifica la superposicion de 2 objetos
	boolean colicion(double x1, double y1, double ancho1, double alto1, double x2, double y2, double ancho2,
			double alto2) {
		boolean tx = (x1 + ancho1 / 2 > x2 - ancho2 / 2) && (x1 - ancho1 / 2 < x2 + ancho2 / 2);
		boolean ty = (y1 + alto1 / 2 > y2 - alto2 / 2) && (y1 - alto1 / 2 < y2 + alto2 / 2);
		return tx && ty;
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y por lo
	 * tanto es el método más importante de esta clase. Aquí se debe actualizar el
	 * estado interno del juego para simular el paso del tiempo (ver el enunciado
	 * del TP para mayor detalle).
	 */

	public void tick() {
		// Procesamiento de un instante de tiempo
		// ...
		// verifica si el conejo tiene vidas
		if (this.conejo.getVidas() > 0) {
			// cuenta los segundos
			this.tiempo++;
			// dibuja los objetos
			this.cesped2.dibujar(this.entorno);
			this.cesped1.dibujar(this.entorno);
			this.cesped3.dibujar(this.entorno);
			this.vias.dibujarVias(entorno, vias);
			this.conejo.dibujar(this.entorno);
			this.tren.dibujar(this.entorno);
			// metodos para mover y objetos y controlar cuando se salgan de pantalla
			this.tren.mover();
			this.pantalla.moverPantalla(this.conejo, this.calle1, this.tren, this.cesped1, this.cesped2, this.cesped3,
					this.vias);
			if (this.tren.getX() > 2000) {
				this.tren.setX(-2000);
			}
			if (this.tren.getY() - this.tren.getAlto() / 2 > this.entorno.alto()) {
				this.tren.setY(0);
			}
			if (this.vias.getY() - this.vias.getAlto() / 2 > this.entorno.alto()) {
				this.vias.setY(0);
			}
			if (this.cesped1.getY() - this.cesped1.getAlto() / 2 > this.entorno.alto()) {
				this.cesped1.setY(20);
			}
			if (this.cesped2.getY() - this.cesped2.getAlto() / 2 > this.entorno.alto()) {
				this.cesped2.setY(20);
			}
			if (this.cesped3.getY() - this.cesped3.getAlto() / 2 > this.entorno.alto()) {
				this.cesped3.setY(20);
			}

			// escribe indicadores de tiempo, puntos, vidas en pantalla
			this.entorno.cambiarFont(null, 20, Color.white);
			this.entorno.escribirTexto("vidas " + conejo.getVidas(), 660, 25);
			this.entorno.escribirTexto("puntos: " + puntos, 660, 45);
			this.entorno.escribirTexto("tiempo: " + Math.round(tiempo * 0.01), 660, 65);

			// verifica la tecla presionada por teclado y realiza la accion
			if (this.entorno.sePresiono(this.entorno.TECLA_ARRIBA)
					&& (this.conejo.getY() - (this.conejo.getAlto() / 2)) > 0) {
				this.conejo.moverArriba();
				this.puntos++;
			} else if (this.entorno.sePresiono(this.entorno.TECLA_DERECHA)
					&& (this.conejo.getX() + this.conejo.getAncho() / 2) < this.entorno.ancho()) {
				this.conejo.moverDerecha();

			} else if (this.entorno.sePresiono(this.entorno.TECLA_IZQUIERDA)
					&& (this.conejo.getX() - this.conejo.getAncho() / 2) > 0) {
				this.conejo.moverIzquieda();
			}
			if (this.entorno.sePresiono(this.entorno.TECLA_ESPACIO)) {
				
				Disparo poderDisparo=this.conejo.dispararPoder(conejo.getX(), conejo.getY()-conejo.getAlto(), conejo.getAncho(), conejo.getAlto());
				poderDisparo.dibujar(entorno);
				poderDisparo.mover();
				//disparo.dibujar(entorno);
				//disparo.mover();
			}
			
			//disparo.dibujar(entorno);
		//	disparo.mover();
			

			// movimiento de los autos a traves de 2 ciclos for
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 3; j++) {
					// dibuja los autos
					this.calle1[i][j].dibujar(entorno);
					// si hay una colicion de conejo/auto o conejo/tren o el conejo se sale de
					// pantalla
					// reinicia los objetos a sus posiciones originales
					if (colicion(this.conejo.getX(), this.conejo.getY(), this.conejo.getAncho(), this.conejo.getAlto(),
							this.calle1[i][j].getX(), this.calle1[i][j].getY(), this.calle1[i][j].getAncho(),
							this.calle1[i][j].getAlto())
							|| colicion(this.conejo.getX(), this.conejo.getY(), this.conejo.getAncho(),
									this.conejo.getAlto(), this.tren.getX(), this.tren.getY(), tren.getAncho(),
									this.tren.getAlto())
							|| this.conejo.getY() - this.conejo.getAlto() > 590) {
						if (conejo.getVidas() > 0) {
							conejo.descontarVida();
							this.calle1[0][0] = new Auto(350, 500, 40, 50);
							this.calle1[0][1] = new Auto(500, 500, 40, 50);
							this.calle1[0][2] = new Auto(700, 500, 40, 50);
							this.calle1[1][0] = new Auto(150, 460, 40, 50);
							this.calle1[1][1] = new Auto(550, 460, 40, 50);
							this.calle1[1][2] = new Auto(750, 460, 40, 50);
							this.calle1[2][0] = new Auto(200, 420, 40, 50);
							this.calle1[2][1] = new Auto(450, 420, 40, 50);
							this.calle1[2][2] = new Auto(800, 420, 40, 50);
							this.calle1[3][0] = new Auto(250, 380, 40, 50);
							this.calle1[3][1] = new Auto(500, 380, 40, 50);
							this.calle1[3][2] = new Auto(650, 380, 40, 50);
							this.calle1[4][0] = new Auto(250, 220, 40, 50);
							this.calle1[4][1] = new Auto(400, 220, 40, 50);
							this.calle1[4][2] = new Auto(560, 220, 40, 50);
							this.calle1[5][0] = new Auto(300, 180, 40, 50);
							this.calle1[5][1] = new Auto(450, 180, 40, 50);
							this.calle1[5][2] = new Auto(550, 180, 40, 50);
							this.calle1[6][0] = new Auto(100, 140, 40, 50);
							this.calle1[6][1] = new Auto(250, 140, 40, 50);
							this.calle1[6][2] = new Auto(500, 140, 40, 50);
							this.calle1[7][0] = new Auto(50, 100, 40, 50);
							this.calle1[7][1] = new Auto(200, 100, 40, 50);
							this.calle1[7][2] = new Auto(400, 100, 40, 50);
							this.conejo.setX(400);
							this.conejo.setY(545);
							this.tren = new Tren(-100, -5, 40, 370);
							this.vias = new Pantalla(400, -5, this.entorno.ancho(), 40);
							this.cesped1 = new Pantalla(400, 565, this.entorno.ancho(), 75);
							this.cesped2 = new Pantalla(400, 300, this.entorno.ancho(), 80);
							this.cesped3 = new Pantalla(400, 30, this.entorno.ancho(), 80);
						}
					}
					// si hay colicion disparo/auto reposiciona el auto y suma 5 puntos
					if (this.entorno.sePresiono(this.entorno.TECLA_ESPACIO)
							&& colicion(this.disparo.getX(), this.disparo.getY(), this.disparo.getAncho(),
									this.disparo.getAlto(), this.calle1[i][j].getX(), this.calle1[i][j].getY(),
									this.calle1[i][j].getAncho(), this.calle1[i][j].getAlto())) {
						this.calle1[i][j].agregarAuto(this.calle1, i, j);
						this.puntos += 5;
					}
					// controla que los autos que se vayan por abajo reaparezcan por arriba de la
					// pantalla
					if (this.calle1[i][j].getY() - this.calle1[i][j].getAlto() / 2 > this.entorno.alto()) {
						this.calle1[i][j].setY(0);
					}
					// control de direccion, limites y velocidad de cada carril
					if (i == 0) {
						this.calle1[0][j].moverDerecha();
						if (this.calle1[0][j].getX() > this.entorno.getWidth() + 150) {
							this.calle1[0][j].setX(-150);
						}
					}
					if (i == 1) {
						this.calle1[1][j].moverIzquieda();
						if (this.calle1[1][j].getX() < 0) {
							this.calle1[1][j].setX(this.entorno.getWidth());
						}
					}
					if (i == 2) {
						this.calle1[2][j].moverDerecha();
						if (this.calle1[2][j].getX() > this.entorno.getWidth()) {
							this.calle1[2][j].setX(0);
						}
					}
					if (i == 3) {
						this.calle1[3][j].setVelocidad(2);
						this.calle1[3][j].moverIzquieda();
						if (this.calle1[3][j].getX() < 0) {
							this.calle1[3][j].setX(this.entorno.getWidth());
						}
					}
					if (i == 4) {
						this.calle1[4][j].moverDerecha();
						if (this.calle1[4][j].getX() > this.entorno.getWidth()) {
							this.calle1[4][j].setX(0);
						}
					}
					if (i == 5) {
						this.calle1[5][j].moverIzquieda();
						if (this.calle1[5][j].getX() < 0) {
							this.calle1[5][j].setX(this.entorno.getWidth());
						}
					}
					if (i == 6) {
						this.calle1[6][j].setVelocidad(2);
						this.calle1[6][j].moverDerecha();
						if (this.calle1[6][j].getX() > this.entorno.getWidth()) {
							this.calle1[6][j].setX(0);
						}
					}
					if (i == 7) {
						this.calle1[7][j].setVelocidad(2);
						this.calle1[7][j].moverIzquieda();
						if (this.calle1[7][j].getX() < 0) {
							this.calle1[7][j].setX(this.entorno.getWidth());
						}
					}

				}

			}
		} else {
			// si el conejo no tiene vidas dibuja pantalla final con estadisticas
			double tiempoFinal = Math.round(this.tiempo * 0.01);
			this.pantalla.gameOver(this.entorno, this.puntos, tiempoFinal);

		}

	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}