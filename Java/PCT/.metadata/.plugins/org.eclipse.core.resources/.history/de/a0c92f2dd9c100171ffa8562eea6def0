package risk.view;
//A specific Observer to observe ClockTimerModel: DigitalClockView

//

import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import risk.model.interfaces.PhaseViewInterface;

public class RiskPhaseViewObserver implements Observer {

	JPanel phaseViewPanel;
	JTextArea phaseViewTextArea;
	JFrame phaseViewFrame;
	StringBuilder phaseViewTextAreaString = new StringBuilder();
	private static RiskPhaseViewObserver instance = new RiskPhaseViewObserver();

	private RiskPhaseViewObserver() {
	}

	public static RiskPhaseViewObserver getInstance() {
		if (instance == null)
			instance = new RiskPhaseViewObserver();
		return instance;
	}

	public void generatePhaseView() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height * 2 / 3;
		int width = screenSize.width * 2 / 3;
		JScrollPane scrollPane;
		
		phaseViewFrame = new JFrame();
		phaseViewFrame.setTitle("Phase View");
		phaseViewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		phaseViewFrame.setPreferredSize(new Dimension(width, height));
		phaseViewPanel = new JPanel();
		phaseViewTextArea = new JTextArea("", 40, 40);
		phaseViewTextArea.setEditable(false);

		scrollPane = new JScrollPane(phaseViewTextArea);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		phaseViewPanel.add(scrollPane);
		
		phaseViewFrame.add(phaseViewPanel);
		phaseViewFrame.setResizable(false);
		phaseViewFrame.pack();
		phaseViewFrame.setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		phaseViewTextAreaString.append(((PhaseViewInterface) o).getContent());
		phaseViewTextArea.setText(phaseViewTextAreaString.toString());
		phaseViewFrame.repaint();
	}

}
