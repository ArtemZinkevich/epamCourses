package test.com.rtmznk.sphere.reader;

import com.rtmznk.sphere.reader.TextFileReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by RTM on 28.02.2017.
 */
public class TextFileReaderTest {
    public static File tempFile;
    @Rule
    public final TemporaryFolder tempFolder = new TemporaryFolder();
    @Before
    public void initTempFile() throws IOException {
        tempFile = tempFolder.newFile("temp.txt");
        FileWriter fileWriter = new FileWriter(tempFile);
        fileWriter.write("First String\n");
        fileWriter.write("Second String\n");
        fileWriter.close();
    }
    @Test
    public void readFileToStringListTest() {
        TextFileReader textFileReader  = new TextFileReader();
        String expectedFirst = "First String";
        String expectedSecond = "Second String";
        int expectedListSize =2;
        List<String> actualList = textFileReader.readFileToStringList(tempFile.getPath());
        Assert.assertEquals("Wrong list size :",expectedListSize,actualList.size());
        Assert.assertEquals(expectedFirst,actualList.get(0));
        Assert.assertEquals(expectedSecond,actualList.get(1));
    }
    @Test(expected = RuntimeException.class)
    public void readFileToStringListFromFileWithIncorrectNameTest(){
        TextFileReader textFileReader  = new TextFileReader();
        textFileReader.readFileToStringList("badFileName");
        Assert.fail("RuntimeException must occure");
    }
}
