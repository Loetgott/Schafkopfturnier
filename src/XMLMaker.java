
import java.io.File;
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

public class XMLMaker {
    int backupVersion = 0;
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
            StreamResult result = new StreamResult(new File( path + "\\" + Game.round + "_" + backupVersion + ".xml"));
            backupVersion ++;
            transformer.transform(source, result);
            System.out.println("XML-Datei erfolgreich unter " + path + "\\" + Game.round + "_" + backupVersion + ".xml");
        } catch (ParserConfigurationException | TransformerException e) {
            System.out.println(Game.RED + "Fehler beim Erstellen des Backups, unbedingt prüfen!");
        }
    }
    public void importBackup(String path){
        Game.tischList.clear();
        Game.playerList.clear();
        Gui.configPlayerListModel.clear();
        Gui.configTischlistModel.clear();
        try {
            File xmlFile = new File(path);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            Element root = doc.getDocumentElement();
            NodeList tischNodes = root.getElementsByTagName("Tisch");

            for (int i = 0; i < tischNodes.getLength(); i++) {
                if (tischNodes.item(i) instanceof Element tisch) {
                    Game.tischList.add(new Tisch(Integer.parseInt(tisch.getAttribute("number"))));
                    Gui.configTischlistModel.add(i,"Tisch " + tisch.getAttribute("number"));
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
            System.out.println(Game.RED + "Fehler beim importieren des Backups! unbedingt prüfen");
        }
    }
    public void nextRound(){
        backupVersion = 0;
    }
}
