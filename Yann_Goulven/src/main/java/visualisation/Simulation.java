package visualisation;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import modelisation.Terrain;
import modelisation.utils.Position;
import modelisation.vivant.EtreVivant;
import modelisation.vivant.fourmi.Fourmi;
import modelisation.vivant.proie.Proie;
import nicellipse.component.NiSpace;


public class Simulation {
	public static final int ZOOM = 5;
	public static final int TEMPS_ENTRE_IMAGES_SIM = 5;
	public static final int TEMPS_ENTRE_IMAGES_BILAN = 30;

	TerrainVue terrainVue;
	Terrain terrain;
	NiSpace space;
	
	public Simulation() {
		Fourmi reine = new Fourmi(new Position(0,0), true);
		this.terrain = new Terrain(reine);
		
		this.space = new NiSpace("Simulation Fourmiliere", new Dimension(800, 600));
		this.terrainVue = new TerrainVue(terrain);
		
		this.space.add(this.terrainVue.vue);
		this.space.setDoubleBuffered(true);
		
		space.openInWindow();
	}
	
	public static void main(String[] args) {
		Simulation simulation = new Simulation();
		//simulation.withoutTimer();
		simulation.terrainVue.start();
		simulation.loop();
		
	}

	public void loop() {
		while (true) {
			this.terrain.stepTerrain();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void withoutTimer() {
		try {
			while(true) {
				for(int i = 0; i < 1; i++) {
					this.terrain.stepTerrain();
				}
				this.terrainVue.update();
				Thread.sleep(10);
			}
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
	}
}
