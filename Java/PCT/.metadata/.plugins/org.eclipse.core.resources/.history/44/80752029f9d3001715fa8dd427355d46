package risk.model.strategy;

import java.util.ArrayList;

import risk.helpers.Utility;
import risk.model.RiskCardModel;
import risk.model.RiskGameModel;
import risk.model.RiskPlayerModel;
import risk.model.RiskTerritoryModel;
import risk.model.interfaces.StrategyInterface;

public class Benevolent implements StrategyInterface {

	RiskGameModel currentRiskModel;

	@Override
	public void startTurn(boolean isTest, RiskGameModel riskGameModel) {
		currentRiskModel = riskGameModel;

		Utility.writeLog(
				"START TURN - benevolent BOT called - " + riskGameModel.curPlayer.getName() + " has started his turn.");
		if (riskGameModel.curPlayer.getCard().size() > 5) {
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
		Utility.writeLog("TRADE CARD -  benevolent BOT called - " + riskGameModel.curPlayer.getName()
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
	}

	@Override
	public void reinforce(boolean isTest, RiskGameModel riskGameModel, int... territory) {
		// TODO Auto-generated method stub
		riskGameModel.setState(RiskGameModel.REINFORCE);
		riskGameModel.notifyPhaseViewChange();
		riskGameModel.curPlayer.addArmies(riskGameModel.currentPlayerBonusArmiesRecieved = riskGameModel.turnBonus());
		Utility.writeLog("REINFORCE - Benevolent BOT called - " + riskGameModel.curPlayer.getName() + " recieved "
				+ riskGameModel.currentPlayerBonusArmiesRecieved + " armies as turn bonus.");
		Utility.writeLog("REINFORCE - Benevolent BOT called - " + riskGameModel.curPlayer.getName()
				+ " decided to Reinforce his armies.");

		if (riskGameModel.curPlayer.getNumberOfArmies() > 0) {
			int occupiedTerritory = getWeakTerritoryOccupiedByPlayer(riskGameModel.curPlayer);
			if (occupiedTerritory != -1) {
				riskGameModel.occupyTerritoryByPlayer(occupiedTerritory, riskGameModel.curPlayer);
				Utility.writeLog("REINFORCE - called - " + riskGameModel.curPlayer.getName()
						+ " placed one of his army on " + riskGameModel.getTerritoryAt(occupiedTerritory).getName());
			}
			// riskGameModel.notifyPhaseViewChange();
		}
		Utility.writeLog("REINFORCE - benevolent BOT called - " + riskGameModel.curPlayer.getName()
				+ " is done with reinforcement and his army count is " + riskGameModel.curPlayer.getNumberOfArmies());
		riskGameModel.notifyPhaseViewChange();
	}

	@Override
	public void attack(boolean isTest, RiskGameModel riskGameModel, int... territory) {
		// TODO Auto-generated method stub
		// the benevolent player not suppose to attack
		reinforce(isTest, riskGameModel, territory);
	}

	@Override
	public void fortify(boolean isTest, RiskGameModel riskGameModel, int... territory) {
		// TODO Auto-generated method stub
		Utility.writeLog("FORTIFY - Some Random dude called - " + riskGameModel.curPlayer.getName()
				+ " has an option to Fortify any of his armies.");

		riskGameModel.setState(RiskGameModel.FORTIFY_PHASE);
		riskGameModel.notifyPhaseViewChange();
		ArrayList<RiskTerritoryModel> fortifiableTerritories = new ArrayList<RiskTerritoryModel>();
		RiskTerritoryModel fortifierTerritory, fortifiedTerritory;
		int fortifiedArmyCount;
		
		Utility.writeLog("FORTIFY - Benevolent BOT called - " + riskGameModel.curPlayer.getName()
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

			// int count = fortifierTerritory.getArmies() - 1; // e.g 5// 5-1 =
			// 4
			fortifiedArmyCount = new java.util.Random().nextInt(fortifierTerritory.getArmies() - 1) + 1; // (0,1,2,3)
																											// +
																											// 1
			Utility.writeLog("FORTIFY - Benevolent BOT called - " + riskGameModel.curPlayer.getName() + " fortified "
					+ fortifiedTerritory.getName() + " through " + fortifierTerritory.getName() + " by "
					+ fortifiedArmyCount + " armie(s)."); // //
			fortifierTerritory.looseArmies(fortifiedArmyCount);
			fortifiedTerritory.addArmies(fortifiedArmyCount);
		}

		Utility.writeLog("FORTIFY - Benevolent BOT called - " + riskGameModel.curPlayer.getName()
				+ " is done fortifying/cannot fortify any more/ choose not to and end his turn");
		riskGameModel.setState(RiskGameModel.START_TURN);
		riskGameModel.nextPlayer();
		riskGameModel.notifyPhaseViewChange();
	}

	
	private RiskTerritoryModel getFortifiedTerritory(RiskTerritoryModel fortifierTerritory) {
		for (int territory : fortifierTerritory.getAdjacents()) {
			if (currentRiskModel.curPlayer.getOccupiedTerritories()
					.contains(currentRiskModel.getTerritoryAt(territory)))
				return currentRiskModel.getTerritoryAt(territory);
		}
		return null;
	}

	
	private boolean hasSelfOccupiedAjacents(RiskTerritoryModel viableTerritory) {
		for (int territory : viableTerritory.getAdjacents()) {
			if (currentRiskModel.curPlayer.getOccupiedTerritories()
					.contains(currentRiskModel.getTerritoryAt(territory))) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void initialReinforce(boolean isTest, RiskGameModel riskGameModel, int... territory) {
		if (riskGameModel.curPlayer.getNumberOfArmies() > 0) {
			int occupiedTerritory = getWeakTerritoryOccupiedByPlayer(riskGameModel.curPlayer);
			if (occupiedTerritory != -1) {
				riskGameModel.occupyTerritoryByPlayer(occupiedTerritory, riskGameModel.curPlayer);
				Utility.writeLog("INITIAL REINFORCE - called - " + riskGameModel.curPlayer.getName()
						+ " placed one of his army on " + riskGameModel.getTerritoryAt(occupiedTerritory).getName());
			}
			riskGameModel.notifyPhaseViewChange();

			if (!riskGameModel.anyPlayerHasArmies()) {
				riskGameModel.setState(RiskGameModel.START_TURN);
				startTurn(false, riskGameModel);
			} else
				riskGameModel.nextPlayer();
		} else
			riskGameModel.nextPlayer();
		// return "";
	}

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

	int getWeakTerritoryOccupiedByPlayer(RiskPlayerModel player) {
		RiskTerritoryModel territory, temp;

		if (player.getOccupiedTerritories().size() > 0) {
			territory = player.getOccupiedTerritories().elementAt(0);
			for (int i = 1; i < player.getOccupiedTerritories().size(); i++) {
				if (territory.getArmies() > player.getOccupiedTerritories().elementAt(i).getArmies())
					territory = player.getOccupiedTerritories().elementAt(i);
			}
			return territory.getId();
		}

		return -1;
	}
}
