package com.example.fitnessapp;

import android.content.Context;
import android.os.Build;
import android.util.Xml;
import android.widget.ArrayAdapter;

import androidx.annotation.RequiresApi;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import static android.content.Context.MODE_APPEND;
import static android.content.Context.MODE_PRIVATE;
import static com.example.fitnessapp.PasswordManager.hashPassword;

public class DatabaseManager{


    public static void createNewAccount(Context context, String username, String password){
        int secrets = hashPassword(password);
        String pass = Integer.toString(secrets);
        try {
            File newXml = new File("data.xml");
            if(newXml.exists()){
                try{
                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = null;
                    db = dbf.newDocumentBuilder();
                    Document doc = db.parse("input.xml");
                    NodeList people = doc.getElementsByTagName("people");
                    people.item(0).appendChild(createPersonElement(doc,username, pass));
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }  catch (ParserConfigurationException e1) {
                    e1.printStackTrace();
                }
            }else {
                FileOutputStream fos = context.openFileOutput("data.xml", Context.MODE_PRIVATE);
                XmlSerializer xmlSerializer = Xml.newSerializer();
                StringWriter writer = new StringWriter();
                xmlSerializer.setOutput(writer);
                xmlSerializer.startDocument("UTF-8", true);
                xmlSerializer.startTag(null, "people");
                xmlSerializer.startTag(null, "person");
                xmlSerializer.startTag(null, "username");
                xmlSerializer.text(username);
                xmlSerializer.endTag(null, "username");
                xmlSerializer.startTag(null, "password");
                xmlSerializer.text(pass);
                xmlSerializer.endTag(null, "password");
                xmlSerializer.endTag(null, "person");
                xmlSerializer.endTag(null, "people");
                xmlSerializer.endDocument();
                xmlSerializer.flush();
                String dataWrite = writer.toString();
                fos.write(dataWrite.getBytes());
                fos.close();
            }
        }
        catch (FileNotFoundException e) {
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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static boolean checkLogin(Context context, String username) {
        try {
            FileInputStream fis = null;
            InputStreamReader isr = null;
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
    private static Element createPersonElement(Document doc, String username, String password) {
        Element el = doc.createElement("person");
        el.appendChild(createPersonDetailElement(doc, "username", username));
        el.appendChild(createPersonDetailElement(doc, "password", password));
        return el;
    }

    private static Element createPersonDetailElement(Document doc, String name, String value) {
        Element el = doc.createElement(name);
        el.appendChild(doc.createTextNode(value));
        return el;
    }
}



