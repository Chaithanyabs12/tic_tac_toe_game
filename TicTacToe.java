import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe {
    
    private static final String PLAYER_X = "X";
    private static final String PLAYER_O = "O";
    private String currentPlayer = PLAYER_X;
    private JButton[] buttons = new JButton[9];
    private boolean gameOver = false;

    public static void main(String[] args) {
        new TicTacToe().createAndShowGUI();
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 100));
            buttons[i].setFocusPainted(false);
            buttons[i].addActionListener(new ButtonClickListener(i));
            frame.add(buttons[i]);
        }

        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        private int index;

        public ButtonClickListener(int index) {
            this.index = index;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!gameOver && buttons[index].getText().equals("")) {
                buttons[index].setText(currentPlayer);
                if (checkWin()) {
                    JOptionPane.showMessageDialog(null, currentPlayer + " Wins!");
                    gameOver = true;
                } else if (isBoardFull()) {
                    JOptionPane.showMessageDialog(null, "It's a Draw!");
                    gameOver = true;
                } else {
                    currentPlayer = (currentPlayer.equals(PLAYER_X)) ? PLAYER_O : PLAYER_X;
                }
            }
        }
    }

    private boolean checkWin() {
        // Check rows, columns, and diagonals
        int[][] winPatterns = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Rows
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Columns
            {0, 4, 8}, {2, 4, 6} // Diagonals
        };

        for (int[] pattern : winPatterns) {
            if (buttons[pattern[0]].getText().equals(currentPlayer) &&
                buttons[pattern[1]].getText().equals(currentPlayer) &&
                buttons[pattern[2]].getText().equals(currentPlayer)) {
                return true;
            }
        }
        return false;
    }

    private boolean isBoardFull() {
        for (JButton button : buttons) {
            if (button.getText().equals("")) {
                return false;
            }
        }
        return true;
    }
}
