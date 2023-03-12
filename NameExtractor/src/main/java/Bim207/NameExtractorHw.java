package Bim207;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.StringTokenizer;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.Span;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class NameExtractorHw {
    public static void main(String[] args) throws Exception {

        InputStream fileInput = new FileInputStream("C:\\Users\\dogac\\IdeaProjects\\NameExtractorHw\\src\\en-ner-person.bin");
        TokenNameFinderModel finder = new TokenNameFinderModel(fileInput);
        NameFinderME nameFinder = new NameFinderME(finder);

        // Type the URL of the page from which the names will be extracted.
        String URL = "https://opennlp.apache.org/books-tutorials-and-talks.html";

        // Fetch the webpage and parse it with Jsoup.
        Document doc = Jsoup.connect(URL).get();
        String text = doc.body().text();

        // Separate the text into tokens
        StringTokenizer tokenizer = new StringTokenizer(text, " ");
        String[] tokens = new String[tokenizer.countTokens()];
        int i = 0;
        while (tokenizer.hasMoreTokens()) {
            tokens[i++] = tokenizer.nextToken();
        }

        // Use the tokens with the name Finder model to find the names.
        Span[] names = nameFinder.find(tokens);

        // Converting the name spans from their current format into Strings.
        String [] Names = Span.spansToStrings(names,tokens);
        for (String Name:Names) {
            System.out.println(Name);
        }
    }
}
