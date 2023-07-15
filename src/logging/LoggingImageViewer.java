package logging;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Image viewer program that logs various events.
 * @version 0.0.1 2023-07-14
 * @author Konstantine Vashalomidze
 */

public class LoggingImageViewer {
    public static void main(String[] args)
    {
        if (System.getProperty("java.util.logging.config.class") == null
            && System.getProperty("java.util.logging.config.file") == null)
        {
            try
            {
                Logger.getLogger("com.logging.LoggingImageViewer").setLevel(Level.ALL);
                final int LOG_ROTATION_COUNT = 10;
                var handler = new FileHandler("%h/LoggingImageViewer.log", 0, LOG_ROTATION_COUNT);
                Logger.getLogger("com.logging.LoggingImageViewer").addHandler(handler);
            }
            catch (IOException e)
            {
                Logger.getLogger("com.logging.LoggingIMageViewer").log(Level.SEVERE,
                        "Can't create log file handler", e);
            }
        }

        EventQueue.invokeLater(() ->
        {
            var windowHandler = new WindowHandler();
            windowHandler.setLevel(Level.ALL);
            Logger.getLogger("com.logging.LoggingImageViewer").addHandler(windowHandler);

            var frame = new ImageViewerFrame();
            frame.setTitle("LoggingImageViewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Logger.getLogger("com.logging.LoggingImageViewer");
            frame.setVisible(true);
        });
    }
}
