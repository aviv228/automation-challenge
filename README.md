# automation-challenge
This project is written in java using Selenium and Maven, and there are two tests which one of them is with JUnit.
Everything is set up and more tests can be added straight away.

## How To Execute
Before building, ensure that you have the most recent chrome\firefox\IE version.
To execute the tests just browse to the path where selenium-project-test is located.
Open a CMD terminal in this location, and type `mvn clean install`
for running the first test: `mvn test -Dtest=AddCommentInPage`
And for the second test: `mvn test -Dtest=PostPolicyApproveAll`
Or execute the tests in your IDE. maven profiles for all browsers exists in the [pom.xml].

## Implemented Browsers
* Chrome
* Firefo
* Internet Explorer

## Generate HTML Report
To generate surefire HTML report, please run after the test the next command: 'mvn surefire-report:report'
