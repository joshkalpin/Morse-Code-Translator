package com.jkalpin.morsecode;


import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;


@SuppressWarnings("serial")
public class MorsePanel extends JFrame implements ActionListener{

    private JTextArea input, output;
    private JPanel panel;
    private JMenuBar mMenuBar;
    private JMenu fileMenu, helpMenu;
    private JMenuItem about, instructions;
    private JMenuItem open, save, print, exit;
    private JFileChooser mFileChooser;
    private Font mFont;
    private Dictionary dictionary;

    public MorsePanel (){
        super("Morse Code Translator");

        dictionary = new Dictionary("assets/Morse Code.txt");

        mFont = new Font ("Courier New", Font.PLAIN,12);
        panel = new JPanel (new FlowLayout());
        input = new JTextArea (10,100);
        output = new JTextArea (10,100);

        mMenuBar = new JMenuBar();

        fileMenu = new JMenu("File");

        open = new JMenuItem("Open");
        KeyStroke ctrlOKeyStroke = KeyStroke.getKeyStroke("control O");
        open.setAccelerator(ctrlOKeyStroke);
        open.addActionListener(this);

        save = new JMenuItem("Save");
        KeyStroke ctrlSKeyStroke = KeyStroke.getKeyStroke("control S");
        save.setAccelerator(ctrlSKeyStroke);
        save.addActionListener(this);

        print = fileMenu.add("Print");
        KeyStroke ctrlPKeyStroke = KeyStroke.getKeyStroke("control P");
        print.setAccelerator(ctrlPKeyStroke);
        print.addActionListener(this);

        exit = fileMenu.add("Exit");
        KeyStroke ctrlQKeyStroke = KeyStroke.getKeyStroke("control Q");
        exit.setAccelerator(ctrlQKeyStroke);
        exit.addActionListener(this);

        fileMenu.add(open);
        fileMenu.addSeparator();
        fileMenu.add(save);
        fileMenu.addSeparator();
        fileMenu.add(print);
        fileMenu.addSeparator();
        fileMenu.add(exit);

        helpMenu = new JMenu("Help");
        instructions = helpMenu.add("Instructions");
        helpMenu.addSeparator();
        about = helpMenu.add("About");

        setJMenuBar(mMenuBar);
        mMenuBar.add(fileMenu);
        mMenuBar.add(helpMenu);

        JScrollPane scroll = new JScrollPane(input);
        JScrollPane scroller = new JScrollPane(output);
        JButton button = new JButton ("Translate!");
        JButton clear = new JButton ("Clear Text Fields");

        input.setLineWrap(true);
        input.setFont(mFont);		

        output.setLineWrap(true);
        output.setFont(mFont);		
        output.setEditable(false);

        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        button.addActionListener(this);
        clear.addActionListener(this);

        panel.add(scroll);
        panel.add(button);
        panel.add(clear);
        panel.add(scroller);

        mFileChooser = new JFileChooser();


        getContentPane().add(panel);
        createAndShowFrame();
    }

    /*
     * Getters and Setters
     */
    public Font getFont(){
        return mFont;
    }

    public void setFont(Font newFont){
        mFont = newFont;
    }

    public Dictionary getDictionary(){
        return dictionary;
    }
    /*
     * End of Getters and Setters
     */



    public void actionPerformed(ActionEvent ae){
        String check; 
        try{
            check = ((JButton)(ae.getSource())).getText();
        }
        catch(ClassCastException c){
            check = ((JMenuItem)(ae.getSource())).getText();
        }

        switch(check){
        case "Translate!":  
            output.setText("");
            output.setText(dictionary.translate(input.getText(), "/"));
            break;   

        case "Clear Text Fields":
            output.setText("");
            input.setText("");
            break;

        case "Open":
            openText();
            break;

        case "Save":
            saveText(); 
            break;

        case "Print":
            break;

        case "Exit":
            confirmExit();
            break;

        case "About":

            break;

        case "Instructions":
            break;
        }
    }
    
    private void openText() {
        int rVal = mFileChooser.showOpenDialog(this);
        if(rVal == JFileChooser.APPROVE_OPTION) {
            File file = mFileChooser.getSelectedFile();
            if(file.getName().contains(".txt")){
                try{
                    
                }
            }
        }
    }

    private void createAndShowFrame(){		
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setSize(800,400);
        setVisible(true);	
    }

    private void saveText(){
        int rVal = mFileChooser.showSaveDialog(this);
        if(rVal == JFileChooser.APPROVE_OPTION){
            File file = mFileChooser.getSelectedFile();
            try{
                PrintWriter out = new PrintWriter(new FileWriter(mFileChooser.getCurrentDirectory()+"//"+file.getName()+".txt"));
                out.println(output.getText());
                out.close();
            }
            catch(IOException e){
                System.out.println("Bad file");
            }
        }        
    }

    private void confirmExit(){
        ConfirmationDialog askExit = new ConfirmationDialog ("Are you sure you want to exit?", "Exit");
        if(askExit.returnAnswer())
            System.exit(0);
    }
}