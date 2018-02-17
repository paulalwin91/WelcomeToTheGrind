package risk.model.strategy;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import risk.helpers.Utility;
import risk.model.RiskGameModel;
import risk.model.RiskPlayerModel;
import risk.model.RiskTerritoryModel;
import risk.model.interfaces.StrategyInterface;

public class Cheater implements StrategyInterface {

	RiskGameModel currentRiskModel;

	@Override
	public String takeTurn(boolean isTest, RiskGameModel riskGameModel) {
		Utility.writeLog("***********" + riskGameModel.curPlayer.getName() + " turn *************");
		currentRiskModel = riskGameModel;
		if (riskGameModel.getState() == RiskGameModel.INITIAL_REINFORCE)
			initialReinforce(false, riskGameModel);
		if (riskGameModel.getState() == RiskGameModel.START_TURN
				|| riskGameModel.getState() == RiskGameModel.ACTIVE_TURN)
			startTurn(false, riskGameModel);

		riskGameModel.mainPanel.repaint();
		riskGameModel.subPanel.repaint();

		return "";
	}

	@Override
	public String initialReinforce(boolean isTest, RiskGameModel riskGameModel, int... territory) {
		if (riskGameModel.curPlayer.getNumberOfArmies() > 0) {
			int occupiedTerritory = getRandomOccupiedTerritoryByPlayer(riskGameModel.curPlayer);
			if (occupiedTerritory != -1) {
				riskGameModel.occupyTerritoryByPlayer(occupiedTerritory, riskGameModel.curPlayer);
				Utility.writeLog("INITIAL REINFORCE - The cheater called - " + riskGameModel.curPlayer.getName()
						+ " placed 'one' of his army on " + riskGameModel.getTerritoryAt(occupiedTerritory).getName());
			}
			riskGameModel.notifyPhaseViewChange();

			if (!riskGameModel.anyPlayerHasArmies()) {
				riskGameModel.setState(RiskGameModel.START_TURN);
				startTurn(false, riskGameModel);
			} else
				riskGameModel.nextPlayer();
		} else
			riskGameModel.nextPlayer();
		return "";
	}

	@Override
	public String startTurn(boolean isTest, RiskGameModel riskGameModel) {
		/* Place all the armies randomly, and place the turn bonus too randomly */
		Utility.writeLog(
				"START TURN - Some Cheater called - " + riskGameModel.curPlayer.getName() + " has started his turn.");
		if (riskGameModel.curPlayer.getCard().size() > (new java.util.Random().nextInt(5) + 1)) { // 1,2,3,4,5
			return tradeCards(riskGameModel);
		} else {
			return reinforce(false, riskGameModel);
		}

	}

	private String tradeCards(RiskGameModel riskGameModel) {
		int count = 0;
		riskGameModel.setState(RiskGameModel.REINFORCE);
		Utility.writeLog("TRADE CARD - Some Cheater called - " + riskGameModel.curPlayer.getName()
				+ " decided to trade cards as he has " + riskGameModel.curPlayer.getCard().size() + " cards!");
		count = RiskGameModel.fetchTradedArmiesCount();
		riskGameModel.lstTradedCards = riskGameModel.curPlayer.getCard().subList(0,
				new Random().nextInt(riskGameModel.curPlayer.getCard().size()));
		riskGameModel.looseCard(riskGameModel.lstTradedCards);
		// riskGameModel.curPlayer.removeCard(riskGameModel.lstTradedCards);
		riskGameModel.curPlayer.setArmiesRecivedByTradingCards(count);
		riskGameModel.curPlayer.addArmies(count);
		riskGameModel.notifyPhaseViewChange();
		return reinforce(false, riskGameModel);
	}

	@Override
	public String reinforce(boolean isTest, RiskGameModel riskGameModel, int... territory) {
		/*
		 * Randomly reinforce any random countries; assuming this time this player has
		 * armies to place recieved from start turn
		 */
		riskGameModel.setState(RiskGameModel.REINFORCE);
		riskGameModel.notifyPhaseViewChange();
		riskGameModel.curPlayer.addArmies(riskGameModel.currentPlayerBonusArmiesRecieved = riskGameModel.turnBonus());

		Utility.writeLog("REINFORCE - Some Cheater called - " + riskGameModel.curPlayer.getName() + " recieved "
				+ riskGameModel.currentPlayerBonusArmiesRecieved + " armies as turn bonus.");
		Utility.writeLog("REINFORCE - Some Cheater called - " + riskGameModel.curPlayer.getName()
				+ " decided to Reinforce his armies.");
		// Double the army count in all of its territories
		for (RiskTerritoryModel cheaterTerritory : riskGameModel.curPlayer.getOccupiedTerritories()) {
			cheaterTerritory.addArmies(cheaterTerritory.getArmies());
		}

		while (riskGameModel.curPlayer.getNumberOfArmies() > 0) {
			if (riskGameModel.curPlayer.getOccupiedTerritories().size() > 0) {
				RiskTerritoryModel reinforcedTerritory = riskGameModel.curPlayer.getOccupiedTerritories()
						.get(new java.util.Random().nextInt(riskGameModel.curPlayer.getOccupiedTerritories().size()));
				riskGameModel.occupyTerritory(reinforcedTerritory);
				Utility.writeLog("REINFORCE - Some Cheater called - " + riskGameModel.curPlayer.getName()
						+ " reinforced " + reinforcedTerritory.getName() + " army count is "
						+ riskGameModel.curPlayer.getNumberOfArmies());
			}
		}
		Utility.writeLog("REINFORCE - Some Cheater called - " + riskGameModel.curPlayer.getName()
				+ " is done with reinforcement and his army count is " + riskGameModel.curPlayer.getNumberOfArmies());
		riskGameModel.notifyPhaseViewChange();
		return attack(false, riskGameModel);
	}

	@Override
	public String attack(boolean isTest, RiskGameModel riskGameModel, int... territory) {
		/*
		 * He will keep on attacking untill he has teeritories which armies > 1 and of
		 * which have others territories adjacent to him
		 */
		Boolean endGame = false;
		Vector<RiskTerritoryModel> cheaterOccupiedTerritories = new Vector<RiskTerritoryModel>();
		cheaterOccupiedTerritories.addAll(riskGameModel.curPlayer.getOccupiedTerritories());
		Utility.writeLog(
				"ATTACK - Some Cheater called - " + riskGameModel.curPlayer.getName() + " has option to attack.");
		riskGameModel.setState(RiskGameModel.ATTACK);

		for (RiskTerritoryModel cheaterTerritory : cheaterOccupiedTerritories) {
			if (endGame = cheaterCaptureAdjacent(cheaterTerritory))
				break;
		}

		if (!endGame) {
			Utility.writeLog("ATTACK - Some Cheater dude called - " + riskGameModel.curPlayer.getName()
					+ " is done with attack.");
			riskGameModel.notifyPhaseViewChange();
			return fortify(false, currentRiskModel);
		} else {
			endGame(riskGameModel);
			return "";
		}
	}

	private Boolean cheaterCaptureAdjacent(RiskTerritoryModel cheaterTerritory) {

		for (int adjacentTerritoryId : cheaterTerritory.getAdjacents()) {
			if (!currentRiskModel.getTerritoryAt(adjacentTerritoryId).getPlayer().equals(currentRiskModel.curPlayer)) {
				RiskTerritoryModel dTerritory = currentRiskModel.getTerritoryAt(adjacentTerritoryId);
				currentRiskModel.defender = dTerritory.getPlayer();
				currentRiskModel.active = currentRiskModel.curPlayer;
				currentRiskModel.defender.looseTerritory(dTerritory);
				currentRiskModel.active.occupyTerritory(dTerritory);

				Utility.writeLog("ATTACK - Some Cheater dude called - " + currentRiskModel.curPlayer.getName()
						+ " captured " + dTerritory.getName() + " through " + cheaterTerritory.getName());

				if (currentRiskModel.defender.getOccupiedTerritories().size() == 0) {
					Utility.writeLog(currentRiskModel.defender.getName() + " lost the game.");
					currentRiskModel.removePlayer(currentRiskModel.defender);
					if (RiskGameModel.players.size() == 1) {
						Utility.writeLog(currentRiskModel.active.getName() + " has won the game");
						currentRiskModel.notifyPhaseViewChange();
						return true;
					}
				}
				currentRiskModel.drawCard(currentRiskModel.curPlayer);
			}
		}
		currentRiskModel.notifyPhaseViewChange();
		return false;
	}

	public void endGame(RiskGameModel riskGameModel) {
		Utility.writeLog("Thats all ya, " + riskGameModel.curPlayer.getName() + " won the game with his "
				+ riskGameModel.curPlayer.getStrategy().getClass().getName() + " strategy!!!!");
		JOptionPane.showMessageDialog(new JFrame("The End"),
				riskGameModel.getCurrentPlayer().getName() + " has won the game with his "
						+ riskGameModel.curPlayer.getStrategy().getClass().getName() + " strategy!",
				"Alert", JOptionPane.INFORMATION_MESSAGE);
	}

	private int getRandomOccupiedTerritoryByPlayer(RiskPlayerModel riskPlayer) {
		if (riskPlayer.getOccupiedTerritories().size() > 0)
			return riskPlayer.getOccupiedTerritories()
					.get(new java.util.Random().nextInt(riskPlayer.getOccupiedTerritories().size())).getId();
		return -1;
	}

	@Override
	public String fortify(boolean isTest, RiskGameModel riskGameModel, int... territory) {
		Utility.writeLog("FORTIFY - Some Cheater dude called - " + riskGameModel.curPlayer.getName()
				+ " has an option to Fortify any of his armies.");

		riskGameModel.setState(RiskGameModel.FORTIFY_PHASE);
		riskGameModel.notifyPhaseViewChange();
		for (RiskTerritoryModel cheaterFortifiableTeeritory : getFortifiableTerritories(riskGameModel)) {
			cheaterFortifiableTeeritory.addArmies(cheaterFortifiableTeeritory.getArmies());
			riskGameModel.notifyPhaseViewChange();
		}
		Utility.writeLog(
				"FORTIFY - Some Cheater dude called - " + riskGameModel.curPlayer.getName() + " is done fortifying");
		riskGameModel.setState(RiskGameModel.START_TURN);
		riskGameModel.nextPlayer();
		riskGameModel.notifyPhaseViewChange();
		return "";
	}

	private ArrayList<RiskTerritoryModel> getFortifiableTerritories(RiskGameModel riskGameModel) {
		ArrayList<RiskTerritoryModel> fortifiableTerritories = new ArrayList<RiskTerritoryModel>();
		for (RiskTerritoryModel riskTerritory : riskGameModel.curPlayer.getOccupiedTerritories()) {
			for (int adjacentTerritory : riskTerritory.getAdjacents()) {
				if (!riskGameModel.getTerritoryAt(adjacentTerritory).getPlayer().equals(riskGameModel.curPlayer)) {
					if (!fortifiableTerritories.contains(riskGameModel.getTerritoryAt(adjacentTerritory))) {
						fortifiableTerritories.add(riskGameModel.getTerritoryAt(adjacentTerritory));
					}
				}
			}
		}
		return fortifiableTerritories;
	}

}
