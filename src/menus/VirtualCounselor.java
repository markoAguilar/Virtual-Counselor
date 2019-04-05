package menus;

import data.ProgramFontsAndColors;

import javax.swing.*;
import java.awt.*;

public class VirtualCounselor extends JFrame{
    private JPanel mainPanel;
    private final int FRAME_WIDTH = 2000;
    private final int FRAME_HEIGHT = 2000;
    private Menu current_menu_panel;    // the Current panel to display example, MainMenu JPanel
    private String selectedMenu = "main"; // will help switch between JPanels it is reassigned
                                          // with a new String coming from each JPanel
    private ProgramFontsAndColors _mainFontsColors;

    /**
     * Initializes our Menu viewer frame with a MainMenu first
     */
    public VirtualCounselor() {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLayout(new BorderLayout());

        _mainFontsColors = new ProgramFontsAndColors();

        mainPanel = new JPanel();
        mainPanel.setBackground(_mainFontsColors.get_mainColor());
        current_menu_panel = new MainMenu();
        mainPanel.add(current_menu_panel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        run();
    }

    public void run()
    {
        while(true)
        {
            if(!current_menu_panel.isInUse() )
                switchPanel(current_menu_panel);
        }
    }

    public void switchPanel(Menu current_menu){
        remove(mainPanel);
        mainPanel.removeAll();
        setVisible(false);

        selectedMenu = current_menu.getNextMenu();
        if (selectedMenu.equals("main"))
            current_menu_panel = new MainMenu();
        else if (selectedMenu.equals("login"))
            current_menu_panel = new LogInMenu();
        else if (selectedMenu.equals("askquestion"))
            current_menu_panel = new AskQuestionMenu();
        else if (selectedMenu.equals("register"))
            current_menu_panel = new RegistrationMenu();
        mainPanel.add( current_menu_panel );
        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
    }
}