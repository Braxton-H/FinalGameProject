package DoorGuessGame;

import javax.swing.*;
import java.awt.*;

public class MessagePanel extends JPanel {
    private JLabel messageLabel;

    public MessagePanel() {
        setLayout(new BorderLayout());
        messageLabel = new JLabel("Please enter your name to begin.", SwingConstants.CENTER);
        add(messageLabel, BorderLayout.CENTER);
    }

    public void setMessage(String message) {
        messageLabel.setText(message);
    }
}
