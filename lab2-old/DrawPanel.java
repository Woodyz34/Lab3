import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represent the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    // Just a single image, TODO: Generalize
    protected BufferedImage carImage;
    // To keep track of a single cars position
    protected Point carPoint = new Point();

    public void moveit(int x, int y){
        carPoint.x = x;
        carPoint.y = y;
    }
    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.cyan);


    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Cars car : CarController.cars) {
            try {
                carImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/" + car.modelName + ".jpg"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            g.drawImage(carImage, (int) car.x, carPoint.y, null);
            carPoint.y += 100;
        }
        //g.drawImage(volvoImage, carPoint.x, carPoint.y, null); // see javadoc for more info on the parameters
        //g.drawImage(saabImage, carPoint.x, carPoint.y + 100, null);
    }
}
