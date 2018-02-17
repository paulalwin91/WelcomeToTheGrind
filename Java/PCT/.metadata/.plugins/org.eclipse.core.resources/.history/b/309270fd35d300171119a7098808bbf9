package risk.model.strategy;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import risk.helpers.Utility;
import risk.model.RiskCardModel;
import risk.model.RiskGameModel;
import risk.model.RiskPlayerModel;
import risk.model.RiskTerritoryModel;
import risk.model.interfaces.StrategyInterface;

// TODO: Auto-generated Javadoc
/**
 * The Class Random.
 */
public class Random implements StrategyInterface {

	/** The current risk model. */
	RiskGameModel currentRiskModel;

	/* (non-Javadoc)
	 * @see risk.model.interfaces.StrategyInterface#takeTurn(boolean, risk.model.RiskGameModel)
	 */
	@Override
	public void takeTurn(boolean isTest, RiskGameModel riskGameModel) {
		Utility.writeLog("***********" + riskGameModel.curPlayer.getName() + " turn *************");

		if (riskGameModel.getState() == RiskGameModel.INITIAL_REINFORCE)
			initialReinforce(false, riskGameModel);
		if (riskGameModel.getState() == RiskGameModel.START_TURN
				|| riskGameModel.getState() == RiskGameModel.ACTIVE_TURN)
			startTurn(false, riskGameModel);

		riskGameModel.mainPanel.repaint();
		riskGameModel.subPanel.repaint();

		// return "";
	}

	/* (non-Javadoc)
	 * @see risk.model.interfaces.StrategyInterface#initialReinforce(boolean, risk.model.RiskGameModel, int[])
	 */
	@Override
	public void initialReinforce(boolean isTest, RiskGameModel riskGameModel, int... territory) {
		if (riskGameModel.curPlayer.getNumberOfArmies() > 0) {
			int occupiedTerritory = getRandomOccupiedTerritoryByPlayer(riskGameModel.curPlayer);
			if (occupiedTerritory != -1) {
				riskGameModel.occupyTerritoryByPlayer(occupiedTerritory, riskGameModel.curPlayer);
				Utility.writeLog("INITIAL REINFORCE - Some Random dude called - " + riskGameModel.curPlayer.getName()
						+ " placed one of his army on " + riskGameModel.getTerritoryAt(occupiedTerritory).getName());
			}
			riskGameModel.notifyPhaseViewChange();

			if (!riskGameModel.anyPlayerHasArmies()) {
				riskGameModel.setState(RiskGameModel.START_TURN);
				startTurn(false, riskGameModel);
			} else
				riskGameModel.nextPlayer();
		} else {
			Utility.writeLog("armies less than 0");
			riskGameModel.nextPlayer();

		}
		// return "";
	}

	/* (non-Javadoc)
	 * @see risk.model.interfaces.StrategyInterface#startTurn(boolean, risk.model.RiskGameModel)
	 */
	@Override
	public void startTurn(boolean isTest, RiskGameModel riskGameModel) {
		currentRiskModel = riskGameModel;
		/* Place all the armies randomly, and place the turn bonus too randomly */
		Utility.writeLog("START TURN - Some Random dude called - " + riskGameModel.curPlayer.getName()
				+ " has started his turn.");
		if (riskGameModel.curPlayer.getCard().size() >= 5) {
			tradeCards(riskGameModel);
		} else {
			reinforce(false, riskGameModel);
		}

	}


	/**
	 * Trade cards.
	 *
	 * @param riskGameModel the risk game model
	 * @return the string
	 */
	private void tradeCards(RiskGameModel riskGameModel) {
		int count = 0, cardCount = 0;
		riskGameModel.lstTradedCards = new ArrayList<RiskCardModel>();
		riskGameModel.setState(RiskGameModel.REINFORCE);
		ArrayList<RiskCardModel> cardsRemoved = new ArrayList<RiskCardModel>();
		Utility.writeLog("TRADE CARD - Some Random dude called - " + riskGameModel.curPlayer.getName()
				+ " decided to trade cards as he has 5 or more cards!");

		do {
			riskGameModel.curPlayer.setCards(riskGameModel.lstTradedCards);
			riskGameModel.lstTradedCards = new ArrayList<RiskCardModel>();
			while (cardCount < 3) {
				riskGameModel.lstTradedCards.add(riskGameModel.curPlayer.getCard()
						.remove(new java.util.Random().nextInt(riskGameModel.curPlayer.getCard().size())));
				cardCount++;
			}
		} while (!riskGameModel.isTradedCardSetValid());

		if (riskGameModel.doesCardMatchCurrentPlayerTerritory() > 0)
			count = RiskGameModel.fetchTradedArmiesCount() + 2;
		else
			count = RiskGameModel.fetchTradedArmiesCount();
		riskGameModel.curPlayer.setArmiesRecivedByTradingCards(count);
		riskGameModel.curPlayer.addArmies(count);
		riskGameModel.deck.addAll(riskGameModel.lstTradedCards);
		cardsRemoved.addAll(riskGameModel.lstTradedCards);
		riskGameModel.looseCard(cardsRemoved);
		riskGameModel.notifyPhaseViewChange();
		// return null;
	}

	/* (non-Javadoc)
	 * @see risk.model.interfaces.StrategyInterface#reinforce(boolean, risk.model.RiskGameModel, int[])
	 */
	@Override
	public void reinforce(boolean isTest, RiskGameModel riskGameModel, int... territory) {
		/*
		 * Randomly reinforce any random countries; assuming this time this player has
		 * armies to place recieved from start turn
		 */
		riskGameModel.setState(RiskGameModel.REINFORCE);
		riskGameModel.notifyPhaseViewChange();
		riskGameModel.curPlayer.addArmies(riskGameModel.currentPlayerBonusArmiesRecieved = riskGameModel.turnBonus());
		Utility.writeLog("REINFORCE - Some Random dude called - " + riskGameModel.curPlayer.getName() + " recieved "
				+ riskGameModel.currentPlayerBonusArmiesRecieved + " armies as turn bonus.");
		Utility.writeLog("REINFORCE - Some Random dude called - " + riskGameModel.curPlayer.getName()
				+ " decided to Reinforce his armies.");
		while (riskGameModel.curPlayer.getNumberOfArmies() > 0) {
			if (riskGameModel.curPlayer.getOccupiedTerritories().size() > 0) {
				RiskTerritoryModel reinforcedTerritory = riskGameModel.curPlayer.getOccupiedTerritories()
						.get(new java.util.Random().nextInt(riskGameModel.curPlayer.getOccupiedTerritories().size()));
				riskGameModel.occupyTerritory(reinforcedTerritory);
				Utility.writeLog("REINFORCE - Some Random dude called - " + riskGameModel.curPlayer.getName()
						+ " reinforced " + reinforcedTerritory.getName() + " army count is "
						+ riskGameModel.curPlayer.getNumberOfArmies());
			}
		}
		Utility.writeLog("REINFORCE - Some Random dude called - " + riskGameModel.curPlayer.getName()
				+ " is done with reinforcement and his army count is " + riskGameModel.curPlayer.getNumberOfArmies());
		riskGameModel.notifyPhaseViewChange();
		attack(false, riskGameModel);
	}

	/* (non-Javadoc)
	 * @see risk.model.interfaces.StrategyInterface#attack(boolean, risk.model.RiskGameModel, int[])
	 */
	@Override
	public void attack(boolean isTest, RiskGameModel riskGameModel, int... territory) {
		/*
		 * He will keep on attacking untill he has teeritories which armies > 1 and of
		 * which have others territories adjacent to him
		 */
		RiskTerritoryModel[] fighterTerritories;
		Utility.writeLog(
				"ATTACK - Some Random dude called - " + riskGameModel.curPlayer.getName() + " has option to attack.");
		riskGameModel.setState(RiskGameModel.ATTACK);
		while (shouldFireInTheHole()) {
			Utility.writeLog("ATTACK - Some Random dude called - " + riskGameModel.curPlayer.getName()
					+ " has decided to attack.");
			if ((fighterTerritories = getValidRandomAttackDefendTerritory(riskGameModel)) != null
					&& fighterTerritories.length > 0) {

				do {
					int attackArmies = (riskGameModel.aTerritory = fighterTerritories[0]).getArmies();
					int defenseArmies = (riskGameModel.defenseTerritory = fighterTerritories[1]).getArmies();

					riskGameModel.notifyPhaseViewChange();

					Utility.writeLog("ATTACK - Some Random dude called - " + riskGameModel.curPlayer.getName()
							+ " has warred against " + riskGameModel.defenseTerritory.getName()
							+ " using his territory - " + riskGameModel.aTerritory.getName());

					// Set the attack number of die
					if (riskGameModel.aTerritory.getArmies() > 3)
						riskGameModel.setAttack(new java.util.Random().nextInt(3) + 1);
					if (riskGameModel.aTerritory.getArmies() == 3)
						riskGameModel.setAttack(new java.util.Random().nextInt(2) + 1);
					if (riskGameModel.aTerritory.getArmies() == 2)
						riskGameModel.setAttack(1);

					// Set the defence number of die
					if (riskGameModel.defenseTerritory.getArmies() > 1 && riskGameModel.attackNum > 1)
						riskGameModel.setDefend(new java.util.Random().nextInt(2) + 1);
					else
						riskGameModel.setDefend(1);

					Utility.writeLog("ATTACK - Some Random dude " + riskGameModel.curPlayer.getName()
							+ " attack results as follows!!");
					Utility.writeLog("ATTACK is with " + riskGameModel.attackNum + " defence is with "
							+ riskGameModel.defenseNum + "dies.");
					riskGameModel.setState(RiskGameModel.ATTACK_PHASE);
					riskGameModel.engageBattle();

					if (defenseArmies - riskGameModel.defenseTerritory.getArmies() == 1) {
						Utility.writeLog(riskGameModel.curPlayer.getName() + " has destroyed an army");
					} else if (defenseArmies - riskGameModel.defenseTerritory.getArmies() == 2) {
						Utility.writeLog(riskGameModel.curPlayer.getName() + " has destroyed two armies");
					} else if (attackArmies - riskGameModel.aTerritory.getArmies() == 1) {
						;
						Utility.writeLog(riskGameModel.curPlayer.getName() + " has lost an army");
					} else if (attackArmies - riskGameModel.aTerritory.getArmies() == 2) {
						Utility.writeLog(riskGameModel.curPlayer.getName() + " has lost two armies");
					}
				} while (!(riskGameModel.getState() == RiskGameModel.DEFEATED
						|| riskGameModel.getState() == RiskGameModel.CAPTURE));

				if (riskGameModel.aTerritory.getArmies() == 1) {
					riskGameModel.setState(RiskGameModel.ACTIVE_TURN);
					Utility.writeLog(riskGameModel.curPlayer.getName() + " has lost the battle");
					riskGameModel.defenseNum = 0;
					riskGameModel.attackNum = 0;
					riskGameModel.defenseTerritory = null;
					riskGameModel.aTerritory = null;
				}

				if (riskGameModel.getState() == RiskGameModel.CAPTURE)
					if (capture(false, riskGameModel)) {
						endGame(riskGameModel);
						return;
					}
			}

		}
		Utility.writeLog("ATTACK - Some Random dude called - " + riskGameModel.curPlayer.getName()
				+ " could not attack/is done with attack/decided not to attack.");
		riskGameModel.notifyPhaseViewChange();
		fortify(false, currentRiskModel);
	}

	/**
	 * End game.
	 *
	 * @param riskGameModel the risk game model
	 */
	public void endGame(RiskGameModel riskGameModel) {
		Utility.writeLog("Thats all ya, " + riskGameModel.curPlayer.getName() + " won the game!!!");
		// System.exit(0);
	}

	/**
	 * Capture.
	 *
	 * @param isTest the is test
	 * @param riskGameModel the risk game model
	 * @return the boolean
	 */
	public Boolean capture(boolean isTest, RiskGameModel riskGameModel) {
		/*
		 * Write Logic for moving random armies after capture and then capture base code
		 * min = risk.attackNum?? why we used 0 to attackarmies -1
		 */
		Boolean isEndOfGame = false;
		riskGameModel.defenseNum = new java.util.Random().nextInt(riskGameModel.aTerritory.getArmies());

		Utility.writeLog("CAPTURE - Some Random dude called - " + riskGameModel.curPlayer.getName() + " moved "
				+ riskGameModel.defenseNum + " armies from " + riskGameModel.aTerritory.getName() + " to "
				+ riskGameModel.defenseTerritory.getName());

		riskGameModel.setAttackDieArray(new Integer[] { 0, 0, 0 });
		riskGameModel.setDefenceDieArray(new Integer[] { 0, 0, 0 });
		if (isEndOfGame = riskGameModel.capture()) {
			Utility.writeLog(riskGameModel.getCurrentPlayer().getName() + " has won the game using "
					+ riskGameModel.curPlayer.getStrategy().getClass().getName() + " strategy!");
			JOptionPane.showMessageDialog(null,
					riskGameModel.getCurrentPlayer().getName() + " has won the game with his "
							+ riskGameModel.curPlayer.getStrategy().getClass().getName() + " strategy!",
					"Alert", JOptionPane.INFORMATION_MESSAGE);
		}
		return isEndOfGame;
	}

	/**
	 * Should fire in the hole.
	 *
	 * @return true, if successful
	 */
	private boolean shouldFireInTheHole() {
		/* Randomly decide to proceed with another attack or not */
		for (RiskTerritoryModel selfOccupiedTerritory : currentRiskModel.curPlayer.getOccupiedTerritories()) {
			if (selfOccupiedTerritory.getArmies() > 1) {
				return (new java.util.Random().nextInt(2) == 0 ? false : true);
			}
		}
		return false;
	}

	/**
	 * Gets the random occupied territory by player.
	 *
	 * @param riskPlayer the risk player
	 * @return the random occupied territory by player
	 */
	private int getRandomOccupiedTerritoryByPlayer(RiskPlayerModel riskPlayer) {
		if (riskPlayer.getOccupiedTerritories().size() > 0)
			return riskPlayer.getOccupiedTerritories()
					.get(new java.util.Random().nextInt(riskPlayer.getOccupiedTerritories().size())).getId();
		return -1;
	}

	/**
	 * Gets the valid random attack defend territory.
	 *
	 * @param riskGameModel the risk game model
	 * @return the valid random attack defend territory
	 */
	private RiskTerritoryModel[] getValidRandomAttackDefendTerritory(RiskGameModel riskGameModel) {
		RiskTerritoryModel validDefenderTerritory;
		for (RiskTerritoryModel validAttackerTerritory : riskGameModel.curPlayer.getOccupiedTerritories()) {
			if (validAttackerTerritory.getArmies() > 1) {
				if ((validDefenderTerritory = getValidRandomDefender(validAttackerTerritory, riskGameModel)) != null) {
					riskGameModel.active = riskGameModel.curPlayer;
					riskGameModel.defender = validDefenderTerritory.getPlayer();
					return new RiskTerritoryModel[] { validAttackerTerritory, validDefenderTerritory };
				}
			}
		}
		return null;
	}

	/**
	 * Gets the valid random defender.
	 *
	 * @param validAttackerTerritory the valid attacker territory
	 * @param riskGameModel the risk game model
	 * @return the valid random defender
	 */
	private RiskTerritoryModel getValidRandomDefender(RiskTerritoryModel validAttackerTerritory,
			RiskGameModel riskGameModel) {
		for (int adjacent : validAttackerTerritory.getAdjacents()) {
			if (!riskGameModel.curPlayer.getOccupiedTerritories().contains(riskGameModel.getTerritoryAt(adjacent))) {
				return riskGameModel.getTerritoryAt(adjacent);
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see risk.model.interfaces.StrategyInterface#fortify(boolean, risk.model.RiskGameModel, int[])
	 */
	@Override
	public void fortify(boolean isTest, RiskGameModel riskGameModel, int... territory) {
		Utility.writeLog("FORTIFY - Some Random dude called - " + riskGameModel.curPlayer.getName()
				+ " has an option to Fortify any of his armies.");

		riskGameModel.setState(RiskGameModel.FORTIFY_PHASE);
		riskGameModel.notifyPhaseViewChange();
		ArrayList<RiskTerritoryModel> fortifiableTerritories = new ArrayList<RiskTerritoryModel>();
		RiskTerritoryModel fortifierTerritory, fortifiedTerritory;
		int fortifiedArmyCount;
		while (shouldFireInTheHole()) {
			fortifiableTerritories = new ArrayList<RiskTerritoryModel>();
			Utility.writeLog("FORTIFY - Some Random dude called - " + riskGameModel.curPlayer.getName()
					+ " has decided to Fortify.");
			for (RiskTerritoryModel viableTerritory : riskGameModel.curPlayer.getOccupiedTerritories()) {
				if (viableTerritory.getArmies() > 1 && hasSelfOccupiedAjacents(viableTerritory)) {
					fortifiableTerritories.add(viableTerritory);
				}
			}

			if (fortifiableTerritories.size() > 0) {
				fortifierTerritory = fortifiableTerritories
						.get(new java.util.Random().nextInt(fortifiableTerritories.size()));
				fortifiedTerritory = getFortifiedTerritory(fortifierTerritory);

				// int count = fortifierTerritory.getArmies() - 1; // e.g 5// 5-1 = 4
				fortifiedArmyCount = new java.util.Random().nextInt(fortifierTerritory.getArmies() - 1) + 1; // (0,1,2,3)
																												// + 1
				Utility.writeLog("FORTIFY - Some Random dude called - " + riskGameModel.curPlayer.getName()
						+ " fortified " + fortifiedTerritory.getName() + " through " + fortifierTerritory.getName()
						+ " by " + fortifiedArmyCount + " armie(s)."); // //
				fortifierTerritory.looseArmies(fortifiedArmyCount);
				fortifiedTerritory.addArmies(fortifiedArmyCount);
			}
		}
		Utility.writeLog("FORTIFY - Some Random dude called - " + riskGameModel.curPlayer.getName()
				+ " is done fortifying/cannot fortify any more/ choose not to and end his turn");
		riskGameModel.setState(RiskGameModel.START_TURN);
		riskGameModel.nextPlayer();
		riskGameModel.notifyPhaseViewChange();
		// return "";
	}

	/**
	 * Gets the fortified territory.
	 *
	 * @param fortifierTerritory the fortifier territory
	 * @return the fortified territory
	 */
	private RiskTerritoryModel getFortifiedTerritory(RiskTerritoryModel fortifierTerritory) {
		for (int territory : fortifierTerritory.getAdjacents()) {
			if (currentRiskModel.curPlayer.getOccupiedTerritories()
					.contains(currentRiskModel.getTerritoryAt(territory)))
				return currentRiskModel.getTerritoryAt(territory);
		}
		return null;
	}

	/**
	 * Checks for self occupied ajacents.
	 *
	 * @param viableTerritory the viable territory
	 * @return true, if successful
	 */
	private boolean hasSelfOccupiedAjacents(RiskTerritoryModel viableTerritory) {
		for (int territory : viableTerritory.getAdjacents()) {
			if (currentRiskModel.curPlayer.getOccupiedTerritories()
					.contains(currentRiskModel.getTerritoryAt(territory))) {
				return true;
			}
		}
		return false;
	}

}
