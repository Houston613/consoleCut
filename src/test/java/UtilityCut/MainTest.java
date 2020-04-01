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
    public void firstTest() throws IOException {
        String[] args = {"-c","-o","./src/test/resources/output/output1.txt","./src/test/resources/input/input1.txt","-2"};
        Main.main(args);
        File test = new File("./src/test/resources/tesFiles/test1.txt");
        File out = new File("./src/" +
                "test/resources/output/output1.txt");
        assertTrue(Files.equal(test,out));
    }
}

