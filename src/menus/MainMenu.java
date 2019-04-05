package menus;

import data.CounselorPortrayal;
import data.ProgramFontsAndColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends Menu {
    private JPanel _buttonPanel;
    private JPanel _counselorPanel;
    private JTextArea _counselorNoteArea;
    private JButton _askQButton;
    private JButton _signinButton;
    private JButton _registerButton;
    private CounselorPortrayal _counselorPortrayalComponent;
    private ProgramFontsAndColors _mainFontsColors; // used to set Fonts and Colors

    public MainMenu(){
        _mainFontsColors = new ProgramFontsAndColors();
        _buttonPanel = new JPanel();
        _buttonPanel.setLayout(new GridLayout(3,1));
        _buttonPanel.setBackground(_mainFontsColors.get_mainColor());
        _counselorPanel = new JPanel();
        _counselorPanel.setBackground(_mainFontsColors.get_mainColor());
        setLayout(new BorderLayout());
        creatCounselorPanel();
        createButtonPanel();
        add(_buttonPanel, BorderLayout.SOUTH);
        add(_counselorPanel, BorderLayout.NORTH);
    }

    public void createButtonPanel(){
        _askQButton = new JButton("Ask A Question");
        _askQButton.setFont(_mainFontsColors.get_mainFont() );
        _askQButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setUse(false);
                setNextMenuSelection("askquestion");
            }
        });

        _signinButton = new JButton("Log In");
        _signinButton.setFont(_mainFontsColors.get_mainFont());
        _signinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setUse(false);
                setNextMenuSelection("login");
            }
        });

        _registerButton = new JButton("Register");
        _registerButton.setFont(_mainFontsColors.get_mainFont());
        _buttonPanel.add(_askQButton);
        _buttonPanel.add(_signinButton);
        _buttonPanel.add(_registerButton);
    }

    public void creatCounselorPanel(){
        _counselorPortrayalComponent = new CounselorPortrayal(); // creates a counselor with the default "greet" expression
        _counselorPortrayalComponent.setPreferredSize(new Dimension(300,350));

        _counselorNoteArea = new JTextArea();
        _counselorNoteArea.setEditable(false);
        _counselorNoteArea.setFont(_mainFontsColors.get_mainFont());
        _counselorNoteArea.append("Hi there!\nChoose from the options below\nto get started!");
        _counselorNoteArea.setBackground(_mainFontsColors.get_mainColor());

        _counselorPanel.add(_counselorPortrayalComponent);
        _counselorPanel.add(_counselorNoteArea);
    }
}
