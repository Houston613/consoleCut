package UtilityCut;

import com.google.common.annotations.Beta;
import com.google.common.io.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    @Beta
    public void testForC() throws IOException {
        String[] args = {"./src/test/resources/input/input1.txt", "-c", "-o", "src/test/resources/output/out1.txt", "-r", "-2"};
        Main.main(args);
        File test = new File("./src/test/resources/testFiles/test1.txt");
        File out = new File("./src/" +
                "test/resources/output/out1.txt");
        assertTrue(Files.equal(test, out));
    }
    @Test
    public void testForW() throws IOException {
        String[] args = {"./src/test/resources/input/input1.txt","-w","-o","src/test/resources/output/out2.txt","-r","-2"};
        Main.main(args);
        File test = new File("./src/test/resources/testFiles/test2.txt");
        File out = new File("./src/" +
                "test/resources/output/out2.txt");
        assertTrue(Files.equal(test,out));
    }
    /*UNDER CONSTRUCTION
    *public void testForConsoleOutAndInput(){
    проверно на jar
    */

    @Test
    public void testForRange() {
        String[] args = {"./src/test/resources/input/input1.txt","-w","-o","src/test/resources/output/out2.txt","-r","10-2"};
        assertThrows(IllegalArgumentException.class,()->UtilityCut.Main.main(args));
    }
    @Test
    public void testForRequired() {
        String[] args = {"./src/test/resources/input/input1.txt","-o","src/test/resources/output/out2.txt","-r","2-10"};
        assertThrows(IllegalArgumentException.class,()->UtilityCut.Main.main(args));
        /*UNDER CONSTRUCTION
        * как проверить ошибку парсера? там же ничего не кидается
         */
        String[] argsR = {"./src/test/resources/input/input1.txt","-w","-o", "src/test/resources/output/out2.txt", "-r"};
    }
}

