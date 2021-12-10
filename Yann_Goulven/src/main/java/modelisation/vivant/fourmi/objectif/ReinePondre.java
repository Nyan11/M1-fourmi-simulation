package modelisation.vivant.fourmi.objectif;

import modelisation.Terrain;
import modelisation.utils.Position;
import modelisation.vivant.fourmi.etat.Adulte;

public class ReinePondre implements Objectif {

	@Override
	public boolean aValiderObjectif(Terrain terrain, Adulte adulte) {
		return false;
	}

	@Override
	public Objectif objectifSuivant(Terrain terrain, Adulte adulte) {
		return null;
	}

	@Override
	public void step(Terrain terrain, Adulte adulte) {
		if (terrain.periodePonte()) {
			adulte.getFourmiliere().pondreOeufs(2);
		} else {
			int random = (int) (Math.random() * 3);
			if (random == 0) {
				adulte.getFourmiliere().pondreOeufs(1);
			}
		}
	}

}
