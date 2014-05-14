

import javax.swing.JFrame;

public class Main {
	
	private static JFrame frame;

	public static void main(String[] args) {
		//FRAME OF PROGRAM
		frame = new JFrame("Physics Simulator");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		
		FunctionPanel function = new FunctionPanel();
		frame.getContentPane().add(function);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	//To be able to change panel in the frame
	public static JFrame getFrame(){
		return frame;
	}
}
