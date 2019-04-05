package menus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel {
    private String nextMenuSelection;
    private boolean inUse = true;

    public Boolean isInUse(){ // have this return string "on" or "nextMenuSelection"
        return inUse;
    }

    public void setNextMenuSelection(String next){nextMenuSelection = next;}
    public String getNextMenu(){ return nextMenuSelection;}

    public void setUse(boolean stateIn){inUse = stateIn;}
}
