package de.team55.mms.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class mainscreen {

	private JFrame frame;
	private JPanel cards = new JPanel();
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

		//Jemand ne bessere idee für einen Button mit Zeilenumbruch?
		JButton btnMHB = new JButton("<html>Modulhandbücher<br>Durchstöbern");
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

		JPanel leiste = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) leiste.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		usrmg.add(leiste, BorderLayout.SOUTH);

		JButton btnHome = new JButton("Zurück");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) cards.getLayout()).first(cards);
			}
		});
		leiste.add(btnHome);

	}

	private void homecard() {
		JPanel welcome = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) welcome.getLayout();
		flowLayout_2.setVgap(20);
		cards.add(welcome, "welcome page");

		JLabel lblNewLabel = new JLabel("Hier könnte ihre Werbung stehen");
		welcome.add(lblNewLabel);

	}
}
