package com.example.fitnessapp;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static boolean checkPassword(Context context, String username, String password) {
        int s = password.hashCode();
        String pass = Integer.toString(s);
        try {
            FileInputStream fis = null;
            InputStreamReader isr = null;
            String path = context.getFilesDir().getAbsolutePath();
            fis = context.openFileInput("data.xml");
            isr = new InputStreamReader(fis);
            char[] inputBuffer = new char[fis.available()];
            isr.read(inputBuffer);
            String data = new String(inputBuffer);
            isr.close();
            fis.close();
            InputStream is = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
            ArrayList<String> xmlDataList = new ArrayList<String>();
            DocumentBuilderFactory dbf;
            DocumentBuilder db;
            NodeList items = null;
            Document dom;
            dbf = DocumentBuilderFactory.newInstance();
            db = dbf.newDocumentBuilder();
            dom = db.parse(is);
            dom.getDocumentElement().normalize();
            NodeList nList = dom.getDocumentElement().getElementsByTagName("person");
            for (int i = 0; i < nList.getLength(); i++){
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    if (element.getElementsByTagName("username").item(0).getTextContent().equals(username)){
                        return element.getElementsByTagName("password").item(0).getTextContent().equals(pass);
                    }
                }
            }
        }catch  (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return false;
    }
}
