package MotherPCT;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ParentGold {
	
	public static void main(String[] args) {
	
		List<String> inputarr = new ArrayList<String>();
		List<String> playerList = new ArrayList<String>();
		
		inputarr.add("D 2 0 D 0 2 T D 3 0");
		inputarr.add("T D 6 0 D 0 3 D 2 0");
		inputarr.add("D 0 -1 D 1 0 G D 0 -1");
		inputarr.add("D 0 3 D 0 2 D 0 2 D 0 3");
		inputarr.add("D -1 0 T D 0 -3 T");
		
		playerList.add("1 4 2 ");
		playerList.add("2 2 0");
		playerList.add("3 0 3"); 
		playerList.add("4 2 3");
		
		for (String s : playerList) {
			new Thread(new GoldHunt(inputarr, s)).start();
		}
		
	}
}
