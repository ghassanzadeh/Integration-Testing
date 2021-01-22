import data.Entry;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import modules.ModuleF;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static junit.framework.TestCase.assertEquals;

public class TestF {

    ModuleF modulef;
    String result  = """ 
                Current Data:
                1 cccc, 3333
                2 bbbb, 2222
                3 aaaa, 1111
                4 hhhh, 8888
                """;
    ArrayList<Entry> mockdata;
    ByteArrayOutputStream outstream = new ByteArrayOutputStream();

    @Before
    public void before() {
        mockdata = new ArrayList<>();
        modulef = new ModuleF();
        modulef.setOutputStream(new PrintStream(outstream));
    }

    @After
    public void after() {
    }

    @Test
    public void testDisplayDataF() {
        mockdata.add(new Entry("cccc", "3333"));
        mockdata.add(new Entry("bbbb", "2222"));
        mockdata.add(new Entry("aaaa", "1111"));
        mockdata.add(new Entry("hhhh", "8888"));
        modulef.displayData(mockdata);
        assertEquals(result, outstream.toString());
    }
}
