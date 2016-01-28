import org.junit.Test;
import org.openqa.selenium.Keys;
import testconfigs.AtTodoMVCPageWithClearedDataAfterEachTest;

import static ua.com.anya.pages.Task.Status.ACTIVE;
import static ua.com.anya.pages.Task.Status.COMPLETED;
import static ua.com.anya.pages.Task.aTask;
import static ua.com.anya.pages.TodoMVC.*;

public class AtActiveFilterTest extends AtTodoMVCPageWithClearedDataAfterEachTest {

    @Test
    public void testEditByPressingEnter(){
        givenAtActive("a");

        startEdit("a", "a-edited").sendKeys(Keys.ENTER);
        assertVisibleTasks("a-edited");
        assertItemsLeft(1);
    }

    @Test
    public void testCompleteAll(){
        givenAtActive("a", "b");

        toggleAll();
        assertVisibleTasksListIsEmpty();
        openCompletedFilter();
        assertVisibleTasks("a", "b");
    }

    @Test
    public void testClearCompleted(){
        givenAtActive(aTask("a", COMPLETED),
                aTask("b", COMPLETED),
                aTask("c", ACTIVE));

        clearCompleted();
        assertExistingTasks("c");
        assertItemsLeft(1);
        openCompletedFilter();
        assertVisibleTasksListIsEmpty();
    }

    @Test
    public void testDelete(){
        givenAtActive("a", "b");

        delete("a");
        assertVisibleTasks("b");
        assertItemsLeft(1);
    }
}
