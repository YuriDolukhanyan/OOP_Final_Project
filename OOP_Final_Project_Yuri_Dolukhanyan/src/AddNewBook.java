// Introduction to Object-Oriented Programming (Sec. A) - Spring 2023
// Final Project - AddNewBook Class
// Yuri Dolukhanyan

import packages.*;

import javax.swing.*;
import java.awt.event.*;

public class AddNewBook extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JTextArea textArea3;
    private JTextArea textArea4;
    private JTextArea textArea5;
    private Person[] arr;

    public AddNewBook(Library lib) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.setEnabled(false);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                arr = lib.getEmployees();

                for (Person i : arr) {
                    if (i instanceof Librarian) {
                        if (textArea5.getText().equals(i.getName())) {
                            Book b_n = new Book(textArea1.getText(), textArea2.getText(), Integer.parseInt(textArea3.getText()), Float.parseFloat(textArea4.getText()));
                            ((Librarian) i).addBook(b_n);
                        }
                    }
                }

                dispose();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        textArea1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (textArea1.getText().equals("") || textArea2.getText().equals("") || textArea3.getText().equals("") || textArea4.getText().equals("")) {
                    buttonOK.setEnabled(false);
                } else {
                    buttonOK.setEnabled(true);
                }
            }
        });
        textArea2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (textArea1.getText().equals("") || textArea2.getText().equals("") || textArea3.getText().equals("") || textArea4.getText().equals("")) {
                    buttonOK.setEnabled(false);
                } else {
                    buttonOK.setEnabled(true);
                }
            }
        });
        textArea3.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (textArea1.getText().equals("") || textArea2.getText().equals("") || textArea3.getText().equals("") || textArea4.getText().equals("")) {
                    buttonOK.setEnabled(false);
                } else {
                    buttonOK.setEnabled(true);
                }
            }
        });
        textArea4.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (textArea1.getText().equals("") || textArea2.getText().equals("") || textArea3.getText().equals("") || textArea4.getText().equals("")) {
                    buttonOK.setEnabled(false);
                } else {
                    buttonOK.setEnabled(true);
                }
            }
        });
        textArea5.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (textArea1.getText().equals("") || textArea2.getText().equals("") || textArea3.getText().equals("") || textArea4.getText().equals("")) {
                    buttonOK.setEnabled(false);
                } else {
                    buttonOK.setEnabled(true);
                }
            }
        });
    }

    private void onOK() {

    }

    private void onCancel() {
        dispose();
    }

    public static void main(String[] args) {

    }
}