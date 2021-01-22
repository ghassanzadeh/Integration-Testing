import data.Entry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import modules.ModuleD;
import modules.ModuleF;
import modules.ModuleG;
import java.util.ArrayList;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

// // Reference: https://www.vogella.com/tutorials/Mockito/article.html
public class TestD {

    @Mock
    private ModuleG moduleg;
    @Mock
    private ModuleF modulef;


    ArrayList<Entry> output;
    ArrayList<Entry> output_updated;
    ArrayList<Entry> output_deleted;
    ArrayList<Entry> mockdata;
    ArrayList<Entry> updateddata;
    ArrayList<Entry> inserteddata;
    ArrayList<Entry> deleteddata;
    ModuleD moduled;

    @Before
    public void before() {
        modulef = mock(ModuleF.class);
        moduleg = mock(ModuleG.class);
        moduled = new ModuleD(modulef, moduleg);
        mockdata = new ArrayList<>();
        inserteddata = new ArrayList<>();
        updateddata = new ArrayList<>();
        deleteddata = new ArrayList<>();
        mockdata.add(new Entry("cccc", "3333"));
        mockdata.add(new Entry("bbbb", "2222"));
        mockdata.add(new Entry("aaaa", "1111"));
        mockdata.add(new Entry("hhhh", "8888"));
    }

    @After
    public void after() {
    }


    @Test
    public void testinsertDataD() {
        inserteddata.add(new Entry("cccc", "3333"));
        inserteddata.add(new Entry("bbbb", "2222"));
        inserteddata.add(new Entry("aaaa", "1111"));
        inserteddata.add(new Entry("hhhh", "8888"));
        inserteddata.add(new Entry("NewInsert", "222222"));
        output = moduled.insertData(mockdata,"NewInsert", "222222", "data_sample_md.txt");
        assertEquals(inserteddata.toString(), output.toString());
//        System.out.println(output.toString());
        verify(modulef).displayData(any());
        verify(moduleg).updateData(anyString(), any());
    }

    @Test
    public void testupdateDataD() {
        updateddata.add(new Entry("NewUpdate", "222222"));
        updateddata.add(new Entry("bbbb", "2222"));
        updateddata.add(new Entry("aaaa", "1111"));
        updateddata.add(new Entry("hhhh", "8888"));
        output_updated = moduled.updateData(mockdata,0,"NewUpdate", "222222", "data_sample_md.txt");
        assertEquals(updateddata.toString(), output_updated.toString());
        verify(modulef).displayData(any());
        verify(moduleg).updateData(anyString(), any());
    }

    @Test
    public void testdeleteDataD() {
        deleteddata.add(new Entry("bbbb", "2222"));
        deleteddata.add(new Entry("aaaa", "1111"));
        deleteddata.add(new Entry("hhhh", "8888"));
        output_deleted = moduled.deleteData(mockdata,0, "data_sample_md.txt");
        assertEquals(deleteddata.toString(), output_deleted.toString());
        verify(modulef).displayData(any());
        verify(moduleg).updateData(anyString(), any());
    }

    @Test
    public void testSetFD(){
        ModuleG moduleg_2 = mock(ModuleG.class);
        ModuleF modulef_2 = mock(ModuleF.class);
        ModuleD moduled_2 = new ModuleD(modulef_2, moduleg_2);
        moduled_2.setF(modulef_2);
    }

    @Test
    public void testSetGD(){
        ModuleF modulef_3 = mock(ModuleF.class);
        ModuleG moduleg_3 = mock(ModuleG.class);
        ModuleD moduled_3 = new ModuleD(modulef_3, moduleg_3);
        moduled_3.setG(moduleg_3);
    }

}
