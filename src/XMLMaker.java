
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.NodeList;

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
            Element game = doc.createElement("Game");
            game.setAttribute("round", String.valueOf(Game.round));
            doc.appendChild(game);

            for(Tisch tisch : tischList){
                Element tischElement = doc.createElement("Tisch" + tisch.getName());
                tischElement.setAttribute("number", tisch.getName());
                for(int i = 0; i < 4; i++){
                    Element playerElement = doc.createElement("Player" + String.valueOf(i + 1));
                    playerElement.setAttribute("vorname",tisch.getPlayerList().get(i).getVorname());
                    playerElement.setAttribute("nachname",tisch.getPlayerList().get(i).getNachname());
                    playerElement.setAttribute("punkte",String.valueOf(tisch.getPlayerList().get(i).getPoints()));
                    tischElement.appendChild(playerElement);
                }
                game.appendChild(tischElement);
            }

            if (!doc.getDocumentElement().hasChildNodes()) {
                System.out.println("Dokument ist leer, nichts zu speichern.");
                return;
            }

            doc.setXmlStandalone(true);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File( path + String.valueOf(Game.round) + ".xml"));
            transformer.transform(source, result);

            System.out.println("XML-Datei erfolgreich erstellt.");
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
            System.out.println(Game.RED + "Fehler beim Erstellen des Backups, unbedingt prüfen!");
        }
    }
    public void importBackup(String path, int round){
        //Game.tischList.clear();
        //try {
        //    File xmlFile = new File(path + String.valueOf(round) + ".xml");
        //    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //    DocumentBuilder builder = factory.newDocumentBuilder();
        //    Document doc = builder.parse(xmlFile);
        //    Element root = doc.getDocumentElement();
        //    NodeList gameNodes = root.getChildNodes();
//
        //    for (int i = 0; i < gameNodes.getLength(); i++) {
        //        Element gameElement = (Element) gameNodes.item(i);
        //        NodeList tischNodes = gameElement.getElementsByTagName("Tisch" + String.valueOf(i + 1));
        //        for (int ii = 0; ii < tischNodes.getLength(); ii++){
        //            Element tischElement = (Element) tischNodes.item(ii);
        //            Game.tischList.add(new Tisch(Integer.parseInt(tischElement.getAttribute("number"))));
        //            for (int iii = 0; iii < 4; iii++) {
        //                NodeList playerNodes = tischElement.getElementsByTagName("Player" + iii);
        //                Element playerElement = (Element) playerNodes.item(iii);
        //                String vorname = playerElement.getAttribute("vorname");
        //                String nachname = playerElement.getAttribute("nachname");
        //                String points = playerElement.getAttribute("punkte");
        //                Game.tischList.get(ii).playerList.add(new Player(vorname,nachname));
        //                Game.tischList.get(ii).playerList.get(iii).setPoints(Integer.parseInt(points));
        //                Game.tischList.get(ii).playerList.get(iii).setTisch(Game.tischList.get(ii));
        //                System.out.println(Game.tischList.get(ii).playerList.get(iii).getName());
        //            }
        //        }
        //    }
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}
    }

}
