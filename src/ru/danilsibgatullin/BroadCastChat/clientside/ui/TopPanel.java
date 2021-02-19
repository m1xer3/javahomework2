package ru.danilsibgatullin.BroadCastChat.clientside.ui;

import javax.swing.*;
import java.awt.*;

public class TopPanel extends JPanel {

    private Image drawImage;

    public TopPanel(Image drawImage) {
        super();
        this.drawImage = drawImage;
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        g.drawImage(drawImage,20,20,null);
    }
}
