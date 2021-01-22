import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import java.io.ByteArrayOutputStream;
import org.junit.After;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import data.Entry;
import modules.*;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import modules.ModuleE.DataBaseExitException;

// Reference: https://www.vogella.com/tutorials/Mockito/article.html
// https://www.javadoc.io/doc/org.mockito/mockito-core/2.8.47/org/mockito/Mockito.html
public class TestA {

    @Rule
    MockitoRule mockitoRule = MockitoJUnit.rule();

    ModuleA modulea;
    ArrayList<Entry> mockdata;
    ArrayList<Entry> sortdata;
    String replace_regex = "\\r{0,1}\\n|\\r";

    ByteArrayOutputStream outstream = new ByteArrayOutputStream();

    @Mock
    ModuleB moduleb;
    @Mock
    ModuleC modulec;
    @Mock
    ModuleD moduled;
    @Mock
    ModuleE modulee;


    String available_command = "Available Commands: \n" +
            "load <filepath>\n" +
            "add <name> <number>\n" +
            "update <index> <name> <number>\n" +
            "delete <index>\n" +
            "sort\n" +
            "exit\n";
    String output_no_file = "No file loaded!\n";
    String output_malformed = "Malformed command!\n";

    @BeforeEach
    public void before() {
        mockdata = new ArrayList<>();
        mockdata.add(new Entry("cccc", "3333"));
        mockdata.add(new Entry("bbbb", "2222"));
        mockdata.add(new Entry("aaaa", "1111"));
        mockdata.add(new Entry("hhhh", "8888"));
        outstream.reset();
        moduleb = mock(ModuleB.class);
        modulec = mock(ModuleC.class);
        moduled = mock(ModuleD.class);
        modulee = mock(ModuleE.class);
        modulea = new ModuleA(moduleb, modulec, moduled, modulee);
        modulea.setOutputStream(new PrintStream(outstream));
    }

    @After
    public void after() {
    }

    @Test
    public void testdisplayHelpA() throws DataBaseExitException {
        modulea.run(new String[]{"help"});
        assertEquals(available_command , outstream.toString().replaceAll(replace_regex, "\n"));
    }

    @Test
    public void testparseLoadA() throws DataBaseExitException {
        modulea.run(new String[]{"load", "data_sample.txt"});
        verify(moduleb).loadFile("data_sample.txt");
    }

    @Test
    public void testparseLoadMalformedA() throws DataBaseExitException {
        String[] command_arg = new String[]{"load"};
        modulea.run(command_arg);
        assertEquals(output_malformed, outstream.toString().replaceAll(replace_regex, "\n"));
    }

    @Test
    public void testparseAddA() throws DataBaseExitException {
        ArrayList<Entry> inserteddata = new ArrayList<>();
        inserteddata.add(new Entry("cccc", "3333"));
        inserteddata.add(new Entry("bbbb", "2222"));
        inserteddata.add(new Entry("aaaa", "1111"));
        inserteddata.add(new Entry("hhhh", "8888"));
        inserteddata.add(new Entry("NewInsert", "222222"));
        Mockito.when(moduled.insertData(mockdata, "NewInsert", "222222", "data_sample.txt")).thenReturn(inserteddata);
        String[] command_arg_1 = new String[]{"load", "data_sample.txt"};
        String[] command_arg_2 = new String[]{"add", "dddd", "4444"};
        modulea.run(command_arg_1);
        modulea.run(command_arg_2);
        verify(moduled).insertData(any(), anyString(), anyString(), anyString());
    }

    @Test
    public void testparseAddNoFileLoadedA() throws DataBaseExitException {
        String[] command_no_file_loaded = new String[]{"add", "dddd", "4444"};
        modulea.run(command_no_file_loaded);
        assertEquals(output_no_file, outstream.toString().replaceAll(replace_regex, "\n"));
    }

    @Test
    public void testparseAddMalformedCommandA() throws DataBaseExitException {
        String[] load_malformed = new String[]{"load", "data_sample.txt"};
        String[] add_malformed = new String[]{"add"};
        modulea.run(load_malformed);
        modulea.run(add_malformed);
        assertEquals(output_malformed, outstream.toString().replaceAll(replace_regex, "\n"));
    }

    @Test
    public void testrunSortA() throws DataBaseExitException {
        sortdata = new ArrayList<>();
        sortdata.add(new Entry("aaaa", "1111"));
        sortdata.add(new Entry("bbbb", "2222"));
        sortdata.add(new Entry("cccc", "3333"));
        sortdata.add(new Entry("hhhh", "8888"));
        Mockito.when(modulec.sortData(mockdata)).thenReturn(sortdata);
        String[] load_sort = new String[]{"load", "data_sample.txt"};
        String[] run_sort = new String[]{"sort"};
        modulea.run(load_sort);
        modulea.run(run_sort);
        verify(modulec).sortData(any());
    }

    @Test
    public void testrunSortNoFileLoadedA() throws DataBaseExitException {
        modulea.run(new String[]{"sort"});
        assertEquals(output_no_file, outstream.toString().replaceAll(replace_regex, "\n"));
    }

    @Test
    public void testparseUpdateA() throws DataBaseExitException {
        ArrayList<Entry> updateddata = new ArrayList<>();
        updateddata.add(new Entry("NewUpdate", "222222"));
        updateddata.add(new Entry("bbbb", "2222"));
        updateddata.add(new Entry("aaaa", "1111"));
        updateddata.add(new Entry("hhhh", "8888"));
        Mockito.when(moduled.updateData(mockdata, 0, "NewUpdate", "222222", "data_sample_md.txt")).thenReturn(updateddata);
        String[] load_update = new String[]{"load", "data_sample.txt"};
        String[] run_update = new String[]{"update", "0", "eeee", "5555"};
        modulea.run(load_update);
        modulea.run(run_update);
        verify(moduled).updateData(any(), anyInt(), anyString(), anyString(), anyString());
    }


    @Test
    public void testparseUpdateNoFileLoadedA() throws DataBaseExitException {
        modulea.run(new String[]{"update", "2", "eeee", "5555"});
        assertEquals(output_no_file, outstream.toString().replaceAll(replace_regex, "\n"));
    }

    @Test
    public void testparseUpdateMalformedA() throws DataBaseExitException {
        String[] load_update_malformed = new String[]{"load", "data_sample.txt"};
        String[] parse_update_malformed = new String[]{"update"};
        modulea.run(load_update_malformed);
        modulea.run(parse_update_malformed);
        assertEquals(output_malformed, outstream.toString().replaceAll(replace_regex, "\n"));
    }

    @Test
    public void testparseDeleteA() throws DataBaseExitException {
        ArrayList<Entry> deleteddata = new ArrayList<>();
        deleteddata.add(new Entry("bbbb", "2222"));
        deleteddata.add(new Entry("aaaa", "1111"));
        deleteddata.add(new Entry("hhhh", "8888"));
        Mockito.when(moduled.deleteData(mockdata, 0, "data_sample_md.txt")).thenReturn(deleteddata);
        String[] load_delete = new String[]{"load", "data_sample.txt"};
        String[] parse_delete = new String[]{"delete", "0"};
        modulea.run(load_delete);
        modulea.run(parse_delete);
        verify(moduled).deleteData(any(), anyInt(), anyString());
    }

    @Test
    public void testparseDeleteNoFileLoadedA() throws DataBaseExitException {
        String[] parse_delete_no_file = new String[]{"delete", "2"};
        modulea.run(parse_delete_no_file);
        assertEquals(output_no_file, outstream.toString().replaceAll(replace_regex, "\n"));
    }

    @Test
    public void testparseDeleteMalformedA() throws DataBaseExitException {
        String[] load_delete_malformed = new String[]{"load", "data_sample.txt"};
        String[] parse_delete_malformed = new String[]{"delete"};
        modulea.run(load_delete_malformed);
        modulea.run(parse_delete_malformed);
        assertEquals(output_malformed, outstream.toString().replaceAll(replace_regex, "\n"));
    }

    @Test
    public void testrunExitA() {
        String[] parse_exit = {"exit"};
        // https://howtodoinjava.com/junit5/expected-exception-example/
        Assertions.assertThrows(DataBaseExitException.class, () -> {
            modulea.run(parse_exit);
        });
    }


    @Test
    public void testUnknownCommandA() throws DataBaseExitException {
        String[] unknown_command = {"run sth"};
        modulea.run(unknown_command);
        String unknown_command_error = "Unknown command, type 'help' for command list.\n";
        assertEquals(unknown_command_error, outstream.toString().replaceAll(replace_regex, "\n"));
    }

}

