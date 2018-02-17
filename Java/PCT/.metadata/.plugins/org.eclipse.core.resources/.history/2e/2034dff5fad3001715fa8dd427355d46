package risk.controller;

import risk.helpers.Utility;
import risk.model.*;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Line2D;
import java.awt.image.PixelGrabber;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * This class is responsible for the MapPanelView.It is responsible for drawing
 * lines connecting the nodes using the coordinate values from the mapfile.It
 * also has the method for creating a fortify panel where user can select how
 * many number of armies to move from one territory to another.
 * 
 * @author Team8
 */
public class RiskMapPanelViewController extends JPanel {
	private Image map;
	PixelGrabber pg;
	RiskGameModel risk;
	private Image army;
	private Image shield;
	public int armies;

	/**
	 * Instantiates a new risk map panel view controller.
	 */
	public RiskMapPanelViewController() {

		try {
			this.map = ImageIO.read(getClass().getResourceAsStream(Utility.getImagePath("map.jpg")));
			this.army = ImageIO.read(getClass().getResourceAsStream(Utility.getImagePath("army.gif")));
			this.shield = ImageIO.read(getClass().getResourceAsStream(Utility.getImagePath("shield.gif")));
			map = map.getScaledInstance(1000, 550, Image.SCALE_SMOOTH);
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	/**
	 * Instantiates a new risk map panel view controller.
	 */
	public RiskMapPanelViewController(RiskGameModel riskgamemodel) {
		this();
		risk = riskgamemodel;
	}

	/**
	 * Refresh.
	 */
	public void refresh() {
		repaint();
	}

	/**
	 * Select country by color.
	 *
	 * @param x,
	 *            x-coordinate
	 * @param y,
	 *            y-coordinate
	 */
	public void selectCountrybyColor(int x, int y) {

	}

	@Override
	public void paintComponent(Graphics graphics) {
		int playerIndex;
		int loc[] = new int[2];
		int align;
		String armies = "";
		int state = risk.getState();

		super.paintComponents(graphics);
		graphics.drawImage(map, 0, 0, null);
		graphics.setColor(Color.white);

		int numTerritories = risk.numOfTerroitories();
		for (int cindex = 0; cindex < numTerritories; cindex++) {
			loc = risk.drawMap(cindex);
			graphics.drawArc(loc[0], loc[1], 30, 30, 0, 360);
		}
		if (numTerritories > 0)
			drawConnectAdjacentCountries(graphics);

		for (int cindex = 0; cindex < numTerritories; cindex++) {
			playerIndex = risk.getOwnership(cindex);
			RiskPlayerModel p = risk.getCurrentPlayer();
			int num = risk.numOfArmiesOnTerritory(cindex);
			if (num > 9)
				align = -3;
			else
				align = 0;
			armies = Integer.toString(num);
			if (armies.equals("0"))
				armies = "";
			// System.out.println(playerIndex + " index = " + c); //Debugging
			if (playerIndex == 0)
				graphics.setColor(Color.red);
			if (playerIndex == 1)
				graphics.setColor(Color.blue);
			if (playerIndex == 2)
				graphics.setColor(Color.yellow);
			if (playerIndex == 3)
				graphics.setColor(Color.green);
			if (playerIndex == 4)
				graphics.setColor(Color.pink);
			if (playerIndex == 5)
				graphics.setColor(Color.orange);
			if (playerIndex == -1)
				graphics.setColor(Color.white);

			loc = risk.fillDrawMap(cindex, playerIndex);
			graphics.fillArc(loc[0], loc[1], 30, 30, 0, 360);
			graphics.setColor(Color.white);
			graphics.drawString(armies, loc[0] + 10 + align, loc[1] + 20);

		} // end draw cilcles and armies
		/**
		 * 
		 * Attack Window is here
		 * 
		 */

		String game_state = "";
		if (state == 0)
			game_state = "New Game";
		if (state == 1)
			game_state = "Initial Reinforce";
		if (state == 2)
			game_state = "Active Game";
		if (state == 3)
			game_state = "Turn Bonus";
		if (state == 4)
			game_state = "Reinforce";
		if (state == 5)
			game_state = "Trade Cards";
		if (state == 6)
			game_state = "Start turn";
		if (state == 7)
			game_state = "Attack";
		if (state == 8)
			game_state = "Attacking";
		if (state == 9)
			game_state = "Attack_Phase";
		if (state == 10)
			game_state = "Battling";
		if (state == 11)
			game_state = "Capture";

		graphics.drawString("Current State: " + game_state, 10, 10);

		/**
		 * Capture
		 *
		 */

		if (state == RiskGameModel.CAPTURE) {

			int max = risk.aTerritory.getArmies();
			int min = risk.attackNum;

			if (risk.defenseNum < min)
				risk.defenseNum = min;
			// risk.defenseNum Store the armies to move here in RiskUI

			graphics.setColor(Color.white);
			Font h2 = new Font("Arial", Font.BOLD, 36);
			Font h3 = new Font("Arial", Font.BOLD, 20);
			graphics.fillRect(250, 100, 500, 300);// Draw main window

			graphics.setColor(Color.black);
			graphics.drawString("How many armies to move?", 430, 200);

			graphics.setFont(h2);
			graphics.setColor(Color.red);
			graphics.drawString("Occupy RiskTerritory", 360, 160);
			graphics.drawString(Integer.toString(risk.defenseNum), 490, 305); // ARMIES

			graphics.drawRect(600, 230, 50, 27); // max
			graphics.drawRect(520, 230, 50, 27); // inc
			graphics.drawRect(440, 230, 50, 27); // dec
			graphics.drawRect(360, 230, 50, 27); // min

			graphics.setFont(new Font("Arial", Font.BOLD, 26));
			graphics.drawString("MOVE", 465, 350);

			graphics.setFont(h3);
			graphics.setColor(Color.black);

			graphics.drawRect(460, 325, 85, 30); // move box

			graphics.drawString("MAX", 605, 250);
			graphics.drawString("INC", 528, 250);
			graphics.drawString("DEC", 445, 250);
			graphics.drawString("MIN", 365, 250);
		} // end capture

		/**
		 *
		 * Fortify
		 *
		 *
		 */

		if (state == RiskGameModel.FORTIFY_PHASE) {
			int max = 0;
			if (risk.aTerritory != null)
				max = risk.aTerritory.getArmies();
			int min = 0;

			// risk.defenseNum Store the armies to move here in RiskUI

			graphics.setColor(Color.white);
			Font font2 = new Font("Arial", Font.BOLD, 36);
			Font font3 = new Font("Arial", Font.BOLD, 20);
			graphics.fillRect(250, 100, 500, 300);// Draw main window

			graphics.setColor(Color.black);
			graphics.drawString("How many armies to move?", 430, 200);

			graphics.setFont(font2);
			graphics.setColor(Color.red);
			graphics.drawString("Fortify RiskTerritory", 360, 160);
			graphics.drawString(Integer.toString(risk.defenseNum), 490, 305); // ARMIES

			graphics.drawRect(600, 230, 50, 27); // max
			graphics.drawRect(520, 230, 50, 27); // inc
			graphics.drawRect(440, 230, 50, 27); // dec
			graphics.drawRect(360, 230, 50, 27); // min

			graphics.setFont(new Font("Arial", Font.BOLD, 26));
			graphics.drawString("MOVE", 465, 350);

			graphics.setFont(font3);
			graphics.setColor(Color.black);

			graphics.drawRect(460, 325, 85, 30); // move box

			graphics.drawString("MAX", 605, 250);
			graphics.drawString("INC", 528, 250);
			graphics.drawString("DEC", 445, 250);
			graphics.drawString("MIN", 365, 250);
		} // end capture

		/**
		 * 
		 * RiskCard Menu
		 * 
		 */

		if (state == RiskGameModel.TRADE_CARDS) {
			int num = risk.curPlayer.getCard().size();
			Vector<RiskCardModel> hand = risk.curPlayer.getCard();
			graphics.setColor(Color.white);
			graphics.fillRect(250, 100, 500, 300);// Draw main window

			graphics.setColor(Color.black);
			Font names = new Font("Arial", Font.BOLD, 36);
			Font f1 = new Font("Arial", Font.BOLD, 15);
			graphics.setFont(names);

			graphics.drawString("Trade Cards", 400, 160);
			if (num < 3) {

				graphics.drawRect(475, 350, 50, 30);// exit box
				graphics.setFont(f1);
				graphics.drawString("You dont have enough cards", 400, 320);
				graphics.drawString("Exit", 485, 370);

			}

			if (num > 2) {

				graphics.setFont(f1);
				int temp;

				for (int c_index = 0; c_index < num; c_index++) {
					graphics.drawString(
							risk.getCountryName(hand.elementAt(c_index).territory) + " value = "
									+ risk.curPlayer.getCard().elementAt(c_index - 1).card_type,
							350, 250 + (c_index * 30));

					if (c_index < num - 1) {

						if (risk.curPlayer.getCard().elementAt(c_index - 1).card_type
								.equals(risk.curPlayer.getCard().elementAt(c_index).card_type))
							risk.attackNum++;
					}

				}

				graphics.drawRect(475, 350, 50, 30);// exit box
				graphics.drawString("Exit", 485, 370);
			}

		} // end card menu

		if ((state == RiskGameModel.ATTACK || state == RiskGameModel.ATTACKING || state == RiskGameModel.ATTACK_PHASE)
				&& risk.aTerritory != null) {
			graphics.drawString("Attacking white " + risk.aTerritory.getName(), 10, 460);
		}

		if (state == RiskGameModel.ATTACK_PHASE) {
			// This Paint the attack "popup" window
			int att = risk.aTerritory.getArmies();
			int def = risk.defenseTerritory.getArmies();

			graphics.setColor(Color.white);
			graphics.fillRect(250, 100, 500, 300);// Draw main window

			graphics.setColor(Color.black);
			Font names = new Font("Arial", Font.BOLD, 36);
			Font f1 = new Font("Arial", Font.BOLD, 15);
			graphics.setFont(names);
			graphics.drawString(risk.curPlayer.getName(), 270, 145); // RiskPlayer Attacking
			graphics.drawString(risk.defender.getName(), 600, 145);
			graphics.setColor(Color.red);
			graphics.drawString(Integer.toString(att), 300, 250);
			graphics.drawString(Integer.toString(def), 660, 250);
			graphics.setFont(f1);

			/**
			 *
			 * Attacker
			 *
			 */

			if (risk.active == risk.curPlayer) {
				graphics.drawString("How many armies to attack with?", 390, 180);
				graphics.drawImage(army, 300, 280, this);
				graphics.setColor(Color.white);

				if (att > 3) {
					graphics.fillRect(420, 250, 40, 40);
					graphics.fillRect(480, 250, 40, 40);
					graphics.fillRect(540, 250, 40, 40);
					graphics.setColor(Color.black);
					// die 1
					graphics.fillArc(435, 265, 10, 10, 0, 360);
					// die 2
					graphics.fillArc(485, 255, 10, 10, 0, 360);
					graphics.fillArc(505, 275, 10, 10, 0, 360);
					// die3
					graphics.fillArc(565, 255, 10, 10, 0, 360);
					graphics.fillArc(555, 265, 10, 10, 0, 360);
					graphics.fillArc(545, 275, 10, 10, 0, 360);

				}
				if (att == 3) {
					graphics.fillRect(460, 250, 40, 40);
					graphics.fillRect(510, 250, 40, 40);
					graphics.setColor(Color.black);
					graphics.fillArc(475, 265, 10, 10, 0, 360);
					graphics.fillArc(515, 255, 10, 10, 0, 360);
					graphics.fillArc(535, 275, 10, 10, 0, 360);

				}
				if (att == 2) {
					graphics.fillRect(480, 250, 40, 40);
					graphics.setColor(Color.black);
					graphics.fillArc(495, 265, 10, 10, 0, 360);
				}
			} // end attttacker painting

			/**
			 *
			 * Defender
			 *
			 */

			if (risk.active == risk.defender) {
				graphics.drawString("How many armies to defend with?", 390, 180);
				graphics.drawImage(shield, 630, 280, this);
				graphics.setColor(Color.white);
				if (def > 1 && risk.attackNum > 1) {
					graphics.fillRect(460, 250, 40, 40);
					graphics.fillRect(510, 250, 40, 40);
					graphics.setColor(Color.black);
					graphics.fillArc(475, 265, 10, 10, 0, 360);
					graphics.fillArc(515, 255, 10, 10, 0, 360);
					graphics.fillArc(535, 275, 10, 10, 0, 360);
				} else {
					graphics.fillRect(480, 250, 40, 40);
					graphics.setColor(Color.black);
					graphics.fillArc(495, 265, 10, 10, 0, 360);
				}

			} // end defender painting

		}

	}

	/**
	 * Draw connect adjacent countries.
	 *
	 */
	private void drawConnectAdjacentCountries(Graphics graphics) {
		// TODO Auto-generated method stub
		for (RiskTerritoryModel territory : RiskGameModel.territories) {
			if (territory.getAdjacents().size() > 0) {
				for (int adjacent : territory.getAdjacents()) {
					try {
						drawLineforCoordinates(territory.getX(), territory.getY(), risk.getTerritoryAt(adjacent).getX(),
								risk.getTerritoryAt(adjacent).getY(), graphics);
					} catch (Exception e) {
						System.out.println("x1 " + territory.getX() + "y1 " + territory.getY() + "x2 "
								+ risk.getTerritoryAt(adjacent).getX() + "y2 " + risk.getTerritoryAt(adjacent).getY());
					}
				}
			}
		}
	}

	/**
	 * Draw line for coordinates.
	 *
	 * @param start_x
	 *            the start x
	 * @param start_y
	 *            the start y
	 * @param destination_x
	 *            the destination x
	 * @param destination_y
	 *            the destination y
	 * @param graphics
	 *            the graphics
	 */
	private void drawLineforCoordinates(int start_x, int start_y, int destination_x, int destination_y,
			Graphics graphics) {
		Graphics2D graphics2 = (Graphics2D) graphics;
		graphics2.setColor(Color.black);
		graphics2.setStroke(new BasicStroke(2));
		graphics2.draw(new Line2D.Float(start_x, start_y, destination_x, destination_y));
	}
}