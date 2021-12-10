package modelisation.vivant.fourmi.objectif;

import modelisation.Terrain;
import modelisation.utils.Position;
import modelisation.vivant.fourmi.etat.Adulte;

public class FemelleFuir implements Objectif {

	@Override
	public boolean aValiderObjectif(Terrain terrain, Adulte adulte) {
		return terrain.malePosition(adulte.getPosition());
	}

	@Override
	public Objectif objectifSuivant(Terrain terrain, Adulte adulte) {
		return new FemelleSeReproduire(this);
	}

	@Override
	public void step(Terrain terrain, Adulte adulte) {
		Position prochaine = adulte.getDeplacement().deplacementFuirFourmiliere(terrain);
		adulte.setProchainePosition(prochaine);
		adulte.lacherPheromoneReproduction(terrain);
	}

}
