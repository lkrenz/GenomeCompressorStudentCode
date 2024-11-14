/******************************************************************************
 *  Compilation:  javac GenomeCompressor.java
 *  Execution:    java GenomeCompressor - < input.txt   (compress)
 *  Execution:    java GenomeCompressor + < input.txt   (expand)
 *  Dependencies: BinaryIn.java BinaryOut.java
 *  Data files:   genomeTest.txt
 *                virus.txt
 *
 *  Compress or expand a genomic sequence using a 2-bit code.
 ******************************************************************************/

/**
 *  The {@code GenomeCompressor} class provides static methods for compressing
 *  and expanding a genomic sequence using a 2-bit code.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *  @author Zach Blick
 */
public class GenomeCompressor {

    /**
     * Reads a sequence of 8-bit extended ASCII characters over the alphabet
     * { A, C, T, G } from standard input; compresses and writes the results to standard output.
     */
    public static void compress() {

        // TODO: complete the compress() method
        int[] map = new int[117];
        map['A'] = 0;
        map['C'] = 1;
        map['G'] = 2;
        map['T'] = 3;

        // Read in the full sequence and measure the length
        String sequence = BinaryStdIn.readString();
        int length = sequence.length();

        // Add the length as an integer at the start of the sequence
        BinaryStdOut.write(length);

        // Convert all chars into 2 bit integers
        for (int i = 0; i < sequence.length(); i++) {
            BinaryStdOut.write(map[sequence.charAt(i)], 2);
        }
        BinaryStdOut.close();
    }

    /**
     * Reads a binary sequence from standard input; expands and writes the results to standard output.
     */
    public static void expand() {
        char[] map = new char[4];
        map[0] = 'A';
        map[1] = 'C';
        map[2] = 'G';
        map[3] = 'T';

        // Read in the length of the file from the first int
        int length = BinaryStdIn.readInt();

        // Convert all ints back into chars
        for (int i = 0; i < length; i++) {
            BinaryStdOut.write(map[BinaryStdIn.readInt(2)]);
        }
        BinaryStdOut.close();
    }


    /**
     * Main, when invoked at the command line, calls {@code compress()} if the command-line
     * argument is "-" an {@code expand()} if it is "+".
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

        if      (args[0].equals("-")) compress();
        else if (args[0].equals("+")) expand();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}