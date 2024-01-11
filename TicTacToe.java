// In order to create my GUI application, I need the following imports:

import java.awt.*;  

// used to develop graphical user interface / windows based applications. 
// however, it is platform dependent, meaning that the console for which the user interacts will look and feel different across different OS. 

import java.util.*; 

import java.awt.event.*;

// provides interfaces and classes for dealing with different types of events fired by the AWT components. 
// events are user interactions with the interface (e.g a click on a button etc.)

import javax.swing.*;

// used to create window based applications, it is built on top of AWT. 
// better than AWT because platform independent, has more components and supports more powerful components. 




// ActionListener
// One of the easiest event handlers to implement, also most common. 
// You implement an action listener to define what should be done when an user performs certain operation.
// An action event occurs, whenever an action is performed by the user. Examples: When the user clicks a button, chooses a menu item, presses Enter in a text field. The result is that an actionPerformed message is sent to all action listeners that are registered on the relevant component.

public class TicTacToe implements ActionListener {
	
	
	// In order to make the game fair, we will randomly generate who begins the game, for this we will use the Random class. 
	Random rdm = new Random();
	
	
	// JFrame is a class which is a child class of java.awt.Frame
	// JFrame allows us to create main window where components like labels, buttons, panels and etc. can be added to the GUI. 
	// Unlike Frame, JFrame has the option to hide or close the frame with the setDefaultCloseOperation();
	JFrame frame = new JFrame();
	
	
	// JPanel extends JComponent and implements Acessible
	// It does not have a title bar and is used to create a new JPanel with a double buffer and a flow layout. 
	JPanel titlepanel = new JPanel();
	
	JPanel buttonpanel = new JPanel();
	
	// JLabel is used to place text in a container, so inside the pannel. 
	JLabel textfield = new JLabel();
	
	// Creates a labeled button which results in some action when pushed. 
	JButton[] buttons = new JButton[9]; // 3X3 = 9
	
	// this is a boolean variable which evaluates whether or not it is player1's (the starting player's) turn. 
	boolean player1;

	
	// Default constructor 
	public TicTacToe() {
		
		// This line sets the default close operation, here, it ensures that the exit application default 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// sets the size of the frame where the content will appear. I chose 1000x1000 so that it appears widely on the screen. 
		frame.setSize(1000, 1000);
		
		// Here, we are setting the color of the background of the panel which will appear when we run the code, I have chosen black. 
		frame.getContentPane().setBackground(Color.BLACK);
		
		// Creates a border with specific gaps between the components so that we can create the boxes. 
		frame.setLayout(new BorderLayout());
		
		// Here, we are setting the frame visibility to true so that the frame can actually appear on screen. 
		frame.setVisible(true);
		
		
	// With all the code above, we have handled everything pertaining to the frame, so now we must handle the textfield aspect. 
		
		// Background of the text is black. 
		textfield.setBackground(Color.BLACK);
		
		// Setting the text to white. 
		textfield.setForeground(new Color(255, 255, 255));
		
		// Choosing the font, then making it bold and choosing it's size. I also aligned it and made the text opaque. 
		textfield.setFont(new Font("Ink Free", Font.BOLD, 90));
		textfield.setHorizontalAlignment(JLabel.CENTER);
		textfield.setText("Tic-Tac-Toe");
		textfield.setOpaque(true);
		
		// Set the layout of the title panel. 
		titlepanel.setLayout(new BorderLayout());
		titlepanel.setBounds(0,0,800, 100);
		
		// Creating the grid, which allows us to represent the grid we make when playing Tic Tac Toe. 
		// Sets the background color to black, so that it seamlessly blends with the title panel to give a more esthetically pleasing look. 
		buttonpanel.setLayout(new GridLayout(3,3)); 
		buttonpanel.setBackground(Color.BLACK);
		
		// As we are dealing with an array of JButtons, a for loop will help us run through these buttons and manipulate them. 
		for (int i=0; i < 9; i++) {
			
			// For every index, we create a new button using the JButtons constructor. 
			// We then add this button to the buttonspanel so that the user can interact with it on the panel. 
			buttons[i] = new JButton();
			buttonpanel.add(buttons[i]);
			
			// Here, we set the font of the buttons. 
			buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
			
			
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this);
		}
		
		
		
		// Here, we are adding the text to the title panel, and then adding the title panel to the frame we have created so that it can all come together. 
		// After this, we add the buttonpanel to the frame. 
		titlepanel.add(textfield);
		frame.add(titlepanel, BorderLayout.NORTH);
		frame.add(buttonpanel);
		
		
		// Then, at the end of the constructor, we call the first turn method, to determine which player will play first. 
		firstTurn();
	}

	
	// To utilize the ActionListener interface, we need to ensure that we use define the methods within the interface itself:
	// Here, we have defined the method actionPerformed from the interface and overridden it. 
	// This method is the only method in the interface and is invoked when an event is fired. An event is fired when the user clicks an on-screen button, the button fires an action event, and this is registered by using an object which implements the ActionListener interface. 
	
	
	// 
	public void actionPerformed(ActionEvent e) {
		
		
		// 
		for (int i=0; i<9; i++) {
			if (e.getSource()==buttons[i]) {
				if(player1) {
					if(buttons[i].getText()=="") {
						buttons[i].setForeground(new Color(255, 0, 0));
						buttons[i].setText("X");
						player1 = false;
						textfield.setText("O turn");
						check();
						
						
					}
				}
				
				else {
					
					// 
					if(buttons[i].getText()=="") {
						buttons[i].setForeground(new Color(0, 0, 255));
						buttons[i].setText("O");
						player1 = true;
						textfield.setText("X turn");
						check();
					}
				}
			}
		}
		
	}
	
	// Here, we are creating a method to deal with the first turn of the game. 
	
	public void firstTurn() {
		
		
		try {
			
			// This statement pauses the main thread execution for 2000 milliseconds so 2 seconds.
			Thread.sleep(2000);
		}
		
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		
		// Here, it identifies whether the next value generated by the random variable will be 0, which will represent X's turn, if not then we know it is Os turn. 
		// Note that the player1 variable represents whether or not it is the player who is represented by X's turn. 
		if (rdm.nextInt(2)==0) {
			player1 = true;
			textfield.setText("X turn");
		}
		
		else {
			player1 = false;
			textfield.setText("O turn");
		}
		
		
		
		
		
		
		
	}
	
	
	// We also need a method to check if any of the users have won:
	public void check() {
		
		// X win conditions:
		
		// Horizontal (left to right)
		// In all these conditions, it identifies whether there are three consecutive X's in a row.
		if (buttons[0].getText()=="X" && buttons[1].getText()=="X" && buttons[2].getText()=="X") {
			xWins(0, 1, 2);
		}
		
		if (buttons[3].getText()=="X" && buttons[4].getText()=="X" && buttons[5].getText()=="X") {
			xWins(3, 4, 5);
		}
		
		if (buttons[6].getText()=="X" && buttons[7].getText()=="X" && buttons[8].getText()=="X") {
			xWins(6, 7, 8);
		}
		
		
		// Vertical (top to bottom)
		// This does the same as above but does so by checking whether there are three consecutive X's in a column.
		if (buttons[0].getText()=="X" && buttons[3].getText()=="X" && buttons[6].getText()=="X") {
			xWins(0, 3, 6);
		}
		
		if (buttons[1].getText()=="X" && buttons[4].getText()=="X" && buttons[7].getText()=="X") {
			xWins(1, 4, 7);
		}
		
		if (buttons[2].getText()=="X" && buttons[5].getText()=="X" && buttons[8].getText()=="X") {
			xWins(2, 5, 8);
		}
		
		// One corner to another corner (across)
		// Checks if they are present across. 
		if (buttons[0].getText()=="X" && buttons[4].getText()=="X" && buttons[8].getText()=="X") {
			xWins(0, 4, 8);
		}
		
		if (buttons[2].getText()=="X" && buttons[4].getText()=="X" && buttons[6].getText()=="X") {
			xWins(2, 4, 6);
		}
		
		// O win conditions:
		// Horizontal (left to right)
		// In all these conditions, it identifies whether there are three consecutive X's in a row.
		
				if (buttons[0].getText()=="O" && buttons[1].getText()=="O" && buttons[2].getText()=="O") {
					OWins(0, 1, 2);
				}
				
				if (buttons[3].getText()=="O" && buttons[4].getText()=="O" && buttons[5].getText()=="O") {
					OWins(3, 4, 5);
				}
				
				if (buttons[6].getText()=="O" && buttons[7].getText()=="O" && buttons[8].getText()=="O") {
					OWins(6, 7, 8);
				}
				
				
				// Vertical (top to bottom)
				// This does the same as above but does so by checking whether there are three consecutive X's in a column.
				if (buttons[0].getText()=="O" && buttons[3].getText()=="O" && buttons[6].getText()=="O") {
					OWins(0, 3, 6);
				}
				
				if (buttons[1].getText()=="O" && buttons[4].getText()=="O" && buttons[7].getText()=="O") {
					OWins(1, 4, 7);
				}
				
				if (buttons[2].getText()=="O" && buttons[5].getText()=="O" && buttons[8].getText()=="O") {
					OWins(2, 5, 8);
				}
				
				
				// One corner to another corner (across)
				// Checks if they are present across. 
				if (buttons[0].getText()=="O" && buttons[4].getText()=="O" && buttons[8].getText()=="O") {
					OWins(0, 4, 8);
				}
				
				if (buttons[2].getText()=="O" && buttons[4].getText()=="O" && buttons[6].getText()=="O") {
					OWins(2, 4, 6);
				}
				
				
				
				
				
		
	}
	
	// We will also create two separate methods, one if X wins and one if O wins:
	// This will light up the boxes which have the winning conditions in green. 
	public void xWins(int x, int y, int z) {
		buttons[x].setBackground(Color.GREEN);
		buttons[y].setBackground(Color.GREEN);
		buttons[z].setBackground(Color.GREEN);
		
		
		
		for (int i=0; i < 9; i++) {
			buttons[i].setEnabled(false);
		}
		
		
		textfield.setText("X wins!");
	}
	
	// We will also create two separate methods, one if X wins and one if O wins:
	// This will light up the boxes which have the winning conditions in green.
	public void OWins(int x, int y, int z) {
		buttons[x].setBackground(Color.GREEN);
		buttons[y].setBackground(Color.GREEN);
		buttons[z].setBackground(Color.GREEN);
		
		
		
		for (int i=0; i < 9; i++) {
			buttons[i].setEnabled(false);
		}
		
		
		textfield.setText("O wins!");
	}
}
