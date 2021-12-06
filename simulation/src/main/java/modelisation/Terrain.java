package modelisation;

import java.util.Collection;

import modelisation.pheromone.Pheromone;
import modelisation.vivant.fourmi.Fourmi;
import modelisation.vivant.proie.Proie;

public class Terrain {
	
	public final int TERRAIN_LARGEUR = 1200;
	public final int TERRAIN_LONGUEUR = 1200;
	
	public final int TAILLE_CASE = 30;
	public final int TEMPS_ETAPE = 10;
	
	protected Collection<Fourmi> fourmis;
	protected Collection<Pheromone> pheromones;
	protected Collection<Proie> proies;
	
	protected Collection<Fourmi> fourmilliere;

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
		for(Fourmi fourmi : this.fourmis) {
			fourmi.step(this);
		}
	}

	private void stepPheromones() {
		for(Pheromone pheromone : this.pheromones) {
			pheromone.step(this);
		}
	}
	
	private void stepProies() {
		for(Proie proie : this.proies) {
			proie.step(this);
		}
	}

}
