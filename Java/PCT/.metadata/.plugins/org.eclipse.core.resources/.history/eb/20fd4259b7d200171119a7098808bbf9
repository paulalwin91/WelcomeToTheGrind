package risk.controller;

import risk.controller.RiskStartGameController;
import risk.helpers.Utility;
import risk.controller.RiskStartGameController;
import risk.model.*;
import risk.model.strategy.Human;

import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;

import java.awt.Color;

import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

/**
 * This class is used to add RiskPlayers to the game.
 * 
 * @author Team8
 */
public class RiskGameModeController extends java.awt.Frame {

	/** The no of players. */
	static int noOfPlayers = 3;
    
    /** The behaviour players. */
    static ArrayList<String> behaviourPlayers=new ArrayList<String>(7);
	
	/**
	 *  Creates new form RiskAddPlayerUI.
	 */
	public RiskGameModeController() {
		initComponents();
		setLocationRelativeTo(null);
	}

	/** The added. */
	boolean added;

	/**
	 * This method is called from within the constructor to initialize the form.
	 */
	private void initComponents() {

		modePanel = new javax.swing.JPanel();
		Integer[] numbers = { 3, 4, 5, 6 };
		String[] behaviours= {"Human","Aggressive","Benevolent","Random","Cheater"};
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				exitForm(evt);
			}
		});

		modePanel.setBackground(new java.awt.Color(1, 1, 1));
		modePanel.setName("jPanel1");
		
		JButton btnSingleMode = new JButton("Single Mode");
		btnSingleMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RiskAddPlayerController singleMode = new RiskAddPlayerController();
				singleMode.setVisible(true);
				setVisible(false);

			}
		});
		
		JButton btnTournamentMode = new JButton("Tournament Mode");
		btnTournamentMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			RiskTournamentModeController tournamentMode= new RiskTournamentModeController();
			tournamentMode.setVisible(true);
			setVisible(false);
			}
			});
		
		JLabel lblChooseTheMode = new JLabel("Choose the mode you want to play the game in ");
		lblChooseTheMode.setForeground(Color.WHITE);
		
		javax.swing.GroupLayout gl_modePanel = new javax.swing.GroupLayout(modePanel);
		gl_modePanel.setHorizontalGroup(
			gl_modePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_modePanel.createSequentialGroup()
					.addGroup(gl_modePanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_modePanel.createSequentialGroup()
							.addGap(174)
							.addGroup(gl_modePanel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnSingleMode, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnTournamentMode, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)))
						.addGroup(gl_modePanel.createSequentialGroup()
							.addGap(150)
							.addComponent(lblChooseTheMode)))
					.addContainerGap(185, Short.MAX_VALUE))
		);
		gl_modePanel.setVerticalGroup(
			gl_modePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_modePanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblChooseTheMode)
					.addGap(60)
					.addComponent(btnSingleMode)
					.addGap(28)
					.addComponent(btnTournamentMode)
					.addContainerGap(124, Short.MAX_VALUE))
		);
		modePanel.setLayout(gl_modePanel);

		add(modePanel, java.awt.BorderLayout.CENTER);

		pack();
	}


	/**
	 *  Exit the Application.
	 *
	 * @param evt the evt
	 */
	private void exitForm(java.awt.event.WindowEvent evt) {
		setVisible(false);
	}
	
	/** The mode panel. */
	private javax.swing.JPanel modePanel;
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		 epptelus
	}
}
