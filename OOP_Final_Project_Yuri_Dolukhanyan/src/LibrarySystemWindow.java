// Introduction to Object-Oriented Programming (Sec. A) - Spring 2023
// Final Project - LibrarySystemWindow Class
// Yuri Dolukhanyan


import packages.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibrarySystemWindow extends JFrame {
    private JPanel mainPanel;
    private JList list1;
    private JButton button1;
    private JButton button2;
    private DefaultListModel objModel;

    private Person[] arr;
    private String book_name = "";

    private void renderPerson() {
        objModel.removeAllElements();

        for (Person i : arr) {
            if (i instanceof Librarian) {
                objModel.addElement("Librarian: " + i.getName() + " " + i.getSurname() + " and his/her books:");
                for (int j = 0; j < ((Librarian) i).getBooks().length; j++) {
                    if (((Librarian) i).getBooks()[j] != null) {
                        objModel.addElement(String.valueOf(((Librarian) i).getBooks()[j]));
                    }
                }
            }
        }
    }

    public LibrarySystemWindow(Library lib) {

        super("Library System Window");

        arr = lib.getEmployees();

        setSize(new Dimension(600, 400));

        setContentPane(mainPanel);

        objModel = new DefaultListModel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        list1.setModel(objModel);

        renderPerson();

        setVisible(true);

        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                if (list1.getSelectedIndex() == -1) {
                    button1.setEnabled(false);
                } else {
                    button1.setEnabled(true);
                }

                if (((String) list1.getSelectedValue()) != null && list1.getSelectedIndex() != 0 && !(((String) list1.getSelectedValue()).contains("Librarian"))) {
                    book_name = ((String) list1.getSelectedValue()).substring(((String) list1.getSelectedValue()).indexOf(':') + 2, ((String) list1.getSelectedValue()).indexOf(','));
                }
            }
        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Person i : arr) {
                    if (i instanceof Librarian) {
                        for (int j = 0; j < ((Librarian) i).getBooks().length; j++) {
                            if (((Librarian) i).getBooks()[j] != null) {
                                if (String.valueOf(((Librarian) i).getBooks()[j].getTitle()).equals(book_name)) {
                                    ((Librarian) i).removeBook(((Librarian) i).getBooks()[j]);
                                    renderPerson();
                                }
                            }
                        }
                    }
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddNewBook dialog = new AddNewBook(lib);
                dialog.pack();
                dialog.setVisible(true);
                renderPerson();
            }
        });
    }
}