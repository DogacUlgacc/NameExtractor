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

        
        /*
        'en.ner.person' is a pre-trained named-entity recognition (NER) model that is specifically designed to identify persons' names 
        (i.e., individuals) in English language text.
        The model is part of the OpenNLP project, which is an open-source library for natural language processing in Java.
        The en.ner.person model uses machine learning algorithms to identify patterns and features in the input text that are characteristic of person names,
        and then makes predictions based on these patterns to identify and extract the names from the text.
        */
        
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
