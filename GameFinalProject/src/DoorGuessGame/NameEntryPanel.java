package DoorGuessGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NameEntryPanel extends JPanel {
    private JTextField nameField;
    private JButton submitButton;
    private DoorGameGUI parentGame;

    public NameEntryPanel(DoorGameGUI parentGame) {
        this.parentGame = parentGame;
        setLayout(new FlowLayout());

        add(new JLabel("Enter your name: "));
        nameField = new JTextField(15);
        add(nameField);

        submitButton = new JButton("Submit Name");
        add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override 																					//This is where i tried to implament a overriden method
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText().trim();
                    parentGame.setPlayer(new Player(name));
                    parentGame.switchToGameMode();
                } catch (CustomExceptions.InvalidNameException ex) {
                    JOptionPane.showMessageDialog(NameEntryPanel.this, ex.getMessage());
                }
            }
        });
    }
}
