import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame {
    private DefaultListModel<String> listModel;
    private JList<String> itemList;
    private JTextField textField;
    private JButton addButton;
    private JButton deleteButton;

    public Main() {
        setTitle("Редактор списка");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Используем GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Создаем модель для списка
        listModel = new DefaultListModel<>();
        itemList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(itemList);

        // Текстовое поле для ввода
        textField = new JTextField(20);

        // Кнопки
        addButton = new JButton("Добавить");
        deleteButton = new JButton("Удалить");

        // Располагаем компоненты

        // Текстовое поле
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(textField, gbc);

        // Кнопка "Добавить"
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(addButton, gbc);

        // Кнопка "Удалить"
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(deleteButton, gbc);

        // Список с прокруткой
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(scrollPane, gbc);

        // Обработчики событий
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addItem();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteSelectedItem();
            }
        });

        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addItem();
            }
        });
    }

    private void addItem() {
        String text = textField.getText().trim();
        if (!text.isEmpty()) {
            listModel.addElement(text);
            textField.setText("");
            textField.requestFocus();
        }
    }

    private void deleteSelectedItem() {
        int selectedIndex = itemList.getSelectedIndex();
        if (selectedIndex != -1) {
            listModel.remove(selectedIndex);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Main editor = new Main();
                editor.setVisible(true);
            }
        });
    }
}