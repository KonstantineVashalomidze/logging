package logging;

import javax.swing.*;
import java.io.OutputStream;
import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;

/**
 * A handler for displaying log records in a window.
 */

public class WindowHandler extends StreamHandler {
    private JFrame frame;

    public WindowHandler()
    {
        this.frame = new JFrame();
        var output = new JTextArea();
        output.setEditable(false);
        this.frame.setSize(200, 200);
        this.frame.add(new JScrollPane());
        this.frame.setFocusableWindowState(false);
        this.frame.setVisible(true);

        this.setOutputStream(new OutputStream()
        {
            public void write(int b)
            {
                // not called
            }

            public void write(byte[] b, int off, int len)
            {
                output.append(new String(b, off, len));
            }
        });
    }
    public void publish(LogRecord record)
    {
        if (!this.frame.isVisible()) return;
        super.publish(record);
        this.flush();
    }

}
