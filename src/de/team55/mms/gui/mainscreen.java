package de.team55.mms.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
	private final Dimension btnSz = new Dimension(140, 40);

	public mainscreen() {
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel left = new JPanel();
		frame.getContentPane().add(left, BorderLayout.WEST);
		left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));

		Component verticalGlue_2 = Box.createVerticalGlue();
		left.add(verticalGlue_2);

		JButton btnModulEinreichen = new JButton("Modul Einreichen");
		btnModulEinreichen.setEnabled(false);
		btnModulEinreichen.setPreferredSize(btnSz);
		btnModulEinreichen.setAlignmentX(Component.CENTER_ALIGNMENT);
		left.add(btnModulEinreichen);

		Component verticalGlue_1 = Box.createVerticalGlue();
		left.add(verticalGlue_1);

		JButton btnModulBearbeiten = new JButton("Modul bearbeiten");
		btnModulBearbeiten.setEnabled(false);
		btnModulBearbeiten.setPreferredSize(btnSz);
		btnModulBearbeiten.setAlignmentX(Component.CENTER_ALIGNMENT);
		left.add(btnModulBearbeiten);

		Component verticalGlue = Box.createVerticalGlue();
		left.add(verticalGlue);

		JButton btnLogin = new JButton("Einloggen");
		btnLogin.setEnabled(false);
		btnLogin.setPreferredSize(btnSz);
		btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
		left.add(btnLogin);

		Component verticalGlue_3 = Box.createVerticalGlue();
		left.add(verticalGlue_3);

		final JPanel cards = new JPanel();
		frame.getContentPane().add(cards, BorderLayout.CENTER);
		cards.setLayout(new CardLayout(0, 0));

		JButton btnUserVerwaltung = new JButton("User Verwaltung");
		btnUserVerwaltung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				((CardLayout) cards.getLayout()).show(cards, "user managment");

			}
		});
		btnUserVerwaltung.setPreferredSize(btnSz);
		btnUserVerwaltung.setAlignmentX(Component.CENTER_ALIGNMENT);
		left.add(btnUserVerwaltung);

		Component verticalGlue_4 = Box.createVerticalGlue();
		left.add(verticalGlue_4);

		JButton btnModulVerwaltung = new JButton("Modul Verwaltung");
		btnModulVerwaltung.setEnabled(false);
		btnModulVerwaltung.setPreferredSize(btnSz);
		btnModulVerwaltung.setAlignmentX(Component.CENTER_ALIGNMENT);
		left.add(btnModulVerwaltung);

		Component verticalGlue_5 = Box.createVerticalGlue();
		left.add(verticalGlue_5);

		JButton btnMHB = new JButton("<html>Modulhandbücher<br>Durchstöbern");
		btnMHB.setEnabled(false);
		btnMHB.setPreferredSize(btnSz);
		btnMHB.setAlignmentX(Component.CENTER_ALIGNMENT);
		left.add(btnMHB);

		Component verticalGlue_6 = Box.createVerticalGlue();
		left.add(verticalGlue_6);

		JPanel top = new JPanel();
		FlowLayout flowLayout = (FlowLayout) top.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		frame.getContentPane().add(top, BorderLayout.NORTH);

		JLabel lblMMS = new JLabel("Modul Management System");
		lblMMS.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMMS.setHorizontalAlignment(SwingConstants.LEFT);
		lblMMS.setLabelFor(frame);
		top.add(lblMMS);

		JPanel welcome = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) welcome.getLayout();
		flowLayout_2.setVgap(25);
		cards.add(welcome, "welcome page");

		JLabel lblNewLabel = new JLabel("Hier k\u00F6nnte ihre Werbung stehen");
		welcome.add(lblNewLabel);

		JPanel usrmg = new JPanel();
		cards.add(usrmg, "user managment");
		usrmg.setLayout(new BorderLayout(0, 0));

		JPanel leiste = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) leiste.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		usrmg.add(leiste, BorderLayout.SOUTH);

		JButton btnHome = new JButton("Zurück");
		leiste.add(btnHome);
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) cards.getLayout()).first(cards);
			}
		});
		
		frame.setVisible(true);
	}
}
