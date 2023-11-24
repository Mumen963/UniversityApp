package ui;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;


// responsible for displaying a splash screen with an image for a specified duration before executing a callback
public class SplashScreen {

    private static final String SPLASH_IMAGE_PATH = "./data/visualComponent.png";

    //EFFECTS: Initializes a new SplashScreen with the given callback
    public SplashScreen(Runnable callback) {
        displaySplashScreen(callback);
    }

    //EFFECTS: Executes the callback after the splash screen duration.
    private void displaySplashScreen(Runnable callback) {
        ImageIcon splashImage = new ImageIcon(SPLASH_IMAGE_PATH);
        if (splashImage != null) {
            JLabel splashLabel = new JLabel(splashImage);
            splashLabel.setHorizontalAlignment(JLabel.CENTER);

            JWindow splashScreen = new JWindow();
            splashScreen.getContentPane().add(splashLabel, BorderLayout.CENTER);
            splashScreen.setSize(splashImage.getIconWidth() - 370, splashImage.getIconHeight() - 370);
            splashScreen.setLocationRelativeTo(null);
            splashScreen.setVisible(true);

            java.util.Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    splashScreen.dispose();
                    callback.run();
                    timer.cancel();
                }
            }, 3000);
        }
    }

    // EFFECTS: Initializes the SplashScreen and then executes the callback
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SplashScreen(() -> SwingUtilities.invokeLater(UniversityGUI::new)));
    }
}