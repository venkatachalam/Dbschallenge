# README #

This README would normally document whatever steps are necessary to get your application up and running.

### What is this repository for? ###

* Quick summary
	Automation testing project is a mavenized project.


### How do I get set up? ###

* Install Maven, Java
	Java version 1.8
	Maven version 3.6.3
	
* Dependencies are download automatically using maven pom.xml
	1. Selenium version : 3.141.59
	2. Apache poi : 4.0.1
	3. Cucumber version : 6.4.0
	4. Apache HttpClinet : 4.5.12
	5. Maven Cucumber reporting : 5.0.0

* Clone the project "git clone https://nvenkatachalam@bitbucket.org/nvenkatachalam/dbschallengerepo.git"
* Clone the project "git clone https://github.com/venkatachalam/Dbschallenge.git"

* How to run tests
	1. Run as maven using following commands
		1. mvn verify
	
	2. Run as Testng using following commands
	 	1. Open testrunner file and run as Testng
	
* Project structure
	1. src/test/java
		1. com.dbs.config - This package will contain config files
		2. com.dbs.library - This package will contain all the generic library files
		3. com.dbs.pageobjects.pages - This package will contain all page objects and action of the page objects
		4. com.dbs.stepdefinitions - This package will contain all the cucumber step definitions and hooks files
		5. com.dbs.support.drivers - This package will contain driver and test runner class

	2. src/test/resources
		1. com/dbs/features - This package will contain all the cucumber feature

### Who do I talk to? ###

* Repo owner venkatachalam neelakantan
