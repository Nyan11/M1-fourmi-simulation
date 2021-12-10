package modelisation.vivant.fourmi.objectif;

import modelisation.Terrain;
import modelisation.utils.Position;
import modelisation.vivant.fourmi.Comportement;
import modelisation.vivant.fourmi.etat.Adulte;

public class ChasseRecherche implements Objectif {

	private Objectif objectifRetour;
	
	public ChasseRecherche(Objectif objectifRetour) {
		this.objectifRetour = objectifRetour;
	}

	@Override
	public Objectif objectifSuivant(Terrain terrain, Adulte adulte) {
		return new ChasseAttaque(objectifRetour);
	}

	@Override
	public boolean aValiderObjectif(Terrain terrain, Adulte adulte) {
		Position position = adulte.getPosition();
		return terrain.contientProiePosition(position);
	}

	@Override
	public void step(Terrain terrain, Adulte adulte) {
		Position prochaine = adulte.getDeplacement().deplacementRecherche(terrain);
		adulte.setProchainePosition(prochaine);
		adulte.lacherPheromone(terrain);
	}

}
