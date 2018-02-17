import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DriveMe extends JFrame{

	public DriveMe()
	{
		initUI();
	}
	
	public void initUI()
	{
		JButton jbtn1 = new JButton("Clicker");
		JButton jbtn2 = new JButton("Inner");
		
		  
		
		jbtn1.addActionListener((ActionEvent act) -> {
	    
//JFrame jf = new JFrame();
//
//				JPanel Jpln = new JPanel();
//				Jpln.add(jbtn2);
//				Jpln.setVisible(true);
//	           getContentPane().add(BorderLayout.CENTER, Jpln);
//	           jf.add(Jpln);
			
			  JButton incrButton = new JButton("Increment");
	          JButton quitButton = new JButton("Quit");
	          JTextField numberField = new JTextField();
	  
	          JFrame frame = new JFrame("Button Example");
	          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	          Container contents = frame.getContentPane();
	          contents.add(incrButton, BorderLayout.NORTH);
	          contents.add(numberField, BorderLayout.CENTER);
	          contents.add(quitButton, BorderLayout.SOUTH);
	          frame.pack();
	          frame.setVisible(true);
			
	           
					});
		
		
		
		
		
		whatchaAdding(jbtn1);
		setTitle("Simple example");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);        
	}
	
	public void whatchaAdding(JComponent... arg)
	{
		Container pane = getContentPane();
		GroupLayout gl = new GroupLayout(pane);
		
		pane.setLayout(gl);
		gl.setAutoCreateContainerGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
        );
		
	}
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(() -> {
			DriveMe dr = new DriveMe();
			dr.setVisible(true);			
		});
		
	}
}
