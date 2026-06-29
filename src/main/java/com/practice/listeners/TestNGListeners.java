package com.practice.listeners;

import com.practice.utils.dataReader.PropertyReader;
import org.testng.*;

public class TestNGListeners implements ISuiteListener, IExecutionListener, IInvokedMethodListener, ITestListener {

    public void onStart(ISuite suite) {
        suite.getXmlSuite().setName("Automatest Test Suite");
    }

    public void onExecutionStart() {
        PropertyReader.loadProperties();
    }
}