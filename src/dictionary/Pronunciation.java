package dictionary;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.media.CannotRealizeException;
import javax.media.NoPlayerException;
/**
 * This class fetches pronunciation audio url with merriam webster API by parsing the XML file 
 * @author YoonKim
 *
 */
public class Pronunciation {
		private String word; 
		private String apiKey; 
		private String xml; 
		private URL url; 
		
		/**
		 * Constructor for the class
		 * Intializes word, apiKey
		 * calls XMLResponse to get response in string form and uses the response to create a url for audio
		 * @param word
		 * @throws IOException
		 */
		public Pronunciation (String word) throws IOException {
			this.word = word;
			apiKey = "876c8a94-49cf-4c90-bba0-effb158a4ba2";
			getXMLresponse();
			getAudioURL();
		}
		
		/**
		 * Requests response for the corresponding word and parses it to String
		 * @throws IOException
		 */
		private void getXMLresponse() throws IOException {
			StringBuffer document = new StringBuffer();
			String address = "http://www.dictionaryapi.com/api/v1/references/learners/xml/"+word+"?key="+apiKey;
			URL url = new URL(address);
			URLConnection conn = url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String line = "";
			while ((line = reader.readLine()) != null) {
				document.append(line + " ");
			}
			reader.close();
			
			xml = document.toString();
		}
		
		/**
		 * Uses the parsed response to create an appropriate url for the audio file
		 * @throws IOException
		 */
		private void getAudioURL() throws IOException {
			Pattern pattern = Pattern.compile(".*<wav>(.*)</wav>.*");	
			Matcher match = pattern.matcher(xml);
			
			if (match.matches()) {
				String m = match.group(1);
				if (m.startsWith("bix")) {
					url = new URL("http://media.merriam-webster.com/soundc11/bix/"+m);
				} else if (m.startsWith("gg")) {
					url = new URL("http://media.merriam-webster.com/soundc11/gg/"+m);
				} else {
					url = new URL("http://media.merriam-webster.com/soundc11/"+m.charAt(0)+"/"+m);
				}
			} else {
					throw new FileNotFoundException();
			}
		}

		public String getWord() {
			return word;
		}

		public void setWord(String word) {
			this.word = word;
		}

		public String getApiKey() {
			return apiKey;
		}

		public void setApiKey(String apiKey) {
			this.apiKey = apiKey;
		}

		public String getXml() {
			return xml;
		}

		public void setXml(String xml) {
			this.xml = xml;
		}

		public URL getUrl() {
			return url;
		}

		public void setUrl(URL url) {
			this.url = url;
		}



}
