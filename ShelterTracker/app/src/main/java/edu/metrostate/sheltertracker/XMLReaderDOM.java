package edu.metrostate.sheltertracker;

import java.io.File;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class XMLReaderDOM
{
    public void fileReader(String filename, List<Shelter> shelterList, List<Animal> animalsOutsideShelters)
    {
        try
        {
            File file = new File(filename);
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            if (document.hasChildNodes())
            {
                parseFile(document.getChildNodes(), shelterList, animalsOutsideShelters);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    private static void parseFile(NodeList baseNodeList, List<Shelter> shelterList, List<Animal> animalsOutsideShelters)
    {
        Node baseNode = baseNodeList.item(0);

        NodeList shelterNodeList = baseNode.getChildNodes();

        for (int i = 0; i < shelterNodeList.getLength(); i++) {
            Node shelterNode = shelterNodeList.item(i);
            if (shelterNode.getNodeType() == Node.ELEMENT_NODE) {
                String shelter_id = null;
                if (shelterNode.hasAttributes()) {
                    NamedNodeMap shelterNodeMap = shelterNode.getAttributes();
                    for (int j = 0; j < shelterNodeMap.getLength(); j++)
                    {
                        Node node1 = shelterNodeMap.item(j);

                        if (node1.getNodeValue() != null) {
                            shelter_id = node1.getNodeValue();
                        }
                    }
                }

                Shelter newShelter = new Shelter(shelter_id, null);

                //newShelter.setShelterName(null);

                NodeList animalNodeList = shelterNode.getChildNodes();

                for (int k = 0; k < animalNodeList.getLength(); k++) {
                    Node animalNode = animalNodeList.item(k);
                    if (animalNode.getNodeType() == Node.ELEMENT_NODE) {
                        if (animalNode.getNodeName().equals("Name")) {
                            newShelter.setShelterName(animalNode.getTextContent());

                        } else if (animalNode.getNodeName().equals("Animal")) {
                            String animal_type = null;
                            String animal_name = null;
                            String animal_id = null;
                            double weight = -1;
                            long receipt_date = -1;
                            String weight_unit = null;

                            NamedNodeMap animalNodeMap = animalNode.getAttributes();
                            for (int l = 0; l < animalNodeMap.getLength(); l++) {
                                Node node2 = animalNodeMap.item(l);

                                if (node2.getNodeName().equals("id")) {
                                    if (node2.getNodeValue() != null) {
                                        animal_id = node2.getNodeValue();
                                    }
                                } else if (node2.getNodeName().equals("type")) {
                                    animal_type = node2.getNodeValue();
                                }
                            }

                            NodeList attributeNodeList = animalNode.getChildNodes();

                            for (int m = 0; m < attributeNodeList.getLength(); m++) {
                                Node attributeNode = attributeNodeList.item(m);
                                if (attributeNode.getNodeType() == Node.ELEMENT_NODE) {
                                    if (attributeNode.getNodeName().equals("Weight")) {
                                        NamedNodeMap attributeNodeMap = attributeNode.getAttributes();

                                        for (int n = 0; n < attributeNodeMap.getLength(); n++) {
                                            Node node3 = attributeNodeMap.item(n);

                                            weight_unit = node3.getNodeValue();
                                        }

                                        weight = Double.parseDouble(attributeNode.getTextContent());

                                    } else if (attributeNode.getNodeName().equals("Name")) {
                                        animal_name = attributeNode.getTextContent();
                                    } else if (attributeNode.getNodeName().equals("ReceiptDate")) {
                                        receipt_date = Long.parseLong(attributeNode.getTextContent());
                                    }
                                }
                            }

                            if (animal_id != null) {
                                if (shelter_id == null) {
                                    animalsOutsideShelters.add(new Animal(null, animal_type, animal_name, animal_id, weight, receipt_date, weight_unit));
                                } else {
                                    newShelter.acceptAnimal(new Animal(shelter_id, animal_type, animal_name, animal_id, weight, receipt_date, weight_unit));
                                }
                            }
                        }
                    }
                }
                if (shelter_id != null) {
                    shelterList.add(newShelter);
                }
            }
        }
    }
}

