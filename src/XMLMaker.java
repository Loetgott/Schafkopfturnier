
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XMLMaker {
    public XMLMaker(){

    }
    public void saveBackup(ArrayList<Tisch> tischlist){
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            // Erstellen Sie hier den Inhalt der XML-Datei

            // Speichern der XML-Datei
            doc.setXmlStandalone(true); // Optional: um die XML-Deklaration hinzuzufügen
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File( String.valueOf(Game.round) + ".xml"));
            transformer.transform(source, result);

            System.out.println("XML-Datei erfolgreich erstellt.");
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
            System.out.println(Game.RED + "Fehler beim erstellen des Backups, unbedingt prüfen!");
        }
    }
}
