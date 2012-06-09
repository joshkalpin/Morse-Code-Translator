package com.jkalpin.morsecode;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

@SuppressWarnings("serial")
public class ConfirmationDialog extends JOptionPane implements KeyListener {

    private final Object [] options = {"Yes", "No"};
    private JDialog dialog;

    public ConfirmationDialog(String message, String title){

        super(message);

        setOptions(options);

        dialog = createDialog(new JFrame(), title);
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dialog.setVisible(true);
    }

    public void setTitle(String newTitle){
        dialog.setTitle(newTitle);
    }

    public String getTitle(){
        return dialog.getTitle();
    }

    public boolean returnAnswer(){
        Object promptAnswer = getValue();

        if(promptAnswer.equals(options[0])){
            dialog.dispose();
            return true;
        }
        else{
            dialog.dispose();
            return false;
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(KeyEvent ke) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyTyped(KeyEvent ke) {
        if(ke.getKeyCode()==KeyEvent.VK_ENTER){
            //TODO
        }

    }
}