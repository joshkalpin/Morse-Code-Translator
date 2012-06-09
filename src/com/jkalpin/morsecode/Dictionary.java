package com.jkalpin.morsecode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Dictionary {

    private Map <String, String> dictionary;

    public Dictionary(String readPath){
        dictionary = new HashMap <String, String> ();
        try{
            loadDictionary(new File(readPath));
        }
        catch(FileNotFoundException f){
            f.printStackTrace();
        }
    }

    public Map <String, String> getTranslation(){
        return dictionary;
    }

    private void loadDictionary(File file) throws FileNotFoundException {
        Scanner s = new Scanner (file);
        while(s.hasNextLine()){
            String key=null, value=null;
            key = s.next();
            value = s.next();
            dictionary.put(key, value);
        }       
        s.close();
    }
    /**
     * Translates a string based on the contents of the dictionary
     * @param text String to be translated
     * @param spaceCharacter What spaces should be represented as
     * @return The translated string
     */
    public String translate (String text, String spaceCharacter){
        String translatedText = "";
        for(int i = 0; i <text.length(); ++i){
            String ch = (text.charAt(i)+"").toUpperCase();

            if(dictionary.containsKey(ch)){
                translatedText+=((String)dictionary.get(ch));
                if(i< text.length()-1){
                    if(text.charAt(i+1) != ' '){
                        translatedText+=" ";
                    }
                }
            }
            else if(ch.equals(" ")){
                translatedText += spaceCharacter;
            }
            else{
                translatedText="Invalid Character \""+ch+"\"";
                break;
            }
        }
        return translatedText;
    }
}
