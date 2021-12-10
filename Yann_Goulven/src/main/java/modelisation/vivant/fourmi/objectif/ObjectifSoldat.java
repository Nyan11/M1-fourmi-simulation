package modelisation.vivant.fourmi.objectif;

import modelisation.Terrain;
import modelisation.vivant.fourmi.etat.Adulte;

public class ObjectifSoldat implements Objectif {
	
	@Override
	public boolean aValiderObjectif(Terrain terrain, Adulte adulte) {
		return true;
	}

	@Override
	public Objectif objectifSuivant(Terrain terrain, Adulte adulte) {
		return new SoldatPatrouille(this);
	}

	@Override
	public void step(Terrain terrain, Adulte adulte) {
		// TODO Auto-generated method stub
		
	}
}
