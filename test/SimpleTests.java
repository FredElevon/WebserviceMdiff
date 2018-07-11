import at.aau.DiffInfo;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static at.aau.DiffMain.diffFiles;

public class SimpleTests {
    @Test
    public void simpleUpdateTest() { // Files: testoriginal.txt; testrevised.txt
        List<DiffInfo> myList = diffFiles("TestFiles/testoriginal.txt", "TestFiles/testrevised.txt");

        Assert.assertEquals("UPDATE", myList.get(0).getActionType());
        Assert.assertEquals(0, myList.get(0).getSrcID());
        Assert.assertEquals(0, myList.get(0).getSrcStartLine());
        Assert.assertEquals(2, myList.get(0).getSrcEndLine());
        Assert.assertEquals(4, myList.get(0).getSrcEndLineOffset());
        Assert.assertEquals(0, myList.get(0).getDstID());
        Assert.assertEquals(0, myList.get(0).getDstStartLine());
        Assert.assertEquals(2, myList.get(0).getDstEndLine());
        Assert.assertEquals(5, myList.get(0).getDstEndLineOffset());

    }

    @Test
    public void newlineLFTest() {
        List<DiffInfo> myList = diffFiles("TestFiles/newlineLF.txt", "TestFiles/newlineLFrevised.txt");

        Assert.assertEquals("UPDATE", myList.get(0).getActionType());
        Assert.assertEquals(0, myList.get(0).getSrcID());
        Assert.assertEquals(0, myList.get(0).getSrcStartLine());
        Assert.assertEquals(0, myList.get(0).getSrcEndLine());
        Assert.assertEquals(31, myList.get(0).getSrcEndLineOffset());
        Assert.assertEquals(0, myList.get(0).getDstID());
        Assert.assertEquals(0, myList.get(0).getDstStartLine());
        Assert.assertEquals(0, myList.get(0).getDstEndLine());
        Assert.assertEquals(33, myList.get(0).getDstEndLineOffset());
    }

    @Test
    public void newlineCRLFTest() {
        List<DiffInfo> myList = diffFiles("TestFiles/newlineCRLF.txt", "TestFiles/newlineCRLFrevised.txt");

        Assert.assertEquals(0, myList.size());
    }
    @Test
    public void newlineAllTypesTest() {
        List<DiffInfo> myList = diffFiles("TestFiles/newlineAllTypes.txt", "TestFiles/newlineAllTypesrevised.txt");

        Assert.assertEquals(0, myList.size());
    }
    @Test
    public void newlineCRTest() {
        List<DiffInfo> myList = diffFiles("TestFiles/newlineCR.txt", "TestFiles/newlineCRrevised.txt");

        Assert.assertEquals(0, myList.size());
    }
}
