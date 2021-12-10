package visualisation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelisation.utils.Position;
import nicellipse.component.NiEllipse;

public class PositionVue implements ActionListener {

	public Position position;
	public NiEllipse vue;
	
	public PositionVue(Position position, int size) {
		this.position = position;
		this.vue = new NiEllipse();
		this.vue.setSize(new Dimension(Simulation.ZOOM/size, Simulation.ZOOM/size));
		this.vue.setLocation(
				400 + this.position.x() * Simulation.ZOOM - Simulation.ZOOM/size/2,
				300 + this.position.y() * Simulation.ZOOM - Simulation.ZOOM/size/2
			);
	}
	
	public void setColor(Color color) {
		this.vue.setBackground(color);
		this.vue.setBorderColor(color);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PositionVue other = (PositionVue) obj;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) { }
}
