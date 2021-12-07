package modelisation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelisation.pheromone.Pheromone;
import modelisation.utils.Position;
import modelisation.vivant.fourmi.Fourmi;
import modelisation.vivant.proie.Proie;

public class Terrain {
	
	public final int TERRAIN_LARGEUR = 1200;
	public final int TERRAIN_LONGUEUR = 1200;
	
	public final int TAILLE_CASE = 30;
	public final int TEMPS_ETAPE = 10;
	
	protected Map<Position, List<Fourmi>> fourmis;
	protected Map<Position, List<Pheromone>> pheromones;
	protected Map<Position, List<Proie>> proies;
	
	protected Collection<Fourmiliere> fourmilliere;
	
	
	public Terrain() {
		this.fourmis = new HashMap<Position, List<Fourmi>>();
		this.pheromones = new HashMap<Position, List<Pheromone>>();
		this.proies = new HashMap<Position, List<Proie>>();
		this.fourmilliere = new ArrayList<Fourmiliere>();
	}

	/**Main loop de la simulation.
	 * Met à jour l'état des différents objets de la simulation avec l'ordre suivant :
	 * - fourmis
	 * - pheromones
	 * - proies
	 */
	public void mainLoop() {
		while(true) {
			this.stepFourmis();
			this.stepPheromones();
			this.stepProies();
		}
	}


	private void stepFourmis() {
		for (List<Fourmi> list : this.fourmis.values()) {
			for (Fourmi fourmi : list) {
				fourmi.step(this);
			}
		}
	}

	private void stepPheromones() {
		for(List<Pheromone> list : this.pheromones.values()) {
			for (Pheromone pheromone : list) {
				pheromone.step(this);
			}
		}
	}
	
	private void stepProies() {
		for(List<Proie> list : this.proies.values()) {
			for (Proie proie : list) {
				proie.step(this);
			}
		}
	}
	
	public Map<Position, List<Pheromone>> getPheromones() {
		return this.pheromones;
	}

}
