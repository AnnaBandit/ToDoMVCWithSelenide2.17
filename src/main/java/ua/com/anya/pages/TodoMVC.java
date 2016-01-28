package ua.com.anya.pages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static ua.com.anya.Helpers.doubleClick;

public class TodoMVC {
    public static ElementsCollection tasksList = $$("#todo-list li");
    public static ElementsCollection visibleTasks = tasksList.filter(visible);

    public static void add(String... tasksTexts){
        for(String taskText: tasksTexts){
            $("#new-todo").shouldBe(enabled).setValue(taskText).pressEnter();
        }
    }

    public static void assertVisibleTasks(String... tasksTexts){
        visibleTasks.shouldHave(exactTexts(tasksTexts));
    }

    public static void assertVisibleTasksListIsEmpty(){
        visibleTasks.shouldBe(empty);
    }

    public static void assertExistingTasks(String... tasksTexts){
        tasksList.shouldHave(exactTexts(tasksTexts));
    }

    public static void assertExistingTasksListIsEmpty(){
        tasksList.shouldBe(empty);
    }

    public static WebElement startEdit(String taskText, String newTaskText){
        doubleClick(tasksList.find(exactText(taskText)).find("label"));
        return tasksList.find(cssClass("editing")).find(".edit").setValue(newTaskText);
    }

    public static void delete(String taskText){
        actions().moveToElement(tasksList.find(exactText(taskText))).perform();
        $(By.className("destroy")).click();
    }

    public static void toggle(String taskText){
        tasksList.find(exactText(taskText)).find(".toggle").click();
    }

    public static void toggleAll(){
        $("#toggle-all").click();
    }

    public static void clearCompleted(){
        $("#clear-completed").click();
        $("#clear-completed").shouldBe(hidden);
    }

    public static void assertItemsLeft(Integer counter){
        $("#todo-count>strong").shouldHave(exactText(String.valueOf(counter)));
    }

    public static void openAllFilter(){
        $(By.linkText("All")).click();
    }

    public static void openActiveFilter(){
        $(By.linkText("Active")).click();
    }

    public static void openCompletedFilter(){
        $(By.linkText("Completed")).click();
    }

    public static void ensureOpenedTodoMVC(){
        if (url()!=("https://todomvc4tasj.herokuapp.com/")) {
            open("https://todomvc4tasj.herokuapp.com/");
        }
    }

    public static void given(Task... tasks){
        ensureOpenedTodoMVC();

        String js = "localStorage.setItem('todos-troopjs', '[";
        for (Task task: tasks){
            boolean isCompleted = task.getStatus() == Task.Status.COMPLETED;
            js += "{\"completed\":" + isCompleted + ", \"title\":\"" +  task.text + "\"},";
        }
        js = js.substring(0, js.length()-1) + "]');";

        executeJavaScript(js);
        refresh();
    }

    public static void givenAtAll(String... tasksTexts){
        given(convertTaskTextsIntoActiveTasks(tasksTexts));
    }

    public static void givenAtActive(Task... tasks){
        given(tasks);
        openActiveFilter();
    }

    public static void givenAtActive(String... tasksTexts){
        givenAtActive(convertTaskTextsIntoActiveTasks(tasksTexts));
    }

    public static void givenAtCompleted(Task... tasks){
        given(tasks);
        openCompletedFilter();
    }

    public static void givenAtCompleted(String... tasksTexts){
        given(convertTaskTextsIntoActiveTasks(tasksTexts));
        openCompletedFilter();
    }

    private static Task[] convertTaskTextsIntoActiveTasks(String...tasksTexts){
        ArrayList<Task> tasks = new ArrayList<Task>();
        for (String taskText: tasksTexts) {
            tasks.add(new Task(taskText, Task.Status.ACTIVE));
        }
        return tasks.toArray(new Task[tasks.size()]);
    }
}
