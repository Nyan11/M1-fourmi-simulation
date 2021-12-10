package modelisation;

import modelisation.fourmiliere.Etat;
import modelisation.fourmiliere.Jeune;
import modelisation.utils.Position;
import modelisation.utils.Simulable;
import modelisation.vivant.EtreVivant;
import modelisation.vivant.fourmi.Fourmi;
import modelisation.vivant.fourmi.etat.Adulte;
import modelisation.vivant.fourmi.etat.Larve;
import modelisation.vivant.fourmi.etat.Mort;
import modelisation.vivant.fourmi.etat.Nymphe;
import modelisation.vivant.fourmi.etat.Oeuf;
import modelisation.vivant.fourmi.etat.Stade;

public class Fourmiliere implements Simulable {

	private Position position;
	public Nid nid;
	public GardeManger gardeManger;
	public Depot depot;
	
	private int age;
	
	private Etat etat;
	
	public int nbOeufs;
	public int nbLarves;
	public int nbNymphes;
	public int nbAdultes;
	public int nbMorts;
	
	public Fourmiliere(Position position) {
		this.etat = new Jeune();
		this.position = position;
		this.nid = new Nid(position, null);
		this.gardeManger = new GardeManger(position);
		this.depot = new Depot(position);
		this.age = 0;
		this.nbOeufs = 0;
		this.nbLarves = 0;
		this.nbNymphes = 0;
		this.nbAdultes = 0;
		this.nbMorts = 0;
	}
	
	public Fourmiliere(Adulte adulte) {
		this(adulte.getPosition());
		this.nid = new Nid(position, adulte.getFourmi());
		this.nbAdultes = 1;
		this.nid.ajouteEnfant(adulte.getFourmi());
		adulte.setFourmiliere(this);
	}

	@Override
	public void step(Terrain terrain) {
		this.etat = this.etat.changerEtat(this);
		this.etat.step(this);
		this.nid.step(terrain);
		this.gardeManger.step(terrain);
		this.depot.step(terrain);
	}

	public void pondreOeufs(int nb) {
		for (int i = 0; i < nb; i++) {
			Fourmi oeuf = new Fourmi(this.position, this);
			this.nid.ajouteEnfant(oeuf);
		}
	}

	public Position getPosition() {
		return this.position;
	}

	public GardeManger getGardeManger() {
		return this.gardeManger;
	}

	public void retireFourmi(Fourmi fourmi) {
		this.nid.retireEnfant(fourmi);
	}

	public EtreVivant nourrirLarves(EtreVivant nourriture) {
		return this.nid.nourrirLarves(nourriture);
	}

	public boolean aOeufs() {
		return this.nbOeufs == 0;
	}

	public boolean ageAncien() {
		return this.age > 1200;
	}
	
	public void augmenteAge() {
		this.age++;
	}
}
