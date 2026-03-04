package mangopoly;
import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

public class RotatedButton extends JButton {

    private double angle = 0; // Angle in radians

    public RotatedButton(String text, double angle) {
        super(text);
        this.angle = angle;
        // The button's size needs to accommodate the rotated text
        // You may need to override getPreferredSize() for proper layout
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Perform standard button painting first (background, border, etc.)
        // We'll skip the super.paintComponent() call here to fully control drawing,
        // but a complete implementation would need to handle background/border drawing
        // more thoroughly or use a custom UI delegate.

        Graphics2D g2 = (Graphics2D) g.create();
        
        // Enable anti-aliasing for smooth text
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Get text dimensions for centering
        Font font = getFont();
        FontRenderContext frc = g2.getFontRenderContext();
        double textWidth = font.getStringBounds(getText(), frc).getWidth();
        double textHeight = font.getStringBounds(getText(), frc).getHeight();

        // Calculate translation coordinates to rotate around the center of the text
        int x = (getWidth() - (int) textWidth) / 2;
        int y = (getHeight() + (int) textHeight) / 2 - getInsets().bottom;

        // Apply rotation
        g2.rotate(angle, getWidth() / 2, getHeight() / 2);

        // Draw the text
        g2.setColor(getForeground());
        g2.drawString(getText(), x, y);

        // Restore the original graphics transform
        g2.dispose();
    }

    public void setAngle(double angle) {
        this.angle = angle;
        repaint(); // Repaint the button with the new angle
    }
}
