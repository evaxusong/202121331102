import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;

public class TextSimilarity {
    public static void main(String[] args) {
        String document1Path = "C:/Users/aroh/Desktop/orig.txt";
        String document2Path = "C:/Users/aroh/Desktop/copy.txt";

        try {
            String document1 = readDocument(document1Path);
            String document2 = readDocument(document2Path);

            double similarity = calculateSimilarity(document1, document2);

            System.out.println("Similarity: " + similarity);
        } catch (IOException e) {
            System.out.println("Error reading the documents: " + e.getMessage());
        }
    }

    private static String readDocument(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = reader.readLine()) != null) {
            content.append(line);
            content.append("\n");
        }

        reader.close();

        return content.toString();
    }

    private static double calculateSimilarity(String document1, String document2) {
        Set<String> words1 = tokenize(document1);
        Set<String> words2 = tokenize(document2);

        Set<String> intersection = new HashSet<>(words1);
        intersection.retainAll(words2);

        Set<String> union = new HashSet<>(words1);
        union.addAll(words2);

        return (double) intersection.size() / (double) union.size();
    }

    private static Set<String> tokenize(String document) {
        String[] tokens = document.toLowerCase().split("[,\\.\n,。]");
        return new HashSet<>(Arrays.asList(tokens));
    }
}