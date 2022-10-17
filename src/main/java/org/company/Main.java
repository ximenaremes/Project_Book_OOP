package org.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static JButton Adaugare, Listare, Iesire, Modifica, Sterge,Revenire,saveButton;
    private static JTextField NumeText, AutorText, NumarText, AnText, LocText;
    private static JList<String>Lista;
    private static DefaultListModel<String>ListaCarteModel;
    private static List<Carte> ListaCarte = new ArrayList<>();
    private static JFrame frame;

    public static void main(String[] args) {
        JPanel jPanel = buildPanel();
        addActionToButonAdaugare(jPanel);
       ListaCarte = Fisier.readFromFile();
    }
    private static JPanel buildPanel() {
        frame = new JFrame("Meniu");
        frame.setSize(490, 630);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents1(panel);
        frame.setVisible(true);
        return panel;
    }
    private static void placeComponents1(JPanel panel) {

        panel.setLayout(null);

        Adaugare = new JButton("Adaugarea obiect");
        Adaugare.setBounds(130, 120, 180, 35);
        Adaugare.setBackground(new java.awt.Color(165, 42, 42));
        Adaugare.setForeground(Color.WHITE);
        Adaugare.setFont(new Font("Serif", Font.BOLD, 15));
        panel.add(Adaugare);

        Listare = new JButton("Listare obiecte");
        Listare .setBounds(130, 200, 180, 35);
        Listare .setBackground(new java.awt.Color(165, 42, 42));
        Listare .setForeground(Color.WHITE);
        Listare.setFont(new Font("Serif", Font.BOLD, 15));
        panel.add(Listare);

        Iesire = new JButton("Parasire program");
        Iesire.setBounds(130, 280, 180, 35);
        Iesire.setBackground(new java.awt.Color(165, 42, 42));
        Iesire.setForeground(Color.WHITE);
        Iesire.setFont(new Font("Serif", Font.BOLD, 15));
        panel.add( Iesire);
        Iesire.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                       Fisier.writeToFile(ListaCarte);
                        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                        /*System.exit(0);*/
                    }
                });

        addActionToButonListare(panel); }

    private static void addActionToButonAdaugare(JPanel panel) {
        Adaugare.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        panel.removeAll();
                        panel.updateUI();
                        placeComponents2(panel); }
                });
    }

    private static void addActionToButonListare(JPanel panel) {
        Listare.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        panel.removeAll();
                        panel.updateUI();
                        placeComponents3(panel);
                    }
                });
    }

    private static void placeComponents2(JPanel panel)
    {
        panel.setLayout(null);

        JLabel NumeLabel = new JLabel("Titlul : ");
        NumeLabel.setBounds(10, 20, 80, 25);
        panel.add(NumeLabel);

        NumeText = new JTextField(20);
        NumeText.setBounds(130, 20, 165, 25);
        panel.add(NumeText);

        JLabel AutorLabel = new JLabel("Autorul : ");
        AutorLabel.setBounds(10, 60, 80, 25);
        panel.add(AutorLabel);

        AutorText = new JTextField(20);
        AutorText.setBounds(130, 60, 165, 25);
        panel.add( AutorText);

        JLabel NumarLabel = new JLabel("Numarul de pagini: ");
        NumarLabel.setBounds(10, 100, 120, 25);
        panel.add(NumarLabel);

        NumarText = new JTextField(20);
        NumarText.setBounds(130, 100, 165, 25);
        panel.add(NumarText);

        JLabel AnLabel = new JLabel("Anul publicarii : ");
        AnLabel.setBounds(10, 140, 100, 25);
        panel.add(AnLabel);

        AnText = new JTextField(20);
        AnText.setBounds(130, 140, 165, 25);
        panel.add(AnText);

        JLabel LocLabel = new JLabel("Locul publicarii : ");
        LocLabel.setBounds(10, 180, 100, 25);
        panel.add(LocLabel);

        LocText = new JTextField(20);
        LocText.setBounds(130, 180, 165, 25);
        panel.add( LocText);

        saveButton = new JButton("Save");
        saveButton.setBounds(40,300,150,30);
        panel.add(saveButton);

        saveButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Carte carte =new Carte();
                        carte.setTitlu(NumeText.getText());
                        carte.setAutor(AutorText.getText());
                        carte.setNumarDePagini(NumarText.getText());
                        carte.setAnulPublicarii(AnText.getText());
                        carte.setLoculPublicarii(LocText.getText());
                        ListaCarte.add(carte);
                        panel.removeAll();
                        panel.updateUI();
                        placeComponents1(panel);
                        Adaugare.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        panel.removeAll();
                                        panel.updateUI();
                                        placeComponents2(panel);
                                    }
                                });
                    }
                });
    }

    private static void placeComponents3(JPanel panel){
        DefaultListModel<String>ListaCarteModel = new DefaultListModel<>();
        ListaCarte.forEach(carte -> ListaCarteModel.addElement(carte.getTitlu()));
        JList<String>Lista = new JList<>(ListaCarteModel);
        Lista.setBounds(110, 50, 250, 250);
        Lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panel.add(Lista);
        Lista.getSelectedIndex();

        Modifica = new JButton("Modifica");
        Modifica.setBounds(30, 400, 100, 35);
        Modifica.setBackground(new java.awt.Color(165, 42, 42));
        Modifica.setForeground(Color.WHITE);
        Modifica.setFont(new Font("Serif", Font.BOLD, 15));
        panel.add(Modifica);
        Modifica.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Integer selectedIndex = Lista.getSelectedIndex();
                        if(selectedIndex <0){throw new ExceptieCarte("Selectarea unei cărți este obligatorie!");}
                        else {
                        panel.removeAll();
                        panel.updateUI();
                        placeComponents4(panel, ListaCarte.get(selectedIndex));}
                    }
                });

        Sterge = new JButton("Sterge");
        Sterge.setBounds(180, 400, 100, 35);
        Sterge.setBackground(new java.awt.Color(165, 42, 42));
        Sterge.setForeground(Color.WHITE);
        Sterge.setFont(new Font("Serif", Font.BOLD, 15));
        panel.add(Sterge);
        Sterge.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int selectedIndex = Lista.getSelectedIndex();
                        if(selectedIndex < 0){
                            throw new ExceptieCarte("Selectarea unei cărți este obligatorie!");
                        }
                        else{
                            ListaCarte.remove(selectedIndex);
                            ListaCarteModel.clear();
                            ListaCarte.forEach(carte -> ListaCarteModel.addElement(carte.getTitlu()));
                        }

                    }
                });

        Revenire = new JButton("Revenire");
        Revenire.setBounds(330, 400, 100, 35);
        Revenire.setBackground(new java.awt.Color(165, 42, 42));
        Revenire.setForeground(Color.WHITE);
        Revenire.setFont(new Font("Serif", Font.BOLD, 15));
        panel.add(Revenire);

        Revenire.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        panel.removeAll();
                        panel.updateUI();
                        placeComponents1(panel);
                        Adaugare.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        panel.removeAll();
                                        panel.updateUI();
                                        placeComponents2(panel); }});
                    }});
    }

    private static void placeComponents4(JPanel panel, Carte carte)
    {   /*Carte carte=new Carte ();*/

        JLabel NumeLabel = new JLabel("Titlul : ");
        NumeLabel.setBounds(10, 20, 80, 25);
        panel.add(NumeLabel);

        NumeText = new JTextField(20);
        NumeText.setBounds(130, 20, 165, 25);
        NumeText.setText(carte.getTitlu());
        panel.add(NumeText);

        JLabel AutorLabel = new JLabel("Autorul : ");
        AutorLabel.setBounds(10, 60, 80, 25);
        panel.add(AutorLabel);

        AutorText = new JTextField(20);
        AutorText.setBounds(130, 60, 165, 25);
        AutorText.setText(carte.getAutor());
        panel.add( AutorText);

        JLabel NumarLabel = new JLabel("Numarul de pagini: ");
        NumarLabel.setBounds(10, 100, 120, 25);
        panel.add(NumarLabel);

        NumarText = new JTextField(20);
        NumarText.setBounds(130, 100, 165, 25);
        NumarText.setText(String.valueOf(carte.getNumarDePagini()));
        panel.add(NumarText);

        JLabel AnLabel = new JLabel("Anul publicarii : ");
        AnLabel.setBounds(10, 140, 100, 25);
        panel.add(AnLabel);

        AnText = new JTextField(20);
        AnText.setBounds(130, 140, 165, 25);
        AnText.setText(String.valueOf(carte.getAnulPublicarii()));
        panel.add(AnText);

        JLabel LocLabel = new JLabel("Locul publicarii : ");
        LocLabel.setBounds(10, 180, 100, 25);
        panel.add(LocLabel);

        LocText = new JTextField(20);
        LocText.setBounds(130, 180, 165, 25);
        LocText.setText(carte.getLoculPublicarii());
        panel.add( LocText);

        saveButton = new JButton("Save");
        saveButton.setBounds(40,300,150,30);
        panel.add(saveButton);

        saveButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        carte.setTitlu(NumeText.getText());
                        carte.setAutor(AutorText.getText());
                        carte.setNumarDePagini(NumarText.getText());
                        carte.setAnulPublicarii(AnText.getText());
                        carte.setLoculPublicarii(LocText.getText());
                        panel.removeAll();
                        panel.updateUI();
                        placeComponents3(panel); }
                });
    }
}



