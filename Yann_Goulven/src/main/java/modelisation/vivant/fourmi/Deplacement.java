package modelisation.vivant.fourmi;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import modelisation.Terrain;
import modelisation.pheromone.Pheromone;
import modelisation.utils.Position;
import modelisation.vivant.EtreVivant;
import modelisation.vivant.fourmi.etat.Adulte;

public class Deplacement {
	
	private Adulte adulte;
	
	public Deplacement(Adulte adulte) {
		this.adulte = adulte;
	}

	public Position deplacementPatrouille(Terrain terrain) {
		if (adulte.proieVisible(terrain)) {
			return this.deplacementVersProie(terrain);
		} else if (adulte.postionVisible(adulte.getFourmiliere().getPosition())) {
			return this.deplacementPheromoneRecherche(terrain, 0);
		} else {
			return deplacementVersPostion(adulte.getFourmiliere().getPosition());
		}
	}

	public Position deplacementRecherche(Terrain terrain) {
		if (adulte.proieVisible(terrain)) {
			return this.deplacementVersProie(terrain);
		} else {
			return this.deplacementPheromoneRecherche(terrain, 0);
		}
	}

	private Position deplacementVersPostion(Position position) {
		int valeurX = adulte.getPosition().x() - position.x();
		int valeurY = adulte.getPosition().y() - position.y();
		if (valeurX < 0) return adulte.getPosition().est();
		if (valeurX > 0) return adulte.getPosition().ouest();
		if (valeurY < 0) return adulte.getPosition().sud();
		if (valeurY > 0) return adulte.getPosition().nord();
		else return adulte.getPosition();
	}

	public Position deplacementVersFourmiliere(Terrain terrain) {
		if (adulte.postionVisible(adulte.getFourmiliere().getPosition())) {
			return deplacementVersPostion(adulte.getFourmiliere().getPosition());
		} else {
			return deplacementPheromoneRecherche(terrain, 0);
		}
	}

	public Position deplacementGardeManger(Terrain terrain) {
		if (adulte.postionVisible(adulte.getFourmiliere().getGardeManger().getPosition())) {
			return deplacementVersPostion(adulte.getFourmiliere().getGardeManger().getPosition());
		} else {
			return deplacementPheromoneRecherche(terrain, 0);
		}
	}

	public Position deplacementVersProie(Terrain terrain) {
		Map<Position, List<EtreVivant>> proies = terrain.getMapProie();
		if (proies.get(adulte.getPosition()) != null) return adulte.getPosition();
		if (proies.get(adulte.getPosition().nord()) != null) return adulte.getPosition().nord();
		if (proies.get(adulte.getPosition().est()) != null) return adulte.getPosition().est();
		if (proies.get(adulte.getPosition().sud()) != null) return adulte.getPosition().sud();
		if (proies.get(adulte.getPosition().ouest()) != null) return adulte.getPosition().ouest();
		return adulte.getPosition();
	}
	
	public Position deplacementAleatoire() {
		Position prochaineCasePosition;
		int rand = (int)(Math.random() * 4.);
		switch (rand) {
		case 0:
			prochaineCasePosition = adulte.getPosition().nord();
			break;
		case 1:
			prochaineCasePosition = adulte.getPosition().est();
			break;
		case 2:
			prochaineCasePosition = adulte.getPosition().sud();
			break;
		default:
			prochaineCasePosition = adulte.getPosition().ouest();
			break;
		}
		return prochaineCasePosition;
	}



	public Position deplacementPheromoneRecherche(Terrain terrain, int alea) {
		if ((int)(Math.random() * 10) < alea) return deplacementAleatoire();
		Map<Position, List<Pheromone>> valPhero = terrain.getMapPheromones();
		List<Pheromone> listPheromones = valPhero.get(adulte.getPosition().nord());
		
		int nord = (listPheromones != null && listPheromones.size() > 0) ? 2 : 1;
		listPheromones = valPhero.get(adulte.getPosition().est());
		int est = (listPheromones != null && listPheromones.size() > 0) ? 2 : 1;
		listPheromones = valPhero.get(adulte.getPosition().sud());
		int sud = (listPheromones != null && listPheromones.size() > 0) ? 2 : 1;
		listPheromones = valPhero.get(adulte.getPosition().ouest());
		int ouest = (listPheromones != null && listPheromones.size() > 0) ? 2 : 1;
		
		int rand = (int)(Math.random() * (nord + est + sud + ouest));
		if (rand < nord) {
			return adulte.getPosition().nord();
		}
		if (rand < nord + est) {
			return adulte.getPosition().est();
		}
		if (rand < nord + est + sud) {
			return adulte.getPosition().sud();
		}
		return adulte.getPosition().ouest();
	}

	public Position deplacementFuirFourmiliere(Terrain terrain) {
		int nord = (terrain.positionLibre(this.adulte.getPosition().nord())) ? 2 : 1;
		int est = (terrain.positionLibre(this.adulte.getPosition().est())) ? 2 : 1;
		int sud = (terrain.positionLibre(this.adulte.getPosition().sud())) ? 2 : 1;
		int ouest = (terrain.positionLibre(this.adulte.getPosition().ouest())) ? 2 : 1;
		
		int rand = (int)(Math.random() * (nord + est + sud + ouest));
		if (rand < nord) {
			return adulte.getPosition().nord();
		}
		if (rand < nord + est) {
			return adulte.getPosition().est();
		}
		if (rand < nord + est + sud) {
			return adulte.getPosition().sud();
		}
		return adulte.getPosition().ouest();
	}

	public Position deplacementTrouverFemelle(Terrain terrain) {
		Position position;
		
		position = this.adulte.getPosition();
		if (terrain.femellePosition(position)) return position;
		
		position = this.adulte.getPosition().nord();
		if (terrain.femellePosition(position)) return position;
		
		position = this.adulte.getPosition().est();
		if (terrain.femellePosition(position)) return position;
		
		position = this.adulte.getPosition().sud();
		if (terrain.femellePosition(position)) return position;
		
		position = this.adulte.getPosition().ouest();
		if (terrain.femellePosition(position)) return position;
		
		return deplacementSuivrePheromoneFemelle(terrain);
	}

	private Position deplacementSuivrePheromoneFemelle(Terrain terrain) {
		
		Position position;
		
		position = this.adulte.getPosition().nord();
		if (this.checkPheromoneReproduction(position, terrain)) return position;
		
		position = this.adulte.getPosition().est();
		if (this.checkPheromoneReproduction(position, terrain)) return position;
		
		position = this.adulte.getPosition().sud();
		if (this.checkPheromoneReproduction(position, terrain)) return position;
		
		position = this.adulte.getPosition().ouest();
		if (this.checkPheromoneReproduction(position, terrain)) return position;
		
		return this.deplacementFuirFourmiliere(terrain);
	}

	private boolean checkPheromoneReproduction(Position position, Terrain terrain) {
		List<Pheromone> list;
		list = terrain.getMapPheromones().get(position);
		if (list != null) {
			list = list.stream().filter(phero -> phero.isReprodution()).collect(Collectors.toList());
			if (list.size() > 0) return true;
		}
		return false;
	}
}
