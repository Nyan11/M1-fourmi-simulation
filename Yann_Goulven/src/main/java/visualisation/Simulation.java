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
	
	public void mainLoop() {
		while (true) {
			this.terrain.stepTerrain();
			;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		
		Simulation simulation = new Simulation();
		simulation.terrainVue.start();
		simulation.loop();
		
		/*
		Fourmi reine = new Fourmi(new Position(0,0), true);
		Terrain terrain = new Terrain(reine);
		
		ajoutProies(terrain, 50, 10, 30, 10);
		ajoutProies(terrain, 50, 40, 0, 10);
		ajoutProies(terrain, 50, -25, -20, 10);
		
		NiSpace space = new NiSpace("Simulation Fourmiliere", new Dimension(800, 600));
		TerrainVue main = new TerrainVue(terrain);
		//BilanVue bilan = new BilanVue(fourmiliere);
		//space.add(bilan.vue);
		space.add(main.vue);
		
		space.openInWindow();

		mainLoop(space, main, terrain);
		*/
		
	}

	private static void ajoutProies(Terrain terrain, int nb, int x, int y, int size) {
		for (int i = 0; i < nb; i++) {
			Position position = new Position(
					(int)(Math.random() * size - x),
					(int)(Math.random() * size - y)
				);
			List<EtreVivant> list = terrain.getMapProie().get(position);
			if (list == null) {
				list = new ArrayList<EtreVivant>();
				terrain.getMapProie().put(position, list);
			}
			list.add(new Proie((int)(Math.random() * 50 + 60), position));
		}
		
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
	
	private static void mainLoop(NiSpace space, TerrainVue vue, Terrain terrain) {
		/*for(int i = 0; i < 600; i++) {
			terrain.stepTerrain();
		}*/
		//BilanVue bilan = new BilanVue(terrain.getFourmilieres().get(0));
		//space.add(bilan.vue);
		int annee = 0;
		try {
			while(true) {
				annee++;
				for(int i = 0; i < 1; i++) {
				terrain.stepTerrain();
				ajoutProies(terrain, 1, 10, 30, 10);
				ajoutProies(terrain, 1, 40, 0, 10);
				ajoutProies(terrain, 1, -25, -20, 10);
				}
				//ajoutProies(terrain, 50, 50, 50, 100);
				
				vue.update();
				Thread.sleep(TEMPS_ENTRE_IMAGES_SIM);
				System.out.println("Simpuluation: nombre d'année : " + (annee / 3600.));
			}
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
	}
}
