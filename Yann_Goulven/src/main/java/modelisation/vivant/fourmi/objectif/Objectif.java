package modelisation.vivant.fourmi.objectif;

import modelisation.Terrain;
import modelisation.vivant.fourmi.etat.Adulte;

public interface Objectif {
	
	public boolean aValiderObjectif(Terrain terrain, Adulte adulte);
	
	public Objectif objectifSuivant(Terrain terrain, Adulte adulte);

	public void step(Terrain terrain, Adulte adulte);

}
