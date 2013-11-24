package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.DateUtil;

/**
 * Skaber et vindue hvor en dato kan indtastes. Indeholder metoder til at sikre en rigtig dato ved eksempelvis
 * skudår, og metoder til rollover og rollback.
 */
public class OpretDato extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblD, lblNewLabel, label, lblKl, label_1, lblCustomLabel, lblDate;
	private JTextField txfDag, txfMonth, txfYear, txfHour, txfMinute;
	private JButton btnDayPlus, btnDayMinus, btnMonthMinus, btnMonthPlus, btnYearMinus, btnYearPlus, btnHourMinus, btnHourPlus, btnMinuteMinus, btnMinutePlus, okButton, cancelButton;
	private JPanel buttonPane;
	private Controller controller = new Controller();
	private Date date, result;
	
	/**
	 * Create the dialog.
	 */
	
	// Hvis OpretDato kaldes uden parameter (null), så anvendes den anden constructor. Dette gøres for at undgå null-parametre i constructor kald.
	public OpretDato(){
		this(new Date());
	}
	
	public OpretDato(Date inputDate) {
		this.date = inputDate;

		this.setIconImage(MainFrame.logo);

		setBounds(100, 100, 454, 268);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		this.setModal(true);
		setTitle("Opret dato");
		
		lblD = new JLabel("d.");
		
		txfDag = new JTextField();
		txfDag.setHorizontalAlignment(SwingConstants.CENTER);
		txfDag.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txfDag.setText("08");
		txfDag.setColumns(10);
		txfDag.setEditable(false);
		
		btnDayPlus = new JButton("+");
		btnDayPlus.setMargin(new Insets(0, 0, 0, 0));
		btnDayPlus.addActionListener(controller);
		
		btnDayMinus = new JButton("-");
		btnDayMinus.setMargin(new Insets(0, 0, 0, 0));
		btnDayMinus.addActionListener(controller);
		
		lblNewLabel = new JLabel("/");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		txfMonth = new JTextField();
		txfMonth.setText("06");
		txfMonth.setHorizontalAlignment(SwingConstants.CENTER);
		txfMonth.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txfMonth.setColumns(10);
		txfMonth.setEditable(false);
		
		btnMonthMinus = new JButton("-");
		btnMonthMinus.setMargin(new Insets(0, 0, 0, 0));
		btnMonthMinus.addActionListener(controller);
		
		btnMonthPlus = new JButton("+");
		btnMonthPlus.setMargin(new Insets(0, 0, 0, 0));
		btnMonthPlus.addActionListener(controller);
		
		label = new JLabel("-");
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		txfYear = new JTextField();
		txfYear.setText("2013");
		txfYear.setHorizontalAlignment(SwingConstants.CENTER);
		txfYear.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txfYear.setColumns(10);
		txfYear.setEditable(false);
		
		btnYearMinus = new JButton("-");
		btnYearMinus.setMargin(new Insets(0, 0, 0, 0));
		btnYearMinus.addActionListener(controller);
		
		btnYearPlus = new JButton("+");
		btnYearPlus.setMargin(new Insets(0, 0, 0, 0));
		btnYearPlus.addActionListener(controller);
		
		lblKl = new JLabel("kl.");
		
		txfHour = new JTextField();
		txfHour.setText("08");
		txfHour.setHorizontalAlignment(SwingConstants.CENTER);
		txfHour.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txfHour.setColumns(10);
		txfHour.setEditable(false);
		
		btnHourMinus = new JButton("-");
		btnHourMinus.setMargin(new Insets(0, 0, 0, 0));
		btnHourMinus.addActionListener(controller);
		
		btnHourPlus = new JButton("+");
		btnHourPlus.setMargin(new Insets(0, 0, 0, 0));
		btnHourPlus.addActionListener(controller);
		
		label_1 = new JLabel(":");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		txfMinute = new JTextField();
		txfMinute.setText("08");
		txfMinute.setHorizontalAlignment(SwingConstants.CENTER);
		txfMinute.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txfMinute.setColumns(10);
		txfMinute.setEditable(false);
		
		btnMinuteMinus = new JButton("-");
		btnMinuteMinus.setMargin(new Insets(0, 0, 0, 0));
		btnMinuteMinus.addActionListener(controller);
		
		btnMinutePlus = new JButton("+");
		btnMinutePlus.setMargin(new Insets(0, 0, 0, 0));
		btnMinutePlus.addActionListener(controller);
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(controller);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(controller);
			}
		}
		
		lblCustomLabel = new JLabel("");
		lblCustomLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		//Auto-generated kode af Window-Builder
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(11)
									.addComponent(lblD))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addContainerGap()
									.addComponent(lblKl)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addComponent(btnDayMinus, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnDayPlus, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
										.addComponent(txfDag, 0, 0, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(txfMonth, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addComponent(btnMonthMinus, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnMonthPlus, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(label, GroupLayout.PREFERRED_SIZE, 11, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addComponent(btnYearMinus, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(btnYearPlus, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
										.addComponent(txfYear, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(txfHour, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addComponent(btnHourMinus, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
											.addGap(6)
											.addComponent(btnHourPlus, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(label_1)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addGap(2)
											.addComponent(btnMinuteMinus, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(btnMinutePlus, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txfMinute, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(lblCustomLabel))))))
						.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, 434, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(txfYear, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(txfDag, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblD)
						.addComponent(lblNewLabel)
						.addComponent(txfMonth, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnDayMinus, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDayPlus)
						.addComponent(btnMonthMinus, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnMonthPlus, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnYearMinus, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnYearPlus, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(36)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(txfHour, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addComponent(label_1)
							.addComponent(txfMinute, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblCustomLabel))
						.addComponent(lblKl))
					.addGap(6)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnHourMinus, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnHourPlus, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnMinuteMinus, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnMinutePlus, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(81, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		txfDag.setText(formatTime(date.getDate())+"");
		txfMonth.setText(formatTime(date.getMonth()+1)+"");
		txfYear.setText(date.getYear()+1900+"");
		txfHour.setText(formatTime(date.getHours())+"");
		txfMinute.setText(formatTime(date.getMinutes())+"");
		
	}
	public boolean isLeapYear() {
		return DateUtil.isLeapYear(Integer.parseInt(txfYear.getText()));
	}
	
	public int getMonth() {
		return Integer.parseInt(txfMonth.getText());
	}
	/**
	 * Er måneden på en kno eller på mellemkødet?
	 * @param måned
	 * @return
	 */
	public boolean isKno(int måned) {
		if(måned==1||måned==3||måned==5||måned==7||måned==8||måned==10||måned==12) {
			return true;
		} else {
			return false;
		}
	}
	
	public String formatTime(int time) {
		
		if(String.valueOf(time).length()==1) {
			return "0"+time;
		} else
			return ""+time;
	}
	
	public int daysInMonth() {
		
		if(!isKno(getMonth())) {
			
			if(getMonth() == 2) {
				if(isLeapYear()) {
					return 29;
				} else {
					return 28;
				}
			} else {
				return 30;
			}
		} else {
			return 31;
		}
	}
	
	public void rollover(JTextField txf) {
		if(txf.equals(txfDag)) {
			txf.setText("01");
			if(getMonth() != 12) {
				txfMonth.setText(formatTime(Integer.parseInt(txfMonth.getText())+1)+"");
			} else {
				rollover(txfMonth);
			}
		} else if (txf.equals(txfMonth)) {
			txf.setText("01");
			txfYear.setText(Integer.parseInt(txfYear.getText())+1+"");
						
		} else if (txf.equals(txfHour)) {
			txf.setText("00");
			if(!txfDag.getText().equals(daysInMonth()+"")) {
				txfDag.setText(formatTime(Integer.parseInt(txfDag.getText())+1)+"");
			} else {
				rollover(txfDag);
			}
		} else if (txf.equals(txfMinute)) {
			txf.setText("00");
			if(!txfHour.getText().equals("23")) {
				txfHour.setText(formatTime(Integer.parseInt(txfHour.getText())+1)+"");
			} else {
				rollover(txfHour);
			}
		}
	}
	
	public void rollback(JTextField txf) {
		if(txf.equals(txfDag)) {
			if(getMonth()!=1) {
				txfMonth.setText(formatTime(Integer.parseInt(txfMonth.getText())-1)+"");
			} else {
				rollback(txfMonth);
			}
			txf.setText(daysInMonth()+"");
		} else if (txf.equals(txfMonth)) {
			txf.setText(12+"");
			txfYear.setText(Integer.parseInt(txfYear.getText())-1+"");
						
		} else if (txf.equals(txfHour)) {
			txf.setText(23+"");
			if(!txfDag.getText().equals("01")) {
				txfDag.setText(formatTime(Integer.parseInt(txfDag.getText())-1)+"");
			} else {
				rollback(txfDag);
			}
		} else if (txf.equals(txfMinute)) {
			txf.setText(59+"");
			if(!txfHour.getText().equals("00")) {
				txfHour.setText(formatTime(Integer.parseInt(txfHour.getText())-1)+"");
			} else {
				rollback(txfHour);
			}
		}
	}
	
	
	public Date showDialog() {
		this.setVisible(true);
		return result;
	}
	
	public void setDatoLabel(JLabel label){
		this.lblDate = label;
	}
	
	private class Controller implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnDayMinus) {
				int i = Integer.parseInt(txfDag.getText());
				if(i > 1) {
					i--;
					txfDag.setText(formatTime(i)+"");
					
				} else {
					rollback(txfDag);
				}
			}
			else if(e.getSource() == btnDayPlus) {
				int i = Integer.parseInt(txfDag.getText());
				
				if(!isKno(getMonth())) {
					
					if(getMonth() == 2) {
						if(isLeapYear()) {
							if(i<29) {
								i++;
								txfDag.setText(formatTime(i)+"");
							} else {
								rollover(txfDag);
							}
						} else {
							if(i<28) {
								i++;
								txfDag.setText(formatTime(i)+"");
							} else {
								rollover(txfDag);
							}
						}
					} else {
						if(i<30) {
							i++;
							txfDag.setText(formatTime(i)+"");
						} else {
							rollover(txfDag);
						}
					}
				} else {
					if(i<31) {
						i++;
						txfDag.setText(formatTime(i)+"");
					} else {
						rollover(txfDag);
						
					}
				}	
			}
			else if(e.getSource() == btnMonthMinus) {
				int i = Integer.parseInt(txfMonth.getText());
				if(i>1) {
					i--;
					txfMonth.setText(formatTime(i)+"");
					if(Integer.parseInt(txfDag.getText()) > daysInMonth()) {
						txfDag.setText(daysInMonth()+"");
					} 
				} else {
					rollback(txfMonth);
				} 
			}
			else if(e.getSource() == btnMonthPlus) {
				int i = Integer.parseInt(txfMonth.getText());
				if(i<12) {
					i++;
					txfMonth.setText(formatTime(i)+"");
					
				} else {
					rollover(txfMonth);
				}
			}
			else if(e.getSource() == btnYearMinus) {
				int i = Integer.parseInt(txfYear.getText());
				//I 1887 blev Danish Crown grundlagt. Derfor kan det overhovedet ikke nikke give nogen mening at gå under denne dato, basta
				if(i>1887) {
					if(DateUtil.isLeapYear(i) && txfMonth.getText().equals("02") && txfDag.getText().equals("29")) {
						txfDag.setText("28");
					}
					i--;
					txfYear.setText(i+"");
					
				} 
			}
			else if(e.getSource() == btnYearPlus) {
				int i = Integer.parseInt(txfYear.getText());
				//Indtil år over 9000 satser vi på at Dansih Crown eksisterer, og dette er lagt på grund af sandsynligheden for dette
				if(i<9001) {
					i++;
					txfYear.setText(i+"");
				} 
			}
			else if(e.getSource() == btnHourMinus) {
				int i = Integer.parseInt(txfHour.getText());
				if(i>0) {
					i--;
					txfHour.setText(formatTime(i)+"");
				} else {
					rollback(txfHour);
				}
			}
			else if(e.getSource() == btnHourPlus) {
				int i = Integer.parseInt(txfHour.getText());
				if(i<23) {
					i++;
					txfHour.setText(formatTime(i)+"");
				} else {
					rollover(txfHour);
				}
			}
			else if(e.getSource() == btnMinuteMinus) {
				int i = Integer.parseInt(txfMinute.getText());
				if(i>0) {
					i--;
					txfMinute.setText(formatTime(i)+"");
				} else {
					rollback(txfMinute);
				}
			}
			else if(e.getSource() == btnMinutePlus) {
				int i = Integer.parseInt(txfMinute.getText());
				if(i<59) {
					i++;
					txfMinute.setText(formatTime(i)+"");
				} else {
					rollover(txfMinute);
				}
			}
			else if(e.getSource() == okButton) {
				result = new Date(Integer.parseInt(txfYear.getText())-1900, Integer.parseInt(txfMonth.getText())-1, Integer.parseInt(txfDag.getText()), Integer.parseInt(txfHour.getText()), Integer.parseInt(txfMinute.getText()));
				setVisible(false);
				dispose();
			}
			else if(e.getSource() == cancelButton) {
				result = null;
				setVisible(false);
				dispose();
			}
			
		}
	}
}
