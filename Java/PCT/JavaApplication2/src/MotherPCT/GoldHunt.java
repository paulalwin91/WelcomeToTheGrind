package MotherPCT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GoldHunt implements Runnable{
	static List<String> arrar = new ArrayList<String>();
	static String inptA = "";
	static List<String> plyrList = new ArrayList<String>();
	static int rowSize = 5;
	static int colSize = 4;
	static int currentply;
	static List<String> inputarr = new ArrayList<String>();
	static String currentPlayer = "";
	
	public GoldHunt(List<String> first,String Player) {
		inputarr = first;
		currentPlayer = Player;
	}

	//public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//	//	String input = br.readLine();
//		List<String> inputarr = new ArrayList<String>();
////		int rowSize = Integer.valueOf(input.split(" ")[0]);
////		int colSize = Integer.valueOf(input.split(" ")[1]);
//		// arrar = new String[rowSize *colSize];
//
////		for (int i = 0; i < rowSize; i++) {
////			inputarr.add(br.readLine());
////		}
//		
////		inputarr.add("T D 1 0 T");
////		inputarr.add("D 1 1 G D 0 -1");
////		inputarr.add("T D 1 1 T");
////		
////		plyrList.add("1 0 1");
////		plyrList.add("2 1 2");
////		plyrList.add("3 1 1"); 
////		plyrList.add("4 2 1"); 
////		
//		inputarr.add("D 2 0 D 0 2 T D 3 0");
//		inputarr.add("T D 6 0 D 0 3 D 2 0");
//		inputarr.add("D 0 -1 D 1 0 G D 0 -1");
//		inputarr.add("D 0 3 D 0 2 D 0 2 D 0 3");
//		inputarr.add("D -1 0 T D 0 -3 T");
//		
//		plyrList.add("1 4 2 ");
//		plyrList.add("4 2 3");
//		plyrList.add("2 2 0");
//		plyrList.add("3 0 3"); 
//		
//		
//		
//
//	//	int playerC = Integer.valueOf(br.readLine());
//
////		for (int i = 0; i < playerC; i++) {
////			plyrList.add(br.readLine());
////		}
//		
//		new Thread().start();
//		for (String s : plyrList) {
//			currentply = Integer.valueOf(s.split(" ")[0]);
//			gameplay(s, "",currentply);
//		}

	
		
	//}

	private static void makeDD(List<String> inputarr) {
		int index = 0;
		for (String sss : inputarr) {
			inptA = inptA + sss + " ";
		}

		for (int i = 0; i < inptA.split(" ").length; i++) {
			String curre = inptA.split(" ")[i];
			if (curre.equals("D")) {
				String one = inptA.split(" ")[i + 1];
				String one1 = inptA.split(" ")[i + 2];
				arrar.add(curre + " " + one + " " + one1);
				i = i + 2;
			} else
				arrar.add(curre);
		}
	}

	private static void gameplay(String play, String previous, int currentply2) {
		int index = getIndex(play, previous);
		if (arrar.get(index).equals("G")) {
			System.out.println(currentply2);
			System.exit(0);
		}
		if (arrar.get(index).equals("T"))
			return;

		gameplay(arrar.get(index), play,currentply2);
	}

	private static int getIndex(String play, String previous) {
		if (previous.equals(""))
			return Integer.valueOf(play.split(" ")[1]) * colSize + Integer.valueOf(play.split(" ")[2]);
		else {
			int CR = Integer.valueOf(play.split(" ")[1]);
			int CL = Integer.valueOf(play.split(" ")[2]);
			int PR = Integer.valueOf(previous.split(" ")[1]);
			int PL = Integer.valueOf(previous.split(" ")[2]);

			int R = Math.abs(CR) - Math.abs(PR);
			int C = Math.abs(CL) - Math.abs(PL);

			if ((R < rowSize - 1 && R > 0) && (C < colSize && C > 0))
				return R * colSize + C;

			if (R > rowSize - 1 || R < 0) {
				R = Math.abs(Math.abs(R) - rowSize);
			}
			if (C > colSize - 1 || C < 0) {
				C = Math.abs(Math.abs(C) - colSize);
			}

			return R * colSize + C;
		}
	}

	@Override
	public void run() {

		makeDD(inputarr);
		currentply = Integer.valueOf(currentPlayer.split(" ")[0]);
		gameplay(currentPlayer, "",currentply);
	}

}
