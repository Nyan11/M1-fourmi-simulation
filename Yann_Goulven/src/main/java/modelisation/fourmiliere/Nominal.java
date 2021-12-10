package modelisation.fourmiliere;

import modelisation.Fourmiliere;

public class Nominal implements Etat {

	@Override
	public void step(Fourmiliere fourmiliere) { }

	@Override
	public Etat changerEtat(Fourmiliere fourmiliere) {
		return this;
	}

}
