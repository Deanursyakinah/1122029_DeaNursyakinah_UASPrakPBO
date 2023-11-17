package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import controller.Controller;
import model.Games;
import model.Transaction;
import model.Users;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameList {
    Controller control = new Controller();
    JFrame jframe;
    JButton trans;

    public GameList(Users loggedInUser) {
        jframe = new JFrame();
        jframe.setTitle("Game List");
        jframe.setSize(500, 300);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setLayout(null);

        trans = new JButton("Transation Menu");
        trans.setBounds(10, 10, 130, 20);
        trans.setEnabled(true);
        jframe.add(trans);

        trans.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TransactionsMenu();
            }
        });

        ArrayList<Games> listGames = control.getAllGames();
        int yNya = 60; // buat rapihin
        for (int i = 0; i < listGames.size(); i++) {
            JTextField namaGame = new JTextField(listGames.get(i).getName());
            namaGame.setBounds(10, yNya, 100, 20);
            jframe.add(namaGame);

            JTextField genreGame = new JTextField(listGames.get(i).getGenre());
            genreGame.setBounds(120, yNya, 100, 20);
            jframe.add(genreGame);

            JTextField priceGame = new JTextField(String.valueOf(listGames.get(i).getPrice()));
            priceGame.setBounds(230, yNya, 100, 20);
            jframe.add(priceGame);

            int gameId = listGames.get(i).getId(); 
            JButton buyButton = new JButton("Buy");
            buyButton.setEnabled(true);
            buyButton.setBounds(340, yNya, 70, 20);

            buyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Transaction newTransaction = new Transaction();
                    newTransaction.setUser_id(loggedInUser.getId());
                    newTransaction.setGame_id(gameId);
                    boolean success = control.insertNewTransaction(newTransaction);
                    if (success) {
                        JOptionPane.showMessageDialog(jframe, "pembelian sukses!");
                    } else {
                        JOptionPane.showMessageDialog(jframe, "pembelian gagal!");
                    }
                }
            });
            yNya += 30;
            jframe.add(buyButton);
        }

        jframe.setVisible(true);
    }
}
