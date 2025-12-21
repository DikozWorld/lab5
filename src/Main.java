import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame {
    private final DefaultListModel<String> listModel;
    private final JList<String> itemList;
    private final JTextField textField;

    public Main() {
        setTitle("Редактор списка - Lab5");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        listModel = new DefaultListModel<>();
        itemList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(itemList);

        textField = new JTextField(20);

        // Локальные переменные для кнопок
        JButton addButton = new JButton("Добавить");
        JButton deleteButton = new JButton("Удалить");

        // Текстовое поле
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        add(textField, gbc);

        // Кнопка "Добавить"
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        add(addButton, gbc);

        // Кнопка "Удалить"
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(deleteButton, gbc);

        // Список
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(scrollPane, gbc);

        // Обработчики (можно оставить анонимные классы для совместимости)
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