import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class xox implements ActionListener {
    private JFrame frame;
    private JButton button1, button2, button3;
    private JButton button4, button5, button6;
    private JButton button7, button8, button9;
    private JLabel scoreLabel;
    private int xScore = 0;
    private int oScore = 0;
    private boolean player1_turn = true;

    public xox() {
        // Pencere ayarları
        frame = new JFrame("XOX Oyunu");
        frame.setSize(300, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Skor paneli
        JPanel scorePanel = new JPanel();
        scoreLabel = new JLabel("X: 0 | O: 0");
        scorePanel.add(scoreLabel);
        frame.add(scorePanel, BorderLayout.NORTH);

        // Oyun paneli (3x3 butonlar)
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(3, 3, 5, 5)); // 3x3 grid ve aralarındaki boşluklar

        button1 = createButton();
        button2 = createButton();
        button3 = createButton();
        button4 = createButton();
        button5 = createButton();
        button6 = createButton();
        button7 = createButton();
        button8 = createButton();
        button9 = createButton();

        gamePanel.add(button1);
        gamePanel.add(button2);
        gamePanel.add(button3);
        gamePanel.add(button4);
        gamePanel.add(button5);
        gamePanel.add(button6);
        gamePanel.add(button7);
        gamePanel.add(button8);
        gamePanel.add(button9);

        frame.add(gamePanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private JButton createButton() {
        JButton button = new JButton();
        button.setFont(new Font("Arial", Font.PLAIN, 40));
        button.setFocusPainted(false);
        button.addActionListener(this);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        if (clickedButton.getText().equals("")) {
            if (player1_turn) {
                clickedButton.setText("X");
            } else {
                clickedButton.setText("O");
            }
            player1_turn = !player1_turn;
            check();
        }
    }

    public void check() {
        if (checkWin("X")) {
            JOptionPane.showMessageDialog(frame, "X Kazandı!");
            xScore++;
            updateScore();
            resetBoard();
        } else if (checkWin("O")) {
            JOptionPane.showMessageDialog(frame, "O Kazandı!");
            oScore++;
            updateScore();
            resetBoard();
        } else if (isBoardFull()) {
            JOptionPane.showMessageDialog(frame, "Berabere!");
            resetBoard();
        }
    }

    public boolean checkWin(String player) {
        // Kazanma koşullarını kontrol et
        return (button1.getText().equals(player) && button2.getText().equals(player) && button3.getText().equals(player)) ||
                (button4.getText().equals(player) && button5.getText().equals(player) && button6.getText().equals(player)) ||
                (button7.getText().equals(player) && button8.getText().equals(player) && button9.getText().equals(player)) ||
                (button1.getText().equals(player) && button4.getText().equals(player) && button7.getText().equals(player)) ||
                (button2.getText().equals(player) && button5.getText().equals(player) && button8.getText().equals(player)) ||
                (button3.getText().equals(player) && button6.getText().equals(player) && button9.getText().equals(player)) ||
                (button1.getText().equals(player) && button5.getText().equals(player) && button9.getText().equals(player)) ||
                (button3.getText().equals(player) && button5.getText().equals(player) && button7.getText().equals(player));
    }

    public boolean isBoardFull() {
        for (JButton button : new JButton[]{button1, button2, button3, button4, button5, button6, button7, button8, button9}) {
            if (button.getText().equals("")) {
                return false;
            }
        }
        return true;
    }

    public void updateScore() {
        scoreLabel.setText("X: " + xScore + " | O: " + oScore);
    }

    public void resetBoard() {
        for (JButton button : new JButton[]{button1, button2, button3, button4, button5, button6, button7, button8, button9}) {
            button.setText("");
        }
        player1_turn = true;
    }

    public static void main(String[] args) {
        new xox();
    }
}
