package test.com.rtmznk.railway.reader;


import com.rtmznk.railway.reader.TextFileReader;
import org.junit.*;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;

/**
 * Created by RTM on 28.02.2017.
 */
public class TextFileReaderTest {
    private static File tempFile;
    private static TextFileReader textFileReader;
    @Rule
    public final TemporaryFolder folder = new TemporaryFolder();

    @BeforeClass
    public static void initTextFileReader() {
        textFileReader = new TextFileReader();
    }

    @AfterClass
    public static void unInit() {
        textFileReader = null;
    }

    @Before
    public void initAndFillFile() throws IOException {
        tempFile = folder.newFile();
        FileWriter fw = new FileWriter(tempFile);
        fw.write("First string\n");
        fw.write("Second string");
        fw.close();
    }

    @Test
    public void readFileToStringListTest() {
        List<String> list = textFileReader.readFileToStringList(tempFile.getPath());
        String firstExpected = "First string";
        String secondExpected = "Second string";
        Assert.assertThat("Wrong list size :", list.size(), equalTo(2));
        Assert.assertThat("List hasn't expected strings: ", list, hasItems(firstExpected, secondExpected));
    }

    @Test(expected = RuntimeException.class)
    public void readFileToStringListWithWrongFileNameTest() {
        textFileReader.readFileToStringList("badFilePath");
        Assert.fail("Exception must occure");
    }
}
