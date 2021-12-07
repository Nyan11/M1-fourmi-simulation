package modelisation.vivant.fourmi;

import java.util.List;

import modelisation.Terrain;
import modelisation.pheromone.Pheromone;
import modelisation.utils.Position;
import modelisation.utils.Simulable;
import modelisation.vivant.EtreVivant;

public class Fourmi implements EtreVivant, Simulable {

	private Position position;

	public Fourmi(int x, int y) {
		this.position = new Position(x, y);
	}
	
	public void deplacer(Terrain terrain) {
		/*
		// Faire les directions directement dans Position
		Position nord = new Position(this.position.x(), this.position.y() - 1);
		Position est = new Position(this.position.x() + 1, this.position.y());
		Position sud = new Position(this.position.x(), this.position.y() + 1);
		Position ouest = new Position(this.position.x() - 1, this.position.y());
		
		List<Pheromone> pNord = terrain.getPheromones().get(nord);
		List<Pheromone> pEst = terrain.getPheromones().get(est);
		List<Pheromone> pSud = terrain.getPheromones().get(sud);
		List<Pheromone> pOuest = terrain.getPheromones().get(ouest);
		
		int vNord = pNord.size();
		int vEst = pEst.size();
		int vSud = pSud.size();
		int vOuest = pOuest.size();
		*/
		
	}

	@Override
	public void step(Terrain terrain) {
		// TODO Auto-generated method stub
		
	}

}
