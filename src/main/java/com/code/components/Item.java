package com.code.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

/**
 *
 * @author Gian
 */
public class Item extends JButton {

    private Image iconImg;
    private Dimension sizeImg = new Dimension(40, 40);
    //private final Animation animation;

    public Item() {
        setContentAreaFilled(false);
        setBorder(null);
        //  animation = new Animation(this);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                //        animation.mouseEnter();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //      animation.mouseExit();
            }

        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR
        );
        int w = getWidth();
        int h = getHeight();
        int x = (w - sizeImg.width) / 2;
        int y = (h - sizeImg.height) / 2;
        g2.drawImage(
                iconImg,
                x,
                y,
                sizeImg.width,
                sizeImg.height,
                null
        );
        g2.dispose();
        super.paintComponent(g);

    }

    public void setIconImg(Image iconImg) {
        this.iconImg = iconImg;
    }

    public void setSizeImg(Dimension sizeImg) {
        this.sizeImg = sizeImg;
        repaint();
    }

    public Dimension getSizeImg() {
        return sizeImg;
    }

}
