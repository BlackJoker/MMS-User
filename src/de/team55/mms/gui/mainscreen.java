package de.team55.mms.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import de.team55.mms.function.User;

public class mainscreen {

	private JFrame frame;
	private JPanel cards = new JPanel();
	private DefaultTableModel tmodel;
	private final Dimension btnSz = new Dimension(140, 50);

	public mainscreen() {
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		centerscr();
		topscr();
		leftscr();

		frame.setVisible(true);
	}

	private void leftscr() {
		JPanel leftpan = new JPanel();
		frame.getContentPane().add(leftpan, BorderLayout.WEST);

		JPanel left = new JPanel();
		leftpan.add(left);
		left.setLayout(new GridLayout(0, 1, 5, 20));

		JButton btnModulEinreichen = new JButton("Modul Einreichen");
		left.add(btnModulEinreichen);
		btnModulEinreichen.setEnabled(false);
		btnModulEinreichen.setPreferredSize(btnSz);
		btnModulEinreichen.setAlignmentX(Component.CENTER_ALIGNMENT);

		JButton btnModulBearbeiten = new JButton("Modul bearbeiten");
		left.add(btnModulBearbeiten);
		btnModulBearbeiten.setEnabled(false);
		btnModulBearbeiten.setPreferredSize(btnSz);
		btnModulBearbeiten.setAlignmentX(Component.CENTER_ALIGNMENT);

		JButton btnLogin = new JButton("Einloggen");
		left.add(btnLogin);
		btnLogin.setEnabled(false);
		btnLogin.setPreferredSize(btnSz);
		btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);

		JButton btnUserVerwaltung = new JButton("User Verwaltung");
		left.add(btnUserVerwaltung);
		btnUserVerwaltung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				((CardLayout) cards.getLayout()).show(cards, "user managment");

			}
		});
		btnUserVerwaltung.setPreferredSize(btnSz);
		btnUserVerwaltung.setAlignmentX(Component.CENTER_ALIGNMENT);

		JButton btnModulVerwaltung = new JButton("Modul Verwaltung");
		left.add(btnModulVerwaltung);
		btnModulVerwaltung.setEnabled(false);
		btnModulVerwaltung.setPreferredSize(btnSz);
		btnModulVerwaltung.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Jemand ne bessere idee f�r einen Button mit Zeilenumbruch?
		JButton btnMHB = new JButton("<html>Modulhandb�cher<br>Durchst�bern");
		left.add(btnMHB);
		btnMHB.setEnabled(false);
		btnMHB.setPreferredSize(btnSz);
		btnMHB.setAlignmentX(Component.CENTER_ALIGNMENT);

	}

	public void topscr() {
		JPanel top = new JPanel();
		FlowLayout flowLayout = (FlowLayout) top.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		frame.getContentPane().add(top, BorderLayout.NORTH);

		JLabel lblMMS = new JLabel("Modul Management System");
		lblMMS.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMMS.setHorizontalAlignment(SwingConstants.LEFT);
		lblMMS.setLabelFor(frame);
		top.add(lblMMS);
	}

	public void centerscr() {

		frame.getContentPane().add(cards, BorderLayout.CENTER);
		cards.setLayout(new CardLayout(0, 0));

		homecard();
		usermgtcard();

	}

	private void usermgtcard() {
		JPanel usrmg = new JPanel();
		cards.add(usrmg, "user managment");
		usrmg.setLayout(new BorderLayout(0, 0));

		JPanel usrpan = new JPanel();
		FlowLayout fl_usrpan = (FlowLayout) usrpan.getLayout();
		fl_usrpan.setAlignment(FlowLayout.RIGHT);
		usrmg.add(usrpan, BorderLayout.SOUTH);

		final JTable usrtbl = new JTable();
		JScrollPane ussrscp = new JScrollPane(usrtbl);
		usrtbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		usrtbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		//
		// Inhalt der Tabelle
		//
		tmodel = new DefaultTableModel(new Object[][] { { "Max", "Muster",
				"max@home.de", "pass123", true, false, false, true } },
				new String[] { "Vorname", "Nachnahme", "e-Mail", "Password",
						"User bearbeiten", "Module einreichen",
						"Module Annehmen", "Module lesen" }) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}

			Class[] columnTypes = new Class[] { String.class, String.class,
					String.class, String.class, boolean.class, boolean.class,
					boolean.class, boolean.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};

		usrtbl.setModel(tmodel);

		JButton btnUserAdd = new JButton("User hinzuf�gen");
		btnUserAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				userdialog dlg = new userdialog(frame, "User hinzuf�gen");
				int response = dlg.showCustomDialog();
				// Wenn ok ged�ckt wird
				// neuen User abfragen
				if (response == 1) {
					User tmp = dlg.getUser();
					/*
					 * abfrage aus Datenbank, ob User schon existiert
					 * 
					 * if(checkUser(tmp)){
					 * 
					 * } else Eintragen!
					 */

					// Vorl�ufig einfach in Tabelle eintragen
					addToTable(tmp);
				}
			}

		});
		usrpan.add(btnUserAdd);

		JButton btnUserEdit = new JButton("User bearbeiten");
		btnUserEdit.setToolTipText("Zum Bearbeiten Benutzer in der Tabelle markieren");
		btnUserEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = usrtbl.getSelectedRow();
				if (row != -1) {
					String vn = (String) usrtbl.getValueAt(row, 0);
					String nn = (String) usrtbl.getValueAt(row, 1);
					String em = (String) usrtbl.getValueAt(row, 2);
					String pw = (String) usrtbl.getValueAt(row, 3);
					boolean r1 = (boolean) usrtbl.getValueAt(row, 4);
					boolean r2 = (boolean) usrtbl.getValueAt(row, 5);
					boolean r3 = (boolean) usrtbl.getValueAt(row, 6);
					boolean r4 = (boolean) usrtbl.getValueAt(row, 7);
					User alt = new User(vn, nn, em, pw, r1, r2, r3, r4);

					userdialog dlg = new userdialog(frame, "User hinzuf�gen",
							alt);
					int response = dlg.showCustomDialog();
					// Wenn ok ged�ckt wird
					// neuen User abfragen
					if (response == 1) {
						User tmp = dlg.getUser();

						// Pr�fen, ob was ver�ndert wurde
						if (!tmp.equals(alt)) {

							/*
							 * abfrage aus Datenbank, ob User schon existiert
							 * 
							 * if(checkUser(tmp)){
							 * 
							 * } else Eintragen! und alten l�schen
							 */

							// Vorl�ufig einfach in Tabelle eintragen, alten
							// entfernen
							removeFromTable(row);
							addToTable(tmp);
						}
					}
				}
			}
		});
		usrpan.add(btnUserEdit);

		JButton btnUserDel = new JButton("User l�schen");
		btnUserDel.setToolTipText("Zum L�schen Benutzer in der Tabelle markieren");
		btnUserDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = usrtbl.getSelectedRow();
				if (row != -1) {
					
					//Vorl�ufig aus Tabelle l�schen
					removeFromTable(row);
				}
			}
		});
		usrpan.add(btnUserDel);

		JButton btnHome = new JButton("Zur�ck");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) cards.getLayout()).first(cards);
			}
		});
		usrpan.add(btnHome);

		JPanel usrcenter = new JPanel();
		usrmg.add(usrcenter, BorderLayout.CENTER);
		usrcenter.setLayout(new BorderLayout(5, 5));

		usrcenter.add(ussrscp);
		JPanel leftpan = new JPanel();
		frame.getContentPane().add(leftpan, BorderLayout.WEST);

	}

	private void homecard() {
		JPanel welcome = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) welcome.getLayout();
		flowLayout_2.setVgap(20);
		cards.add(welcome, "welcome page");

		JLabel lblNewLabel = new JLabel("Hier k�nnte ihre Werbung stehen");
		welcome.add(lblNewLabel);

	}

	private void addToTable(User usr) {
		tmodel.addRow(new Object[] { usr.getVorname(), usr.getNachname(),
				usr.geteMail(), usr.getPassword(), usr.getManageUsers(),
				usr.getCreateModule(), usr.getAcceptModule(),
				usr.getReadModule() });
	}

	private void removeFromTable(int rowid) {
		tmodel.removeRow(rowid);
	}

}
