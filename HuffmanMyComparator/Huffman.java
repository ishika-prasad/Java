/*
 * Huffman.java
 *
 * Version:
 *     2
 *
 */

/**
 * This program is used to implement Huffman decoding
 * of the Huffman encoded file.
 *
 *
 * @author  Ishika Prasad
 *
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.TreeMap;


public class Huffman {
    // Use to create huffman tree
    static PriorityQueue<HuffManNode> nodes = new PriorityQueue<>(new MyComparator());
    // Use to store huffman codes mapping of character with its huffman code
    static TreeMap<Character, String> codes = new TreeMap<>();
    // Use to store characters mapping with their frequency
    static Map<String, Long> characterSet = new HashMap<>();

    /**
     * This method is used to encode the given file
     * @param fileToEncode input file which needs to be encoded
     * @param encodedFile output file in which encoded part to be written
     * @return true is encoding is done. Otherwise, false.
     */
    public static boolean encode(String fileToEncode, String encodedFile) {
        try {
            // Parse the input file to calculate frequency of the characters
            parseFile(fileToEncode);
            // Fill priority queue with huffman nodes
            fillQueue();
            // Construct huffman tree and generate huffman codes
            constructPrefixCodeTree();
            // printCodes();
            // Write headers in output file
            writeHeaderToFile(encodedFile);
            // Encode input file
            StringBuilder stb = encodeCharacters(fileToEncode);
            // write encoded data in output file as body
            writeBody(stb, encodedFile);
            return true;
        } catch (Exception e) {
            System.out.println("Exception in encoding the file" + e.getMessage());
            return false;
        }
    }

    /**
     * This method is used to parse the file and
     * store the character(as key) and frequency(as value)
     * of character in Hashmap
     * @param fileName input file
     * @throws IOException
     */
    static void parseFile(String fileName) throws IOException {

        try (FileInputStream input = new FileInputStream(fileName);) {
            int nextChar = input.read();
            while (nextChar != -1) {
                Byte b = (byte) nextChar;
                char c = (char) (b & 0xFF);
                nextChar = input.read();
                updateCharSet(c + "");
            }
        }
    }

    /**
     * This method update the frequency of character if
     * exist already. Otherwise, put in map with the frequency
     * as 1
     *
     * @param ch character for which frequency to be counted
     */
    private static void updateCharSet(String ch) {
        if (characterSet.get(ch) == null) {
            characterSet.put(ch, 1L);
        } else {
            characterSet.put(ch, characterSet.get(ch) + 1L);
        }
    }

    /**
     * This method used to iterate the hashmap and add the node in
     * priority queue
     */
    private static void fillQueue() {
        for (Entry<String, Long> entry : characterSet.entrySet()) {
            nodes.add(new HuffManNode(entry.getValue(), entry.getKey()));
        }
    }

    /**
     * This method build a tree and generates the
     * unique Huffman code for each character
     */
    private static void constructPrefixCodeTree() {
        buildTree(nodes);
        generateCodes(nodes.peek(), "");
    }

    /**
     * This method build a tree. Till the priority queue size is
     * greater than one, needs to check the node and add according
     * to the smallest node (poll will give the smallest node)
     * @param vector priority queue
     */
    private static void buildTree(PriorityQueue<HuffManNode> vector) {
        while (vector.size() > 1)
            vector.add(new HuffManNode(vector.poll(), vector.poll()));
    }

    /**
     * This method generates the code. Traverse till leaf node
     * and assign all left values as 0 and right as 1. If leaf node
     * then put the character with it's Huffman code in TreeMap
     * @param node peek value of node which will be smallest
     * @param s empty string
     */
    private static void generateCodes(HuffManNode node, String s) {
        if (node != null) {
            if (node.right != null)
                generateCodes(node.right, s + "1");

            if (node.left != null)
                generateCodes(node.left, s + "0");

            if (node.left == null && node.right == null)
                codes.put(node.character.charAt(0), s);
        }
    }

    /**
     * This method is used to write header file to the output file.
     * The output file will have binary string of character and
     * huffman code
     * @param output1 output file
     * @return header
     */
    private static String writeHeaderToFile(String output1) {
        String header = "";
        for (Entry<Character, String> entry : codes.entrySet()) {
            header += convertTo8bits(Integer.toBinaryString(entry.getKey() & 0xFF)) + "," + entry.getValue() + "\n";
        }
        header += "\n";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(output1)))) {
            writer.write(header);
        } catch (Exception e) {
            System.out.println(e);
        }
        return header;
    }

    /**
     * This method store the huffman encoded characters in string builder.
     * @param fileName name of the file
     * @return string builder which have encoded data
     * @throws IOException
     */
    private static StringBuilder encodeCharacters(String fileName) throws IOException {
        StringBuilder stb = new StringBuilder();
        try (FileInputStream input = new FileInputStream(fileName);) {
            int nextChar = input.read();
            while (nextChar != -1) {
                Byte b = (byte) nextChar;
                char c = (char) (b & 0xFF);
                stb.append(codes.get(c) != null ? codes.get(c) : "");
                nextChar = input.read();
            }
        }
        return stb;
    }

    /**
     * This method write the body of the file which is the huffman encoded
     * code for characters and for the last byte if not equal to 8 then append
     * the code with 0.
     * @param binChar huffman encoded characters
     * @param outputFileName output file
     * @throws IOException
     */
    public static void writeBody(StringBuilder binChar, String outputFileName)
            throws IOException {
        // write the bytes to the file
        while (binChar.length() > 8) {
            int readMax = 8 * (binChar.length() / 8);
            writeBinStrToFile(binChar.substring(0, readMax), outputFileName);
            binChar.delete(0, readMax);
        }

        if (binChar.length() != 0) {
            for (int i = binChar.length(); i != 8; i++)
                binChar.append("0");
            writeBinStrToFile(binChar.toString(), outputFileName);
        }
    }

    /**
     * This method write the body of the file which is the huffman encoded
     * code and compressed file
     * @param outputStr string builder
     * @param outputFileName output file
     * @throws IOException
     */
    public static void writeBinStrToFile(String outputStr, String outputFileName)
            throws IOException {

        int strLen = outputStr.length();
        byte[] toWrite = new byte[strLen / 8];
        for (int i = 0; i < outputStr.length() / 8; i++) {
            toWrite[i] = (byte) Integer.parseInt(outputStr.substring(i * 8, (i + 1) * 8), 2);
        }
        try (FileOutputStream output = new FileOutputStream(outputFileName, true);) {
            output.write(toWrite);
        }
    }

    /**
     * This method is used to convert the binary to bits and
     * add 0 when length is not equal to 8.
     * @param binary
     * @return
     */
    public static String convertTo8bits(String binary) {
        String zeros = "";
        for (int i = 0; i != (8 - binary.length()); i++) {
            zeros += "0";
        }
        return zeros + binary;
    }

    /**
     * This method is used to decode the encoded file.
     * @param target input file
     * @param destination output file
     * @return true if decoded successfully. Otherwise, false
     */
    public static boolean decode(String target, String destination) {

        FileInputStream input;
        try {
            input = new FileInputStream(target);
            String header = retrieveEncodedHeader(input);
            String body = retrieveEncodedBody(input);
            HashMap<String, String> codewords = retrieveCodewords(header);
            String bin = "";
            StringBuilder section = new StringBuilder("");
            char[] bodyArray = body.toCharArray();
            int length = body.length();
            for (int i = 0; i < length; i++) {
                bin = bin + bodyArray[i];
                String code = searchForCode(bin, codewords);
                //check for huffman code
                if (code != null) {
                    if (code.equals("EOF"))
                        break;
                    section.append(code);
                    bin = "";
                }
            }
            writeDecodedFile(section, destination);
            return true;
        } catch (FileNotFoundException e) {
            System.err.printf("No such file: %s\n", target);
            return false;
        } catch (IOException e) {
            System.err.printf("IOException while reading from file %s\n", target);
            return false;
        }
    }

    /**
     * This method is used to retrieve the encoded header.
     * @param it input file stream
     * @return header
     * @throws NumberFormatException
     * @throws IOException
     */
    public static String retrieveEncodedHeader(FileInputStream it) throws NumberFormatException, IOException {
        String endOfHeader = convertTo8bits(Integer.toBinaryString('\n'));
        StringBuilder header = new StringBuilder("");
        String doubleKey = "";
        String section = "";
        boolean error = true;
        endOfHeader += endOfHeader;
        int read;
        while ((read = it.read()) != -1) {
            Byte b = (byte) read;
            String toRtn = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
            section = toRtn;
            doubleKey += section;
            header.append((char) Integer.parseInt(section, 2));

            if (doubleKey.length() == 16) {
                if (doubleKey.equals(endOfHeader)) {
                    error = false;
                    break;
                }
                doubleKey = doubleKey.substring(8);
            }
        }

        // check if the header is well formated
        if (error == true) {
            System.err.println("Error: bad header format");
            System.exit(0);
        }
        return header.toString();
    }

    /**
     * This method is used to retrieve the encoded body.
     * @param it input file stream
     * @return body or section of a file
     * @throws IOException
     */
    public static String retrieveEncodedBody(FileInputStream it) throws IOException {
        StringBuilder section = new StringBuilder("");
        int read;
        while ((read = it.read()) != -1) {
            Byte b = (byte) read;
            String toRtn = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
            section.append(toRtn);
        }
        return section.toString();
    }

    /**
     * This method is used to retrieve the header key and value
     * in hashmap.
     * @param header header of a file
     * @return hashmap with the key and value
     */
    public static HashMap<String, String> retrieveCodewords(String header) {
        String[] lines = header.split("\n");
        HashMap<String, String> codewords = new HashMap<String, String>();

        for (int i = 0; i < lines.length; i++) {
            String[] codes = lines[i].split(",");

            if (codes.length > 1)
                codewords.put(codes[1], codes[0]);
        }
        return codewords;
    }

    /**
     * This method checks if the codewords contain the code of the given body.
     *
     * @param code the code for the body
     * @param codewords code words with the key and value
     * @return code from codewords if matched the code. Otherwise null
     */
    public static String searchForCode(String code, HashMap<String, String> codewords) {
        return codewords.containsKey(code) ? codewords.get(code) : null;
    }

    /**
     * This method write the decoded file in the destination output file
     * @param section empty string builder
     * @param destination output file
     * @throws IOException
     */
    public static void writeDecodedFile(StringBuilder section, String destination)
            throws IOException {
        while (section.length() > 8) {
            int readMax = 8 * (section.length() / 8);
            writeBinStrToFile(section.substring(0, readMax), destination);
            section.delete(0, readMax);
        }

        if (section.length() != 0) {
            for (int x = section.length(); x != 8; x++)
                section.append("0");
            writeBinStrToFile(section.toString(), destination);
        }
    }

    /**
     * The main function
     * @param args args[0] is the -i which is command for input file
     *             args[1] is the input file
     *             args[2] is the -o which is the output file
     *             args[3] is the output file
     *             args[4] is the -e or -d, which is encoding instruction
     *             or decoding instruction accordingly
     */
    public static void main(String[] args) {
        String inputFile = "", outputFile = "";
        if (args[0].equals("-i") || args[1].equals("-i")) {
            if (args[0].equals("-i")) {
                inputFile = args[1];
            }
            if (args[2].equals("-o")) {
                outputFile = args[3];
            }
            if (args[4].equals("-e")) {
                System.out.println("Encoding started");
                boolean success = encode(inputFile, outputFile);
                if (success) {
                    System.out.println("Encoded successfully");
                } else {
                    System.out.println("Could not encode");
                }
            }
            if (args[4].equals("-d")) {
                System.out.println("Decoding started");
                boolean success = decode(inputFile, outputFile);
                if (success) {
                    System.out.println("Decoded successfully");
                } else {
                    System.out.println("Could not decode");
                }
            }

        }
    }

}
