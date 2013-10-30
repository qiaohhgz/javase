package com.jim.subject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 10/21/13
 * Time: 9:56 AM
 * To change this template use File | Settings | File Templates.
 */
public class GetWordDemo {
    public static final char SPACE = ' ';
    private List<String> wordList = new ArrayList<String>();
    private List<Integer> wordIndexList = new ArrayList<Integer>();

    public void findIndex(String s) {
        boolean firstWord = false;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (!firstWord && c != SPACE) {
                firstWord = true;
                wordIndexList.add(i);
                System.out.print(i + " ");
            }
            if (firstWord && (c == SPACE || i == chars.length - 1)) {
                firstWord = false;
                int offset = wordIndexList.get(wordIndexList.size() - 1).intValue();
                int count = i - offset;
                String word = new String(chars, offset, ++count);
                System.out.println(word);
                wordList.add(word);
            }
        }
    }

    public List<Integer> getWordIndexList() {
        return wordIndexList;
    }

    public List<String> getWordList() {
        return wordList;
    }
}
