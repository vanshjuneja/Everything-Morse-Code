/* Vansh Juneja
 * Ms.Krasteva
 * 2018-12-20
 * This program cana do everything morse code! You can learn morse code! You convert to morse code! you can convert FROM morse code! You can even save and read files in morse code! You can also save and read files in english code. The end.
   GLOBAL VARIABLE DICTIONARY:
   NAME             TYPE        DESCRIPTION
   ----------------------------------------
   c                Console     console window object varaible
   filename         String      name of currently opened file
   fileType         int         file type ofcurrently opened file
   choice           int         menu option selectedhas all changes saved
   text             String      holding contents of currently open file
   score            int         holds user's score in the quiz
*/

// import liabraries
import java.awt.*;
import java.io.*;
import hsa.*;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import java.awt.image.*;

// EverythingMorseCode class
public class EverythingMorseCode
{
    // global variable declaration
    Console c;
    String filename = "";
    int fileType;
    int choice = 0;
    boolean saved = true;
    String text = "";
    int score = 160;

    // class constructor method
    public EverythingMorseCode ()
    {
	c = new Console ("Everything Morse Code");
    }


    /*
    title method
    ------------
    */
    private void title ()
    {
	c.clear (); // clear screen
	// draw background
	c.setColor (new Color (221, 126, 108));
	c.fillRect (0, 0, 640, 500);
	// draw title
	c.setColor (Color.white);
	c.setFont (new Font ("Consolas", 1, 20));
	c.drawString ("Everything Morse Code", 200, 20);
    }


    /*
    pauseProgram method
    -------------------
    */
    private void pauseProgram ()
    {
	// draw input prompt to screen
	c.setColor (Color.white);
	c.setFont (new Font ("Tahoma", 1, 14));
	c.drawString ("Press any key to continue", 235, 495);
	// wait until input recieved from user
	c.getChar ();
    }


    /*
    menu method
    -----------
    LOOPS:
    while A is to keep looping until the user enters a valid option
    while B is to keep looping until the user presses enter

    CONDITIONAL STATMENTS:
    if A only executes its code if the input hasnt already reached 2, and if the entered char isnt backspace
    if B only executes its code if the input text length ismore than 0, and the backspace character was pressed
    if C only executes its code if the entered input is a valid option between 1 and 11. Otherwise, an error message is displayed
    if D only executes its code if the user chooses option 6, 7, 9, or 10 but they have not entered or opened any data, or else otherwise
    if E only executes its code if the user chooses option 5, 8, or 10 but they have not saved the currently open data
    if F only executes its code if the user selectedyes when asked if they want to continue with unsaved changes, or else otherwise

    SEGMENT BLOCKS:
    the only try-catch block is to catch an exception if the user enters something other than a number in the text box

    LOCAL VARIABLE DICTIONARY:
    NAME            TYPE        DESCRIPTION
    ----------------------------------------
    input           String      holds textbox userinput
    chr             char        holds last key user pressed
    */
    public void menu ()
    {
	// local variable declaration
	String input = "";
	char chr = 0;
	title (); // clear screen and draw title
	// display all menu options
	c.setFont (new Font ("Tahoma", 1, 18));
	c.drawString ("1. Instruction", 255, 100);
	c.drawString ("2. Highscores", 255, 120);
	c.drawString ("3. Learn", 280, 140);
	c.drawString ("4. Quiz", 285, 160);
	c.drawString ("5. New", 285, 180);
	c.drawString ("6. Save As", 270, 200);
	c.drawString ("7. Save", 285, 220);
	c.drawString ("8. Open", 282, 240);
	c.drawString ("9. Display", 270, 260);
	c.drawString ("10. Translate", 255, 280);
	c.drawString ("11. Quit", 280, 300);
	// prompt user for input
	c.setFont (new Font ("Tahoma", 1, 30));
	c.drawString ("Please enter your", 192, 363);
	c.drawString ("choice: ", 192, 398);
	// textbox
	while (choice == 0) // while A
	{
	    // draw textbox
	    c.setColor (Color.black);
	    c.fillRect (312, 374, 130, 33);
	    c.setColor (Color.white);
	    c.fillRect (314, 376, 126, 29);
	    try
	    {
		// reset variable values
		input = "";
		chr = 0;
		// get character input
		c.setFont (new Font ("Tahoma", 1, 15));
		chr = c.getChar ();
		while (chr != 10) // while B
		{
		    if (input.length () < 2 && chr != 8) // if A
		    {
			// add latest character entered to total input variable
			input += chr;
			// draw input to textbox
			c.setColor (Color.black);
			c.drawString (input, 320, 396);
		    }
		    if (chr == 8 && input.length () > 0) // if B
		    {
			// get rid of last chacactr in input Strign
			input = input.substring (0, input.length () - 1);
			// redraw textbox field
			c.setColor (Color.white);
			c.fillRect (314, 376, 126, 29);
			// redraw input to textbox
			c.setColor (Color.black);
			c.drawString (input, 320, 396);
		    }
		    // get next character input
		    chr = c.getChar ();
		}
		choice = Integer.parseInt (input);
		if (choice > 0 && choice < 12) // if C
		{
		    if ((choice == 6 || choice == 7 || choice == 9 || choice == 10) && text.equals (""))   // if D
		    {
			JOptionPane.showMessageDialog (null, "No data has been entered or opened yet.", "ERROR", JOptionPane.ERROR_MESSAGE);
			choice = 0;
		    }
		    else
		    {
			if ((choice == 5 || choice == 8 || choice == 10) && !saved) // if E
			{
			    // citation: https://stackoverflow.com/questions/8396870/joptionpane-yes-or-no-window
			    int reply = JOptionPane.showConfirmDialog (null, "Would you like to continue with unsaved changes?", "WARNING", JOptionPane.YES_NO_OPTION);
			    if (reply == JOptionPane.YES_OPTION) // if F
				break;
			    else
				choice = 0;
			}
			else
			    break;
		    }
		}
		else
		{
		    choice = 0;
		    JOptionPane.showMessageDialog (null, "Please enter a valid choice.", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	    }
	    catch (NumberFormatException e)
	    {
		JOptionPane.showMessageDialog (null, "Please enter a valid choice.", "ERROR", JOptionPane.ERROR_MESSAGE);
	    }
	}
    }


    /*
    goodbye method
    --------------
    SEGMENT BLOCKS;
    the only try block in this method is used to catch an exception if one is thrown when creating a 3.5 second delay before the window closes
    */
    public void goodbye ()
    {
	title (); // clear screen and draw tite
	// goodbye message
	c.setFont (new Font ("Tahoma", 1, 30));
	c.drawString ("It's sad to see you go :(", 150, 150);
	c.drawString ("All of us here at the morse code", 90, 220);
	c.drawString ("program facility will miss you.", 110, 255);
	c.drawString ("Have a nice day :)", 200, 355);
	c.drawString ("By: Vansh J", 250, 390);
	// delay before closing window
	try
	{
	    Thread.sleep (3500);
	}
	catch (Exception e)
	{
	}
	// close window
	c.close ();
	// confirm closing to menu method by setting choice variable to 12
	choice = 12;
    }


    /*
    instructions method
    -------------------
    */
    public void instructions ()
    {
	title (); // clear screen and draw title
	// display instructions
	c.setFont (new Font ("Tahoma", 1, 30));
	c.drawString ("This program allows you to select", 50, 145);
	c.drawString ("any choice from the main menu", 50, 180);
	c.drawString ("including learning morse code,", 50, 215);
	c.drawString ("testing yourself on translating", 50, 250);
	c.drawString ("morse code, as well as saving and", 50, 285);
	c.drawString ("reading morse code and english", 50, 320);
	c.drawString ("files.", 50, 355);
	// wait until user enters a key before going back to menu
	pauseProgram ();
    }


    /*
    convert blackbox return method
    ------------------------------
    LOOPS:
    for A and C loop through each character of the passed text
    for B loops through each element of the english array
    for D loops through each element of the seperatedMorse array
    for E loops through each element of the morse array
    CONDITIONAL STATMENTS:
    if A runs if toMorse is true, and else otherwise
    if B runs if the crrent text character being looped through is equivelant to the english array character being looped through
    if C runs if the returnStr variable is lonnger than 0 characters, and else otherwise
    if D runs if the current character being looped through is a backslash
    if E runs if the current text character being looped throgh is equivelant to the morse array character being looepd through

    LOCAL VARIABLE DICTIONARY:
    NAME            TYPE            DESCRIPTION
    -------------------------------------------
    morse           String array    holds morse codes for english characters
    english         String array    holds english equivelants to morse codes
    returnStr       String          holds return string value
    arrayLength     int             needed array length to create array with an element assigned for each morse code letter in the passed text
    seperatedMorse  String array    array holding each morse code letter as a sepreate element
    currentIndex    int             holds current element index being set of seperatedMorse array
    */
    private String convert (String text, boolean toMorse)
    {
	// local variable declaration
	// constants
	final String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", ".----", "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.", "-----", ".-.-.-", "--..--", ".----.", "..--..", "---...", "-....-", "-..-.", "-.--.", "-.--.-", "-.--.-", ".-..-.", "..--.-", "-...-", ".-.-.", ".--.-.", "-.-.--", ".-..."};
	final String[] english = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", ".", ",", "'", "?", ":", "-", "/", "[", "(", "]", ")", "\"", "_", "=", "+", "@", "!", "&"};
	// vairables
	String returnStr = "";
	int arrayLength;
	String[] seperatedMorse;
	int currentIndex;

	if (toMorse) // if A
	{
	    for (int i = 0 ; i < text.length () ; i++) // for A
	    {
		for (int l = 0 ; l < english.length ; l++) // for B
		{
		    if (english [l].equalsIgnoreCase (text.charAt (i) + "")) // if B
		    {
			returnStr += morse [l] + "\\";
		    }
		}
	    }
	    if (returnStr.length () > 0) // if C
		return returnStr.substring (0, returnStr.length () - 1);
	    else
		return "";
	}
	else
	{
	    arrayLength = 1;
	    // 1. count number of '\'s to make an array of that length
	    for (int i = 0 ; i < text.length () ; i++) // for C
		if (text.charAt (i) == '\\') // if D
		    arrayLength++;
	    seperatedMorse = new String [arrayLength];
	    // 2. set array values
	    currentIndex = 0;
	    while (text.indexOf ('\\') != -1) // while A
	    {
		seperatedMorse [currentIndex] = text.substring (0, text.indexOf ('\\'));
		currentIndex++;
		text = text.substring (text.indexOf ('\\') + 1, text.length ());
	    }
	    seperatedMorse [currentIndex] = text;
	    // 3. convert them to english
	    char engASCII;
	    for (int i = 0 ; i < seperatedMorse.length ; i++) // for D
	    {
		for (int imorse = 0 ; imorse < morse.length ; imorse++) // for E
		{
		    if (seperatedMorse [i].equals (morse [imorse])) // if E
		    {
			returnStr += english [imorse].charAt (0);
			break;
		    }
		}
	    }
	    // 4. return converted english string
	    return returnStr;
	}
    }


    /*
    splashscreen method
    -------------------
    LOOPS:
    for A loops to animate the morse code String entering the screen from the left
    for B loops to animate the english String exiting the screen to the right
    SEGMENT BLOCKS:
    try A and B add delays between their respective animation's frames
    */
    public void splashscreen ()
    {
	title (); // clear screen and draw title
	c.setColor (new Color (206, 225, 243));
	// draw left side
	c.fillPolygon (new int[]
	{
	    150, 250, 250, 150
	}
	, new int[]
	{
	    100, 170, 280, 350
	}
	, 4);
	// draw right side
	c.fillPolygon (new int[]
	{
	    490, 390, 390, 490
	}
	, new int[]
	{
	    100, 170, 280, 350
	}
	, 4);
	// draw center
	c.setColor (new Color (158, 196, 232));
	c.fillRect (250, 155, 140, 140);
	c.setColor (Color.black);
	// draw top arrow pointing left
	c.fillPolygon (new int[]
	{
	    255, 255, 325, 325, 345, 325, 325
	}
	, new int[]
	{
	    210, 206, 206, 200, 208, 216, 210
	}
	, 7);
	// draw bottom arrow pointing right
	c.fillPolygon (new int[]
	{
	    385, 385, 315, 315, 295, 315, 315
	}
	, new int[]
	{
	    240, 236, 236, 230, 238, 246, 240
	}
	, 7);
	for (int i = 0 ; i < 225 ; i++) // for A
	{
	    c.setColor (new Color (221, 126, 108));
	    c.fillRect (-75 + i, 244, 63, 21);
	    c.setColor (Color.white);
	    c.drawString ("---...", -75 + i, 250);
	    c.drawString ("-.--.-", -75 + i, 265);
	    c.setColor (new Color (206, 225, 243));
	    c.fillPolygon (new int[]
	    {
		150, 250, 250, 150
	    }
	    , new int[]
	    {
		100, 170, 280, 350
	    }
	    , 4);
	    try // try A
	    {
		Thread.sleep (5);
	    }
	    catch (Exception e)
	    {
	    }
	}
	for (int i = 0 ; i < 350 ; i++) // for B
	{
	    c.setColor (new Color (221, 126, 108));
	    c.fillRect (400 + i, 235, 18, 19);
	    c.setColor (Color.white);
	    c.drawString (":)", 400 + i, 250);
	    c.setColor (new Color (206, 225, 243));
	    c.fillPolygon (new int[]
	    {
		490, 390, 390, 490
	    }
	    , new int[]
	    {
		100, 170, 280, 350
	    }
	    , 4);
	    try // try B
	    {
		Thread.sleep (5);
	    }
	    catch (Exception e)
	    {
	    }
	}
    }


    /*
    quiz method
    ------------
    LOOPS:
    for A loops 5 times
    while A loops to pick a different question until one is picked which hasn't been picked before
    while B loops until the user enters * to exit back to the menu
    CONDITIONAL STATMENTS:
    if A, C, and D all break from the loop, to go back to hte menu if the user enters *
    if B runs if the user enters presses backspace and the current textbox input length is more than 0
    if E runs if the choice varible is between 1 and 4, and else otherwise
    if F runs if the choice variable is equal to the correct answer, and else otherwise
    if G runs highscores, if the user has not entered * to indicate they want to go back to the main menu
    SEGMENT BLOCKS:
    try A catches any VumberFormatException if the user enters something that is not a number in the textbox

    LOCAL VARIABLE DICTIONARY:
    NAME                                    TYPE            DESCRIPTION
    -------------------------------------------------------------------
    input                                   String          hoolds textbox userinput
    questions                               String array    holds all possible questions
    answers                                 String array    holds all answers to all possible questions
    correctAnswer                           int array       holds correct answer number
    questionsAsked                          boolean         holds which questions have been aksed before
    option1, option2 option3, option4       String          holds possible answers to current question
    question                                String          holds current question text
    currentQuestion                         int             holds current question number
    questionNum                             int             holds which question number is being asked currently
    */
    public void quiz ()
    {
	// local variable declaration
	String input = "";
	String[] questions = {"What are the 2 symbols used to express morse code visually?", "What are the 2 sounds used to express morse code auditorily?", "When was morse code invented?", "Why was morse code invented?", "Which of the following is SOS in morse code?", "Which of the following is 3 \"dits\" followed by 3 \"dahs\"?", "Are upper and lowercase letters in morse code different?", "Which was the first species to invent morse code?", "During which war was morse code vital?", "What is the morse code for the letter 'E'?"};
	String[] answers = {"'.' and '-'", "'-' and '\'", "Cuba", "'*' and '-'", "silences and beeps", "bleeps and bloops", "Thailand and Paraguay", "short beeps and long beeps", "ancient Greece", "1836", "1936", "1946", "World War 1", "telagraph communication", "World War 2", "Mexican independance", "---...---", "-.-...-.-", "...---...", "-----------------", "Totalitarianism", "---...", "....---", "...---", "yes", "no", "sometimes", "not in Tel Aviv", "Vultures", "Mozart", "Homo Sapiens", "clearly wrong answer", "WW2", "Gulf War", "WW1", "Syria", "a single dash", "5 dots", "a single dot", "vegan burgers"};
	int[] correctAnswer = {1, 4, 2, 2, 3, 4, 2, 4, 1, 3};
	boolean[] questionsAsked = {false, false, false, false, false, false, false, false, false, false};
	String option1, option2, option3, option4;
	String question;
	int currentQuestion = 0;
	int questionNum = 0;

	for (int i = 0 ; i < 5 ; i++) // for A
	{
	    // pick question
	    do
	    {
		questionNum = (int) (Math.random () * 10);
	    }
	    while (questionsAsked [questionNum] == true); // while A
	    // set variables accordingly
	    currentQuestion++;
	    questionsAsked [questionNum] = true;
	    question = questions [questionNum];
	    option1 = answers [questionNum * 4];
	    option2 = answers [questionNum * 4 + 1];
	    option3 = answers [questionNum * 4 + 2];
	    option4 = answers [questionNum * 4 + 3];
	    title (); // clear screen and draw title
	    // display text for questions and answers, etc. on screen
	    c.setFont (new Font ("Tahoma", 1, 12));
	    c.drawString ("Enter * to go back to the menu", 230, 492);
	    c.setFont (new Font ("Tahoma", 1, 17));
	    c.drawString (question, 320 - question.length () * 4, 100);
	    c.drawString ("1. " + option1, 100, 200);
	    c.drawString ("2. " + option2, 350, 200);
	    c.drawString ("3. " + option3, 100, 300);
	    c.drawString ("4. " + option4, 350, 300);
	    c.drawString (currentQuestion + " / 5", 20, 20);
	    c.setFont (new Font ("Tahoma", 1, 30));
	    c.drawString ("Please enter your", 192, 409);
	    c.drawString ("choice: ", 192, 444);
	    // textbox
	    while (input != "*") // while B
	    {
		c.setColor (Color.black);
		c.fillRect (312, 420, 130, 33);
		c.setColor (Color.white);
		c.fillRect (314, 422, 126, 29);
		try // try A
		{
		    input = "";
		    char chr = 0;
		    c.setFont (new Font ("Tahoma", 1, 15));
		    chr = c.getChar ();
		    if (chr == '*') // if A
		    {
			input = "*";
			break;
		    }
		    while (chr != 10) // while C
		    {
			if (input.length () < 1 && chr != 8)
			{
			    input += chr;
			    c.setColor (Color.black);
			    c.drawString (input, 320, 442);
			}
			if (chr == 8 && input.length () > 0) // if B
			{
			    input = input.substring (0, input.length () - 1);
			    c.setColor (Color.black);
			    c.fillRect (312, 420, 130, 33);
			    c.setColor (Color.white);
			    c.fillRect (314, 422, 126, 29);
			    c.setColor (Color.black);
			    c.drawString (input, 320, 442);
			}
			chr = c.getChar ();
			if (chr == '*') // if C
			{
			    input = "*";
			    break;
			}
		    }
		    if (input.equals ("*")) // if D
			break;
		    choice = Integer.parseInt (input);
		    if (choice > 0 && choice < 5) // if E
		    {
			if (choice == correctAnswer [questionNum]) // if F
			    score += 100;
			else
			    score -= 10;
			break;
		    }
		    else
		    {
			choice = 0;
			JOptionPane.showMessageDialog (null, "Please enter a valid choice.", "ERROR", JOptionPane.ERROR_MESSAGE);
		    }
		}
		catch (NumberFormatException e)
		{
		    JOptionPane.showMessageDialog (null, "Please enter a valid choice.", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	    }
	}
	if (!input.equals ("*")) // if G
	{
	    highscores ();
	}
    }


    /*
    newFile method
    --------------
    LOOPS:
    while A and C loop until the user enters * to indicate they wish to exit back to the menu
    while B and D loop until the last character pressed (chr variable) is enter
    for A and B loops throgh the input, breaking it down line-by-line based on the character limit set in the chrLmt variable
    CONDITIONAL STATEMENTS:
    if A D and H break from the loop if the user enters * indicating they wish to exit back to the menu
    if B runs if the textbox input length is less than 1 and the character pressed (chr variable) isn't backspace
    if C and M run if the textbox input length is more than 0 and the character pressed (chr variable) is backspace
    if E checks if the entered textbox userinput is 1, 2, or something else
    if F runs if the entered input is not *
    if G and I run if the user entered 1 previously to indicate they wish to create a morse code file (type == 1), and else otherwise
    if J runs if chr is either -, ., or \, and the input is less than 7 times the character limit (chrLmt)
    if K runs if chr is not either -, ., \, or *
    if L runs if the textbox user input is within the maximum character limit (chrLmt * 7 lines) and chr is not backspace
    if M runs if the user has not entered any text and presses enter, or else otherwise
    if N runs if the user has not entered * which would have indicated they wish to go back to the menu, in this case the if structure sets global variables accordingly to the actions the user took in this method

    LOCAL VARIABLE DICTIONARY:
    NAME        TYPE        DESCRIPTION
    -----------------------------------
    type        int         file type chosen by user (1 for morsecode, 2 for english)
    input       String      textbox input
    chrLmt      int         character limit from left to right in textbox (42 for english, 77 for morse)
    chr         char        holds last key user pressed
    */
    public void newFile ()
    {
	// local variable declaration
	int type = 0;
	String input = "";
	int chrLmt = 42;
	char chr;

	title (); // clear screen and draw title
	// draw options text on screen
	c.setFont (new Font ("Tahoma", 1, 20));
	c.drawString ("1. Morse Code", 250, 70);
	c.drawString ("2. English", 273, 95);
	c.setFont (new Font ("Tahoma", 1, 12));
	c.drawString ("Enter * to go back to the menu", 230, 492);
	c.setFont (new Font ("Tahoma", 1, 18));
	c.drawString ("Please enter the file type", 40, 130);
	// file type selection textbox
	while (!input.equals ("*")) // while A
	{
	    // draw textbox
	    c.setColor (Color.black);
	    c.fillRect (40, 140, 560, 33);
	    c.setColor (Color.white);
	    c.fillRect (42, 142, 556, 29);
	    chr = 0; // reset chr varible
	    c.setFont (new Font ("Tahoma", 1, 15));
	    chr = c.getChar ();
	    while (chr != 10) // while B
	    {
		if (chr == '*') // if A
		{
		    input = "*";
		    break;
		}
		if (input.length () < 1 && chr != 8) // if B
		{
		    input += chr;
		    c.setColor (Color.black);
		    c.drawString (input, 47, 163);
		}
		if (chr == 8 && input.length () > 0) // if C
		{
		    input = input.substring (0, input.length () - 1);
		    c.setColor (Color.white);
		    c.fillRect (42, 142, 556, 29);
		    c.setColor (Color.black);
		    c.drawString (input, 47, 163);
		}
		chr = c.getChar ();
	    }
	    if (input == "*") // if D
		break;
	    else
	    {
		if (input.equals ("1")) // if E
		{
		    type = 1;
		    break;
		}
		else if (input.equals ("2"))
		{
		    type = 2;
		    break;
		}
		else
		{
		    choice = 0;
		    input = "";
		    JOptionPane.showMessageDialog (null, "Please enter a 1 or 2 to specify your choice.", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	    }
	}
	// actual text textbox
	if (!input.equals ("*")) // if F
	{
	    c.setColor (Color.white);
	    c.setFont (new Font ("Tahoma", 1, 18));
	    c.drawString ("Please enter the text", 40, 204);
	    input = "";
	    while (!input.equals ("*")) // while C
	    {
		c.setColor (Color.black);
		c.fillRect (40, 210, 560, 163);
		c.setColor (Color.white);
		c.fillRect (42, 212, 556, 159);
		chr = 0;
		if (type == 1) // if G
		{
		    c.setFont (new Font ("Tahoma", 1, 12));
		    chrLmt = 77;
		}
		else
		    c.setFont (new Font ("Tahoma", 1, 15));
		chr = c.getChar ();
		while (chr != 10) // while D
		{
		    if (chr == '*') // if H
		    {
			input = "*";
			break;
		    }
		    c.setColor (Color.black);
		    if (type == 1) // if I
		    {
			if ((chr == 45 || chr == 46 || chr == 92) && input.length () < chrLmt * 7) // if J
			{
			    input += chr;
			    for (int i = 0 ; i < input.length () / chrLmt + 1 ; i++) // for A
			    {
				c.drawString (input.substring (i * chrLmt, Math.min (i * chrLmt + chrLmt, input.length ())), 47, 234 + (i * 20));
			    }
			}
			if (!(chr == 45 || chr == 46 || chr == 42 || chr == 92 || chr == 8)) // if K
			    JOptionPane.showMessageDialog (null, "You may only enter '.', '-', '\\', and '*' to exit.", "ERROR", JOptionPane.ERROR_MESSAGE);
		    }
		    else
		    {
			if (input.length () < chrLmt * 7 && chr != 8) // if L
			{
			    input += chr;
			    for (int i = 0 ; i < input.length () / chrLmt + 1 ; i++) // for B
			    {
				c.drawString (input.substring (i * chrLmt, Math.min (i * chrLmt + chrLmt, input.length ())), 47, 234 + (i * 20));
			    }
			}
		    }
		    if (chr == 8 && input.length () > 0) // if M
		    {
			input = input.substring (0, input.length () - 1);
			c.setColor (Color.white);
			c.fillRect (42, 212, 556, 159);
			c.setColor (Color.black);
			for (int i = 0 ; i < input.length () / chrLmt + 1 ; i++) // for C
			{
			    c.drawString (input.substring (i * chrLmt, Math.min (i * chrLmt + chrLmt, input.length ())), 47, 234 + (i * 20));
			}
		    }
		    chr = c.getChar ();
		}
		if (input == "") // if M
		    JOptionPane.showMessageDialog (null, "Please enter some text, or * to exit back to the menu.", "ERROR", JOptionPane.ERROR_MESSAGE);
		else
		    break;
	    }
	    // set global variables accordingly
	    if (!input.equals ("*")) // if N
	    {
		fileType = type;
		text = input;
		saved = false;
	    }
	}
    }


    /*
    learn method
    ------------
    LOOPS:
    while A loops as long as the user input (input variable) is not equal to * which would indicate that they want to exit the loop and go back to the menu
    while B loops as long as the last character pressed (chr variable) is not enter
    SEGMENT BLOCKS:
    try A catches IOException in case the image file being opened and displayed is deleted
    CONDITIONAL STATEMENTS:
    if A and E break from the loop back to the menu if the user enters * indicating they wish to do so
    if B runs if the input is less than 11 and chr (the last character pressed) is not backspace
    if C runs if chr is ., -, or \
    if D runs if chr is backspace and the textbox input length is more than 0.

    LOCAL VARIABLE DICTIONARY:
    NAME        TYPE        DESCRIPTION
    -----------------------------------
    input       String      textbox userinput
    char        char        last character pressed by user
    */
    public void learn ()
    {
	// global declaration
	String input = "";
	char chr = 0;

	title (); // clear screen and draw title
	// explain
	c.setFont (new Font ("Tahoma", 4, 18));
	c.drawString ("Morse code is made up of dots and dashes. The translation", 70, 60);
	c.drawString ("can be determined with the map below. Try to translate and", 65, 85);
	c.drawString ("enter a character and see if you're right!", 140, 110);
	c.setFont (new Font ("Tahoma", 1, 30));
	c.drawString ("Please enter your", 10, 433);
	c.drawString ("choice: ", 10, 468);
	c.drawString ("You just typed the", 330, 433);
	c.drawString ("letter: ", 330, 468);
	c.setFont (new Font ("Tahoma", 1, 12));
	c.drawString ("Enter * to go back to the menu", 13, 492);
	c.drawString ("When seperating letters in other program sections, use '\\'", 275, 492);
	try // try A
	{
	    c.drawImage (ImageIO.read (new File ("diagram.jpg")), 0, 120, null);
	}
	catch (IOException e)
	{
	}
	// textbox and display conversion
	while (!input.equals ("*")) // while A
	{
	    c.setColor (Color.black);
	    c.fillRect (128, 443, 130, 33);
	    c.setColor (Color.white);
	    c.fillRect (130, 445, 126, 29);
	    input = "";
	    chr = 0;
	    c.setFont (new Font ("Tahoma", 1, 15));
	    chr = c.getChar ();
	    while (chr != 10) // while B
	    {
		if (chr == '*') // if A
		{
		    input = "*";
		    break;
		}
		if (input.length () < 11 && chr != 8) // if B
		{
		    if (chr == 45 || chr == 46 || chr == 42) // if C
		    {
			input += chr;
			c.setColor (Color.black);
			c.drawString (input, 134, 466);
		    }
		    else
			JOptionPane.showMessageDialog (null, "You may only enter '.' or '-', and '*' to exit.", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		if (chr == 8 && input.length () > 0) // if D
		{
		    input = input.substring (0, input.length () - 1);
		    c.setColor (Color.white);
		    c.fillRect (130, 445, 126, 29);
		    c.setColor (Color.black);
		    c.drawString (input, 134, 466);
		}
		chr = c.getChar ();
	    }
	    if (input.equals ("*")) // if E
		break;
	    c.setColor (new Color (221, 126, 108));
	    c.fillRect (425, 438, 30, 40);
	    c.setColor (Color.black);
	    c.setFont (new Font ("Consolas", 1, 40));
	    c.drawString (convert (input, false), 430, 468);
	}
    }


    /*
    save method
    -----------
    LOOPS:
    while A keeps looping until the user enters * indicating they wish to exit back to the main menu
    while B keeps looping as long as the last character pressed (chr) is not enter which would indicate the user has completed waht they wish to enter in the textbox
    SEGMENT BLOCKS:
    try A and B catches any IOException in case the user tries to save a file with an invalid name, or an Excpetion if one is thrown with the ending delay before the method ends and the program goes back to the main menu
    CONDITIONAL STATEMENTS:
    if A runs if the saveAs variable is true, meaning the user wishes to save the current text as new file, and else otherwise
    if B breaks from the loop if the user enters * indicating they wish to exit back to the main menu
    if C runs if the textbox input length is less than 11 and the laast character pressed (chr variable) is not backspace
    if D tuns if the textboc input length is more than 0 and the last charater pressed (chr) is backspace
    if E runs if the last character pressed (chr) is enter
    if F runs if the current loaded text's file type (fileType variable) is 1 indicating that the text is in morse code format, or else otherwise
    if G runs if the global filename vairable is still the same as it was at the start, indicating that it has not been changed, or else otherwise
    if H runs if the global fileType vairable is 1 (for morse code format), or else otherwise

    LOCAL VARIABLE DICTIONARY:
    NAME        TYPE            DESCRIPTION
    ---------------------------------------
    input       String          textbox input
    chr         chr             last character pressed
    output      PrintWriter     holds FileWriter to save currently loaded text into file
    */
    public void save (boolean saveAs)
    {
	// local varable declaration
	String input;
	char chr;
	PrintWriter output;

	if (saveAs) // A
	{
	    title (); // clear screen and draw input
	    c.setFont (new Font ("Tahoma", 1, 12));
	    c.drawString ("Enter * to go back to the menu", 230, 492);
	    input = "";
	    c.setFont (new Font ("Tahoma", 1, 18));
	    c.drawString ("Please enter the filename", 40, 75);
	    // textbox
	    while (!input.equals ("*")) // while A
	    {
		// draw textbox
		c.setColor (Color.black);
		c.fillRect (40, 85, 530, 33);
		c.setColor (Color.white);
		c.fillRect (42, 87, 526, 29);
		// reset varibles
		input = "";
		chr = 0;
		c.setFont (new Font ("Tahoma", 1, 15));
		chr = c.getChar ();
		while (chr != 10) // while B
		{
		    if (chr == '*') // if B
		    {
			input = "*";
			break;
		    }
		    // add pressed character to textbox input vairable
		    if (input.length () < 11 && chr != 8) // if C
		    {
			input += chr;
			c.setColor (Color.black);
			c.drawString (input, 47, 107);
		    }
		    // backspace
		    if (chr == 8 && input.length () > 0) // if D
		    {
			input = input.substring (0, input.length () - 1);
			c.setColor (Color.white);
			c.fillRect (42, 87, 526, 29);
			c.setColor (Color.black);
			c.drawString (input, 47, 107);
		    }
		    // get next character
		    chr = c.getChar ();
		}
		// done input
		if (chr == 10) // if E
		{
		    try // try A
		    {
			// set vairables accordingly
			filename = input;
			if (fileType == 1) // if F
			    input += ".mor";
			else
			    input += ".eng";
			// print to file
			output = new PrintWriter (new FileWriter (input));
			output.println ("This file was written in the EverythingMorseCode program created by Vansh J.");
			output.println (text);
			output.close ();
			saved = true;
			c.setColor (Color.white);
			c.setFont (new Font ("Tahoma", 1, 20));
			c.drawString ("File Saved.", 40, 350);
			// delay before going back to menu
			Thread.sleep (2000);
			break;
		    }
		    catch (IOException e)
		    {
			JOptionPane.showMessageDialog (null, "Please enter a valid file name with no special characters.", "ERROR", JOptionPane.ERROR_MESSAGE);
		    }
		    catch (Exception e)
		    {
		    }
		}
	    }
	}
	else
	{
	    if (filename.equals ("")) // if G
		save (true);
	    else
	    {

		try // try B
		{
		    input = filename;
		    if (fileType == 1) // if H
			input += ".mor";
		    else
			input += ".eng";
		    output = new PrintWriter (new FileWriter (input));
		    output.println ("This file was written in the EverythingMorseCode program created by Vansh J.");
		    output.println (text);
		    output.close ();
		    saved = true;
		    c.setColor (Color.white);
		    c.setFont (new Font ("Tahoma", 1, 20));
		    c.drawString ("File Saved.", 40, 120);
		    Thread.sleep (2000);
		}
		catch (Exception e)
		{
		}
	    }
	}
    }


    /*
    open method
    -----------
    LOOPS:
    while A and C loop until the user enters * to indicate they wish to exit back to the menu
    while B and D loop until the user presses enter to idicate they are done typing in the textbox
    while E keeps looping through the file until it reaches the end of the file -- when the line variable is equal to null
    SEGMENT BLOCKS:
    try A catches any IOException if the user asks to opn a file that does not exist, or if an exception is thrown when ading a delay before the method ends and goes bakc to the main menu
    CONDITIONAL STATEMENTS:
    if A, D, and G break from the loop if the user enters * to indicate they wish to exit to the main menu
    if B runs if the top textbox input (finput variable) length is less than 11 and the backspace key was not pressed (chr variable)
    if C runs if the top textbox input length is more than 0 and the backspace key was pressed
    if E runs if the bottom textbox input (input variable) length is less than 1 and the backspace key was not pressed (chr variable)
    if F runs if the bottom textbox input length is more than 0 and the backspace key was pressed
    if H runs if the bottom textbox input is either 1 or 2, and else otherwise
    if I runs if the bottom textbox input is 1, and else otherwise

    LOCAL VARIABLE DICTIONARY:
    NAME        TYPE            DESCRIPTION
    ---------------------------------------
    finput      String          file type textbox input
    input       String          filename textbox input
    type        int             type of file chosen to open
    chr         char            last character pressed
    r           BufferedReader  holds FileReader to read from file user wishes to open
    line        String          holds the last line read from the file being read
    */
    public void open ()
    {
	// local vairable decalration
	String finput = "";
	String input;
	int type;
	char chr;
	BufferedReader r;
	String line;

	title (); // clear screen and draw title
	// explaining text
	c.setFont (new Font ("Tahoma", 1, 12));
	c.drawString ("Enter * to go back to the menu", 230, 492);
	c.setFont (new Font ("Tahoma", 1, 18));
	c.drawString ("Please enter the filename", 40, 75);
	// top filename textbox
	while (!finput.equals ("*")) // while A
	{
	    // reset variables
	    finput = "";
	    input = "";
	    type = 0;
	    chr = 0;
	    // draw top textbox
	    c.setColor (Color.black);
	    c.fillRect (40, 85, 530, 33);
	    c.setColor (Color.white);
	    c.fillRect (42, 87, 526, 29);
	    c.setFont (new Font ("Tahoma", 1, 15));
	    chr = c.getChar ();
	    while (chr != 10) // while B
	    {
		if (chr == '*') // if A
		{
		    finput = "*";
		    break;
		}
		if (finput.length () < 11 && chr != 8) // if B
		{
		    finput += chr;
		    c.setColor (Color.black);
		    c.drawString (finput, 47, 107);
		}
		if (chr == 8 && finput.length () > 0) // if C
		{
		    finput = finput.substring (0, finput.length () - 1);
		    c.setColor (Color.white);
		    c.fillRect (42, 87, 526, 29);
		    c.setColor (Color.black);
		    c.drawString (finput, 47, 107);
		}
		chr = c.getChar ();
	    }
	    c.setFont (new Font ("Tahoma", 1, 20));
	    c.setColor (Color.white);
	    c.drawString ("1. Morse Code", 250, 170);
	    c.drawString ("2. English", 273, 195);
	    c.setFont (new Font ("Tahoma", 1, 12));
	    c.drawString ("Enter * to go back to the menu", 230, 492);
	    c.setFont (new Font ("Tahoma", 1, 18));
	    c.drawString ("Please enter the file type", 40, 230);
	    // bottom file type textbox
	    while (!finput.equals ("*")) // while C
	    {
		// draw bottom textbox
		c.setColor (Color.black);
		c.fillRect (40, 240, 560, 33);
		c.setColor (Color.white);
		c.fillRect (42, 242, 556, 29);
		chr = 0;
		c.setFont (new Font ("Tahoma", 1, 15));
		chr = c.getChar ();
		while (chr != 10) // while D
		{
		    if (chr == '*') // if D
		    {
			finput = "*";
			break;
		    }
		    if (input.length () < 1 && chr != 8) // if E
		    {
			input += chr;
			c.setColor (Color.black);
			c.drawString (input, 47, 263);
		    }
		    if (chr == 8 && input.length () > 0) // if F
		    {
			input = input.substring (0, input.length () - 1);
			c.setColor (Color.white);
			c.fillRect (42, 242, 556, 29);
			c.setColor (Color.black);
			c.drawString (input, 47, 263);
		    }
		    chr = c.getChar ();
		}
		if (finput == "*") // if G
		    break;
		else
		{
		    if (input.equals ("1") || input.equals ("2")) // if H
		    {
			try // try A
			{
			    if (input.equals ("1")) // if I
			    {
				r = new BufferedReader (new FileReader (finput + ".mor"));
				fileType = 1;
			    }
			    else
			    {
				r = new BufferedReader (new FileReader (finput + ".eng"));
				fileType = 2;
			    }
			    if (r.readLine ().equals ("This file was written in the EverythingMorseCode program created by Vansh J.")) // if J
			    {
				line = r.readLine ();
				text = "";
				while (line != null) // while E
				{
				    text += line;
				    line = r.readLine ();
				}
				r.close ();
				saved = true;
				filename = finput;
				c.setColor (Color.white);
				c.setFont (new Font ("Tahoma", 1, 20));
				c.drawString ("File Opened.", 40, 350);
				Thread.sleep (1000);
				finput = "*";
				break;
			    }
			    else
			    {
				JOptionPane.showMessageDialog (null, "Please open an unaltered file created by this program.", "ERROR", JOptionPane.ERROR_MESSAGE);
				c.setColor (Color.black);
				c.fillRect (40, 240, 560, 33);
				c.setColor (Color.white);
				c.fillRect (42, 242, 556, 29);
				break;
			    }
			}
			catch (IOException e)
			{
			    JOptionPane.showMessageDialog (null, "Please enter a real filename.", "ERROR", JOptionPane.ERROR_MESSAGE);
			    c.setColor (Color.black);
			    c.fillRect (40, 240, 560, 33);
			    c.setColor (Color.white);
			    c.fillRect (42, 242, 556, 29);
			    break;
			}
			catch (Exception e)
			{
			}
		    }
		    else
		    {
			choice = 0;
			input = "";
			JOptionPane.showMessageDialog (null, "Please enter a 1 or 2 to specify your choice.", "ERROR", JOptionPane.ERROR_MESSAGE);
		    }
		}
	    }
	}
    }


    /*
    display method
    --------------
    LOOPS:
    for A loops through the text to print it line by line within the textbox
    CONDITIONAL STATEMENTS:
    if A checks if the file type is 1 for morse, or 2 for english, and displays the file type on the screen, along with setting variables and the font size accordingly

    LOCAL VARIABLE DICTIONARY:
    NAME        TYPE    DESCRIPTION
    -------------------------------
    chrLmt      int     number of characters from left to right in textbox
    */
    public void display ()
    {
	// local varible declaration
	int chrLmt = 42;

	title (); // clear screen and draw title
	// explaining text
	c.setColor (Color.white);
	c.setFont (new Font ("Tahoma", 1, 18));
	c.drawString ("Your current text is:", 40, 100);
	// draw textbox
	c.setColor (Color.black);
	c.fillRect (40, 115, 560, 223);
	c.setColor (Color.white);
	c.fillRect (42, 117, 556, 219);
	// display what type of file is open
	if (fileType == 1) // if A
	{
	    c.drawString ("\"" + filename + "\" is currently an morse code file.", 40, 370);
	    c.setFont (new Font ("Tahoma", 1, 12));
	    chrLmt = 77;
	}
	else
	{
	    c.drawString ("\"" + filename + "\" is currently an english file.", 40, 370);
	    c.setFont (new Font ("Tahoma", 1, 15));
	}
	// draw file text in textbox
	c.setColor (Color.black);
	for (int i = 0 ; i < text.length () / chrLmt + 1 ; i++) // for A
	{
	    c.drawString (text.substring (i * chrLmt, Math.min (i * chrLmt + chrLmt, text.length ())), 47, 135 + (i * 20));
	}
	// wait until user input before continuing bakc to main menu
	pauseProgram ();
    }


    /*
    highscores method
    -----------------
    LOOPS:
    while A loops until the user enters a valid hgihscore username
    for A loops through the highscores file line-by-line and sets local variables accordingly
    for B loops from 0 to 9 to sort names and scores arrays from lowest to highest
    for C loops from 0 to 9 to save the newly updates and sorted arrays to the highscore file
    for D loops from 0 to 9 to print the highscore list to the screen

    SEGMENT BLOCKS:
    try A catches any IOExceptions thrown when reading the highscores file
    try B catches any IOExceptions thrown when writing the highscores file

    CONDITIONAL STATEMENTS:
    if A checks if the current score being looped through is lower the current lowest, and sets local varibles accoridngly
    if B checks if the new user score is lower than the previous lowest, and asks for the users name to update the highsocre list if so
    if C user's enetered name is longer than the max length of 10, shows an error if so, or else otherwise
    if D sets the loop variable to 1 if it is at or less than 0, which could reult in an error otherwise
    if E checks if the current score being looped through is less than the previous one, and sets local variables accordingly
    if F only runs if username is not null, to avoid errors, and else otherwise

    LOCAL VARIABLE DICTIONARY:
    NAME                TYPE            DESCRIPTION
    -----------------------------------------------
    r                   BufferedReader  holds FileReader to read highscores file
    w                   PrintWriter     holds PrintWriter to write highscores file
    currentLine         String          holds current line being read from
    username            String          holds name user enters if they get a highscore
    names               String array    holds names of highscores place holders
    scores              int array       holds scores of highscores palce holders
    lowestScore         int             lowest score from highscore list
    lowestScoreIndex    int             index of lowest score from highscore list in names and scores arrays
    tempInt             int             holds temporary integer value while switching scores index in array
    tempString          String          holds temporary String value while switching names index in array
    */
    public void highscores ()
    {
	// local variable declaration
	BufferedReader r;
	PrintWriter w;
	String currentLine = "";
	String username = "";
	String[] names = new String [10];
	int[] scores = new int [10];
	int lowestScore = 0;
	int lowestScoreIndex = 0;
	int tempInt;
	String tempString;

	// read highscores and set local vairables accordingly
	try // try A
	{
	    r = new BufferedReader (new FileReader ("highscores.hsc"));
	    currentLine = r.readLine ();
	    names [0] = currentLine.substring (0, currentLine.length () - 6);
	    scores [0] = Integer.parseInt (currentLine.substring (currentLine.length () - 3, currentLine.length ()));
	    lowestScore = scores [0];
	    for (int i = 1 ; i < 10 ; i++) // for A
	    {
		currentLine = r.readLine ();
		names [i] = currentLine.substring (0, currentLine.length () - 6);
		scores [i] = Integer.parseInt (currentLine.substring (currentLine.length () - 3, currentLine.length ()));
		if (scores [i] < lowestScore) // if A
		{
		    lowestScore = scores [i];
		    lowestScoreIndex = i;
		}
	    }
	    r.close ();
	}
	catch (IOException e)
	{
	}

	// if new user score is more than lowest score in list, get user name
	if (score > lowestScore) // if B
	{
	    while (username.equals ("")) // while A
	    {
		username = JOptionPane.showInputDialog ("Please enter your name:"); // citation: https://www.homeandlearn.co.uk/java/java_option_panes.html
		if (username != null) // if F
		{
		    if (username.length () > 10 || username.equals ("")) // if C
		    {
			JOptionPane.showMessageDialog (null, "Please enter fewer than 11 characters", "ERROR", JOptionPane.ERROR_MESSAGE);
			username = "";
		    }
		    else
		    {
			names [lowestScoreIndex] = username;
			scores [lowestScoreIndex] = score;
			score = 160;
		    }
		}
		else
		{
		    JOptionPane.showMessageDialog (null, "Please enter your name.", "ERROR", JOptionPane.ERROR_MESSAGE);
		    username = "";
		}
	    }
	}

	// sort array from lowest to highest
	for (int i = 0 ; i < 10 ; i++) // for B
	{
	    if (i <= 0) // if D
		i = 1;
	    if (scores [i] < scores [i - 1]) // if E
	    {
		tempInt = scores [i - 1];
		tempString = names [i - 1];
		scores [i - 1] = scores [i];
		names [i - 1] = names [i];
		scores [i] = tempInt;
		names [i] = tempString;
		i -= 2;
	    }
	}

	// save new array as highscores file
	try // try B
	{
	    w = new PrintWriter (new FileWriter ("highscores.hsc"));
	    for (int i = 0 ; i < 10 ; i++) // for C
	    {
		w.println (names [9 - i] + " - " + scores [9 - i]);
	    }
	    w.close ();
	}
	catch (IOException e)
	{
	}

	title (); // clear screen and draw title
	c.setColor (Color.white);
	c.setFont (new Font ("Tahoma", 1, 20));
	// print highscores to screen
	for (int i = 0 ; i < 10 ; i++) // for D
	{
	    c.drawString (i + 1 + ". " + names [9 - i] + " - " + scores [9 - i], 100 + i / 5 * 260, 100 + i * 60 - i / 5 * 300);
	}
	// wait for user input before continuing
	pauseProgram ();
    }


    /*
    translate method
    ----------------
    LOOPS:
    for A loops through the original text to print it line by line in the top textbox
    for B loops through the converted text to print it line by line in the bottom textbox
    CONDITIONAL STATEMENTS:
    if A checks current text file type and sets left to right textbox char limit accordingly
    if B checks the current text file type and runs the appropriate code to convert it to the opposite type, and sets local + global vairables accordingly

    LOCAL VARIABLE DICTIONARY:
    NAME        TYPE    DESCRIPTION
    -------------------------------
    chrLmt      int     number of characters from left to right in textbox
    */
    public void translate ()
    {
	// local variable declaration
	int chrLmt = 42;

	title (); // clear screen and draw title
	// explaining text
	c.setColor (Color.white);
	c.setFont (new Font ("Tahoma", 1, 18));
	c.drawString ("Your current text is:", 40, 70);
	// draw text box
	c.setColor (Color.black);
	c.fillRect (40, 85, 560, 148);
	c.setColor (Color.white);
	c.fillRect (42, 87, 556, 144);
	c.setColor (Color.black);
	// set char limit based on the current text filetype
	if (fileType == 1) // if A
	{
	    chrLmt = 77;
	    c.setFont (new Font ("Tahoma", 1, 12));
	}
	else
	    c.setFont (new Font ("Tahoma", 1, 15));
	// print text in textbox
	for (int i = 0 ; i < text.length () / chrLmt + 1 ; i++) // for A
	{
	    c.drawString (text.substring (i * chrLmt, Math.min (i * chrLmt + chrLmt, text.length ())), 48, 105 + (i * 20));
	}
	// convert text to opposite filetype
	if (fileType == 1) // if B
	{
	    text = convert (text, false);
	    fileType = 2;
	    chrLmt = 120;
	}
	else
	{
	    text = convert (text, true);
	    fileType = 1;
	    chrLmt = 42;
	}
	saved = false;
	// explaining text
	c.setColor (Color.white);
	c.setFont (new Font ("Tahoma", 1, 18));
	c.drawString ("Your translated text is:", 40, 280);
	// draw bottom textbox
	c.setColor (Color.black);
	c.fillRect (40, 295, 560, 163);
	c.setColor (Color.white);
	c.fillRect (42, 297, 556, 159);
	c.setColor (Color.black);
	c.setFont (new Font ("Tahoma", 1, 15));
	// draw converted text in bottom textbox
	for (int i = 0 ; i < text.length () / chrLmt + 1 ; i++) // for B
	{
	    c.drawString (text.substring (i * chrLmt, Math.min (i * chrLmt + chrLmt, text.length ())), 47, 315 + (i * 20));
	}
	// wait for user input before going back to menu
	pauseProgram ();
    }


    /*
    main method
    -----------
    LOOPS:
    the only while loop on the method is to keep looping until the user chooses to exit and the goodbye method is run (broken from inside the loop)
    CONDITIONAL STATEMENTS:
    all but the last if statement in this method are used to run the correct method with the right parameters for which option the user selected in the menu
    the last if statement in this method is used to check the choice variable was set 12 fro within the goodbye method to confirm closing the window and ending the program

    LOCAL VARIABLE DICTIONARY:
    NAME    TYPE                    DESCRIPTION
    -------------------------------------------
    v       EverythingMorseCode     class object vairable
    */
    public static void main (String[] args)
    {
	EverythingMorseCode e = new EverythingMorseCode ();
	e.splashscreen ();
	while (true)
	{
	    e.choice = 0; // reset choice variable
	    e.menu ();
	    if (e.choice == 1)
		e.instructions ();
	    if (e.choice == 2)
		e.highscores ();
	    if (e.choice == 3)
		e.learn ();
	    if (e.choice == 4)
		e.quiz ();
	    if (e.choice == 5)
		e.newFile ();
	    if (e.choice == 6)
		e.save (true);
	    if (e.choice == 7)
		e.save (false);
	    if (e.choice == 8)
		e.open ();
	    if (e.choice == 9)
		e.display ();
	    if (e.choice == 10)
		e.translate ();
	    if (e.choice == 11)
	    {
		e.goodbye ();
		if (e.choice == 12)
		    break;
	    }
	}
    }
}


