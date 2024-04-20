
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
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class XMLMaker {
    public XMLMaker(){

    }
    public void saveBackup(ArrayList<Tisch> tischList, String path){
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            // Erstellen des Wurzelelements
            Element game = doc.createElement("Game");
            game.setAttribute("round", String.valueOf(Game.round));

            // Füge das Wurzelelement dem Dokument hinzu
            doc.appendChild(game);

            // Erstellen der Kind-Elemente und Hinzufügen zum Wurzelelement
            for(Tisch tisch : tischList){
                Element tischElement = doc.createElement("Tisch" + tisch.getName());
                tischElement.setAttribute("number", tisch.getName());
                for(int i = 0; i < 4; i++){
                    Element playerElement = doc.createElement("Player1");
                    playerElement.setAttribute("vorname",tisch.getPlayerList().get(i).getVorname());
                    playerElement.setAttribute("nachname",tisch.getPlayerList().get(i).getNachname());
                    playerElement.setAttribute("punkte",String.valueOf(tisch.getPlayerList().get(i).getPoints()));
                    tischElement.appendChild(playerElement);
                }
                game.appendChild(tischElement);
            }

            // Überprüfe, ob das Dokument nicht leer ist
            if (!doc.getDocumentElement().hasChildNodes()) {
                // Wenn das Dokument leer ist, gebe eine Meldung aus und breche ab
                System.out.println("Dokument ist leer, nichts zu speichern.");
                return;
            }

            // Speichern der XML-Datei
            doc.setXmlStandalone(true); // Optional: um die XML-Deklaration hinzuzufügen
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // Formatierung aktivieren
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File( path + String.valueOf(Game.round) + ".xml"));
            transformer.transform(source, result);

            System.out.println("XML-Datei erfolgreich erstellt.");
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
            System.out.println(Game.RED + "Fehler beim Erstellen des Backups, unbedingt prüfen!");
        }
    }

}
