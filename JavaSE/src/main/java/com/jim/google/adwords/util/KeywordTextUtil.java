package com.jim.google.adwords.util;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 9/10/13
 * Time: 3:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class KeywordTextUtil {

    /**
     * Remove all multiple spaces
     * Convert all words to lowercase
     * remove all special chars except: "space # $ & _ - " [ ] ' + . / :"
     * remove all special chars: ! @ % ^ * () = {} ; ~ ` <> ? \ |
     * @param keywordText
     * @return
     */
    public static String getNormalizeKeywordText(String keywordText) {
        if (keywordText != null) {
            keywordText = keywordText.replaceAll(" +", " ");

            //LAN P(7/16/2012)- allow most of special chars because of internationalization
            keywordText = keywordText.replaceAll("[^A-Za-z0-9\\ \\#\\$\\&\\_\\-\\\"\\[\\]\\'\\+\\.\\/\\:]", "");
            //Add for Google requirement of: This string must match the regular expression '[^\x00]*' This field is required and should not be null when it is contained within
            keywordText = keywordText.replaceAll("\\x00", "");
            //keywordText = keywordText.replaceAll("[\\!\\@\\%\\^\\*\\(\\)\\=\\{\\}\\;\\~\\`\\<\\>\\?\\\\\\|]", "");
            keywordText = keywordText.toLowerCase();
            keywordText = keywordText.trim();
            return keywordText;
        } else {
            return null;
        }
    }
}
