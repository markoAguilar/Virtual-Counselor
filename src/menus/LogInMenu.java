package menus;

import data.CounselorPortrayal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInMenu extends Menu {
    private JLabel idFieldLabel;
    private JTextField iDField;
    private JLabel passFieldLabel;
    private JTextField passwordField;
    private CounselorPortrayal _counselorPortrayalComponent;
    private JButton doneButton;

    public LogInMenu(){
        createComponents();
    }

    public void createComponents(){
        setLayout(new GridLayout(2,1) );

        idFieldLabel = new JLabel("ID:");
        iDField = new JTextField();
        iDField.setEditable(true);
        add(idFieldLabel);
        add(iDField);

        passFieldLabel = new JLabel("Password");
        passwordField = new JTextField();
        passwordField.setEditable(true);
        add(passFieldLabel);
        add(passwordField);

        doneButton = new JButton("DONE");
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                setUse(false);
                setNextMenuSelection("main");
            }
        });

        add(doneButton, BorderLayout.SOUTH);
    }

}
