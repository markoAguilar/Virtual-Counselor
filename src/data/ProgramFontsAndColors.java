package data;

import java.awt.*;

public class ProgramFontsAndColors {
    private Font _mainFont;
    private Color _mainColor;

    public ProgramFontsAndColors(){
        _mainFont = new Font("Ariel", Font.BOLD, 30);
        _mainColor = new Color(91, 155,228);
    }

    public Font get_mainFont(){ return _mainFont; }
    public Color get_mainColor(){return _mainColor; }
}
