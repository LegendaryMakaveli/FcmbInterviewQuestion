package com.bankTransaction.classWork;

public class Capitalization {
    public static String capitalize(String str){
        StringBuilder sentence = new StringBuilder();
        boolean capitalization = true;

        for(char character : str.toCharArray()){
            if(Character.isWhitespace(character)){
                sentence.append(character);
                capitalization = true;
            }else if(capitalization){
                sentence.append(Character.toUpperCase(character));
                capitalization = false;
            }else{
                sentence.append(Character.toLowerCase(character));
            }
        }

        return sentence.toString();
    }
}
