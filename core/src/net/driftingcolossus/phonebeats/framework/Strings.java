package net.driftingcolossus.phonebeats.framework;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.badlogic.gdx.Gdx;

import net.driftingcolossus.phonebeats.PhoneBeats;

public class Strings {
	
	private static final String XML_LOAD_DIR = "lang";
	private static final String XML_DEV_DIR = "bin/lang";
	
	private static Document language_file;
	
	public static final void load(LanguageSet set) throws ParserConfigurationException, SAXException, IOException{
		
		String dev = XML_DEV_DIR + "/" + set.toString() + ".xml";
		String dir = XML_LOAD_DIR + "/" + set.toString() + ".xml";
		
		String path = Gdx.files.internal(PhoneBeats.isDevEnviroment() ? dev : dir).path();
		
		File file = new File(path);
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		
		Document doc = dBuilder.parse(file);
		
		doc.getDocumentElement().normalize();
		
		language_file = doc;
		
	}
	
	public static final String getString(String tag, String element){
		NodeList list = language_file.getElementsByTagName(tag);
		for(int i = 0; i < list.getLength(); i++){
			Node node = list.item(i);
			if(node.getAttributes().getNamedItem(element) != null){
				return node.getAttributes().getNamedItem(element).getNodeValue();
			}
			
		}
		return null;
	}

}
