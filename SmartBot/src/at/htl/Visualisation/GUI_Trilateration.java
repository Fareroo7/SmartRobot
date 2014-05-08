package at.htl.Visualisation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

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

public class GUI_Trilateration extends JFrame {

	private JPanel contentPane;
	private JPanel panel_draw;
	private JPanel panel_menu;
	private JLabel lblKreis;
	private JLabel lblX;
	private JTextField m1_X;
	private JTextField m1_Y;
	private JLabel lblY;
	private JLabel lblR;
	private JTextField textField;
	private JLabel label;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel label_1;
	private JTextField textField_3;
	private JLabel label_2;
	private JLabel lblKreis_1;
	private JLabel label_4;
	private JTextField textField_4;
	private JTextField textField_5;
	private JLabel label_5;
	private JTextField textField_6;
	private JLabel label_6;
	private JLabel lblKreis_2;
	private JButton btnTrilaterate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		setBounds(100, 100, 673, 447);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		panel_draw = new JPanel();
		panel_draw.setBackground(Color.WHITE);
		panel_draw.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		panel_menu = new JPanel();
		panel_menu.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_draw, GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_menu, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_menu, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
						.addComponent(panel_draw, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		lblKreis = new JLabel("Kreis 1:");
		
		lblX = new JLabel("X");
		
		m1_X = new JTextField();
		m1_X.setColumns(10);
		
		m1_Y = new JTextField();
		m1_Y.setColumns(10);
		
		lblY = new JLabel("Y");
		
		lblR = new JLabel("r");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		label = new JLabel("r");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		label_1 = new JLabel("Y");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		label_2 = new JLabel("X");
		
		lblKreis_1 = new JLabel("Kreis 2:");
		
		label_4 = new JLabel("r");
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		
		label_5 = new JLabel("Y");
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		
		label_6 = new JLabel("X");
		
		lblKreis_2 = new JLabel("Kreis 3:");
		
		btnTrilaterate = new JButton("Trilaterate");
		GroupLayout gl_panel_menu = new GroupLayout(panel_menu);
		gl_panel_menu.setHorizontalGroup(
			gl_panel_menu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_menu.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_menu.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnTrilaterate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_panel_menu.createSequentialGroup()
							.addGroup(gl_panel_menu.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblR, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblY, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblX, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 12, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_menu.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_menu.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(m1_Y, 0, 0, Short.MAX_VALUE)
									.addComponent(m1_X, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_menu.createSequentialGroup()
							.addGroup(gl_panel_menu.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label_2, GroupLayout.DEFAULT_SIZE, 12, Short.MAX_VALUE))
							.addGroup(gl_panel_menu.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_menu.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel_menu.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
									.addGroup(Alignment.TRAILING, gl_panel_menu.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)))
								.addGroup(Alignment.TRAILING, gl_panel_menu.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel_menu.createSequentialGroup()
							.addGroup(gl_panel_menu.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(label_4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label_5, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label_6, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 12, Short.MAX_VALUE))
							.addGroup(gl_panel_menu.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_menu.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel_menu.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(textField_6, 0, 0, Short.MAX_VALUE)
										.addComponent(textField_5, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)))
								.addGroup(Alignment.TRAILING, gl_panel_menu.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))))
						.addComponent(lblKreis_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblKreis_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblKreis, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(28, Short.MAX_VALUE))
		);
		gl_panel_menu.setVerticalGroup(
			gl_panel_menu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_menu.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblKreis)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_menu.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblX)
						.addComponent(m1_X, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_menu.createParallelGroup(Alignment.BASELINE)
						.addComponent(m1_Y, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblY))
					.addGroup(gl_panel_menu.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_menu.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_menu.createSequentialGroup()
							.addGap(9)
							.addComponent(lblR)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblKreis_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_menu.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_menu.createSequentialGroup()
							.addGap(6)
							.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_menu.createSequentialGroup()
							.addGap(9)
							.addComponent(label_2)))
					.addGroup(gl_panel_menu.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_menu.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_menu.createSequentialGroup()
							.addGap(9)
							.addComponent(label_1)))
					.addGroup(gl_panel_menu.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_menu.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_menu.createSequentialGroup()
							.addGap(9)
							.addComponent(label)))
					.addGap(11)
					.addComponent(lblKreis_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_menu.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_menu.createSequentialGroup()
							.addGap(6)
							.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_menu.createSequentialGroup()
							.addGap(9)
							.addComponent(label_6)))
					.addGap(6)
					.addGroup(gl_panel_menu.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_menu.createSequentialGroup()
							.addGap(9)
							.addComponent(label_5))
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_panel_menu.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_menu.createSequentialGroup()
							.addGap(9)
							.addComponent(label_4)))
					.addGap(18)
					.addComponent(btnTrilaterate)
					.addContainerGap(15, Short.MAX_VALUE))
		);
		panel_menu.setLayout(gl_panel_menu);
		contentPane.setLayout(gl_contentPane);
	}
}
