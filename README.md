# 🚀 Project: Test Automation Framework with Cucumber and Allure

This project is an automation testing framework that utilizes **Cucumber** for behavior-driven testing and **Allure**
for generating interactive reports. The main focus is on testing a product page using Cucumber scenarios and visualizing
the results with Allure.

---

## 📌 Main Features

- ✅ **Behavior-Driven Development (BDD)**: Uses **Cucumber** with **JUnit 5** to define tests in plain language.
- ✅ **Allure Reporting**: Generates interactive and detailed reports.
- ✅ **Automated Feature Generation from Excel**: Automatically generates `.feature` files from data in Excel.
- ✅ **CI/CD Integration**: Fully integrated with **GitHub Actions** to automate test execution and report generation on
  every push or pull request.
- ✅ **Data-Driven Testing**: Uses **Excel** to define test scenarios dynamically.
- ✅ **Easy to Extend**: Easily extendable for new test cases and features.

---

## 📂 Project Structure

```
📦 test-automation-sjc
 ┣ 📂 .github
 ┃ ┗ 📂 workflows         # GitHub Actions workflow file 
 ┣ 📂 scripts
 ┃ ┣ run_suite.sh         # Executes tests via Maven
 ┃ ┣ run_cucumber.sh      # Generates Cucumber Report
 ┃ ┗ run_allure.sh        # Generates Allure Report
 ┣ 📂 src
 ┃ ┣ 📂 main
 ┃ ┃ ┗ 📂 resources       # General configuration files (e.g., config.properties) 
 ┃ ┣ 📂 test
 ┃ ┃ ┣ 📂 java
 ┃ ┃ ┃ ┣ 📂 data          # JSON and Excel data readers
 ┃ ┃ ┃ ┣ 📂 hooks         # Hooks
 ┃ ┃ ┃ ┣ 📂 listeners     # Listeners
 ┃ ┃ ┃ ┣ 📂 models        # Data classes (POJOs)
 ┃ ┃ ┃ ┣ 📂 pages         # Page Object classes
 ┃ ┃ ┃ ┣ 📂 steps         # Step Definitions Cases
 ┃ ┃ ┃ ┣ 📂 principal     # Run Test
 ┃ ┃ ┃ ┗ 📂 utilities     # BasePage, driver setup, flows, logs
 ┃ ┃ ┣ 📂 resources       # Archivos como reportes, allure-results, etc.
 ┃ ┃ ┃ ┣ 📂 features      # Features Cases
 ┃ ┃ ┃ ┗ 📂 data          # JSON for login and Excel for test inputs
 ┣ 📜 pom.xml             # Maven project configuration and dependencies
```

---

## ⚙️ Installation & Setup

### 🔹 Prerequisites

- Java 17+
- Maven
- Cucumber and JUnit dependencies
- Allure plugin

### 🔹 Installation

#### Clone the repository

```
https://github.com/JosKavi33/test-automation-sjc.git
```

### Install dependencies

```
mvn clean install
```

---

# 🧪 Running the Test Suite

🔹 Run all tests

```
mvn clean test
```

This command will execute all tests in the project.

🔹 Running Tests on GitHub Actions
Every push or pull request to the main branch triggers the test suite on GitHub Actions. After execution:

Allure reports are generated.

The reports are uploaded as artifacts (downloadable ZIP).

The reports are also deployed to GitHub Pages.

---

# 📊 View the Allure Report

1. View the Allure Report from GitHub Actions
   To download and view the Allure report:

Go to the Actions tab on GitHub.

Select the latest workflow run (it will show a ✅ or ❌ depending on the test results).

Scroll down to the Artifacts section.

Download the allure-report ZIP file.

Extract the file and open the index.html file in your browser.

⚠️ If the report does not load correctly locally, use the GitHub Pages link provided below.

🌐 View the Allure Report via GitHub Pages
You can directly access the latest Allure report from the following URL:

```http
https://your-username.github.io/cucumber-allure-automation/
```

---

# 🧪 Automatic Feature Generation from Excel

To automatically generate .feature files from an Excel file, use the following Maven command:

```bash
mvn compile exec:java -Dexec.mainClass="utilities.FeatureGeneratorFromExcel"
```

This command will read data from ItemDetails.xlsx and generate a corresponding .feature file.

---

# 🔄 GitHub Actions Workflow

Workflow Details
The GitHub Actions workflow is configured to:

Trigger on push or pull request to the main branch.

Compile the project and run tests using Maven.

Generate the Cucumber and Allure reports.

Upload the reports as artifacts for downloading.

Publish the Cucumber report to GitHub Pages for easy access.

This ensures that every change in the main branch is automatically tested and the results are available for review.

---

# ✨ Contributing

We welcome contributions! Fork the repository, create a new branch, and open a pull request. 🚀

---

# 📄 License

MIT License — You are free to use, modify, and distribute this code. 🚀

---

# 👤 Author

Developed and maintained by Jose Cabrejo Villar.

---

# 🧠 Additional Notes

This project uses Cucumber for BDD-style test cases and Allure for detailed visual reports.

Make sure to update the Excel data file if you want to create new .feature files dynamically.

If you face issues with the Allure report, check for proper installation of the Allure plugin and ensure the Maven
commands are executed correctly.