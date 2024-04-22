
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
                Element tischElement = doc.createElement("Tisch");
                tischElement.setAttribute("number", tisch.getName());
                for(int i = 0; i < 4; i++){
                    Element playerElement = doc.createElement("Player");
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
        Game.tischList.clear();
        Game.playerList.clear();
        Gui.configPlayerListModel.clear();
        try {
            File xmlFile = new File(path + String.valueOf(round) + ".xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            Element root = doc.getDocumentElement();
            NodeList tischNodes = root.getElementsByTagName("Tisch");

            for (int i = 0; i < tischNodes.getLength(); i++) {
                if (tischNodes.item(i) instanceof Element) {
                    Element tisch = (Element) tischNodes.item(i);
                    Game.tischList.add(new Tisch(Integer.parseInt(tisch.getAttribute("number"))));
                    NodeList playerNodes = tisch.getElementsByTagName("Player");
                    for (int ii = 0; ii < playerNodes.getLength(); ii++) {
                        if (playerNodes.item(ii) instanceof Element) {
                            Game.tischList.get(i).playerList.add(new Player(((Element) playerNodes.item(ii)).getAttribute("vorname"),((Element) playerNodes.item(ii)).getAttribute("nachname")));
                            Game.tischList.get(i).playerList.get(ii).setTisch(Game.tischList.get(i));
                            Game.tischList.get(i).playerList.get(ii).setPoints(Integer.parseInt(((Element) playerNodes.item(ii)).getAttribute("punkte")));
                            Game.playerList.add(Game.tischList.get(i).playerList.get(ii));
                            Gui.configPlayerListModel.add(i * ii,((Element) playerNodes.item(ii)).getAttribute("vorname") + " " + ((Element) playerNodes.item(ii)).getAttribute("nachname"));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
