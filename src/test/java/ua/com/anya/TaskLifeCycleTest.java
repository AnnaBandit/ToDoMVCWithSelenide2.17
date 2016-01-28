package ua.com.anya;

import org.junit.Test;
import ua.com.anya.testconfigs.AtTodoMVCPageWithClearedDataAfterEachTest;

import static ua.com.anya.pages.TodoMVC.*;

public class TaskLifeCycleTest extends AtTodoMVCPageWithClearedDataAfterEachTest {

    @Test
    public void testTasksMainFlowThroughFilters(){

        ensureOpenedTodoMVC();

        add("a");
        toggle("a");
        assertExistingTasks("a");

        openActiveFilter();
        assertVisibleTasksListIsEmpty();

        openCompletedFilter();
        assertVisibleTasks("a");

        //activate
        toggle("a");
        assertVisibleTasksListIsEmpty();

        openActiveFilter();
        assertVisibleTasks("a");

        toggle("a");
        assertVisibleTasksListIsEmpty();

        openAllFilter();
        assertExistingTasks("a");

        delete("a");
        assertExistingTasksListIsEmpty();
    }
}
