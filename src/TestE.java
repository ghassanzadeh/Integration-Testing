import modules.ModuleE;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import modules.ModuleE.DataBaseExitException;

public class TestE {

    ModuleE exit;

    @Before
    public void before() {
        exit = new ModuleE();
    }

    @After
    public void after() {
    }

    @Test(expected = DataBaseExitException.class)
    public void testExitProgram() throws DataBaseExitException {
        exit.exitProgram();
    }
}
