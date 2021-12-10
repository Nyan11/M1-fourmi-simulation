package visualisation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import modelisation.Fourmiliere;
import modelisation.Terrain;
import nicellipse.component.NiLabel;
import nicellipse.component.NiRectangle;

public class BilanVue {

	public Fourmiliere fourmiliere;
	public NiRectangle vue;
	public NiLabel jour;
	public NiLabel nourriture;
	public NiLabel nbOeufs;
	public NiLabel nbLarves;
	public NiLabel nbNymphes;
	public NiLabel nbAdultes;
	public NiLabel nbMorts;
	
	
	private int compteurJour;
	
	public BilanVue(Fourmiliere fourmiliere) {
		this.compteurJour = 0;
		
		this.fourmiliere = fourmiliere;
		this.vue = new NiRectangle();
		this.vue.setSize(new Dimension(200, 400));
		this.vue.setBackground(Color.WHITE);
		
		this.jour = new NiLabel();
		this.jour.setFont(new Font("Arial", Font.BOLD, 15));
		this.jour.setLocation(0, 0);
		
		this.nourriture = new NiLabel();
		this.nourriture.setFont(new Font("Arial", Font.BOLD, 15));
		this.nourriture.setLocation(0, 20);
		
		this.nbOeufs = new NiLabel();
		this.nbOeufs.setFont(new Font("Arial", Font.BOLD, 15));
		this.nbOeufs.setLocation(0, 50);
		
		this.nbLarves = new NiLabel();
		this.nbLarves.setFont(new Font("Arial", Font.BOLD, 15));
		this.nbLarves.setLocation(0, 100);
		
		this.nbNymphes = new NiLabel();
		this.nbNymphes.setFont(new Font("Arial", Font.BOLD, 15));
		this.nbNymphes.setLocation(0, 150);
		
		this.nbAdultes = new NiLabel();
		this.nbAdultes.setFont(new Font("Arial", Font.BOLD, 15));
		this.nbAdultes.setLocation(0, 200);
		
		this.nbMorts = new NiLabel();
		this.nbMorts.setFont(new Font("Arial", Font.BOLD, 15));
		this.nbMorts.setLocation(0, 250);
		
		this.vue.add(this.nbOeufs);
		this.vue.add(this.nbLarves);
		this.vue.add(this.nbNymphes);
		this.vue.add(this.nbAdultes);
		this.vue.add(this.nbMorts);
		
		this.vue.add(this.jour);
		this.vue.add(this.nourriture);
		this.update();
	}

	void update() {
		compteurJour += 1;
		this.jour.setText("Jour: " + (this.compteurJour) + "H:" + this.compteurJour);
		this.nourriture.setText("Nouriture : " + this.fourmiliere.getGardeManger().poidsTotalNourriture());
		nbOeufs.setText("Nombre Oeufs   : " + fourmiliere.nbOeufs);
		nbOeufs.setText("Nombre Oeufs   : " + fourmiliere.nbOeufs);
		nbLarves.setText("Nombre Larves  : " + fourmiliere.nbLarves);
		nbNymphes.setText("Nombre Nymphes : " + fourmiliere.nbNymphes);
		nbAdultes.setText("Nombre Adultes : " + fourmiliere.nbAdultes);
		nbMorts.setText("Nombre Morts   : " + fourmiliere.nbMorts);
	}
}
