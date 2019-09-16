import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * ScavengerHunt.java
 *
 * Version:
 *     1
 *
 */

/**
 * This program is to connect the database using postgresql, execute a query,
 * parse the resulting URL, find hidden html, download the contents, compile
 * and run the downladed contents.
 *
 * @author  Ishika Prasad
 *
 */
public class ScavengerHunt {
    private final String url = "jdbc:postgresql://reddwarf.cs.rit.edu/";
    private final String user = "csci605";
    private final String password = "sometables";
    String url1;
    String download_path = "Ranges.java";
    String pathWithoutExt = "/home/stu13/s18/ip1262/java/Scav/Ranges";
    String query = "SELECT to_parse FROM sites";
    String reguleExp = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([a-zA-Z0-9@.%_\\+.~#?&//=]*)";

    public Connection connect() {
        Connection conn = null;
        Statement statement = null;

        try {
            //Connecting to database
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to PostgreSQL");
            statement = conn.createStatement();
            //execute query
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                System.out.println("Executing query");
                System.out.println(resultSet.getString(1));

                //access the url page source
                URL aURL = new URL(resultSet.getString(1));
                URLConnection urlConnection = aURL.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String inputLine;
                while((inputLine = in.readLine()) != null) {
                    if(inputLine.contains("hidden_html")) {
                        //to find hidden url using regex
                       String regex = reguleExp;
                       Pattern pattern = Pattern.compile(regex);
                       Matcher matcher = pattern.matcher(inputLine);
                       boolean result = matcher.find();
                       while(result) {
                           url1 = matcher.group(0);
                           //print hidden url
                           System.out.println(url1);
                           result = matcher.find();
                       }

                        //download ranges.java
                        try (BufferedInputStream inputstream = new BufferedInputStream(new URL(url1).openStream());
                             FileOutputStream download_file = new FileOutputStream(download_path)) {
                            byte data[] = new byte[1024];
                            int byteContent;
                            while((byteContent = inputstream.read(data, 0, 1024)) != -1) {
                                download_file.write(data, 0, byteContent);
                            }
                            //Ranges.main(new String[0]);

                            //compile and run downloaded content
                            Process compile = Runtime.getRuntime().exec("javac " + download_path);
                            compile.waitFor();
                            Process run = Runtime.getRuntime().exec("java " + pathWithoutExt);
                            BufferedReader inStream = new BufferedReader(new InputStreamReader( run.getInputStream()));
                            String content;
                            while((content = inStream.readLine()) != null) {
                                System.out.println(content);
                            }
                        }
                    }
                }
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * The main method
     * @param args
     */
    public static void main(String[] args) {
        ScavengerHunt scavengerHunt = new ScavengerHunt();
        scavengerHunt.connect();
    }
}
