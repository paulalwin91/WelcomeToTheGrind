package risk.model.strategy;

import risk.helpers.Utility;
import risk.model.RiskGameModel;
import risk.model.RiskTerritoryModel;
import risk.model.interfaces.StrategyInterface;

// TODO: Auto-generated Javadoc
/**
 * The Class Human.
 */
public class Human implements StrategyInterface {

	/* (non-Javadoc)
	 * @see risk.model.interfaces.StrategyInterface#takeTurn(boolean, risk.model.RiskGameModel)
	 */
	@Override
	public void takeTurn(boolean isTest, RiskGameModel riskGameModel) {
		// TODO Auto-generated method stub
		return;
	}

	/**
	 * this function is for Risk start turn.
	 *
	 * @param isTest the is test
	 * @param riskGameModel the risk game model
	 * @return the string
	 */
	@Override
	public void startTurn(boolean isTest, RiskGameModel riskGameModel) {
		riskGameModel.currentPlayerBonusArmiesRecieved = riskGameModel.turnBonus();
		riskGameModel.curPlayer.addArmies(riskGameModel.currentPlayerBonusArmiesRecieved);
		if (!isTest)
			riskGameModel.notifyPhaseViewChange();
		// recive turn bonus
		if (riskGameModel.curPlayer.getCard().size() > 5) {
			riskGameModel.setState(RiskGameModel.TRADE_CARDS);
			//return "tradecards";
		} else {
			riskGameModel.setState(RiskGameModel.REINFORCE);
			//return "reinforce";
		}
	}

	/* (non-Javadoc)
	 * @see risk.model.interfaces.StrategyInterface#attack(boolean, risk.model.RiskGameModel, int[])
	 */
	@Override
	public void attack(boolean isTest, RiskGameModel riskGameModel, int... territory) {
		if (riskGameModel.getState() == RiskGameModel.ATTACKING)
			 RiskAttacking(territory[0], riskGameModel);
		if (riskGameModel.getState() == RiskGameModel.ATTACK)
			Riskattack(territory[0], riskGameModel);
		if (riskGameModel.getState() == RiskGameModel.ATTACK_PHASE)
			 RiskAttackPhase(territory[0], riskGameModel.xCoordinate, riskGameModel.yCoordinate, riskGameModel);
		if (riskGameModel.getState() == RiskGameModel.CAPTURE)
			capture(false,territory[0], riskGameModel);

		return;
	}

	/* (non-Javadoc)
	 * @see risk.model.interfaces.StrategyInterface#reinforce(boolean, risk.model.RiskGameModel, int[])
	 */
	@Override
	public void reinforce(boolean isTest, RiskGameModel riskGameModel, int... territory) {
		if (territory[0] != -1) // if not a country
			if (riskGameModel.getOwnership(territory[0]) == riskGameModel.curPlayer.getPlayerIndex()) // if owned
			{
				riskGameModel.occupyTerritory(RiskGameModel.territories.elementAt(territory[0])); // occupy
				Utility.writeLog("INITIAL REINFORCE - Some Human called - " + riskGameModel.curPlayer.getName()
						+ " placed one of his army on " + riskGameModel.getTerritoryAt(territory[0]).getName());
				riskGameModel.notifyPhaseViewChange();
//				return "true";
			}
		return;
	}

	/* (non-Javadoc)
	 * @see risk.model.interfaces.StrategyInterface#fortify(boolean, risk.model.RiskGameModel, int[])
	 */
	@Override
	public void fortify(boolean isTest, RiskGameModel riskGameModel, int... territory) {
		if (riskGameModel.getState() == RiskGameModel.FORTIFYING)
			RiskFortifying(territory[0], riskGameModel);
		if (riskGameModel.getState() == RiskGameModel.FORTIFY)
			RiskFortify(territory[0], riskGameModel);
		if (riskGameModel.getState() == RiskGameModel.FORTIFY_PHASE)
			RiskFortifyPhase(territory[0], riskGameModel);
		return;
	}

	/**
	 * this function is for Risk attacking.
	 *
	 * @param country            the country
	 * @param riskGameModel the risk game model
	 * @return the string
	 */
	public String RiskAttacking(int country, RiskGameModel riskGameModel) {
		if (country != -1) {// not a country
			RiskTerritoryModel d = RiskGameModel.territories.elementAt(country); // defending
			// territory

			System.out.println(riskGameModel.aTerritory.getAdjacents().size());

			for (int index : riskGameModel.aTerritory.getAdjacents()) {
				System.out.println(RiskGameModel.territories.get(index));
			}

			if (riskGameModel.getOwnership(country) == riskGameModel.curPlayer.getPlayerIndex())
				return "You own that territory.";
			if (riskGameModel.aTerritory.isAdjacent(d)) {// if its adjacent...
				riskGameModel.setState(RiskGameModel.ATTACK_PHASE);
				riskGameModel.defenseTerritory = d;
				riskGameModel.defender = d.getPlayer();
			} else
				// if its not adjacent
				System.out.println("That territory is not adjacent, try again.");

			return "That territory is not adjacent, try again.";

		}
		riskGameModel.notifyPhaseViewChange();
		return "";

	}

	/**
	 * Risk attack phase.
	 *
	 * @param territory the territory
	 * @param xCoordinate the x coordinate
	 * @param yCoordinate the y coordinate
	 * @param risk the risk
	 * @return the string
	 */
	public String RiskAttackPhase(int territory, int xCoordinate, int yCoordinate, RiskGameModel risk) {
		int attackArmies = risk.aTerritory.getArmies();
		int defenseArmies = risk.defenseTerritory.getArmies();
		int numofatt = 0;
		risk.numberofAttack = 0;
		// If attackers turn
		if (risk.active == risk.curPlayer) {
			if (attackArmies > 3) {
				if (yCoordinate > 250 && yCoordinate < 280) {// if in
																// yCoordinate
																// coord
					if (xCoordinate > 420 && xCoordinate < 460) // If dice
																// one
						numofatt = 1;
					if (xCoordinate > 480 && xCoordinate < 520) // if dice
																// two
						numofatt = 2;
					if (xCoordinate > 540 && xCoordinate < 580)
						numofatt = 3;
				} // in yCoordinate coord
			} // if attcking with 3
			if (attackArmies == 3) {// if attakking with two
				if (yCoordinate > 250 && yCoordinate < 280) {
					if (xCoordinate > 460 && xCoordinate < 500)
						numofatt = 1;
					if (xCoordinate > 510 && xCoordinate < 550)
						numofatt = 2;
				} // in yCoordinate coord
			} // end if can attack with two

			if (attackArmies == 2) {// can only attack with one
				if (yCoordinate > 250 && yCoordinate < 280) {
					if (xCoordinate > 480 && xCoordinate < 520)
						numofatt = 1;
				} // in yCoordinate
			} // end only attack with one

			if (numofatt != 0) {// change player is num is selected
				risk.active = risk.defender;
				risk.setAttack(numofatt);
				Utility.writeLog(risk.getCurrentPlayer().getName() + " has " + attackArmies + " armies");
				Utility.writeLog(risk.getCurrentPlayer().getName() + " attacking with " + numofatt + " armies");
			}

		} // end attackers turn

		// If defenders turn
		else if (risk.active == risk.defender) {
			if (defenseArmies > 1 && risk.attackNum > 1) {
				if (yCoordinate > 250 && yCoordinate < 280) {
					if (xCoordinate > 460 && xCoordinate < 500)
						numofatt = 1;
					if (xCoordinate > 510 && xCoordinate < 550)
						numofatt = 2;
				}
			} else {
				if (yCoordinate > 250 && yCoordinate < 280) {
					if (xCoordinate > 480 && xCoordinate < 520)
						numofatt = 1;
				}
			}

			risk.notifyPhaseViewChange();
			if (numofatt > 0) {
				risk.setDefend(numofatt);
				risk.engageBattle();
				if (defenseArmies - risk.defenseTerritory.getArmies() == 1) {
					Utility.writeLog(risk.curPlayer.getName() + " has destroyed an army");
				} else if (defenseArmies - risk.defenseTerritory.getArmies() == 2) {
					Utility.writeLog(risk.curPlayer.getName() + " has destroyed two armies");
				} else if (attackArmies - risk.aTerritory.getArmies() == 1) {
					Utility.writeLog(risk.curPlayer.getName() + " has lost an army");
				} else if (attackArmies - risk.aTerritory.getArmies() == 2) {
					Utility.writeLog(risk.curPlayer.getName() + " has lost two armies");
				}

				if (risk.aTerritory.getArmies() == 1) {
					risk.setState(RiskGameModel.ACTIVE_TURN);
					// AttackButton.setText("Attack");
					// FortifyButton.setVisible(true);
					// EndButton.setVisible(true);
					// risk.defenseNum = 0;
					// risk.attackNum = 0;
					// risk.defenseTerritory = null;
					// risk.aTerritory = null;
				}
			}

		} /// end if defenders turn
		return String.valueOf(numofatt); // Print details in risk model
	}

	/**
	 * This function is about Risk attack.
	 *
	 * @param country            the country
	 * @param riskGameModel the risk game model
	 * @return the string
	 */
	public String Riskattack(int country, RiskGameModel riskGameModel) {
		if (country != -1)// if not a country
			if (riskGameModel.getOwnership(country) == riskGameModel.curPlayer.getPlayerIndex()) {
				if (RiskGameModel.territories.elementAt(country).getArmies() < 2)
					return "Not enough armies to battle, need at least 2";
				else {
					riskGameModel.setState(RiskGameModel.ATTACKING);
					riskGameModel.aTerritory = RiskGameModel.territories.elementAt(country);
					return "true";
				}
			} // end is curPlayers country
		riskGameModel.notifyPhaseViewChange();
		return "";
	}

	/**
	 * this function is for Risk trade cards.
	 *
	 * @param country            the country
	 * @param riskGameModel the risk game model
	 * @return the string
	 */
	// public String RiskTradeCards(int country, RiskGameModel riskGameModel) {
	//// if (country != -1) // if not a country
	//// if (riskGameModel.getOwnership(country) ==
	// riskGameModel.curPlayer.getPlayerIndex()) // if
	//// // owned
	//// {
	//// riskGameModel.occupyTerritory(riskGameModel.territories.elementAt(country));
	// // occupy
	//// riskGameModel.notifyPhaseViewChange();
	//// riskGameModel.setState(riskGameModel.REINFORCE);
	//// return "true";
	//// }
	//// return "";
	// }

	/**
	 * this function is for Risk fortify.
	 *
	 * @param country
	 *            the country
	 * @return the string
	 */
	public String RiskFortify(int country, RiskGameModel riskGameModel) {
		if (country != -1) {
			if (riskGameModel.getOwnership(country) == riskGameModel.curPlayer.getPlayerIndex()) {
				riskGameModel.setState(RiskGameModel.FORTIFYING);
				riskGameModel.aTerritory = RiskGameModel.territories.elementAt(country);
				riskGameModel.notifyPhaseViewChange(); // get the first country to
				// fotify
				return "true";
			}
		}
		return "false";
	}

	/**
	 * Risk fortifying.
	 *
	 * @param country the country
	 * @param riskGameModel the risk game model
	 * @return the string
	 */
	public String RiskFortifying(int country, RiskGameModel riskGameModel) {
		if (country != -1) {// not a country
			riskGameModel.defenseTerritory = RiskGameModel.territories.elementAt(country); // move to
			// territory
			if (riskGameModel.getOwnership(country) == riskGameModel.curPlayer.getPlayerIndex())
				if (riskGameModel.aTerritory.isAdjacent(riskGameModel.defenseTerritory)) {// if its
					// adjacent...

					// if (flag) //Commented 21 Nov - Does not seem to be neccessary - paul
					// return "true";
					riskGameModel.notifyPhaseViewChange();
					riskGameModel.setState(RiskGameModel.FORTIFY_PHASE);
					return "true";
				} else
					return "false";
		} // end if a county
		return "";
	}

	/**
	 * Risk fortify phase.
	 *
	 * @param territory the territory
	 * @param riskGameModel the risk game model
	 * @return the string
	 */
	public String RiskFortifyPhase(int territory, RiskGameModel riskGameModel) {
		String statusLabelText = "";
		int from = riskGameModel.aTerritory.getArmies();

		if (riskGameModel.yCoordinate > 230 && riskGameModel.yCoordinate < 257) {// if yCoordinate is
			// correct
			if (riskGameModel.xCoordinate > 600 && riskGameModel.xCoordinate < 650)// if max
				if (riskGameModel.defenseNum == (from - 1))
					statusLabelText = "Maximum units already selected";
				else
					riskGameModel.defenseNum = (from - 1);// all but one
			if (riskGameModel.xCoordinate > 520 && riskGameModel.xCoordinate < 570) { // if inc
				if (riskGameModel.defenseNum < (from - 1))
					riskGameModel.defenseNum++;
				else
					statusLabelText = "Maximum units already selected";
			} // end if inc
			if (riskGameModel.xCoordinate > 440 && riskGameModel.xCoordinate < 490) {// if dec
				if (riskGameModel.defenseNum > (from - 1))
					riskGameModel.defenseNum--;
				else
					statusLabelText = "Minimum units already selected";
			} // end if add
			if (riskGameModel.xCoordinate > 360 && riskGameModel.xCoordinate < 410)// min
				if (riskGameModel.defenseNum == 0)
					statusLabelText = "Minimum units already selected";
				else
					riskGameModel.defenseNum = 0;
		} // end if yCoordinate coord

		if (riskGameModel.xCoordinate > 460 && riskGameModel.xCoordinate < 545) {// move has ben
			// clicked
			if (riskGameModel.yCoordinate > 325 && riskGameModel.yCoordinate < 355) {// then occupy
				// the territory
				if (riskGameModel.defenseNum == 1)
					statusLabelText = "1 army moved to " + riskGameModel.defenseTerritory.getName();
				else
					statusLabelText = riskGameModel.defenseNum + " armies moved to "
							+ riskGameModel.defenseTerritory.getName();
				riskGameModel.notifyPhaseViewChange();
				Utility.writeLog(riskGameModel.getCurrentPlayer().getName() + " has " + riskGameModel.defenseNum
						+ " armies moved to " + riskGameModel.defenseTerritory.getName());
				riskGameModel.aTerritory.looseArmies(riskGameModel.defenseNum);
				riskGameModel.defenseTerritory.addArmies(riskGameModel.defenseNum);
				riskGameModel.setState(RiskGameModel.ACTIVE_TURN);
			} // end yCoordinate
		} // end xCoordinate for movwe
		return statusLabelText;
	}

	/**
	 * Capture.
	 *
	 * @param isTest the is test
	 * @param territory the territory
	 * @param riskGameModel the risk game model
	 * @return the string
	 */
	public String capture(boolean isTest, int territory, RiskGameModel riskGameModel) {
		// statusLabel.setText("Select number of armies to move to " +
		// risk.defenseTerritory.getName());
		// AttackButton.setVisible(false);
		// AttackButton.setText("Attack");
		String statusLabelText = "";
		int max = riskGameModel.aTerritory.getArmies() - 1;
		int min = riskGameModel.attackNum;
		if (riskGameModel.yCoordinate > 230 && riskGameModel.yCoordinate < 257) {// if yCoordinate is
			// correct
			if (riskGameModel.xCoordinate > 600 && riskGameModel.xCoordinate < 650)// if max
				riskGameModel.defenseNum = riskGameModel.aTerritory.getArmies() - 1; // max
			if (riskGameModel.xCoordinate > 520 && riskGameModel.xCoordinate < 570) { // if inc
				if (riskGameModel.defenseNum < max)
					riskGameModel.defenseNum++;
				else
					statusLabelText = "Maximum units already selected";
			} // end if sub
			if (riskGameModel.xCoordinate > 440 && riskGameModel.xCoordinate < 490) {// if dec
				if (riskGameModel.defenseNum > min)
					riskGameModel.defenseNum--;
				else
					statusLabelText = "Minimum units already selected";
			} // end if add
			if (riskGameModel.xCoordinate > 360 && riskGameModel.xCoordinate < 410)// min
				riskGameModel.defenseNum = min;
		} // end if yCoordinate coord

		if (riskGameModel.xCoordinate > 460 && riskGameModel.xCoordinate < 545) {// move has ben
			// clicked
			if (riskGameModel.yCoordinate > 325 && riskGameModel.yCoordinate < 355) {// then occupy
				// the territory
				// AttackButton.setVisible(true);
				if (riskGameModel.defenseNum == 1)
					statusLabelText = "1 army moved to " + riskGameModel.defenseTerritory.getName();
				else
					statusLabelText = riskGameModel.defenseNum + " armies moved to "
							+ riskGameModel.defenseTerritory.getName();

				riskGameModel.notifyPhaseViewChange();
				riskGameModel.setAttackDieArray(new Integer[] { 0, 0, 0 });
				riskGameModel.setDefenceDieArray(new Integer[] { 0, 0, 0 });
				Utility.writeLog(riskGameModel.getCurrentPlayer().getName() + " has " + riskGameModel.defenseNum
						+ " armies moved to " + riskGameModel.defenseTerritory.getName());
				// EndButton.setVisible(true);
				// FortifyButton.setVisible(true);
				//if (riskGameModel.capture()) {
					// AttackButton.setVisible(false);
					// FortifyButton.setVisible(false);
					// CardButton.setVisible(false);
					// EndButton.setVisible(false);
					//statusLabelText = riskGameModel.getCurrentPlayer().getName() + " has won the game";
					// JOptionPane.showMessageDialog(null,
					// riskGameModel.getCurrentPlayer().getName() + " has won the game",
					// "Alert", JOptionPane.INFORMATION_MESSAGE);
				//}
			}
		}
		// risk.notifyPhaseViewChange();
		return statusLabelText;
	}

	/* (non-Javadoc)
	 * @see risk.model.interfaces.StrategyInterface#initialReinforce(boolean, risk.model.RiskGameModel, int[])
	 */
	@Override
	public void initialReinforce(boolean isTest, RiskGameModel riskGameModel, int... territory) {
		// TODO Auto-generated method stub
		return;
	}

}
