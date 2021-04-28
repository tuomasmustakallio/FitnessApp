package com.example.fitnessapp;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Xml;

import androidx.annotation.RequiresApi;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlSerializer;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import static com.example.fitnessapp.PasswordManager.hashPassword;

public class DatabaseManager{

    /*Creates an account to data.xml*/
    public static void createNewAccount(Context context, String username, String password){
        String path = context.getFilesDir().getAbsolutePath();
        int secrets = hashPassword(password);
        String pass = Integer.toString(secrets);
        String xml = "data.xml";
        File xmlFile = new File(path + "/data.xml");
        FileOutputStream fos;
        /*If file already exists adds a new person element to database*/
        if(xmlFile.exists()) {
            try {
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document doc = documentBuilder.parse(xmlFile);
                Element root = doc.getDocumentElement();

                Element newUser = doc.createElement("person");

                Element nameElement = doc.createElement("username");
                nameElement.appendChild(doc.createTextNode(username));

                Element passElement = doc.createElement("password");
                passElement.appendChild(doc.createTextNode(pass));

                newUser.appendChild(nameElement);
                newUser.appendChild(passElement);

                DOMSource source = new DOMSource(doc);

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                StreamResult result = new StreamResult(xmlFile);
                transformer.transform(source, result);

            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (TransformerConfigurationException e) {
                e.printStackTrace();
            } catch (TransformerException e) {
                e.printStackTrace();
            }
        }
        /*If file doesn't exist creates the database and adds the core elements*/
        else{
            try {
                fos = context.openFileOutput(xml, Context.MODE_PRIVATE);
                XmlSerializer serializer = Xml.newSerializer();
                serializer.setOutput(fos, "UTF-8");
                serializer.startDocument(null, Boolean.valueOf(true));
                serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
                serializer.startTag(null, "people");
                serializer.startTag(null, "person");
                serializer.startTag(null, "username");
                serializer.text(username);
                serializer.endTag(null, "username");
                serializer.startTag(null, "password");
                serializer.text(pass);
                serializer.endTag(null, "password");
                serializer.endTag(null, "person");
                serializer.endTag(null, "people");
                serializer.endDocument();
                serializer.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*comment here*/
    public static void setUserInfo(Context context, Person person, String username){
        username = "eka";
        String path = context.getFilesDir().getAbsolutePath();
        File xmlFile = new File(path + "/data.xml");

        try{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(xmlFile);
            NodeList nList = doc.getDocumentElement().getElementsByTagName("person");

            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element user = (Element) node;
                    if (user.getElementsByTagName("username").item(0).getTextContent().equals(username)) {

                        Element age = doc.createElement("age");
                        age.appendChild(doc.createTextNode(person.getAge()));
                        user.appendChild(age);

                        Element gender = doc.createElement("gender");
                        gender.appendChild(doc.createTextNode(person.getGender()));
                        user.appendChild(gender);

                        Element height = doc.createElement("height");
                        height.appendChild(doc.createTextNode(person.getHeight()));
                        user.appendChild(height);

                        Element weight = doc.createElement("weight");
                        height.appendChild(doc.createTextNode(person.getWeight()));
                        user.appendChild(weight);

                        DOMSource source = new DOMSource(doc);

                        TransformerFactory transformerFactory = TransformerFactory.newInstance();
                        Transformer transformer = transformerFactory.newTransformer();
                        StreamResult result = new StreamResult(xmlFile);
                        transformer.transform(source, result);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    /*Checks if user exists in user database data.xml*/
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static boolean checkLogin(Context context, String username) {
        try {
            FileInputStream fis = null;
            InputStreamReader isr = null;
            fis = context.openFileInput( "data.xml");
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
                    return element.getElementsByTagName("username").item(0).getTextContent().equals(username);
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



