package com.bankTransaction.classWork;

public class Capitalization {
    public static String capitalize(String str){
        StringBuilder sentence = new StringBuilder();
        boolean capitalization = true;

        for(char character : str.toCharArray()){
            if(Character.isWhitespace(character)){
                System.out.println(character);
                sentence.append(character);
                capitalization = true;
                System.out.println(sentence);
            }else if(capitalization){
                sentence.append(Character.toUpperCase(character));
                System.out.println(sentence);
                capitalization = false;
                System.out.println(sentence);
            }else{
                sentence.append(Character.toLowerCase(character));
                System.out.println(sentence);
            }
        }

        return sentence.toString();
    }
}
