package logging;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImageViewerFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 400;

    private JLabel label;
    private static Logger logger = Logger.getLogger("com.logging.LoggingImageViewer");

    public ImageViewerFrame()
    {
        logger.entering("ImageViewerFrame", "<init>");
        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // Set up menu bar
        var menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        var menu = new JMenu("File");
        menuBar.add(menu);

        var openItem = new JMenuItem("Open");
        menu.add(openItem);
        openItem.addActionListener(new FileOpenListener());

        var exitItem = new JMenuItem("Exit");
        menu.add(exitItem);

        exitItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                logger.fine("Exiting.");
                System.exit(0);
            }
        });

        // Use a label to display the images
        this.label = new JLabel();
        this.add(this.label);
        logger.exiting("ImageViewerFrame", "<init>");
    }

    private class FileOpenListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent event) {
            logger.entering("ImageViewerFrame.FileOpenListener", "actionPerformed", event);

            // Set up file chooser
            var chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("."));

            // Accept al files ending with .gif
            chooser.setFileFilter(new javax.swing.filechooser.FileFilter()
            {

                @Override
                public boolean accept(File f) {
                    return f.getName().toLowerCase().endsWith(".gif") || f.isDirectory();
                }

                @Override
                public String getDescription() {
                    return "GIF Images";
                }
            });

            // Show file chooser dialog
            int r = chooser.showOpenDialog(ImageViewerFrame.this);

            // If image file accepted, set it as icon of the label
            if (r == JFileChooser.APPROVE_OPTION)
            {
                String name = chooser.getSelectedFile().getPath();
                logger.log(Level.FINE, "Reading file {0}", name);
                label.setIcon(new ImageIcon(name));
            }
            else logger.fine("File open dialog canceled.");
            logger.exiting("ImageViewerFrm.FileOpenListener", ":actionPerformed");


        }

    }


}
