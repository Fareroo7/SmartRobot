package at.htl.Visualisation;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.RenderingHints;
import at.htl.smartbot.*;

public class CoordinatePanel extends Panel {

	private static final long serialVersionUID = 1L;
	private Graphics2D g;
	private Point origin;
	private int step = 100;

	@Override
	public void paint(Graphics g2) {
		g = (Graphics2D) g2;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setStroke(new BasicStroke(1));
		drawCoordinateSystem(new Point(200, 100));
	}

	public void drawCoordinateSystem(Point origin) {
		int width = this.getWidth();
		int height = this.getHeight();
		g.drawLine(0, (int) origin.getY(), width, (int) origin.getY());
		g.drawLine((int) origin.getX(), 0, (int) origin.getX(), height);

	}

}
