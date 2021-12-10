package modelisation.vivant.fourmi.objectif;

import modelisation.Terrain;
import modelisation.utils.Position;
import modelisation.vivant.fourmi.etat.Adulte;

public class MaleSuivre implements Objectif {

	@Override
	public boolean aValiderObjectif(Terrain terrain, Adulte adulte) {
		return terrain.femellePosition(adulte.getPosition());
	}

	@Override
	public Objectif objectifSuivant(Terrain terrain, Adulte adulte) {
		return new MaleSeReproduire();
	}

	@Override
	public void step(Terrain terrain, Adulte adulte) {
		Position prochaine = adulte.getDeplacement().deplacementTrouverFemelle(terrain);
		adulte.setProchainePosition(prochaine);
	}

}
