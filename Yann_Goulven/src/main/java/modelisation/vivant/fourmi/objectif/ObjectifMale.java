package modelisation.vivant.fourmi.objectif;

import modelisation.Terrain;
import modelisation.vivant.fourmi.etat.Adulte;

public class ObjectifMale implements Objectif {

	@Override
	public boolean aValiderObjectif(Terrain terrain, Adulte adulte) {
		return true;
	}

	@Override
	public Objectif objectifSuivant(Terrain terrain, Adulte adulte) {
		if (terrain.periodeDeReproduction()) {
			return new MaleSuivre();
		} else {
			return new SexuelBalade(this);
		}
	}

	@Override
	public void step(Terrain terrain, Adulte adulte) { }
}
