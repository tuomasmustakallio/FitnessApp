package com.example.fitnessapp;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class PasswordManager {

    public static boolean passwordRules(String password) {
        /* Checks if the password abides the rules of a good password*/
        boolean overTwelve = false;
        boolean hasCapital = false;
        boolean hasLower = false;
        boolean hasNumb = false;
        boolean hasSpecial = false;
        char ch;
        Pattern special= Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = special.matcher(password);
        boolean hasSymbols = matcher.find();
        if (password.length()< 12) {
            overTwelve = true;
        }
        for (int i = 0; i < password.length(); i++) {
            ch = password.charAt(i);
            if (Character.isDigit(ch)) {
                hasNumb = true;
            } else if (Character.isUpperCase(ch)) {
                hasCapital = true;
            } else if (Character.isLowerCase(ch)) {
                hasLower = true;
            } else if (hasSymbols) {
                hasSpecial = true;
            }
            if (overTwelve && hasNumb && hasCapital && hasLower && hasSpecial) {
                return true;
            }
        }
        return false;
    }

    /*Password is hash protected which return an integer*/
    public static int hashPassword(String password){
        int hash = password.hashCode();
        return hash;
    }

    /*Compares the given password to the users hash protected password to see if the integers match*/
    public static boolean checkPassword(String username, String password) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse("app/src/main/res/data.xml");
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getDocumentElement().getElementsByTagName("person");
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    if (element.getElementsByTagName("username").item(0).getTextContent().equals(username)) {
                        if (element.getElementsByTagName("password").item(0).getTextContent().equals(password.hashCode())) {
                            return true;
                        }
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
