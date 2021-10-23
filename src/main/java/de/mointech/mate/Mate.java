package de.mointech.mate;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Hello world!
 *
 */

//
public class Mate
{

    // add new mate context for good java<->mate embeddable usage.
    // var ctx = new Mate.Context();
    // ctx.eval('puts $"Hello World: ${Date.now() }" ')
    // ctx.addModule('myObject', Object)
    public static void main( String[] args ) throws IOException {
        runFile("./data/main.m8");
    }

    private static void runFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()));
    }

    private static void run(String source) {
        Scanner scanner = new Scanner(source); List<Token> tokens = scanner.scanTokens();
        // For now, just print the tokens.
        for (Token token : tokens) {
            System.out.println(token);
        }
    }
}
