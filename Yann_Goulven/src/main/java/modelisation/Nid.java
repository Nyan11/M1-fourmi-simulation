package modelisation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import modelisation.utils.Position;
import modelisation.utils.Simulable;
import modelisation.vivant.EtreVivant;
import modelisation.vivant.fourmi.Fourmi;
import modelisation.vivant.fourmi.etat.Oeuf;

public class Nid implements Simulable {
	
	public Fourmi reine;
	public Collection<Fourmi> enfants;
	public Collection<Fourmi> aAjouter;
	public Collection<Fourmi> aRetirer;
	public Position position;

	public Nid(Position position, Fourmi reine) {
		this.enfants = new LinkedList<Fourmi>();
		this.aAjouter = new LinkedList<Fourmi>();
		this.aRetirer = new LinkedList<Fourmi>();
		this.position = position;
		this.reine = reine;
	}

	@Override
	public void step(Terrain terrain) {
		for (Fourmi enfant : enfants) {
			enfant.step(terrain);
		}
		this.enfants.removeAll(this.aRetirer);
		this.aRetirer.clear();
		this.enfants.addAll(this.aAjouter);
		this.aAjouter.clear();
	}
	
	public Position getPosition() {
		return this.position;
	}

	public int getNbOeufs() {
		int ret = 0;
		for (Fourmi enfant : enfants) {
			if (enfant.getStade() instanceof Oeuf) {
				ret++;
			}
		}
		return ret;
	}

	public void ajouteEnfant(Fourmi oeuf) {
		this.aAjouter.add(oeuf);
	}

	public void retireEnfant(Fourmi fourmi) {
		this.aRetirer.add(fourmi);
	}

	public EtreVivant nourrirLarves(EtreVivant nourriture) {
		if (reine != null) {
			reine.nourrir(nourriture);
		}
		if (nourriture == null) {
			return null;
		}
		for (Fourmi enfant : this.enfants) {
			nourriture = enfant.nourrir(nourriture);
			if (nourriture == null) {
				return null;
			}
		}
		return nourriture;
	}
}
