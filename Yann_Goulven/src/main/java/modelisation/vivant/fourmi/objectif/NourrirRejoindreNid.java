package modelisation.vivant.fourmi.objectif;

import modelisation.Terrain;
import modelisation.utils.Position;
import modelisation.vivant.fourmi.Comportement;
import modelisation.vivant.fourmi.etat.Adulte;

public class NourrirRejoindreNid implements Objectif {

	private Objectif objectifRetour;
	
	public NourrirRejoindreNid(Objectif objectifRetour) {
		this.objectifRetour = objectifRetour;
	}

	@Override
	public boolean aValiderObjectif(Terrain terrain, Adulte adulte) {
		return adulte.presentSurFourmiliere();
	}

	@Override
	public Objectif objectifSuivant(Terrain terrain, Adulte adulte) {
		return new NourrirDonnerNourriture(objectifRetour);
	}

	@Override
	public void step(Terrain terrain, Adulte adulte) {
		Position prochaine = adulte.getDeplacement().deplacementVersFourmiliere(terrain);
		adulte.setProchainePosition(prochaine);
	}

}
