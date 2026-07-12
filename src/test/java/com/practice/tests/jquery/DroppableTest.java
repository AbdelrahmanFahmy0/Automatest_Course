package com.practice.tests.jquery;

import com.practice.pages.jquery.DroppablePage;
import com.practice.template.TestCase;
import org.testng.annotations.Test;

public class DroppableTest extends TestCase {

    // Tests
    @Test
    public void checkDraggingAndDropping() {
        new DroppablePage(driver.get())
                .navigate()
                .dragBoxAndDrop()
                .checkBoxIsDragged();
    }
}