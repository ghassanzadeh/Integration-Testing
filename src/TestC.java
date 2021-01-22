import data.Entry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import modules.ModuleC;
import modules.ModuleF;
import java.util.ArrayList;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
// // Reference: https://www.vogella.com/tutorials/Mockito/article.html
public class TestC {

    @Mock
    private ModuleF modulef;


    ArrayList<Entry> mockdata;
    ArrayList<Entry> sortdata;
    ModuleC modulec;

    @Before
    public void before() {
        modulef = mock(ModuleF.class);
        modulec = new ModuleC(modulef);
        mockdata = new ArrayList<>();
        sortdata = new ArrayList<>();
    }

    @After
    public void after() {

    }

    @Test
    public void testsortDataC() { // fail
        sortdata.add(new Entry("aaaa", "1111"));
        sortdata.add(new Entry("bbbb", "2222"));
        sortdata.add(new Entry("cccc", "3333"));
        sortdata.add(new Entry("hhhh", "8888"));
        mockdata.add(new Entry("cccc", "3333"));
        mockdata.add(new Entry("bbbb", "2222"));
        mockdata.add(new Entry("aaaa", "1111"));
        mockdata.add(new Entry("hhhh", "8888"));
        assertEquals(modulec.sortData(mockdata).toString(), sortdata.toString());
        verify(modulef).displayData(any());
    }

    @Test
    public void TestSetFC(){
        ModuleF modulef_2 = mock(ModuleF.class);
        ModuleC modulec_2 = new ModuleC(modulef_2);
        modulec_2.setF(modulef_2);
    }
}