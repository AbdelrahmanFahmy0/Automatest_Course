package com.practice.tests.jquery;

import com.practice.pages.jquery.DroppablePage;
import com.practice.template.TestCase;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

@Epic("Automatest Course")
@Feature("jQuery UI Droppable")
@Owner("Abdelrahman Fahmy")
public class DroppableTest extends TestCase {

    // Tests
    @Test(description = "Dragging a box into the drop target marks it as dropped")
    @Description("Verifies that dragging the draggable box and dropping it on the target marks the box as dropped")
    @Severity(SeverityLevel.NORMAL)
    public void checkDraggingAndDropping() {
        new DroppablePage(driver)
                .navigate()
                .dragBoxAndDrop()
                .checkBoxIsDragged();
    }
}