package mangopoly;
import java.awt.*;
import java.awt.geom.AffineTransform;
import javax.swing.*;

public class RotatedLabel extends JLabel {
    private double angle;

    public RotatedLabel(String text, double angle) {
        super(text);
        this.angle = angle;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        // Save the original transform
        AffineTransform originalTransform = g2.getTransform();

        // Apply rotation around the center of the label
        int x = getWidth() / 2;
        int y = getHeight() / 2;
        g2.rotate(Math.toRadians(angle), x, y);

        // Turn on antialiasing for better text quality
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Call the superclass's paintComponent to draw the text with the new transform
        super.paintComponent(g2);

        // Restore the original transform
        g2.setTransform(originalTransform);
    }
}
