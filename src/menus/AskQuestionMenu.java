package menus;

// my packages
import data.CounselorPortrayal;
import data.ProgramFontsAndColors;

// Java packages
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A JPanel class that extends the Menu class. It will hold the GUI components that are
 * responsible for getting question input, searching for an answer and displaying that
 * answer to the user. If there are multiple matches for the user's question, all
 * matches will be displayed to the user and the user will choose which question they
 * want the answer for.
 */
public class AskQuestionMenu extends Menu {
    private JLabel _questionFieldLabel;     // IN - for getting user's question
    private JLabel _answerFieldLabel;
    private JTextField _questionField;
    private JTextArea _answerField;         // OUT - for displaying an answer to the user
    private JScrollPane _answerFieldScroller;
    private JButton _submitQButton;
    private JButton _doneButton;
    private JPanel _fieldsPanel;
    private ClassLoader classLoader;

    private JPanel _csCounselorPanel;
    private JTextArea _counselorNoteArea;
    private String _question;
    private File _fileIn;
    private Scanner _fileScan;
    private ArrayList<String> _qAndA;
    private ArrayList<String> _questionMatches;
    private JPanel _matchesPanel;
    private ArrayList<JButton> _questionMatchButtons; // add a counter before each button
    private CounselorPortrayal _counselorPortrayalComponent;         // OUT - for displaying the counselor portrayal
    private ProgramFontsAndColors _fontsAndColors;

    /**
     * Initializes all the components.
     */
    public AskQuestionMenu(){
        classLoader = getClass().getClassLoader(); // sets the classLoader

        _qAndA = new ArrayList<>();
        _fontsAndColors = new ProgramFontsAndColors(); // sets the font and colors to the ones I created

        _questionFieldLabel = new JLabel("Question:");
        _questionField = new JTextField();

        _answerFieldLabel = new JLabel("Answer:");
        _answerField = new JTextArea("",10,5);
        _answerFieldScroller = new JScrollPane(_answerField);

        _fieldsPanel = new JPanel();
        _fieldsPanel.setLayout(new GridLayout(6,2) );
        _fieldsPanel.setBackground(_fontsAndColors.get_mainColor());
        _fieldsPanel.setPreferredSize(new Dimension(800,500));

        _csCounselorPanel = new JPanel();
        _counselorPortrayalComponent = new CounselorPortrayal("speak");
        _counselorNoteArea = new JTextArea();
        _csCounselorPanel.setBackground(_fontsAndColors.get_mainColor());

        setBackground(_fontsAndColors.get_mainColor());
        setLayout( new BorderLayout() );

        getQandA();
        createComponents();

        add(_fieldsPanel, BorderLayout.SOUTH);
        add(_csCounselorPanel, BorderLayout.NORTH);
    }

    /**
     * creates the components for the AskQuestion object.
     * Creates the JTextArea for the answer output the JText field for the question input
     * The buttons for done and submit question and the virtual counselor portrayal.
     */
    public void createComponents(){

        // Fields and Labels
        _questionFieldLabel.setFont(_fontsAndColors.get_mainFont());
        _questionField.setFont(_fontsAndColors.get_mainFont());
        _questionField.setEditable(true);

        _answerFieldLabel.setFont(_fontsAndColors.get_mainFont());
        _answerField.setEditable(false);
        _answerField.setFont(_fontsAndColors.get_mainFont() );
        _answerFieldScroller.setWheelScrollingEnabled(true);

        // creating the counselor image to show.
        _counselorNoteArea.setBackground(_fontsAndColors.get_mainColor());
        _counselorNoteArea.setFont(_fontsAndColors.get_mainFont());
        _counselorNoteArea.append("Enter a question.\nI'll try to give you an answer.");
        _counselorNoteArea.setEditable(false);
        _counselorPortrayalComponent.setPreferredSize(new Dimension(500,500));

        // Buttons
        _doneButton = new JButton("Done");
        _doneButton.setFont(_fontsAndColors.get_mainFont());
        _doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setUse(false);
                setNextMenuSelection("main");
                _fileScan.close();
            }
        });

        _submitQButton = new JButton("Submit");
        _submitQButton.setFont(_fontsAndColors.get_mainFont());
        _submitQButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                _answerField.setText("");
                _question = _questionField.getText().toLowerCase();
                getAnswer();
            }
        });

        // adding components together
        _fieldsPanel.add(_questionFieldLabel);
        _fieldsPanel.add(_questionField);
        _fieldsPanel.add(_answerFieldLabel);
        _fieldsPanel.add(_answerFieldScroller);
        _fieldsPanel.add(_submitQButton);
        _fieldsPanel.add(_doneButton);
        _csCounselorPanel.add(_counselorPortrayalComponent);
        _csCounselorPanel.add(_counselorNoteArea);
    }

    public void getAnswer()
    {
        String locQuestionLine;
        String locAnswerLine;

        for(int counter = 0; counter < _qAndA.size(); counter+=2) // skips over the answer
        {
            locQuestionLine = _qAndA.get(counter);
            if (_question.equals(locQuestionLine)) {
                locAnswerLine = _qAndA.get(counter+1);
                _answerField.setText(locAnswerLine);    // sets the answer after the _question
                return;                                  // stop search once answer is set
            }
        }
        if(_answerField.getText().equals("")) // if the answer field is still empty
        {
            getNextClosest();
        }
    }

    public void getQandA()
    {
        String locLine;
        try{
            //Note&ask why here your fileName does not require '/' but in CounselorPortrayal.java...
            _fileIn = new File( classLoader.getResource("qandA.txt").getFile() );
            _fileScan = new Scanner(_fileIn);
        }
        catch(FileNotFoundException exception){
            System.out.println("File For QandA Not Found");
        }
        while(_fileScan.hasNext()){
            locLine = _fileScan.nextLine();
            _qAndA.add(locLine);
        }
        _fileScan.close();
    }

    public void getNextClosest()
    {
        _questionMatches = new ArrayList<>();
        Scanner locLineScan;
        int locMatchCounter;
        int nextHighScore;
        String currentWord;
        ArrayList<Integer> matchScores;

        matchScores = new ArrayList<>();
        // look for highest keyword match
        for(String line : _qAndA)
        {
            //System.out.println("line: " + line);
            locMatchCounter = 0;
            locLineScan = new Scanner(line);
            locLineScan.useDelimiter("([^A-Za-z]+)");
            while(locLineScan.hasNext())
            {
                currentWord = locLineScan.next();
                if (line.contains(currentWord) )
                    locMatchCounter++;
                //System.out.println("Current Word:" + currentWord);
            }
            matchScores.add(locMatchCounter);
        }

        nextHighScore = -1;
        for(int counter=0; counter< matchScores.size(); counter++)
        {
            if(matchScores.get(counter) == nextHighScore & nextHighScore!=-1) // multiple matches
                _questionMatches.add(_qAndA.get(counter));
            else if(matchScores.get(counter)>nextHighScore)
                nextHighScore = matchScores.get(counter);
        }

        if(_questionMatches.size()==0)
            _answerField.append(_qAndA.get(matchScores.indexOf(nextHighScore)));
        else
            showMultipleMatches();
    }

    public void showMultipleMatches()
    {
        remove(_fieldsPanel);
        setVisible(false);
        _matchesPanel = new JPanel();
        // create a panel with rows equal to the number of question matches
        _matchesPanel.setLayout(new GridLayout(_questionMatches.size(), 1) );

        int counter = 0;
        JButton matchButton;
        for (String match : _questionMatches)
        {
            matchButton = new JButton(match);
            matchButton.setFont(_fontsAndColors.get_mainFont());
            matchButton.setText(match);
            matchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                    remove(_matchesPanel);
                    add(_fieldsPanel, BorderLayout.SOUTH);
                    setVisible(true);
                    _answerField.append("A question match was chosen");
                    setAnswer();
                }
            });
            _matchesPanel.add(matchButton);
            counter++;
        }
        add(_matchesPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    public void setAnswer()
    {
        // couldn't figure how to add the answer to _answerField after button press
        // getText() would not work. No way of keeping track of which button to get text
        // from so added the string "a question match was chosen"
    }
}