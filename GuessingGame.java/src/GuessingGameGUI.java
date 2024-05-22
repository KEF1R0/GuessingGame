import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessingGameGUI extends JFrame {

    private JLabel messageLabel;
    private JTextField guessField;
    private JButton guessButton;
    private int secretNumber;
    private int guessCount;
    private int maxGuesses = 7; // Количество попыток

    public GuessingGameGUI() {
        super("Угадай число");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null); // Центрирование окна

        // Создаем компоненты
        messageLabel = new JLabel("Я загадал число от 1 до 100. У вас есть " + maxGuesses + " попыток.");
        guessField = new JTextField(5);
        guessButton = new JButton("Угадать");

        // Создаем панель для компоновки элементов
        JPanel contentPane = new JPanel(new FlowLayout());
        contentPane.add(messageLabel);
        contentPane.add(guessField);
        contentPane.add(guessButton);

        // Устанавливаем панель в качестве содержимого окна
        setContentPane(contentPane);

        // Настраиваем обработчик событий для кнопки "Угадать"
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guessCount++;
                try {
                    int guess = Integer.parseInt(guessField.getText());
                    guessField.setText(""); // Очищаем поле ввода

                    if (guess == secretNumber) {
                        messageLabel.setText("Поздравляю! Вы угадали число за " + guessCount + " попыток.");
                        guessButton.setEnabled(false); // Деактивируем кнопку
                    } else if (guess < secretNumber) {
                        messageLabel.setText("Слишком мало. Попробуйте еще раз.");
                    } else {
                        messageLabel.setText("Слишком много. Попробуйте еще раз.");
                    }

                    if (guessCount == maxGuesses) {
                        messageLabel.setText("У вас закончились попытки. Загаданное число было: " + secretNumber);
                        guessButton.setEnabled(false);
                    }
                } catch (NumberFormatException ex) {
                    messageLabel.setText("Введите число!");
                }
            }
        });

        // Загадываем случайное число
        secretNumber = new Random().nextInt(100) + 1;
        guessCount = 0;

        setVisible(true);
    }

    public static void main(String[] args) {
        new GuessingGameGUI();
    }
}
