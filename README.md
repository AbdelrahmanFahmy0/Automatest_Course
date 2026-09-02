# Automatest Course

A comprehensive Java-based test automation framework built with **Selenium WebDriver** and **TestNG**. This project
demonstrates best practices in automation testing including page object pattern, driver management, and modular test
structure.

## 📋 Project Overview

**Automatest_Course** is an educational automation testing project that showcases:

- Web automation testing using Selenium WebDriver
- Test framework setup with TestNG
- Page Object Model (POM) design pattern
- Multiple browser support (Chrome, Firefox, Edge)
- Data-driven testing capabilities
- Custom utility modules for common testing operations
- Test reporting and listener integration with AspectJ

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
│   │       │   ├── AbstractDriver.java
│   │       │   ├── ChromeFactory.java
│   │       │   ├── FirefoxFactory.java
│   │       │   └── EdgeFactory4.java
│   │       ├── pages/                # Page Object Model classes
│   │       │   ├── duckduckgo/
│   │       │   ├── heroku/
│   │       │   ├── jquery/
│   │       │   └── w3schools/
│   │       ├── utils/
│   │       │   ├── WaitManager.java  # Implicit/explicit wait handling
│   │       │   ├── bots/             # Helper utilities
│   │       │   │   ├── BrowserBot.java
│   │       │   │   ├── ActionsBot.java
│   │       │   │   └── AssertionsBot.java
│   │       │   └── dataReader/       # Configuration & data reading
│   │       │       ├── PropertyReader.java
│   │       │       └── JsonReader.java
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
│   └── config.properties             # Configuration file
├── pom.xml                          # Maven build configuration
├── .github/                         # GitHub workflows
└── test-output/                     # Test reports and outputs
```

---

## 🔧 Technologies & Dependencies

| Technology             | Version  | Purpose                              |
|------------------------|----------|--------------------------------------|
| **Java**               | 23       | Programming language                 |
| **Selenium WebDriver** | 4.46.0   | Browser automation                   |
| **TestNG**             | 7.12.0   | Test framework                       |
| **Maven**              | Latest   | Build and dependency management      |
| **AspectJ**            | 1.9.25.1 | AOP for test listeners and reporting |
| **Commons IO**         | 2.22.0   | File operations                      |
| **JSON Path**          | 3.0.0    | JSON parsing and extraction          |
| **JSON Simple**        | 1.1.1    | JSON handling                        |

---

## 🚀 Getting Started

### Prerequisites

- **Java 23** or higher installed
- **Maven 3.6+** installed
- **Git** installed
- Chrome and/or Firefox browsers installed (for WebDriver compatibility)

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
    - Edit `src/main/resources/config.properties` to set target browser and execution type

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

### View Test Reports

Test reports are generated in the `test-output/` directory after running tests.

---

## 📦 Key Components

### Driver Management

- **Driver.java**: Central driver management class
- **Browser.java**: Browser enumeration (Chrome, Firefox, EdgeFactory4, etc.)
- **ChromeFactory.java**: Chrome WebDriver instantiation
- **FirefoxFactory.java**: Firefox WebDriver instantiation
- **EdgeFactory4.java**: Edge WebDriver instantiation
- **AbstractDriver.java**: Abstract base class for driver implementations

### Page Object Model

Page classes encapsulate web elements and actions for specific pages:

- **DuckDuckGo Pages**: Search engine automation
- **Heroku Pages**: Demo application testing (checkboxes, uploads)
- **W3Schools Pages**: Web table interaction
- **jQuery Pages**: Droppable element interactions

### Utilities

- **WaitManager.java**: Handles implicit and explicit waits
- **BrowserBot.java**: Common browser operations (click, type, navigate)
- **ActionsBot.java**: Advanced actions (hover, drag-drop, keyboard)
- **AssertionsBot.java**: Custom assertion utilities
- **PropertyReader.java**: Reads configuration from properties files
- **JsonReader.java**: Parses JSON data files

### Test Listeners

- **TestNGListeners.java**: Custom listeners for test lifecycle events (onStart, onFinish, onFailure)

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
✅ **Modular Structure** - Organized packages by functionality  
✅ **Data-Driven Testing** - External data files (properties, JSON) for test data  
✅ **Test Listeners** - Custom event handlers for test lifecycle  
✅ **Factory Pattern** - Driver instantiation with factory classes  
✅ **Configuration Management** - Externalized configuration in properties files

---

## 🔧 Configuration

Edit `src/main/resources/config.properties` to customize:

```properties
TargetBrowser=Chrome             # Browser type (Chrome, Firefox, EdgeFactory4)
ExecutionType=Local              # Execution type (Local, LocalHeadless)
```

---

## 📚 Learning Resources

- [Selenium Documentation](https://www.selenium.dev/documentation/)
- [TestNG Documentation](https://testng.org/doc/)
- [Page Object Model Pattern](https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/)
- [Maven Documentation](https://maven.apache.org/guides/)
