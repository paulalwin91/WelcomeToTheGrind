package risk.model.strategy;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import risk.helpers.Utility;
import risk.model.RiskCardModel;
import risk.model.RiskGameModel;
import risk.model.RiskPlayerModel;
import risk.model.RiskTerritoryModel;
import risk.model.interfaces.StrategyInterface;

public class Aggressive implements StrategyInterface {


	RiskTerritoryModel currentStrongestTerritory;

	@Override
	public void takeTurn(boolean isTest, RiskGameModel riskGameModel) {
		Utility.writeGameStats(riskGameModel);
		Utility.writeLog("***********" + riskGameModel.curPlayer.getName() + " turn *************");
		//riskGameModel = riskGameModel;
		if (riskGameModel.getState() == RiskGameModel.INITIAL_REINFORCE)
			initialReinforce(false, riskGameModel);
		if (riskGameModel.getState() == RiskGameModel.START_TURN
				|| riskGameModel.getState() == RiskGameModel.ACTIVE_TURN)
			startTurn(false, riskGameModel);

		riskGameModel.mainPanel.repaint();
		riskGameModel.subPanel.repaint();
		Utility.writeGameStats(riskGameModel);
		//return "";
	}

	@Override
	public void initialReinforce(boolean isTest, RiskGameModel riskGameModel, int... territory) {
		if (riskGameModel.curPlayer.getNumberOfArmies() > 0) {

			if (currentStrongestTerritory == null) {
				currentStrongestTerritory = makeStrongestOccupiedTerritory(riskGameModel.curPlayer, riskGameModel);
				if (currentStrongestTerritory == null) {
					endGame(riskGameModel);
					return;
				}
			}

			riskGameModel.occupyTerritoryByPlayer(currentStrongestTerritory.getId(), riskGameModel.curPlayer);
			Utility.writeLog("INITIAL REINFORCE - Some Aggressive dude called - " + riskGameModel.curPlayer.getName()
					+ " placed one of his army on "
					+ riskGameModel.getTerritoryAt(currentStrongestTerritory.getId()).getName());
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
		//return "";
	}

	@Override
	public void startTurn(boolean isTest, RiskGameModel riskGameModel) {
		riskGameModel = riskGameModel;
		/* Place all the armies randomly, and place the turn bonus too randomly */
		Utility.writeLog("START TURN - Some Random dude called - " + riskGameModel.curPlayer.getName()
				+ " has started his turn.");
		if (riskGameModel.curPlayer.getCard().size() >= 5) {
			 tradeCards(riskGameModel);
		} else {
			 reinforce(false, riskGameModel);
		}
	}

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
		//return null;
	}

	@Override
	public void reinforce(boolean isTest, RiskGameModel riskGameModel, int... territory) {
		/*
		 * Randomly reinforce any random countries; assuming this time this player has
		 * armies to place recieved from start turn
		 */
		riskGameModel.setState(RiskGameModel.REINFORCE);
		riskGameModel.notifyPhaseViewChange();
		riskGameModel.curPlayer.addArmies(riskGameModel.currentPlayerBonusArmiesRecieved = riskGameModel.turnBonus());
		Utility.writeLog("REINFORCE - Some Aggressive dude called - " + riskGameModel.curPlayer.getName() + " recieved "
				+ riskGameModel.currentPlayerBonusArmiesRecieved + " armies as turn bonus.");
		Utility.writeLog("REINFORCE - Some Aggressive dude called - " + riskGameModel.curPlayer.getName()
				+ " decided to Reinforce his armies.");
		while (riskGameModel.curPlayer.getNumberOfArmies() > 0) {

			if (currentStrongestTerritory == null) {
				currentStrongestTerritory = makeStrongestOccupiedTerritory(riskGameModel.curPlayer, riskGameModel);
				if (currentStrongestTerritory == null) {
					endGame(riskGameModel);
					return;
				}
			}

			riskGameModel.occupyTerritoryByPlayer(currentStrongestTerritory.getId(), riskGameModel.curPlayer);
			Utility.writeLog("REINFORCE - Some Aggressive dude called - " + riskGameModel.curPlayer.getName()
					+ " reinforced " + currentStrongestTerritory.getName() + " army count is "
					+ riskGameModel.curPlayer.getNumberOfArmies() + " and territory army count is "
					+ currentStrongestTerritory.getArmies());

		}
		Utility.writeLog("REINFORCE - Some Aggressive dude called - " + riskGameModel.curPlayer.getName()
				+ " is done with reinforcement and his army count is " + riskGameModel.curPlayer.getNumberOfArmies());
		riskGameModel.notifyPhaseViewChange();
		 attack(false, riskGameModel);
	}

	@Override
	public void attack(boolean isTest, RiskGameModel riskGameModel, int... territory) {
		/*
		 * He will keep on attacking untill he has teeritories which armies > 1 and of
		 * which have others territories adjacent to him
		 */
		RiskTerritoryModel[] fighterTerritories;
		Utility.writeLog("ATTACK - Some Aggressive dude called - " + riskGameModel.curPlayer.getName()
				+ " has option to attack.");
		riskGameModel.setState(RiskGameModel.ATTACK);
		riskGameModel.notifyPhaseViewChange();
		while (shouldFireInTheHole(riskGameModel)) {
			Utility.writeLog("ATTACK - Some Aggressive dude called - " + riskGameModel.curPlayer.getName()
					+ " has decided to attack.");
			if ((fighterTerritories = getValidAttackDefendTerritory(riskGameModel)) != null
					&& fighterTerritories.length > 0) {

				int attackArmies = (riskGameModel.aTerritory = fighterTerritories[0]).getArmies();
				int defenseArmies = (riskGameModel.defenseTerritory = fighterTerritories[1]).getArmies();

				Utility.writeLog("ATTACK - Some Aggressive dude called - " + riskGameModel.curPlayer.getName()
						+ " has warred against " + riskGameModel.defenseTerritory.getName() + " using his territory - "
						+ riskGameModel.aTerritory.getName());

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

				Utility.writeLog("ATTACK - Some Aggressive dude " + riskGameModel.curPlayer.getName()
						+ " attack results as follows!!");
				Utility.writeLog("ATTACK is with " + riskGameModel.attackNum + " defence is with "
						+ riskGameModel.defenseNum + "dies.");

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
				riskGameModel.defender = null;
			}

		}
		Utility.writeLog("ATTACK - Some Aggressive dude called - " + riskGameModel.curPlayer.getName()
				+ " could not attack/is done with attack/decided not to attack.");
		riskGameModel.notifyPhaseViewChange();
		 fortify(false, riskGameModel);
	}

	public void endGame(RiskGameModel riskGameModel) {
		Utility.writeLog("Thats all ya, " + riskGameModel.curPlayer.getName() + " won the game!!!");
		JOptionPane.showMessageDialog(null,
				riskGameModel.getCurrentPlayer().getName() + " has won the game with his "
						+ riskGameModel.curPlayer.getStrategy().getClass().getName() + " strategy!",
				"Alert", JOptionPane.INFORMATION_MESSAGE);
	}

	public Boolean capture(boolean isTest, RiskGameModel riskGameModel) {
		/*
		 * Write Logic for moving random armies after capture and then capture base code
		 * min = risk.attackNum?? why we used 0 to attackarmies -1
		 */
		Boolean isEndOfGame = false;
		riskGameModel.defenseNum = new java.util.Random().nextInt(riskGameModel.aTerritory.getArmies());

		Utility.writeLog("CAPTURE - Some Aggressive dude called - " + riskGameModel.curPlayer.getName() + " moved "
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

	private boolean shouldFireInTheHole(RiskGameModel riskGameModel) {
		/* Aggressively decide to proceed with another attack or not */
		if (currentStrongestTerritory != null && currentStrongestTerritory.getArmies() > 1) {
			for (int adjacents : currentStrongestTerritory.getAdjacents()) {
				if (!riskGameModel.getTerritoryAt(adjacents).getPlayer().equals(riskGameModel.curPlayer)) {
					return true;
				}
			}
		}
		currentStrongestTerritory = null;
		return false;
	}

	private RiskTerritoryModel makeStrongestOccupiedTerritory(RiskPlayerModel riskPlayer,RiskGameModel riskGameModel) {
		for (RiskTerritoryModel viableAggressiveTerritory : riskPlayer.getOccupiedTerritories()) {
			for (int adjacents : viableAggressiveTerritory.getAdjacents()) {
				if (!riskGameModel.getTerritoryAt(adjacents).getPlayer().equals(riskGameModel.curPlayer)) {
					currentStrongestTerritory = riskGameModel.getTerritoryAt(adjacents);
					break;
				}
			}
		}
		return currentStrongestTerritory;
	}

	private RiskTerritoryModel getStrongestOccupiedTerritoryWithEnimies(RiskPlayerModel riskPlayer,RiskGameModel riskGameModel) {
		for (RiskTerritoryModel viableAggressiveTerritory : riskPlayer.getOccupiedTerritories()) {
			if(viableAggressiveTerritory.getArmies() > 1)
			for (int adjacents : viableAggressiveTerritory.getAdjacents()) {
				if (!riskGameModel.getTerritoryAt(adjacents).getPlayer().equals(riskGameModel.curPlayer)) {
					currentStrongestTerritory = viableAggressiveTerritory;
					break;
				}
			}
		}
		return currentStrongestTerritory;
	}

	private RiskTerritoryModel[] getValidAttackDefendTerritory(RiskGameModel riskGameModel) {
		// Check strongest country is still good to attack
		RiskTerritoryModel validDefenderTerritory = null;
		if (currentStrongestTerritory.getArmies() <= 1) {
			currentStrongestTerritory = getStrongestOccupiedTerritoryWithEnimies(riskGameModel.getCurPlayer(),riskGameModel);
		}

		for (int defender : currentStrongestTerritory.getAdjacents()) {
			if (!riskGameModel.getTerritoryAt(defender).getPlayer().equals(riskGameModel.curPlayer)) {
				validDefenderTerritory = riskGameModel.getTerritoryAt(defender);
				riskGameModel.active = riskGameModel.curPlayer;
				riskGameModel.defender = validDefenderTerritory.getPlayer();
				break;
			}
		}

		return (new RiskTerritoryModel[] { currentStrongestTerritory, validDefenderTerritory });
	}

	@Override
	public void fortify(boolean isTest, RiskGameModel riskGameModel, int... territory) {
		Utility.writeLog("FORTIFY - Some Aggressive dude called - " + riskGameModel.curPlayer.getName()
				+ " has an option to Fortify any of his armies.");

		riskGameModel.setState(RiskGameModel.FORTIFY_PHASE);
		riskGameModel.notifyPhaseViewChange();

		if (currentStrongestTerritory == null) {
			currentStrongestTerritory = makeStrongestOccupiedTerritory(riskGameModel.curPlayer,riskGameModel);
			if (currentStrongestTerritory == null) {
				endGame(riskGameModel);
				return;
			}
		}

		for (int selfOccupiedAdjacent : currentStrongestTerritory.getAdjacents()) {
			if (riskGameModel.getTerritoryAt(selfOccupiedAdjacent).getPlayer().equals(riskGameModel.curPlayer)) {
				int fortifiedArmyCount = riskGameModel.getTerritoryAt(selfOccupiedAdjacent).getArmies() - 1;
				currentStrongestTerritory.addArmies(fortifiedArmyCount);
				riskGameModel.getTerritoryAt(selfOccupiedAdjacent).looseArmies(fortifiedArmyCount);

				Utility.writeLog("FORTIFY - Some Aggressive dude called - " + riskGameModel.curPlayer.getName()
						+ " fortified " + currentStrongestTerritory.getName() + " through "
						+ riskGameModel.getTerritoryAt(selfOccupiedAdjacent).getName() + " by " + fortifiedArmyCount
						+ " armie(s). And Strongest Teeritory Army count is now -"
						+ currentStrongestTerritory.getArmies()); // //
			}
		}

		Utility.writeLog("FORTIFY - Some Aggressive dude called - " + riskGameModel.curPlayer.getName()
				+ " is done fortifying/cannot fortify any more/ choose not to and end his turn");
		riskGameModel.setState(RiskGameModel.START_TURN);
		riskGameModel.nextPlayer();
		riskGameModel.notifyPhaseViewChange();
		//return "";
	}
}
