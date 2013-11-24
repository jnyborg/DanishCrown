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
import model.Rampe;
import service.Service;

/**
 * Skaber brugergrænsefladen til indtastning af trailerens vægt ved kontrolvejning.
 */
public class IndtastKontrolvejningVægt extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextFieldHint txfHint;
	private Controller controller;
	private JButton btnOk, btnCancel;
	private Lastbil lastbil;
	private ChaufførMain frame;

	/**
	 * Create the dialog.
	 */
	public IndtastKontrolvejningVægt() {
		this.setIconImage(MainFrame.logo);
		controller = new Controller();
		setBounds(100, 100, 450, 103);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		txfHint = new JTextFieldHint(txfHint, null, "Indtast vægt i kg");
		txfHint.setColumns(10);
		
		//Auto-generated kode af Window-Builder
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(txfHint, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(txfHint, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
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
	public void setLastbil(Lastbil lastbil){
		this.lastbil = lastbil;
	}
	
	public void setFrame(ChaufførMain chaufførMain) {
		this.frame=chaufførMain;
		
	}
	private class Controller implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(btnOk)){
				try {
					double vægt = Double.parseDouble(txfHint.getText());
					if (vægt >= 0) {
						if(!lastbil.getTrailer().kontrolvejning(Integer.valueOf(txfHint.getText()))){
							Rampe r1 = Service.getInstance().omplanlægAflæsning(lastbil.getAflæsning());
							dispose();
							JOptionPane.showMessageDialog(null,
							    "Din trailer skal til omlæsning på rampe nr.: " + r1.getRampeNummer(),
							    "Bemærk",
							    JOptionPane.WARNING_MESSAGE);
							frame.updateData(lastbil);
						}
						else{
							dispose();
							JOptionPane.showMessageDialog(null, "Vægt godkendt");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Vægt skal være positiv!", "Advarsel", JOptionPane.WARNING_MESSAGE);
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Ikke en gyldig vægt!", "Advarsel",
							JOptionPane.WARNING_MESSAGE);
				}			
			}
			else if(e.getSource().equals(btnCancel)){
				dispose();
			}
		}
	}
}
