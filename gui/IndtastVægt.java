package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.Lastbil;

/**
 * Skaber brugergr�nsefladen til indtastning af trailerens v�gt.
 */
public class IndtastV�gt extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextFieldHint txfHint;
	private Controller controller;
	private JButton btnOk, btnCancel;
	private Lastbil lastbil;
	private Chauff�rMain frame;

	/**
	 * Create the dialog.
	 */
	public IndtastV�gt() {
		this.setIconImage(MainFrame.logo);
		this.setTitle("Indtast trailerens v�gt");
		controller = new Controller();
		setBounds(100, 100, 450, 103);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		txfHint = new JTextFieldHint(txfHint, null, "Indtast v�gt i kg");
		txfHint.setColumns(10);
		
		//Auto-generated kode af Window-Builder
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(
				Alignment.LEADING).addComponent(txfHint,
				GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_contentPanel
						.createSequentialGroup()
						.addComponent(txfHint, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnOk = new JButton("OK");
				btnOk.setActionCommand("OK");
				buttonPane.add(btnOk);
				getRootPane().setDefaultButton(btnOk);
				btnOk.addActionListener(controller);
			}
			{
				btnCancel = new JButton("Cancel");
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
				btnCancel.addActionListener(controller);
			}
		}
	}

	public void setLastbil(Lastbil lastbil) {
		this.lastbil = lastbil;
	}
	
	public void setFrame(Chauff�rMain frame) {
		this.frame = frame;
	}

	private class Controller implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(btnOk)) {
				
				try {
					double v�gt = Double.parseDouble(txfHint.getText());
					if (v�gt >= 0) {
						lastbil.getTrailer().setV�gtF�rKontrolvejning(v�gt);
						JOptionPane.showMessageDialog(null,
								"Trailerens v�gt er registreret!",
								"V�gt godkendt",
								JOptionPane.INFORMATION_MESSAGE);
						frame.updateData(lastbil);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "V�gt skal v�re positiv!", "Advarsel", JOptionPane.WARNING_MESSAGE);
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Ikke en gyldig v�gt!", "Advarsel",
							JOptionPane.WARNING_MESSAGE);
				}

			} else if (e.getSource().equals(btnCancel)) {
				dispose();
			}
		}

	}
}
