package modelisation.fourmiliere;

import modelisation.Fourmiliere;
import modelisation.utils.Position;
import modelisation.vivant.proie.Proie;

public class Jeune implements Etat {

	@Override
	public void step(Fourmiliere fourmiliere) {
		fourmiliere.nourrirLarves(new Proie((float) 100.0, new Position(0,0)));
		fourmiliere.augmenteAge();
	}

	@Override
	public Etat changerEtat(Fourmiliere fourmiliere) {
		if (fourmiliere.ageAncien()) {
			return new Nominal();
		} else {
			return this;
		}
	}

}
