# Automatest Course

A comprehensive Java-based test automation framework built with **Selenium WebDriver** and **TestNG**. This project
demonstrates best practices in automation testing including page object pattern, driver management, structured
logging, and Allure 3 reporting, wired into a GitHub Actions CI pipeline.

## 📋 Project Overview

**Automatest_Course** is an educational automation testing project that showcases:

- Web automation testing using Selenium WebDriver
- Test framework setup with TestNG
- Page Object Model (POM) design pattern
- Multiple browser support (Chrome, Firefox, Edge)
- Data-driven testing capabilities
- Structured logging with Log4j2 and console output capture
- Allure 3 reporting
- CI pipeline (GitHub Actions) running cross-platform/browser test matrices

---

## 🏗️ Project Structure

```
Automatest_Course/
├── src/
│   ├── main/
│   │   └── java/com/practice/
│   │       ├── drivers/              # WebDriver management
│   │       │   ├── Driver.java
│   │       │   ├── Browser.java
│   │       │   ├── UITest.java       # Marker annotation for UI test classes
│   │       │   ├── AbstractDriver.java
│   │       │   ├── ChromeFactory.java
│   │       │   ├── FirefoxFactory.java
│   │       │   └── EdgeFactory.java
│   │       ├── pages/                # Page Object Model classes
│   │       │   ├── duckduckgo/
│   │       │   ├── heroku/
│   │       │   ├── jquery/
│   │       │   └── w3schools/
│   │       ├── utils/
│   │       │   ├── Indexes.java      # Shared path constants
│   │       │   ├── bots/             # Helper utilities
│   │       │   │   ├── BrowserBot.java
│   │       │   │   ├── ActionsBot.java
│   │       │   │   └── AssertionsBot.java
│   │       │   ├── core/             # Core framework utilities
│   │       │   │   ├── WaitManager.java        
│   │       │   │   ├── FileUtils.java          # Directory cleanup & file deletion
│   │       │   │   ├── OSUtils.java            # OS detection (Windows/Mac/Linux)
│   │       │   │   ├── ScreenshotsManager.java # Full-page screenshot capture
│   │       │   │   ├── TerminalUtils.java      # Executes external CLI commands
│   │       │   │   └── TimeManager.java
│   │       │   ├── dataReader/       # Configuration & data reading
│   │       │   │   ├── PropertyReader.java
│   │       │   │   └── JsonReader.java
│   │       │   ├── logs/             # Logging
│   │       │   │   ├── LogsManager.java
│   │       │   │   └── ConsoleOutputCapture.java
│   │       │   └── report/           # Allure reporting integration
│   │       │       ├── AllureAttachmentManager.java
│   │       │       ├── AllureEnvironmentManager.java
│   │       │       └── AllureReportGenerator.java
│   │       └── listeners/            # TestNG listeners
│   │           └── TestNGListeners.java
│   └── test/
│       └── java/com/practice/
│           ├── template/             # Base test templates
│           │   ├── TestCase.java
│           │   └── TestScenario.java
│           └── tests/                # Actual test classes
│               ├── duckduckgo/
│               ├── heroku/
│               ├── jquery/
│               └── w3schools/
├── src/main/resources/
│   ├── config.properties             # Browser, execution type & environment
│   ├── allure.properties             # Allure results directory & auto-open flag
│   ├── environment.properties         # Target application URLs
│   ├── waits.properties              # Default wait timeout
│   └── log4j2.properties             # Logging configuration
├── allurerc.mjs                      # Allure 3 report configuration (history, plugins)
├── pom.xml                            # Maven build configuration
├── .github/workflows/Pipeline.yml     # CI pipeline definition
└── test-output/                       # Logs, screenshots, allure results/report
```

---

## 🔧 Technologies & Dependencies

| Technology                    | Version  | Purpose                                       |
|--------------------------------|----------|------------------------------------------------|
| **Java**                       | 23       | Programming language                           |
| **Selenium WebDriver**         | 4.48.0   | Browser automation                             |
| **TestNG**                     | 7.12.0   | Test framework                                 |
| **Maven**                      | Latest   | Build and dependency management                |
| **Node.js**                    | 24       | Runs the Allure 3 CLI via `npx`                |
| **AspectJ**                    | 1.9.25.1 | AOP for test listeners and reporting           |
| **Allure TestNG / allure-bom**| 2.35.4   | Test result collection for Allure 3 reports    |
| **Log4j2 (core/api/slf4j2)**   | 2.26.0   | Structured logging                             |
| **Commons IO**                 | 2.22.0   | File operations                                |
| **JSON Path**                  | 3.0.0    | JSON parsing and extraction                    |
| **JSON Simple**                | 1.1.1    | JSON handling                                  |

---

## 🚀 Getting Started

### Prerequisites

- **Java 23** or higher installed
- **Maven 3.6+** installed
- **Node.js 24+** installed (required to generate/open Allure 3 reports via `npx`)
- **Git** installed
- Chrome, Firefox, and/or Edge browsers installed (for WebDriver compatibility)

### Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/AbdelrahmanFahmy0/Automatest_Course.git
   cd Automatest_Course
   ```

2. **Install dependencies:**
   ```bash
   mvn clean install
   ```

3. **Configure properties** (optional):
    - Edit `src/main/resources/config.properties` to set target browser, execution type and environment

---

## ✅ Running Tests

### Run All Tests

```bash
mvn test
```

### Run Specific Test Class

```bash
mvn test -Dtest=TablesTest
```

### Run Tests by Suite

```bash
mvn test -Dtest=**/duckduckgo/**
```

### Run with a Specific Browser / Environment

Override `config.properties` values from the command line, e.g. to match the CI pipeline:

```bash
mvn test -DTargetBrowser=Edge -DExecutionType=LocalHeadless
```

### View Test Reports

An Allure 3 report is automatically generated (and optionally opened) at the end of every test run via
`TestNGListeners`. Reports and other outputs are written under `test-output/`:

- `test-output/logs/` – Log4j2 log files
- `test-output/screenshots/` – full-page screenshots for `@UITest`-annotated classes
- `test-output/reports/allure-results/` – raw Allure results consumed by the CLI
- `test-output/reports/allure-report/` – the generated Allure 3 HTML report
- `test-output/reports/.allure/history.jsonl` – historical trend data (cached across CI runs)

Report generation relies on the Allure 3 CLI executed on demand via `npx`, so **Node.js must be installed** even
though there is no `package.json` in the repository.

---

## 📦 Key Components

### Driver Management

- **Driver.java**: Central driver management class exposing `action()`, `browser()` and `check()` bots
- **Browser.java**: Browser enumeration (Chrome, Firefox, Edge)
- **UITest.java**: Marker annotation applied to UI test classes so listeners know to capture screenshots
- **ChromeFactory.java**: Chrome WebDriver instantiation
- **FirefoxFactory.java**: Firefox WebDriver instantiation
- **EdgeFactory.java**: Edge WebDriver instantiation
- **AbstractDriver.java**: Abstract base class for driver implementations

### Page Object Model

Page classes encapsulate web elements and actions for specific pages:

- **DuckDuckGo Pages**: Search engine automation
- **Heroku Pages**: Demo application testing (checkboxes, uploads)
- **W3Schools Pages**: Web table interaction
- **jQuery Pages**: Droppable element interactions

### Utilities

- **FileUtils.java**: Cleans/deletes directories and files between runs
- **OSUtils.java**: Detects the current operating system (Windows/Mac/Linux)
- **TerminalUtils.java**: Executes external CLI commands (e.g. the Allure npx CLI)
- **ScreenshotsManager.java**: Captures full-page screenshots on test completion
- **TimeManager.java**: Time/date helpers
- **BrowserBot.java**: Common browser operations (click, type, navigate)
- **ActionsBot.java**: Advanced actions (hover, drag-drop, keyboard)
- **AssertionsBot.java**: Custom assertion utilities
- **PropertyReader.java**: Reads configuration from properties files
- **JsonReader.java**: Parses JSON data files
- **LogsManager.java**: Log4j2 wrapper used across the framework
- **ConsoleOutputCapture.java**: Captures console output per test for Allure attachments

### Reporting

- **AllureReportGenerator.java**: Resolves the latest Allure 3 CLI version and generates/opens the HTML report via `npx`
- **AllureAttachmentManager.java**: Attaches captured console logs to Allure test results
- **AllureEnvironmentManager.java**: Writes environment metadata (Java version, browser, environment) into the report

### Test Listeners

- **TestNGListeners.java**: Custom listeners for the full test lifecycle — cleans output directories, loads
  properties, captures console output, takes screenshots, attaches logs, and generates/opens the Allure report at
  suite end

---

## 📝 Test Categories

### DuckDuckGo Tests

- Search functionality
- Results navigation
- Search result validation

### Heroku Tests

- Checkbox interactions
- File upload functionality
- Dynamic element handling

### W3Schools Tests

- HTML table element interaction
- Table sorting and filtering
- Table content extraction

### jQuery Tests

- Droppable widget interactions
- Drag and drop operations
- jQuery-specific element handling

---

## 🎯 Best Practices Implemented

✅ **Page Object Model (POM)** - Separates page logic from test logic  
✅ **DRY Principle** - Reusable utility methods and common actions  
✅ **Wait Strategies** - Implicit and explicit waits for stability  
✅ **Modular Structure** - Organized packages by functionality (bots, core, dataReader, logs, report)  
✅ **Data-Driven Testing** - External data files (properties, JSON) for test data  
✅ **Test Listeners** - Custom event handlers for test lifecycle  
✅ **Factory Pattern** - Driver instantiation with factory classes  
✅ **Configuration Management** - Externalized configuration in properties files  
✅ **Structured Logging** - Log4j2 logging with per-test console capture  
✅ **Rich Reporting** - Allure 3 reports with attachments, environment info and history trends  
✅ **Continuous Integration** - Automated cross-browser/OS test runs on every push and PR

---

## 🔧 Configuration

Edit `src/main/resources/config.properties` to customize:

```properties
TargetBrowser=Chrome             # Browser type (Chrome, Firefox, Edge)
ExecutionType=Local              # Execution type (Local, LocalHeadless)
Environment=Test                 # Environment (Test, Staging)
```

Edit `src/main/resources/allure.properties` to customize Allure result output and report auto-opening:

```properties
allure.results.directory=test-output/reports/allure-results
OpenAllureReportAfterExecution=false   # Set to true to auto-open the report after a local run
```

Edit `src/main/resources/waits.properties` to adjust the default explicit wait timeout (in seconds), and
`src/main/resources/environment.properties` to change the target application URLs used by each page/test.

---

## 🤖 Continuous Integration

The `.github/workflows/Pipeline.yml` workflow runs on every push and pull request to `master`:

- Executes a matrix build: **Chrome on Ubuntu (headless)** and **Edge on Windows (headless)**
- Sets up Java 23, Maven and Node.js 24 (Node.js is required to run the Allure 3 CLI)
- Restores/saves the Allure history (`history.jsonl`) via `actions/cache` so report trends persist across runs
- Continues past test failures to still publish reports and logs
- Uploads the generated Allure report and log files as workflow artifacts (30-day retention)

---

## 📚 Learning Resources

- [Selenium Documentation](https://www.selenium.dev/documentation/)
- [TestNG Documentation](https://testng.org/doc/)
- [Page Object Model Pattern](https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/)
- [Maven Documentation](https://maven.apache.org/guides/)
- [Allure Report Documentation](https://allurereport.org/docs/)
