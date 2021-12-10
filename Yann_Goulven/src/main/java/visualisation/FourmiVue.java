package visualisation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import modelisation.utils.Position;
import modelisation.vivant.fourmi.Fourmi;
import nicellipse.component.NiEllipse;
import modelisation.vivant.fourmi.etat.Adulte;
import modelisation.vivant.fourmi.role.Role;

public class FourmiVue extends PositionVue {

	public FourmiVue(Position position, List<Fourmi> list) {
		super(position, 1);
		this.setColor(this.getColor(list));
	}

	private Color getColor(List<Fourmi> list) {
		Color color = Color.BLUE;

		for (Fourmi fourmi: list) {
			if (fourmi.getStade().estReine()) {
				return Color.CYAN;
			} else if (color != Color.ORANGE && fourmi.getStade().estFemelle()) {
				color = Color.ORANGE;
			} else if (color != Color.PINK && color != Color.ORANGE &&fourmi.getStade().estMale()) {
				color = Color.PINK;
			} else if (color != Color.RED && color != Color.PINK && color != Color.ORANGE &&fourmi.getStade().estSoldat()) {
				color = Color.RED;
			} else if (color != Color.WHITE && color != Color.RED && color != Color.PINK && color != Color.ORANGE &&fourmi.getStade().estMort()) {
				color = Color.WHITE;
			}
		}
		return color;
	}
}
