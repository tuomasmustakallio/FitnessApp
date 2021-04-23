package com.example.fitnessapp;

import android.content.Context;
import android.util.Xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import static android.content.Context.MODE_APPEND;
import static android.content.Context.MODE_PRIVATE;
import static com.example.fitnessapp.PasswordManager.hashPassword;

public class DatabaseManager{


    public static void createNewAccount(Context context, String username, String password){
        Integer secrets = hashPassword(password);
        String pass = secrets.toString();
        final String xmlFile = "data";
        try {
            FileOutputStream fos = new FileOutputStream("data.xml");
            FileOutputStream file = context.openFileOutput(xmlFile, Context.MODE_PRIVATE);
            XmlSerializer xmlSerializer = Xml.newSerializer();
            StringWriter writer = new StringWriter();
            xmlSerializer.setOutput(writer);
            xmlSerializer.startDocument("UTF-8", true);
            xmlSerializer.startTag(null, "person");
            xmlSerializer.startTag(null, "username");
            xmlSerializer.text(username);
            xmlSerializer.endTag(null, "username");
            xmlSerializer.startTag(null, "password");
            xmlSerializer.text(pass);
            xmlSerializer.endTag(null, "password");
            xmlSerializer.endTag(null, "person");
            xmlSerializer.endDocument();
            xmlSerializer.flush();
            String dataWrite = writer.toString();
            file.write(dataWrite.getBytes());
            file.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        catch (IllegalStateException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setUserInfo(){
    }

    public static boolean checkLogin(Context context, String username) {
        final String xmlFile = "data";
        return false;
    }
}
