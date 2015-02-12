

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class FunctionPanel extends JPanel implements ChangeListener{

	//properties
	private JTextField mass, XVelocity, YVelocity, X,Y;
	private JLabel massL,XVelocityL,YVelocityL, XL,YL,elasticityL, checkBoxL, backgroundL;
	private JButton reset,addBall,pause,play;
	private BallPanel ballPanel;
	private JPanel btnPanel,textFieldPanel,elasticityPanel,checkBoxP,backgroundP;
	private JSlider elasticity;
	private JCheckBox velDirection, ballTrack;
	private JRadioButton yellow, cyan, white;
	private static final int FPS_MIN = 0;
	private static final int FPS_MAX = 10;
	private static final int FPS_INIT = 5; 
	public FunctionPanel()
	{
		setLayout(new BorderLayout());
		//initializing
//		ballPanel = new BallPanel();
		
		
		btnPanel = new JPanel();
		btnPanel.setLayout(new GridLayout(9,1));
		
		textFieldPanel = new JPanel();
		textFieldPanel.setLayout(new GridLayout(5,2));
		
		elasticityPanel = new JPanel();
		elasticityPanel.setLayout(new GridLayout(2,1));
		
		mass = new JTextField(3);
		massL = new JLabel("Mass");
		textFieldPanel.add(massL);
		textFieldPanel.add(mass);
		
		XVelocity = new JTextField(3);
		XVelocityL = new JLabel("X velocity");
		textFieldPanel.add(XVelocityL);
		textFieldPanel.add(XVelocity);
		
		YVelocity = new JTextField(3);
		YVelocityL = new JLabel("Y velocity");
		textFieldPanel.add(YVelocityL);
		textFieldPanel.add(YVelocity);
		
		X= new JTextField(3);
		XL = new JLabel("X position");
		textFieldPanel.add(XL);
		textFieldPanel.add(X);
		
		Y= new JTextField(3);
		YL = new JLabel("Y position");
		textFieldPanel.add(YL);
		textFieldPanel.add(Y);
		textFieldPanel.setBorder(BorderFactory.createLineBorder(Color.cyan, 3));
		
		reset = new JButton("Reset");
		addBall = new JButton("Add Ball");
		pause = new JButton("Pause");
		play = new JButton("Play");
		
		  

		elasticity = new JSlider(JSlider.HORIZONTAL, FPS_MIN, FPS_MAX, FPS_INIT);
		elasticity.setMajorTickSpacing(1);
		elasticity.setMinorTickSpacing(0);
		elasticity.setPaintTicks(true);
		elasticity.setPaintLabels(true);
		
        Hashtable labelTable = new Hashtable();
        labelTable.put( new Integer( 0 ), new JLabel("Inelastic") );
        labelTable.put( new Integer( FPS_MAX ), new JLabel("Elastic") );
        elasticity.setLabelTable( labelTable );
        elasticity.addChangeListener(this);
        
        elasticityL = new JLabel ("	elasticity of collision:"	 );
        elasticityPanel.add(elasticityL);
        elasticityPanel.add(elasticity);
        elasticityPanel.setBorder(BorderFactory.createLineBorder(Color.cyan,3));
        
        
        
        checkBoxL = new JLabel ("show:");
        velDirection = new JCheckBox("velocity vector");
        velDirection.addItemListener(new VelDirectionItemListener());
        ballTrack = new JCheckBox ("track of balls");
        ballTrack.addItemListener(new BallTrackItemListener());
        
        checkBoxP = new JPanel();
        checkBoxP.setLayout(new GridLayout(3,1));
        checkBoxP.add(checkBoxL);
        checkBoxP.add(velDirection);
        checkBoxP.add(ballTrack);
        checkBoxP.setBorder(BorderFactory.createMatteBorder(1, 3, 3, 3, Color.cyan));
        
        backgroundL = new JLabel ("choose background: ");
        yellow = new JRadioButton ("Yellow");
        yellow.addActionListener(new colorChooser());
        cyan = new JRadioButton ("Cyan");
        cyan.addActionListener(new colorChooser());
        white = new JRadioButton ("White");
        white.addActionListener(new colorChooser());
        
        backgroundP = new JPanel ();
        backgroundP.setLayout(new GridLayout(4,1));
        backgroundP.add(backgroundL);
        backgroundP.add(yellow);
        backgroundP.add(cyan);
        backgroundP.add(white);
        backgroundP.setBorder(BorderFactory.createMatteBorder(1, 3, 3, 3, Color.cyan));
        
        ButtonGroup group = new ButtonGroup();
        group.add(yellow);
        group.add(white);
        group.add(cyan);
        
        ballPanel = new BallPanel ((double)elasticity.getValue()/10);
        
		//wiring up
		reset.addActionListener(new MyActionListener());
		addBall.addActionListener(new MyActionListener());
		pause.addActionListener(new MyActionListener());
		play.addActionListener(new MyActionListener());
		
		//adding the properties to panel
		btnPanel.add(textFieldPanel);
		btnPanel.add(addBall);
		btnPanel.add(pause);
		btnPanel.add(play);
		btnPanel.add(reset);
		btnPanel.add(elasticityPanel);
		btnPanel.add(checkBoxP);
		btnPanel.add(backgroundP);
	
		add(ballPanel, BorderLayout.CENTER);
		add(btnPanel,BorderLayout.EAST);
	}
	
	public class MyActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			double x,y, m, vx,vy;
		
			if(e.getSource() == addBall)
			{
				try{
					m= Double.parseDouble(mass.getText());
				}catch(NumberFormatException error){
					m = 0;
				}
				
				try{
					vx = Double.parseDouble(XVelocity.getText());
				}catch(NumberFormatException error){
					vx = 0;
				}
				
				try{
					vy = Double.parseDouble(YVelocity.getText());
				}catch(NumberFormatException error){
					vy = 0;
				}
				
				try{
					x = Double.parseDouble(X.getText());
				}catch(NumberFormatException error){
					x = 0;
				}
				
				try{
					y = Double.parseDouble(Y.getText());
				}catch(NumberFormatException error){
					y = 0;
				}
				
				ballPanel.addBall(new Ball(m,new Vector(x,y), new Vector(vx,vy)));
				repaint();
			}
			
			if(e.getSource() == pause)
				ballPanel.pauseTimer();
			
			if (e.getSource() == play)
				ballPanel.playTimer();
			
			if(e.getSource() == reset){
				ballPanel.resetTimer((double)elasticity.getValue()/10);
				velDirection.setSelected(false);
				ballTrack.setSelected(false);
				repaint();
			}
		}
		
		
	
}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		ballPanel.getCollision().setCOR((double)elasticity.getValue()/10);
//		System.out.println ((double)framesPerSecond.getValue()/10);
	}

	public class BallTrackItemListener implements ItemListener{ 
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
	
		if (ballTrack.isSelected())
		{
			for(Ball ball:ballPanel.getBalls())
				ball.setBallTrack(true);
		}
		
		if (!ballTrack.isSelected())
		{
			for(Ball ball:ballPanel.getBalls())
				ball.setBallTrack(false);
		}
	}
	}
	
	public class VelDirectionItemListener implements ItemListener
	{

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if(velDirection.isSelected())
			{
				for(Ball ball:ballPanel.getBalls())
					ball.setVelDirection(true);
			}
			
			if (!velDirection.isSelected()){
				for(Ball ball:ballPanel.getBalls())
					ball.setVelDirection(false);
			}
			
		}
		
	}
	
	public class colorChooser implements ActionListener{
	
	
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object source = e.getSource();
			
			if (source == yellow)
				ballPanel.setBackground1(Color.YELLOW);
			else 
				if (source == white)
					ballPanel.setBackground1(Color.WHITE);
				else
					ballPanel.setBackground1(Color.CYAN);
		}
}}
