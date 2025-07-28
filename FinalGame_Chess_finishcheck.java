//Name: Kumar Sharma
//Date: January 15, 2025
//Purpose: Chess Final Code
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.Applet;

public class FinalGame_Chess_finishcheck extends Applet implements ActionListener
{
    // Label to display the current turn as an image
    JLabel turnpic;

    // Label for spacing or decorative purposes
    JLabel pixel;

    // Character to track the current player's turn ('b' for black, 'w' for white)
    char turn = 'b';

    // Reset button to restart the game
    JButton reset;

    // Resign button to allow a player to forfeit
    JButton resign;

    // Buttons for instructions and home navigation
    JButton Instrust; // Button to display instructions
    JButton Home;     // Button to navigate to the home screen

    // Buttons for introduction screens
    JButton intro_1;
    JButton intro_2;

    // Play button for screens 4 and 5
    JButton play;

    // Boolean to track if the game is in check state
    boolean Check;

    // Character to store the opponent's color based on the current player's turn
    char opponent = 'o';

    // Variables to store the king's position; initialized to placeholder values
    int KRow = 0; // King's row position
    int KCol = 0; // King's column position

    // Row and column differences to calculate distance from the king
    int rowDiff = 0;
    int colDiff = 0;

    // Character to store the currently selected piece of the opponent
    char current_piece;

    // Integer to store the last position of the piece; initialized to -1 as placeholder
    int last = -1;

    // Panel to hold all screens (menu, game, etc.)
    Panel p_card;

    // Individual panels for each screen (e.g., intro, game, instructions)
    Panel card1, card2, card3, card4, card5, card7, card8;

    // Layout manager to switch between screens
    CardLayout cdLayout = new CardLayout ();

    // Grid dimensions for the chessboard
    int row = 8;
    int col = 8;

    // Array to hold all buttons representing the chessboard squares
    JButton a[] = new JButton [row * col];

    // 2D array to represent the pieces on the chessboard
    // Initial setup includes black pieces ('r', 'n', 'b', 'k', 'q', 'p'),
    // white pieces ('r', 'n', 'b', 'k', 'q', 'p'), and empty squares ('x').
    char piece[] [] = {
	    {'r', 'n', 'b', 'k', 'q', 'b', 'n', 'r'},
	    {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
	    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
	    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
	    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
	    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
	    {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
	    {'r', 'n', 'b', 'k', 'q', 'b', 'n', 'r'}
	};

    // 2D array to track the selection state of each square ('u' for unselected)
    char select[] [] = {{'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}, {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},
	    {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}, {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},
	    {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}, {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},
	    {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}, {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}};

    // 2D array to track the color of the pieces ('w' for white, 'b' for black)

    char colour[] [] = {{'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w'}, {'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w'},
	    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'}, {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
	    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'}, {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
	    {'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'}, {'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'}};
    // 2D array to represent the background color of each square ('b' or 'w')
    char bg[] [] = {
	    {'b', 'w', 'b', 'w', 'b', 'w', 'b', 'w'},
	    {'w', 'b', 'w', 'b', 'w', 'b', 'w', 'b'},
	    {'b', 'w', 'b', 'w', 'b', 'w', 'b', 'w'},
	    {'w', 'b', 'w', 'b', 'w', 'b', 'w', 'b'},
	    {'b', 'w', 'b', 'w', 'b', 'w', 'b', 'w'},
	    {'w', 'b', 'w', 'b', 'w', 'b', 'w', 'b'},
	    {'b', 'w', 'b', 'w', 'b', 'w', 'b', 'w'},
	    {'w', 'b', 'w', 'b', 'w', 'b', 'w', 'b'}
	};

    //---------------------------------------------------------------------------------------------------------------
    // Reset Board Arrays
    // These arrays represent the default setup of the chessboard
    //---------------------------------------------------------------------------------------------------------------Reset Board Array----------------------------------------------------------
    char resetpiece[] [] = {{'r', 'n', 'b', 'k', 'q', 'b', 'n', 'r'}, {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
	    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'}, {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
	    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'}, {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
	    {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'}, {'r', 'n', 'b', 'k', 'q', 'b', 'n', 'r'}};

    char resetselect[] [] = {{'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}, {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},
	    {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}, {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},
	    {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}, {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},
	    {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}, {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}};

    char resetcolour[] [] = {{'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w'}, {'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w'},
	    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'}, {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
	    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'}, {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
	    {'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'}, {'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'}};

    char resetbg[] [] = {{'b', 'w', 'b', 'w', 'b', 'w', 'b', 'w'}, {'w', 'b', 'w', 'b', 'w', 'b', 'w', 'b'},
	    {'b', 'w', 'b', 'w', 'b', 'w', 'b', 'w'}, {'w', 'b', 'w', 'b', 'w', 'b', 'w', 'b'},
	    {'b', 'w', 'b', 'w', 'b', 'w', 'b', 'w'}, {'w', 'b', 'w', 'b', 'w', 'b', 'w', 'b'},
	    {'b', 'w', 'b', 'w', 'b', 'w', 'b', 'w'}, {'w', 'b', 'w', 'b', 'w', 'b', 'w', 'b'}};


    public void init ()
    {
	// Main panel to hold all screens
	p_card = new Panel ();
	p_card.setLayout (cdLayout); // CardLayout is used to switch between screens

	// Initialize all screens
	screen1 ();
	screen2 ();
	screen3 ();
	screen4 ();
	screen5 ();
	screen7 ();
	screen8 ();

	// Set the dimensions of the window
	resize (650, 780);
	setLayout (new BorderLayout ());
	add ("Center", p_card); // Add the card layout to the center of the window
    }


    //-----------------------------------------Welcome Screen
    public void screen1 ()
    {
	// Screen 1 setup - Welcome Screen
	card1 = new Panel ();
	card1.setBackground (new Color (84, 0, 50)); // Set background color

	// Button to navigate to Screen 2
	JButton next = new JButton (createImageIcon ("marvel.png"));
	next.setActionCommand ("s2"); // Assign command for ActionListener
	next.addActionListener (this);
	next.setPreferredSize (new Dimension (650, 790)); // Set button size
	next.setBorder (null); // Remove button border
	card1.add (next); // Add button to the panel
	p_card.add ("1", card1); // Add the panel to CardLayout with identifier "1"
    }


    //---------------------------------------------------Instrusction screens
    public void screen2 ()
    {
	// Screen 2 setup - Instrusction screen
	card2 = new Panel ();
	card2.setBackground (new Color (84, 0, 50)); // Set background color

	// Button to navigate to Screen 3
	JButton next = new JButton (createImageIcon ("In.png"));
	next.setActionCommand ("s3"); // Assign command for ActionListener
	next.addActionListener (this);
	next.setBorder (null); // Remove button border
	card2.add (next); // Add button to the panel
	p_card.add ("2", card2); // Add the panel to CardLayout with identifier "2"
    }


    public void screen3 ()
    {
	// Screen 3 setup - Second Instrusction screen
	card3 = new Panel ();
	card3.setBackground (new Color (84, 0, 50)); // Set background color

	// Add title to the screen
	JLabel title = new JLabel ("Marvel Rivals");
	title.setFont (new Font ("Comic Sans MS", Font.BOLD, 30)); // Set font and style
	title.setForeground (new Color (255, 255, 255)); // Set text color

	// Add a 1-pixel image placeholder
	pixel = new JLabel (createImageIcon ("1pixel.png"));

	// Add turn title
	JLabel t_title = new JLabel ("Current Turn:");
	t_title.setFont (new Font ("Comic Sans MS", Font.BOLD, 20));
	t_title.setForeground (new Color (255, 255, 255));

	// Add an image for the current turn
	turnpic = new JLabel (createImageIcon ("kbbu.png"));

	// Add reset button
	reset = new JButton ("Reset");
	reset.setActionCommand ("reset");
	reset.addActionListener (this);
	reset.setFont (new Font ("Comic Sans MS", Font.BOLD, 16));
	reset.setBackground (new Color (255, 255, 255)); // Set button color
	reset.setBorder (null);

	// Add resign button
	resign = new JButton ("Resign?");
	resign.setActionCommand ("resign");
	resign.addActionListener (this);
	resign.setFont (new Font ("Comic Sans MS", Font.BOLD, 16));
	resign.setBackground (new Color (255, 255, 255));
	resign.setBorder (null);

	// Add instructions button
	Instrust = new JButton ("Instructions");
	Instrust.setActionCommand ("Instrust");
	Instrust.addActionListener (this);
	Instrust.setFont (new Font ("Comic Sans MS", Font.BOLD, 16));
	Instrust.setBackground (new Color (255, 255, 255));
	Instrust.setBorder (null);

	// Add main menu button
	Home = new JButton ("Main Menu");
	Home.setActionCommand ("Menu");
	Home.addActionListener (this);
	Home.setFont (new Font ("Comic Sans MS", Font.BOLD, 16));
	Home.setBackground (new Color (255, 255, 255));
	Home.setBorder (null);

	// Create a grid for game layout
	Panel p = new Panel (new GridLayout (row, col));
	int move = 0;
	for (int i = 0 ; i < row ; i++)
	{
	    for (int j = 0 ; j < col ; j++)
	    {
		a [move] = new JButton (createImageIcon (piece [i] [j] + "" + colour [i] [j] + "" + bg [i] [j] + "" + select [i] [j] + ".png"));
		a [move].setPreferredSize (new Dimension (75, 75)); // Set button size
		a [move].addActionListener (this);
		a [move].setActionCommand ("" + move); // Assign action command
		p.add (a [move]); // Add button to the grid panel
		move++;
	    }
	}

	// Add all components to the screen
	card3.add (title);
	card3.add (pixel);
	card3.add (t_title);
	card3.add (turnpic);
	card3.add (p); // Add the grid panel
	card3.add (reset);
	card3.add (resign);
	card3.add (Instrust);
	card3.add (Home);
	p_card.add ("3", card3); // Add the panel to CardLayout with identifier "3"
    }


    //---------------------------------------------------------------Black resigns, White wins Screen:
    public void screen4 ()
    {
	// Screen 4 is set up
	card4 = new Panel ();
	card4.setBackground (new Color (84, 0, 50)); // Set background color

	// Button to indicate white wins (displaying "Wwins.png" image)
	play = new JButton (createImageIcon ("Wwins.png"));
	play.setActionCommand ("S4"); // Action command to navigate or reset
	play.addActionListener (this); // Add ActionListener for functionality
	play.setBorder (null); // Remove button border
	card4.add (play); // Add button to the panel
	p_card.add ("4", card4); // Add panel to the CardLayout with identifier "4"
    }


    //-----------------------------------------------------White resigns, Black wins Screen:
    public void screen5 ()
    {
	// Screen 5 is set up
	card5 = new Panel ();
	card5.setBackground (new Color (84, 0, 50)); // Set background color

	// Button to indicate black wins (displaying "Bwin.png" image)
	play = new JButton (createImageIcon ("Bwin.png"));
	play.setActionCommand ("S5"); // Action command to navigate or reset
	play.addActionListener (this); // Add ActionListener for functionality
	play.setBorder (null); // Remove button border
	card5.add (play); // Add button to the panel
	p_card.add ("5", card5); // Add panel to the CardLayout with identifier "5"
    }


    public void screen7 ()
    {
	// Screen 7 is set up
	card7 = new Panel ();
	card7.setBackground (new Color (84, 0, 50)); // Set background color

	// Button to display an introductory image ("Marvel_intro_1.png")
	intro_1 = new JButton (createImageIcon ("Marvel_intro_1.png"));
	intro_1.setActionCommand ("S7"); // Action command to proceed
	intro_1.addActionListener (this); // Add ActionListener for functionality
	intro_1.setBorder (null); // Remove button border
	card7.add (intro_1); // Add button to the panel
	p_card.add ("7", card7); // Add panel to the CardLayout with identifier "7"
    }


    public void screen8 ()
    {
	// Screen 8 is set up
	card8 = new Panel ();
	card8.setBackground (new Color (84, 0, 50)); // Set background color

	// Button to display another introductory image ("Marvel_intro_2.png")
	intro_2 = new JButton (createImageIcon ("Marvel_intro_2.png"));
	intro_2.setActionCommand ("S8"); // Action command to proceed
	intro_2.addActionListener (this); // Add ActionListener for functionality
	intro_2.setBorder (null); // Remove button border
	card8.add (intro_2); // Add button to the panel
	p_card.add ("8", card8); // Add panel to the CardLayout with identifier "8"
    }


    //  --------------------------------------------------------------------------------------------Create Image Icon---------------------------------------------------------------------------
    // Helper method to create an ImageIcon from a given file path
    protected static ImageIcon createImageIcon (String path)
    {
	// Change the red text to your class name
	java.net.URL imgURL = FinalGame_Chess.class.getResource (path);
	if (imgURL != null)
	{
	    return new ImageIcon (imgURL); // Return the created ImageIcon
	}
	else
	{
	    System.err.println ("Couldn't find file: " + path); // Print an error if the file isn't found
	    return null; // Return null if the file doesn't exist
	}
    }


    // -----------------------------------------------------------------------------------Redraw--------------------------------------------------------------------------------------------------
    // Redraw the board by updating the icons of all squares
    public void redraw ()
    {
	int move = 0; // Index to track button position
	for (int i = 0 ; i < row ; i++)
	{
	    for (int j = 0 ; j < col ; j++)
	    {
		// Update each button's icon based on the current game state
		a [move].setIcon (createImageIcon (piece [i] [j] + "" + colour [i] [j] + "" + bg [i] [j] + "" + select [i] [j] + ".png"));
		move++; // Increment the button index
	    }
	}
    }



    ///---------------------------------------------------------------------------------Chess piece Pawn movement---------------------------------------------------------------------------------
    public void selectPawn (int x, int y)
    {
	// Black pawn's initial double-step forward move
	if (x - 2 >= 0 && colour [x] [y] == 'b' && x == 6 && colour [x - 1] [y] == 'x' && colour [x - 2] [y] == 'x')
	{
	    select [x - 1] [y] = 's'; // Mark the square one step forward as selectable
	    select [x - 2] [y] = 's'; // Mark the square two steps forward as selectable
	}
	// White pawn's initial double-step forward move
	else if (x + 2 < row && colour [x] [y] == 'w' && x == 1 && colour [x + 1] [y] == 'x' && colour [x + 2] [y] == 'x')
	{
	    select [x + 1] [y] = 's'; // Mark the square one step forward as selectable
	    select [x + 2] [y] = 's'; // Mark the square two steps forward as selectable
	}
	// Black pawn's single-step forward move
	else if (x - 1 >= 0 && colour [x] [y] == 'b' && colour [x - 1] [y] == 'x')
	{
	    select [x - 1] [y] = 's'; // Mark the square one step forward as selectable
	}
	// White pawn's single-step forward move
	else if (x + 1 < row && colour [x] [y] == 'w' && colour [x + 1] [y] == 'x')
	{
	    select [x + 1] [y] = 's'; // Mark the square one step forward as selectable
	}

	//----------------------------------Kill condition for pawns (attacking diagonally)
	// Black pawn attacking diagonally right
	if (x - 1 >= 0 && y + 1 < col && colour [x] [y] == 'b' && colour [x - 1] [y + 1] == 'w')
	{
	    select [x - 1] [y + 1] = 's'; // Mark the target square as selectable
	}
	// Black pawn attacking diagonally left
	if (x - 1 >= 0 && y - 1 >= 0 && colour [x] [y] == 'b' && colour [x - 1] [y - 1] == 'w')
	{
	    select [x - 1] [y - 1] = 's'; // Mark the target square as selectable
	}
	// White pawn attacking diagonally right
	if (x + 1 < row && y + 1 < col && colour [x] [y] == 'w' && colour [x + 1] [y + 1] == 'b')
	{
	    select [x + 1] [y + 1] = 's'; // Mark the target square as selectable
	}
	// White pawn attacking diagonally left
	if (x + 1 < row && y - 1 >= 0 && colour [x] [y] == 'w' && colour [x + 1] [y - 1] == 'b')
	{
	    select [x + 1] [y - 1] = 's'; // Mark the target square as selectable
	}
    }


    //-----------------------------------------------------------------------------------------Chess piece King movement------------------------------------------------------------------------
    public void selectKing (int x, int y)
    {
	// Check all adjacent squares for valid moves
	if (x - 1 >= 0 && y + 1 < col && colour [x - 1] [y + 1] != turn)
	    select [x - 1] [y + 1] = 's'; // Top-right
	if (x - 1 >= 0 && colour [x - 1] [y] != turn)
	    select [x - 1] [y] = 's'; // Top
	if (x - 1 >= 0 && y - 1 >= 0 && colour [x - 1] [y - 1] != turn)
	    select [x - 1] [y - 1] = 's'; // Top-left
	if (y - 1 >= 0 && colour [x] [y - 1] != turn)
	    select [x] [y - 1] = 's'; // Left
	if (y + 1 < col && colour [x] [y + 1] != turn)
	    select [x] [y + 1] = 's'; // Right
	if (x + 1 < row && y + 1 < col && colour [x + 1] [y + 1] != turn)
	    select [x + 1] [y + 1] = 's'; // Bottom-right
	if (x + 1 < row && colour [x + 1] [y] != turn)
	    select [x + 1] [y] = 's'; // Bottom
	if (x + 1 < row && y - 1 >= 0 && colour [x + 1] [y - 1] != turn)
	    select [x + 1] [y - 1] = 's'; // Bottom-left

	// Check for castling opportunities
	CastleK (x, y);
    }


    //------------------------------------------------------------------------------------------------Chess piece Knight movement------------------------------------------------------------------
    public void selectKnight (int x, int y)
    {
	// Check all possible L-shaped moves for the knight
	if (x - 2 >= 0 && y + 1 < col && colour [x - 2] [y + 1] != turn)
	    select [x - 2] [y + 1] = 's'; // Up-left
	if (x - 2 >= 0 && y - 1 >= 0 && colour [x - 2] [y - 1] != turn)
	    select [x - 2] [y - 1] = 's'; // Up-right
	if (x - 1 >= 0 && y + 2 < col && colour [x - 1] [y + 2] != turn)
	    select [x - 1] [y + 2] = 's'; // Left-up
	if (x - 1 >= 0 && y - 2 >= 0 && colour [x - 1] [y - 2] != turn)
	    select [x - 1] [y - 2] = 's'; // Right-up
	if (x + 2 < row && y + 1 < col && colour [x + 2] [y + 1] != turn)
	    select [x + 2] [y + 1] = 's'; // Down-left
	if (x + 2 < row && y - 1 >= 0 && colour [x + 2] [y - 1] != turn)
	    select [x + 2] [y - 1] = 's'; // Down-right
	if (x + 1 < row && y + 2 < col && colour [x + 1] [y + 2] != turn)
	    if (x + 1 < row && y - 2 >= 0 && colour [x + 1] [y - 2] != turn)
		select [x + 1] [y - 2] = 's'; // Right-down
    }


    //--------------------------------------------------------------------------------------------------------------------Rook movement:------------------------------------------------------
    public void selectRook (int x, int y)
    {
	// Move UP
	int cx1 = x - 1;
	while (cx1 >= 0 && colour [cx1] [y] == 'x')
	{ // While the square is within bounds and empty
	    select [cx1] [y] = 's'; // Mark the square as selectable
	    cx1--; // Move up
	}
	if (cx1 >= 0 && colour [cx1] [y] != turn && colour [cx1] [y] != 'x')
	{ // If an opponent's piece is encountered
	    select [cx1] [y] = 's'; // Mark the square as selectable
	}

	// Move DOWN
	int cx2 = x + 1;
	while (cx2 < row && colour [cx2] [y] == 'x')
	{ // While the square is within bounds and empty
	    select [cx2] [y] = 's'; // Mark the square as selectable
	    cx2++; // Move down
	}
	if (cx2 < row && colour [cx2] [y] != turn && colour [cx2] [y] != 'x')
	{ // If an opponent's piece is encountered
	    select [cx2] [y] = 's'; // Mark the square as selectable
	}

	// Move LEFT
	int cy1 = y - 1;
	while (cy1 >= 0 && colour [x] [cy1] == 'x')
	{ // While the square is within bounds and empty
	    select [x] [cy1] = 's'; // Mark the square as selectable
	    cy1--; // Move left
	}
	if (cy1 >= 0 && colour [x] [cy1] != turn && colour [x] [cy1] != 'x')
	{ // If an opponent's piece is encountered
	    select [x] [cy1] = 's'; // Mark the square as selectable
	}

	// Move RIGHT
	int cy2 = y + 1;
	while (cy2 < col && colour [x] [cy2] == 'x')
	{ // While the square is within bounds and empty
	    select [x] [cy2] = 's'; // Mark the square as selectable
	    cy2++; // Move right
	}
	if (cy2 < col && colour [x] [cy2] != turn && colour [x] [cy2] != 'x')
	{ // If an opponent's piece is encountered
	    select [x] [cy2] = 's'; // Mark the square as selectable
	}
    }


    //-----------------------------------------------------------------------------------------------------------------------------Bishop movement:----------------------------------------------
    public void selectBishop (int x, int y)
    {
	// Move diagonally UP-RIGHT
	int cx1 = x - 1;
	int cy1 = y + 1;
	while (cx1 >= 0 && cy1 < col && colour [cx1] [cy1] == 'x')
	{ // While the square is within bounds and empty
	    select [cx1] [cy1] = 's'; // Mark the square as selectable
	    cx1--; // Move up
	    cy1++; // Move right
	}
	if (cx1 >= 0 && cy1 < col && colour [cx1] [cy1] != turn && colour [cx1] [cy1] != 'x')
	{ // If an opponent's piece is encountered
	    select [cx1] [cy1] = 's'; // Mark the square as selectable
	}

	// Move diagonally UP-LEFT
	int cx2 = x - 1;
	int cy2 = y - 1;
	while (cx2 >= 0 && cy2 >= 0 && colour [cx2] [cy2] == 'x')
	{ // While the square is within bounds and empty
	    select [cx2] [cy2] = 's'; // Mark the square as selectable
	    cx2--; // Move up
	    cy2--; // Move left
	}
	if (cx2 >= 0 && cy2 >= 0 && colour [cx2] [cy2] != turn && colour [cx2] [cy2] != 'x')
	{ // If an opponent's piece is encountered
	    select [cx2] [cy2] = 's'; // Mark the square as selectable
	}

	// Move diagonally DOWN-RIGHT
	int cx3 = x + 1;
	int cy3 = y + 1;
	while (cx3 < row && cy3 < col && colour [cx3] [cy3] == 'x')
	{ // While the square is within bounds and empty
	    select [cx3] [cy3] = 's'; // Mark the square as selectable
	    cx3++; // Move down
	    cy3++; // Move right
	}
	if (cx3 < row && cy3 < col && colour [cx3] [cy3] != turn && colour [cx3] [cy3] != 'x')
	{ // If an opponent's piece is encountered
	    select [cx3] [cy3] = 's'; // Mark the square as selectable
	}

	// Move diagonally DOWN-LEFT
	int cx4 = x + 1;
	int cy4 = y - 1;
	while (cx4 < row && cy4 >= 0 && colour [cx4] [cy4] == 'x')
	{ // While the square is within bounds and empty
	    select [cx4] [cy4] = 's'; // Mark the square as selectable
	    cx4++; // Move down
	    cy4--; // Move left
	}
	if (cx4 < row && cy4 >= 0 && colour [cx4] [cy4] != turn && colour [cx4] [cy4] != 'x')
	{ // If an opponent's piece is encountered
	    select [cx4] [cy4] = 's'; // Mark the square as selectable
	}
    }


    //-----------------------------------------------------------------------------------------------------------------Select Queen-------------------------------------------------------------------------------------
    public void selectQueen (int x, int y)
    {
	// Combine the movement logic of the Rook and Bishop for the Queen
	selectRook (x, y);  // Queen moves like a Rook
	selectBishop (x, y); // Queen moves like a Bishop
    }


    //---------------------------------------------------------------------------------------------------------------Reset Board-----------------------------------------------------------
    public void reset ()
    {
	// Reset the board to its initial state
	for (int i = 0 ; i < row ; i++)
	{
	    for (int j = 0 ; j < col ; j++)
	    {
		piece [i] [j] = resetpiece [i] [j]; // Reset pieces
		select [i] [j] = resetselect [i] [j]; // Reset selection
		colour [i] [j] = resetcolour [i] [j]; // Reset colors
		bg [i] [j] = resetbg [i] [j];       // Reset background
	    }
	}

	// Reset the turn to black
	turn = 'b';
	turnpic.setIcon (createImageIcon ("kbbu.png")); // Update turn display
    }


    //---------------------------------------------------------------------------------------------------------------------------------Pawn Promotion
    public void promote (int x, int y)
    {
	// Check if the piece is a black pawn and is at the first rank (x == 0)
	if (colour [x] [y] == 'b' && piece [x] [y] == 'p' && x == 0)
	{
	    // Present the user with options to promote the pawn
	    String[] possibleValues = {"a) Queen", "b) Rook", "c) Bishop", "d) Knight"};
	    String selectedValue = (String) JOptionPane.showInputDialog (null, "What would you like your pawn to become?", "Choose one",
		    JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues [0]);

	    // Update the pawn's piece type based on user selection
	    if (selectedValue.equals ("a) Queen"))
	    {
		piece [x] [y] = 'q'; // Promote to Queen
	    }
	    else if (selectedValue.equals ("b) Rook"))
	    {
		piece [x] [y] = 'r'; // Promote to Rook
	    }
	    else if (selectedValue.equals ("c) Bishop"))
	    {
		piece [x] [y] = 'b'; // Promote to Bishop
	    }
	    else if (selectedValue.equals ("d) Knight"))
	    {
		piece [x] [y] = 'n'; // Promote to Knight
	    }
	}

	// Check if the piece is a white pawn and is at the last rank (x == 7)
	if (colour [x] [y] == 'w' && piece [x] [y] == 'p' && x == 7)
	{
	    // Present the user with options to promote the pawn
	    String[] possibleValues = {"a) Queen", "b) Rook", "c) Bishop", "d) Knight"};
	    String selectedValue = (String) JOptionPane.showInputDialog (null, "What would you like your pawn to become?", "Choose one",
		    JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues [0]);

	    // Update the pawn's piece type based on user selection
	    if (selectedValue.equals ("a) Queen"))
	    {
		piece [x] [y] = 'q'; // Promote to Queen
	    }
	    else if (selectedValue.equals ("b) Rook"))
	    {
		piece [x] [y] = 'r'; // Promote to Rook
	    }
	    else if (selectedValue.equals ("c) Bishop"))
	    {
		piece [x] [y] = 'b'; // Promote to Bishop
	    }
	    else if (selectedValue.equals ("d) Knight"))
	    {
		piece [x] [y] = 'n'; // Promote to Knight
	    }
	}
    }


    //----------------------------------------------------------------------------------------------------CASTLING MAIN METHOD
    public void CastleK (int x, int y)
    {
	// Check conditions for black king's castling to the left (bottom left corner)
	if ((piece [7] [3] == 'k' && colour [7] [3] == 'b' && y - 2 >= 0 && colour [7] [1] == 'x' && colour [7] [2] == 'x') &&
		(piece [7] [0] == 'r' && colour [7] [0] == 'b' && y + 2 < col && colour [7] [1] == 'x' && colour [7] [2] == 'x'))
	{
	    select [7] [1] = 's'; // Mark the castling position
	}

	// Check conditions for black king's castling to the right (bottom right corner)
	if ((piece [7] [3] == 'k' && colour [7] [3] == 'b' && y + 2 < col && colour [7] [4] == 'x' && colour [7] [5] == 'x' && colour [7] [6] == 'x') &&
		(piece [7] [7] == 'r' && colour [7] [7] == 'b' && y - 3 >= 0 && colour [7] [4] == 'x' && colour [7] [5] == 'x' && colour [7] [6] == 'x'))
	{
	    select [7] [5] = 's'; // Mark the castling position
	}

	// Check conditions for white king's castling to the left (top left corner)
	if ((piece [0] [3] == 'k' && colour [0] [3] == 'w' && y - 2 >= 0 && colour [0] [1] == 'x' && colour [0] [2] == 'x') &&
		(piece [0] [0] == 'r' && colour [0] [0] == 'w' && y + 2 < col && colour [0] [1] == 'x' && colour [0] [2] == 'x'))
	{
	    select [0] [1] = 's'; // Mark the castling position
	}

	// Check conditions for white king's castling to the right (top right corner)
	if ((piece [0] [3] == 'k' && colour [0] [3] == 'w' && y + 2 < col && colour [0] [4] == 'x' && colour [0] [5] == 'x' && colour [0] [6] == 'x') &&
		(piece [0] [7] == 'r' && colour [0] [7] == 'w' && y + 3 < col && colour [0] [4] == 'x' && colour [0] [5] == 'x' && colour [0] [6] == 'x'))
	{
	    select [0] [5] = 's'; // Mark the castling position
	}
    }


    //------------------------------------------Castle bottom left (black)
    public void CastleCorner1 ()
    {
	// Update piece positions for black castling to the bottom left corner
	piece [7] [1] = 'k';
	piece [7] [2] = 'r';
	piece [7] [0] = 'x';
	piece [7] [3] = 'x';

	// Update the piece colors for black castling to the bottom left corner
	colour [7] [0] = 'x';
	colour [7] [1] = 'b';
	colour [7] [2] = 'b';
	colour [7] [3] = 'x';
    }


    //------------------------------------------Castle bottom right (black)
    public void CastleCorner2 ()
    {
	// Update piece positions for black castling to the bottom right corner
	piece [7] [3] = 'x';
	piece [7] [4] = 'r';
	piece [7] [5] = 'k';
	piece [7] [6] = 'x';
	piece [7] [7] = 'x';

	// Update the piece colors for black castling to the bottom right corner
	colour [7] [3] = 'x';
	colour [7] [4] = 'b';
	colour [7] [5] = 'b';
	colour [7] [6] = 'x';
	colour [7] [7] = 'x';
    }


    //------------------------------------------Castle Top left (white)
    public void CastleCorner3 ()
    {
	// Update piece positions for white castling to the top left corner
	piece [0] [0] = 'x';
	piece [0] [1] = 'k';
	piece [0] [2] = 'r';
	piece [0] [3] = 'x';

	// Update the piece colors for white castling to the top left corner
	colour [0] [0] = 'x';
	colour [0] [1] = 'w';
	colour [0] [2] = 'w';
	colour [0] [3] = 'x';
    }


    //------------------------------------------Castle Top Right (white)
    public void CastleCorner4 ()
    {
	// Update piece positions for white castling to the top right corner
	piece [0] [3] = 'x';
	piece [0] [4] = 'r';
	piece [0] [5] = 'k';
	piece [0] [6] = 'x';
	piece [0] [7] = 'x';

	// Update the piece colors for white castling to the top right corner
	colour [0] [3] = 'x';
	colour [0] [4] = 'w';
	colour [0] [5] = 'w';
	colour [0] [6] = 'x';
	colour [0] [7] = 'x';
    }


    //----------------------------------------------------------------------------------------------King Is in Check works enough but not really :( :
    // Improved check detection methods
    private boolean isSquareUnderAttack (int targetRow, int targetCol, char playerColor)
    {
	char opponent = (playerColor == 'w') ? 'b':
	'w';

	for (int i = 0 ; i < row ; i++)
	{
	    for (int j = 0 ; j < col ; j++)
	    {
		if (colour [i] [j] == opponent)
		{
		    if (canAttack (i, j, targetRow, targetCol, opponent))
		    {
			return true;
		    }
		}
	    }
	}
	return false;
    }


    private boolean canAttack (int fromX, int fromY, int toX, int toY, char attackerColor)
    {
	int rowDiff = toX - fromX;
	int colDiff = toY - fromY;
	int rowStep = (rowDiff == 0) ? 0:
	(rowDiff > 0) ? 1:
	    - 1;
	int colStep = (colDiff == 0) ? 0:
	(colDiff > 0) ? 1:
	    - 1;

	// Manual absolute value calculations
	int absRowDiff = (rowDiff < 0) ? -rowDiff:
	rowDiff;
	int absColDiff = (colDiff < 0) ? -colDiff:
	colDiff;

	switch (piece [fromX] [fromY])
	{
	    case 'p': // Pawn
		if (attackerColor == 'w')
		{
		    return (toX == fromX - 1) && (absColDiff == 1);
		}
		else
		{
		    return (toX == fromX + 1) && (absColDiff == 1);
		}

	    case 'r': // Rook
		if (fromX == toX || fromY == toY)
		{
		    return isPathClear (fromX, fromY, toX, toY, rowStep, colStep);
		}
		return false;

	    case 'n': // Knight
		return (absRowDiff == 2 && absColDiff == 1) ||
		    (absRowDiff == 1 && absColDiff == 2);

	    case 'b': // Bishop
		if (absRowDiff == absColDiff)
		{
		    return isPathClear (fromX, fromY, toX, toY, rowStep, colStep);
		}
		return false;

	    case 'q': // Queen
		if ((fromX == toX || fromY == toY) ||
			(absRowDiff == absColDiff))
		{
		    return isPathClear (fromX, fromY, toX, toY, rowStep, colStep);
		}
		return false;

	    case 'k': // King
		return absRowDiff <= 1 && absColDiff <= 1;

	    default:
		return false;
	}
    }


    private boolean isPathClear (int fromX, int fromY, int toX, int toY, int rowStep, int colStep)
    {
	int x = fromX + rowStep;
	int y = fromY + colStep;

	while (x != toX || y != toY)
	{
	    if (piece [x] [y] != 'x')
		return false;
	    x += rowStep;
	    y += colStep;
	}
	return true;
    }


    public void KingCheck ()
    {
	// Find the current player's king position
	int kingRow = -1, kingCol = -1;
	for (int i = 0 ; i < row ; i++)
	{
	    for (int j = 0 ; j < col ; j++)
	    {
		if (piece [i] [j] == 'k' && colour [i] [j] == turn)
		{
		    kingRow = i;
		    kingCol = j;
		    break;
		}
	    }
	    if (kingRow != -1)
		break;
	}

	if (kingRow == -1)
	    return;                    // Should never happen

	// Check if king is under attack
	boolean inCheck = isSquareUnderAttack (kingRow, kingCol, turn);

	if (inCheck)
	{
	    if (turn == 'w')
	    {
		JOptionPane.showMessageDialog (null, "White King is in Check", "Check", JOptionPane.WARNING_MESSAGE);
	    }
	    else
	    {
		JOptionPane.showMessageDialog (null, "Black King is in Check", "Check", JOptionPane.WARNING_MESSAGE);
	    }
	}
    }


    private int[] findKing (char kingColor)
    {
	for (int i = 0 ; i < row ; i++)
	{
	    for (int j = 0 ; j < col ; j++)
	    {
		if (piece [i] [j] == 'k' && colour [i] [j] == kingColor)
		{
		    return new int[]
		    {
			i, j
		    }
		    ;
		}
	    }
	}
	return new int[]
	{
	    - 1, -1
	}
	;                         // Should never happen
    }


    // Method to handle actions triggered by buttons or events
    public void actionPerformed (ActionEvent e)
    {
	if (e.getActionCommand ().equals ("reset"))
	{
	    reset ();
	    redraw ();
	}
	//moves between the screens
	else if (e.getActionCommand ().equals ("s1"))
	    cdLayout.show (p_card, "1");
	else if (e.getActionCommand ().equals ("s2"))
	    cdLayout.show (p_card, "2");
	else if (e.getActionCommand ().equals ("Instrust"))
	    cdLayout.show (p_card, "2");
	else if (e.getActionCommand ().equals ("s3"))
	    cdLayout.show (p_card, "7");
	else if (e.getActionCommand ().equals ("S7"))
	{
	    cdLayout.show (p_card, "8");
	}
	else if (e.getActionCommand ().equals ("S8"))
	{
	    cdLayout.show (p_card, "3");
	}
	else if (e.getActionCommand ().equals ("Menu"))
	{
	    cdLayout.show (p_card, "1");
	}

	else if (e.getActionCommand ().equals ("S4"))
	{
	    cdLayout.show (p_card, "3");
	    reset ();
	    redraw ();
	}


	else if (e.getActionCommand ().equals ("S5"))
	{
	    cdLayout.show (p_card, "3");
	    reset ();
	    redraw ();
	}

	else if (e.getActionCommand ().equals ("resign"))
	{
	    if (turn == 'b')
	    {
		cdLayout.show (p_card, "4");
	    }
	    else if (turn == 'w')
	    {
		cdLayout.show (p_card, "5");
	    }
	}
	else if (e.getActionCommand ().equals ("s6"))
	    System.exit (0);

	else
	{ //code to handle the game
	    int n = Integer.parseInt (e.getActionCommand ());
	    int x = n / col;
	    int y = n % col;
	    showStatus ("(" + x + ", " + y + ")");

	    if (turn != colour [x] [y] && last == -1)
	    {
		showStatus ("It's not your turn.");
	    }
	    else if (last == -1 && turn == colour [x] [y])
	    {
		KingCheck ();
		//Where all the chess pieces get selected:----------------------------------------------------------------------------------------------------------------------------
		if (piece [x] [y] == 'p')
		{
		    selectPawn (x, y);
		}
		else if (piece [x] [y] == 'k')
		{
		    selectKing (x, y);
		}
		else if (piece [x] [y] == 'n')
		{
		    selectKnight (x, y);
		}
		else if (piece [x] [y] == 'r')
		{
		    selectRook (x, y);
		}
		else if (piece [x] [y] == 'b')
		{
		    selectBishop (x, y);
		}
		else if (piece [x] [y] == 'q')
		{
		    selectQueen (x, y);
		}
		last = n;
	    }
	    else
	    { // Piece movement handling
		int n = Integer.parseInt (e.getActionCommand ());
		int x = n / col;
		int y = n % col;

		if (last == -1)
		{
		    // Selecting a piece - keep your original selection code
		    if (turn == colour [x] [y])
		    {
			// Clear previous selections
			for (int i = 0 ; i < row ; i++)
			{
			    for (int j = 0 ; j < col ; j++)
			    {
				select [i] [j] = 'u';
			    }
			}

			// Highlight possible moves
			if (piece [x] [y] == 'p')
			    selectPawn (x, y);
			else if (piece [x] [y] == 'k')
			    selectKing (x, y);
			else if (piece [x] [y] == 'n')
			    selectKnight (x, y);
			else if (piece [x] [y] == 'r')
			    selectRook (x, y);
			else if (piece [x] [y] == 'b')
			    selectBishop (x, y);
			else if (piece [x] [y] == 'q')
			    selectQueen (x, y);

			last = n;
		    }
		}
		else
		{
		    // Moving a piece
		    int lastx = last / col;
		    int lasty = last % col;

		    if (select [x] [y] == 's')
		    {
			// Make the move
			char tempPiece = piece [x] [y];
			char tempColor = colour [x] [y];

			piece [x] [y] = piece [lastx] [lasty];
			colour [x] [y] = colour [lastx] [lasty];
			piece [lastx] [lasty] = 'x';
			colour [lastx] [lasty] = 'x';

			// Check if move leaves king in check
			boolean stillInCheck = isSquareUnderAttack (
				(piece [x] [y] == 'k') ? x:
			(turn == 'w' ? findKing ('w'):
			    findKing ('b')) [0],
				(piece [x] [y] == 'k') ? y:
			    (turn == 'w' ? findKing ('w'):
				findKing ('b')) [1],
				    turn
				    );

			if (stillInCheck)
			{
			    // Undo the move
			    piece [lastx] [lasty] = piece [x] [y];
			    colour [lastx] [lasty] = colour [x] [y];
			    piece [x] [y] = tempPiece;
			    colour [x] [y] = tempColor;

			    JOptionPane.showMessageDialog (null,
				    "Cannot move into or stay in check!",
				    "Illegal Move",
				    JOptionPane.ERROR_MESSAGE);
			}
			else
			{
			    // Valid move - switch turns
			    turn = (turn == 'b') ? 'w':
			    'b';
			    turnpic.setIcon (createImageIcon (turn == 'b' ? "kbbu.png":
			    "kwwu.png"));

			    // Check if opponent is now in check
			    KingCheck ();

			    // Check for promotion
			    promote (x, y);
			}
		    }

		    // Clear selections
		    for (int i = 0 ; i < row ; i++)
		    {
			for (int j = 0 ; j < col ; j++)
			{
			    select [i] [j] = 'u';
			}
		    }
		    last = -1;
		}
		redraw ();
	    }
	}
    }
}


