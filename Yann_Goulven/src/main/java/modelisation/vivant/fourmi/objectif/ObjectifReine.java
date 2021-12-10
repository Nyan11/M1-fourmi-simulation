package modelisation.vivant.fourmi.objectif;

import modelisation.Terrain;
import modelisation.utils.Position;
import modelisation.vivant.fourmi.etat.Adulte;

public class ObjectifReine implements Objectif {

	@Override
	public boolean aValiderObjectif(Terrain terrain, Adulte adulte) {
		return terrain.positionLibre(adulte.getPosition(), 10, adulte.getFourmi());
	}

	@Override
	public Objectif objectifSuivant(Terrain terrain, Adulte adulte) {
		return new ReineFonder();
	}

	@Override
	public void step(Terrain terrain, Adulte adulte) {
		Position prochaine = adulte.getDeplacement().deplacementFuirFourmiliere(terrain);
		adulte.setProchainePosition(prochaine);
	}

}
