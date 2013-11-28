مقاولتي (moqawalati)
==========

This is a business project based on the following technologies:

* J2E
* Spring
* Hibernate
* Postgres
* Flex
* BlazeDS
* Maven
* JMeter

Needed tools to work on this project:

* Google https://www.google.com
* GitHub https://github.com
* Maven 3.0.5 http://maven.apache.org/download.cgi
* Flash Player Projector http://www.adobe.com/support/flashplayer/downloads.html
* Postgres http://jmeter.apache.org/download_jmeter.cgi
* Tomcat 7 http://tomcat.apache.org/download-70.cgi
* Eclipse http://www.eclipse.org/downloads/
* JMeter http://jmeter.apache.org/download_jmeter.cgi

How to build and run Moqawalati RIA (Rich Internet Application):
==========

* In the following, replace the string DEVELOPER_HOME by your working directory (on my computer DEVELOPER_HOME points to /Users/faresbelhaouas/Developer)

Setup your system and install the needed tools:

* Install Java Platform (JDK) (http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* Add Java bin folder to system path then check that it is working:
  - Command Line# java -version

* Install Maven 3.0.5 (http://maven.apache.org/download.cgi)
* Add Maven bin folder to system path then check that it is working:
  - Command Line# mvn --version

* Install Git (http://git-scm.com/downloads) or GitHub (https://github.com)
* Add Git bin folder to system path then check that it is working:
  - Command Line# git --version

* Download Flash Standalone Player or Projector (http://www.adobe.com/support/flashplayer/downloads.html)
* Copy the player to DEVELOPER_HOME/tools then check that it is working:
  - Command Line Mac OSX# DEVELOPER_HOME/tools/Flash Player.app/Contents/MacOS/Flash Player 
  - Command Line MS DOS# DEVELOPER_HOME/tools/flashplayer_11_sa.exe

* Install PostgreSQL (http://www.postgresql.org/download/)
* Create needed USER (moqawalati_user) and DATABASE (moqawalati_db)
  - PostgreSQL Command# CREATE USER moqawalati_user WITH PASSWORD 'reverse';
  - PostgreSQL Command# CREATE DATABASE moqawalati_db OWNER moqawalati_user ENCODING='UTF8';

Maven Build Moqawalati WAR

* mkdir DEVELOPER_HOME/git
* mkdir DEVELOPER_HOME/git/alkhwarizmix
* cd DEVELOPER_HOME/git/alkhwarizmix
* git clone https://github.com/alkhwarizmix/frameworks.git
* git clone https://github.com/alkhwarizmix/moqawalati.git
* cd frameworks/source/
* mvn -f alkhwarizmix-common.pom.xml clean install
* mvn -f install-PureMVC_AS3_MultiCore_Framework.pom.xml antrun:run install:install-file -DgroupId=org.puremvc -DartifactId=PureMVC_AS3_MultiCore_Framework -Dversion=1.0.5 -Dpackaging=swc -Dfile=target/download/PureMVC_AS3_MultiCore_1_0_5.swc
* cd alkhwarizmixFlexFramework
* mvn clean install -Dflex.flashPlayer.command="DEVELOPER_HOME/tools/Flash Player.app/Contents/MacOS/Flash Player"
* cd ../../../../alkhwarizmix/moqawalati/source
* mvn clean install -Dflex.flashPlayer.command="DEVELOPER_HOME/tools/Flash Player.app/Contents/MacOS/Flash Player"

Maven Run WAR

* cd moqawalatiWeb
* mvn jetty:run-war
* http://localhost:9787/moqawalati-webapp/index.html
