package at.htl.Visualisation;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.Shape;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

import at.htl.smartbot.*;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.UIManager;
import javax.swing.JMenuItem;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUI_Trilateration extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel_draw;
	private JPanel panel_menu;
	private JLabel lblKreis;
	private JLabel lblX;
	private JTextField m1_X;
	private JTextField m1_Y;
	private JLabel lblY;
	private JLabel lblR;
	private JTextField m1_r;
	private JLabel label;
	private JTextField m2_r;
	private JTextField m2_Y;
	private JLabel label_1;
	private JTextField m2_X;
	private JLabel label_2;
	private JLabel lblKreis_1;
	private JLabel label_4;
	private JTextField m3_r;
	private JTextField m3_Y;
	private JLabel label_5;
	private JTextField m3_X;
	private JLabel label_6;
	private JLabel lblKreis_2;
	private JButton btnTrilaterate;

	private Graphics g;

	private static int width;
	private static int height;

	protected static int step = 100;
	protected static Point origin;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmNewOrigin;
	private JMenuItem mntmClose;

	private boolean newOrigin = false;
	private JLabel lblState;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_Trilateration frame = new GUI_Trilateration();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the frame.
	 */
	public GUI_Trilateration() {
		initComponents();
	}

	private void initComponents() {
		setTitle("SmartBot Trilateration");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 673, 477);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnFile = new JMenu("Datei");
		menuBar.add(mnFile);

		mntmNewOrigin = new JMenuItem("Neuer Ursprung");
		mntmNewOrigin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mntmNewOriginActionPerformed(e);
			}
		});
		mnFile.add(mntmNewOrigin);

		mntmClose = new JMenuItem("Schlie\u00DFen");
		mntmClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mntmCloseActionPerformed(arg0);
			}
		});
		mnFile.add(mntmClose);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		panel_draw = new JPanel() {
			public void paintComponent(Graphics g) {
				GUI_Trilateration.width = this.getWidth();
				GUI_Trilateration.height = this.getHeight();
				GUI_Trilateration.origin = new Point(width * 0.1, height
						- (height * (0.1)));
				Utils.drawCoordinateSystem(width, height, origin, g);

				// nicht genau....
				// for(int i=0;i<width/GUI_Trilateration.STEP;i++){
				// g.drawLine(i*GUI_Trilateration.STEP, (height/2)-2,
				// i*GUI_Trilateration.STEP, (height/2)+2);
				// }
				// for(int i=0;i<height/GUI_Trilateration.STEP;i++){
				// g.drawLine((width/2)-2, i*GUI_Trilateration.STEP,
				// (width/2)+2, i*GUI_Trilateration.STEP);
				// }

			}
		};
		panel_draw.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				panel_drawMouseClicked(arg0);
			}
		});
		panel_draw.setBackground(Color.WHITE);
		panel_draw.setBorder(new LineBorder(new Color(0, 0, 0)));

		panel_menu = new JPanel();
		panel_menu.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));

		lblState = new JLabel("Status:");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								Alignment.LEADING,
								gl_contentPane
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																lblState,
																GroupLayout.DEFAULT_SIZE,
																627,
																Short.MAX_VALUE)
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addComponent(
																				panel_draw,
																				GroupLayout.DEFAULT_SIZE,
																				485,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				panel_menu,
																				GroupLayout.PREFERRED_SIZE,
																				136,
																				GroupLayout.PREFERRED_SIZE)))
										.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(
				Alignment.TRAILING).addGroup(
				Alignment.LEADING,
				gl_contentPane
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								gl_contentPane
										.createParallelGroup(
												Alignment.TRAILING, false)
										.addComponent(panel_menu,
												Alignment.LEADING,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(panel_draw,
												Alignment.LEADING,
												GroupLayout.DEFAULT_SIZE, 375,
												Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED, 7,
								Short.MAX_VALUE).addComponent(lblState)));

		lblKreis = new JLabel("Kreis 1:");

		lblX = new JLabel("X");

		m1_X = new JTextField();
		m1_X.setColumns(10);

		m1_Y = new JTextField();
		m1_Y.setColumns(10);

		lblY = new JLabel("Y");

		lblR = new JLabel("r");

		m1_r = new JTextField();
		m1_r.setColumns(10);

		label = new JLabel("r");

		m2_r = new JTextField();
		m2_r.setColumns(10);

		m2_Y = new JTextField();
		m2_Y.setColumns(10);

		label_1 = new JLabel("Y");

		m2_X = new JTextField();
		m2_X.setColumns(10);

		label_2 = new JLabel("X");

		lblKreis_1 = new JLabel("Kreis 2:");

		label_4 = new JLabel("r");

		m3_r = new JTextField();
		m3_r.setColumns(10);

		m3_Y = new JTextField();
		m3_Y.setColumns(10);

		label_5 = new JLabel("Y");

		m3_X = new JTextField();
		m3_X.setColumns(10);

		label_6 = new JLabel("X");

		lblKreis_2 = new JLabel("Kreis 3:");

		btnTrilaterate = new JButton("Trilaterate");
		btnTrilaterate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnTrilaterateActionPerformed(arg0);
			}
		});
		GroupLayout gl_panel_menu = new GroupLayout(panel_menu);
		gl_panel_menu
				.setHorizontalGroup(gl_panel_menu
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panel_menu
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_panel_menu
														.createParallelGroup(
																Alignment.LEADING,
																false)
														.addComponent(
																btnTrilaterate,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addGroup(
																gl_panel_menu
																		.createSequentialGroup()
																		.addGroup(
																				gl_panel_menu
																						.createParallelGroup(
																								Alignment.TRAILING,
																								false)
																						.addComponent(
																								lblR,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								lblY,
																								Alignment.LEADING,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								lblX,
																								Alignment.LEADING,
																								GroupLayout.DEFAULT_SIZE,
																								12,
																								Short.MAX_VALUE))
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				gl_panel_menu
																						.createParallelGroup(
																								Alignment.TRAILING)
																						.addGroup(
																								gl_panel_menu
																										.createParallelGroup(
																												Alignment.TRAILING,
																												false)
																										.addComponent(
																												m1_Y,
																												0,
																												0,
																												Short.MAX_VALUE)
																										.addComponent(
																												m1_X,
																												GroupLayout.DEFAULT_SIZE,
																												80,
																												Short.MAX_VALUE))
																						.addComponent(
																								m1_r,
																								GroupLayout.PREFERRED_SIZE,
																								80,
																								GroupLayout.PREFERRED_SIZE)))
														.addGroup(
																gl_panel_menu
																		.createSequentialGroup()
																		.addGroup(
																				gl_panel_menu
																						.createParallelGroup(
																								Alignment.TRAILING,
																								false)
																						.addComponent(
																								label,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								label_1,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								label_2,
																								GroupLayout.DEFAULT_SIZE,
																								12,
																								Short.MAX_VALUE))
																		.addGroup(
																				gl_panel_menu
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								gl_panel_menu
																										.createParallelGroup(
																												Alignment.LEADING)
																										.addGroup(
																												gl_panel_menu
																														.createSequentialGroup()
																														.addPreferredGap(
																																ComponentPlacement.RELATED)
																														.addComponent(
																																m2_X,
																																GroupLayout.PREFERRED_SIZE,
																																80,
																																GroupLayout.PREFERRED_SIZE))
																										.addGroup(
																												Alignment.TRAILING,
																												gl_panel_menu
																														.createSequentialGroup()
																														.addPreferredGap(
																																ComponentPlacement.RELATED)
																														.addComponent(
																																m2_Y,
																																GroupLayout.PREFERRED_SIZE,
																																80,
																																GroupLayout.PREFERRED_SIZE)))
																						.addGroup(
																								Alignment.TRAILING,
																								gl_panel_menu
																										.createSequentialGroup()
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												m2_r,
																												GroupLayout.PREFERRED_SIZE,
																												80,
																												GroupLayout.PREFERRED_SIZE))))
														.addGroup(
																gl_panel_menu
																		.createSequentialGroup()
																		.addGroup(
																				gl_panel_menu
																						.createParallelGroup(
																								Alignment.TRAILING,
																								false)
																						.addComponent(
																								label_4,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								label_5,
																								Alignment.LEADING,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								label_6,
																								Alignment.LEADING,
																								GroupLayout.DEFAULT_SIZE,
																								12,
																								Short.MAX_VALUE))
																		.addGroup(
																				gl_panel_menu
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								gl_panel_menu
																										.createSequentialGroup()
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addGroup(
																												gl_panel_menu
																														.createParallelGroup(
																																Alignment.TRAILING,
																																false)
																														.addComponent(
																																m3_X,
																																0,
																																0,
																																Short.MAX_VALUE)
																														.addComponent(
																																m3_Y,
																																GroupLayout.DEFAULT_SIZE,
																																80,
																																Short.MAX_VALUE)))
																						.addGroup(
																								Alignment.TRAILING,
																								gl_panel_menu
																										.createSequentialGroup()
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												m3_r,
																												GroupLayout.PREFERRED_SIZE,
																												80,
																												GroupLayout.PREFERRED_SIZE))))
														.addComponent(
																lblKreis_2,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																lblKreis_1,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																lblKreis,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addContainerGap(28, Short.MAX_VALUE)));
		gl_panel_menu
				.setVerticalGroup(gl_panel_menu
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panel_menu
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(lblKreis)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_panel_menu
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(lblX)
														.addComponent(
																m1_X,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_panel_menu
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																m1_Y,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblY))
										.addGroup(
												gl_panel_menu
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_panel_menu
																		.createSequentialGroup()
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				m1_r,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_panel_menu
																		.createSequentialGroup()
																		.addGap(9)
																		.addComponent(
																				lblR)))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addComponent(lblKreis_1)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_panel_menu
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_panel_menu
																		.createSequentialGroup()
																		.addGap(6)
																		.addComponent(
																				m2_X,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_panel_menu
																		.createSequentialGroup()
																		.addGap(9)
																		.addComponent(
																				label_2)))
										.addGroup(
												gl_panel_menu
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_panel_menu
																		.createSequentialGroup()
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				m2_Y,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_panel_menu
																		.createSequentialGroup()
																		.addGap(9)
																		.addComponent(
																				label_1)))
										.addGroup(
												gl_panel_menu
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_panel_menu
																		.createSequentialGroup()
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				m2_r,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_panel_menu
																		.createSequentialGroup()
																		.addGap(9)
																		.addComponent(
																				label)))
										.addGap(11)
										.addComponent(lblKreis_2)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_panel_menu
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_panel_menu
																		.createSequentialGroup()
																		.addGap(6)
																		.addComponent(
																				m3_X,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_panel_menu
																		.createSequentialGroup()
																		.addGap(9)
																		.addComponent(
																				label_6)))
										.addGap(6)
										.addGroup(
												gl_panel_menu
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_panel_menu
																		.createSequentialGroup()
																		.addGap(9)
																		.addComponent(
																				label_5))
														.addComponent(
																m3_Y,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(6)
										.addGroup(
												gl_panel_menu
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																m3_r,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(
																gl_panel_menu
																		.createSequentialGroup()
																		.addGap(9)
																		.addComponent(
																				label_4)))
										.addGap(18)
										.addComponent(btnTrilaterate)
										.addContainerGap(15, Short.MAX_VALUE)));
		panel_menu.setLayout(gl_panel_menu);
		contentPane.setLayout(gl_contentPane);

	}

	protected void btnTrilaterateActionPerformed(ActionEvent arg0) {

		g = panel_draw.getGraphics();
		width=panel_draw.getWidth();
		height=panel_draw.getHeight();

		g.clearRect(1, 1, width-2, height-2);
		Utils.drawCoordinateSystem(panel_draw.getWidth(),
				panel_draw.getHeight(), origin, g);

		String input = m1_X.getText();
		double x = Double.parseDouble(input);
		input = m1_Y.getText();
		double y = Double.parseDouble(input);
		Point m1 = new Point(x, y);
		input = m1_r.getText();
		double r1 = Double.parseDouble(input);

		input = m2_X.getText();
		x = Double.parseDouble(input);
		input = m2_Y.getText();
		y = Double.parseDouble(input);
		Point m2 = new Point(x, y);
		input = m2_r.getText();
		double r2 = Double.parseDouble(input);

		input = m3_X.getText();
		x = Double.parseDouble(input);
		input = m3_Y.getText();
		y = Double.parseDouble(input);
		Point m3 = new Point(x, y);
		input = m3_r.getText();
		double r3 = Double.parseDouble(input);

		Point position = Trilateration.trilaterate(m1, m2, m3, r1, r2, r3);

		// Ans Java-Koordinatensystem Anpassen
		System.out.println(origin);
		int x_offset = (int) Math.round(origin.getX());
		int y_offset = (int) (Math.round(origin.getY()));

		int cx = (int) Math.round((m1.getX() - r1) * step) + x_offset;
		// cy = (int) (panel_draw.getHeight() -
		// Math.round((m1.getY()+r1)*step)-y_offset);
		int cy = (int) (y_offset - Math.round((m1.getY() + r1) * step));
		int cd = (int) (Math.round(2 * r1 * step));
		g.drawOval(cx, cy, cd, cd);

		cx = (int) Math.round(m1.getX() * step) + x_offset;
		cy = (int) (y_offset - Math.round(m1.getY() * step));
		g.drawLine(cx - 2, cy - 2, cx + 2, cy + 2);
		g.drawLine(cx - 2, cy + 2, cx + 2, cy - 2);

		cx = (int) Math.round((m2.getX() - r2) * step) + x_offset;
		cy = (int) (y_offset - Math.round((m2.getY() + r2) * step));
		cd = (int) (Math.round(2 * r2 * step));
		g.drawOval(cx, cy, cd, cd);

		cx = (int) Math.round(m2.getX() * step) + x_offset;
		cy = (int) (y_offset - Math.round(m2.getY() * step));
		g.drawLine(cx - 2, cy - 2, cx + 2, cy + 2);
		g.drawLine(cx - 2, cy + 2, cx + 2, cy - 2);

		cx = (int) Math.round((m3.getX() - r3) * step) + x_offset;
		cy = (int) (y_offset - Math.round((m3.getY() + r3) * step));
		cd = (int) (Math.round(2 * r3 * step));
		g.drawOval(cx, cy, cd, cd);
		cx = (int) Math.round(m3.getX() * step) + x_offset;
		cy = (int) (y_offset - Math.round(m3.getY() * step));
		g.drawLine(cx - 2, cy - 2, cx + 2, cy + 2);
		g.drawLine(cx - 2, cy + 2, cx + 2, cy - 2);

		g.setColor(Color.MAGENTA);
		cx = (int) Math.round(position.getX() * step) + x_offset;
		// int cy = (int) (panel_draw.getHeight() -
		// Math.round(position.getY()*step) - y_offset);
		cy = (int) (y_offset - Math.round((position.getY()) * step));
		g.drawLine(cx - 2, cy - 2, cx + 2, cy + 2);
		g.drawLine(cx - 2, cy + 2, cx + 2, cy - 2);
		

	}

	protected void mntmCloseActionPerformed(ActionEvent arg0) {
		System.exit(0);
	}

	protected void mntmNewOriginActionPerformed(ActionEvent e) {
		newOrigin = true;
		lblState.setText("Status: Bitte auf der Zeichenfläche auf den neuen Ursprung klicken");
	}

	protected void panel_drawMouseClicked(MouseEvent arg0) {
		width=panel_draw.getWidth();
		height=panel_draw.getHeight();
		g.setColor(Color.BLACK);
		if (newOrigin) {
			g.clearRect(1, 1, width-2, height-2);
			origin=new Point(arg0.getX(),arg0.getY());
			System.out.println(origin);
			newOrigin=false;
			lblState.setText("Status:");
			Utils.drawCoordinateSystem(width, height, origin, g);
			btnTrilaterateActionPerformed(null);
		}
	}
}
