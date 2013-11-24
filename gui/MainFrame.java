package gui;

import java.awt.Container;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * Skaber brugergrænsefladen til startvinduet
 */
public class MainFrame extends JFrame {

	private JLabel lblSystemadminstrator, lblLagermedarbejder, lblChauffør;
	private MouseListener listen;
	private ImageIcon icon, iLager, iLast, iSystem;
	static Image logo;

	public MainFrame() throws IOException {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setTitle("System");
		this.setLocation(200, 70);
		this.setSize(700, 640);

		Container BackGround = getContentPane();

		listen = new MouseListener();

		InputStream input2 = getClass().getResourceAsStream("/icon.png");
		logo = ImageIO.read(input2);
		this.setIconImage(logo);

		InputStream input3 = getClass().getResourceAsStream("/lagermedarbejder.png");
		Image logo3 = ImageIO.read(input3);
		iLager = new ImageIcon(logo3);

		InputStream input4 = getClass().getResourceAsStream("/lastbil.png");
		Image logo4 = ImageIO.read(input4);
		iLast = new ImageIcon(logo4);

		InputStream input = getClass().getResourceAsStream("/administrator.png");
		Image logo = ImageIO.read(input);
		iSystem = new ImageIcon(logo);
		lblSystemadminstrator = drawLabel((this.getWidth() / 2) - (iSystem.getIconWidth() / 2), 20, iSystem);
		lblSystemadminstrator.addMouseListener(listen);
		lblLagermedarbejder = drawLabel(10, this.getHeight() - iLager.getIconHeight() - 40, iLager);
		lblLagermedarbejder.addMouseListener(listen);
		lblChauffør = drawLabel(this.getWidth() - iLast.getIconWidth() - 30, this.getHeight() - iLast.getIconHeight(), iLast);
		lblChauffør.addMouseListener(listen);
		this.setVisible(true);
	}

	public JLabel drawLabel(int x, int y, ImageIcon i1) {
		JLabel newLabel = new JLabel(i1);
		newLabel.setLocation(x, y);
		newLabel.setSize(i1.getIconWidth(), i1.getIconHeight());
		this.add(newLabel);
		return newLabel;
	}

	private class MouseListener implements java.awt.event.MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {

		}

		@Override
		public void mouseExited(MouseEvent arg0) {

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			if (arg0.getSource().equals(lblChauffør)) {
				ChaufførMain frame = new ChaufførMain();
				frame.setVisible(true);
			} else if (arg0.getSource().equals(lblLagermedarbejder)) {
				LagermedarbejderMain frame = new LagermedarbejderMain();
				frame.setVisible(true);
			} else if (arg0.getSource().equals(lblSystemadminstrator)) {
				SystemadministratorMain frame = new SystemadministratorMain();
				frame.setVisible(true);
			}
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {

		}
	}
}
