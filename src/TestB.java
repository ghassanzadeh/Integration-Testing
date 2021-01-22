import data.Entry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import java.util.ArrayList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import modules.ModuleB;
import modules.ModuleF;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.verify;
// // Reference: https://www.vogella.com/tutorials/Mockito/article.html
public class TestB {

    @Mock
    private ModuleF modulef;

    private ArrayList<Entry> mockdata;

    @Before
    public void before() {
        modulef = mock(ModuleF.class);
    }

    @After
    public void after() {
    }


    @Test
    public void testValidFileB() {
        ModuleB moduleb = new ModuleB(modulef);
        mockdata = new ArrayList<>();
        mockdata.add(new Entry("cccc", "3333"));
        mockdata.add(new Entry("bbbb", "2222"));
        mockdata.add(new Entry("aaaa", "1111"));
        mockdata.add(new Entry("hhhh", "8888"));
        // System.out.println(moduleb.loadFile("data_sample.txt").toString());
        assertEquals(mockdata.toString(), moduleb.loadFile("data_sample.txt").toString());
        verify(modulef).displayData(any());
    }

    @Test
    public void testNotFoundB() {
        ModuleB moduleb = new ModuleB(modulef);
        moduleb.loadFile("notfound.txt");
    }

    @Test
    public void testSetFB(){
        ModuleF modulef_2 = mock(ModuleF.class);
        ModuleB moduleb_2 = new ModuleB(modulef_2);
        moduleb_2.setF(modulef_2);
    }

}
