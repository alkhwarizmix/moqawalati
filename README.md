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

Check your system

* java -version
* mvn --version

Setup

* export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.7.0_45.jdk/Contents/Home
* export M2_HOME=~/Developer/tools/maven
* export M2=$M2_HOME/bin
* export PATH=$M2:$PATH
* export CATALINA_HOME=~/Developer/tools/tomcat

Maven Build

* mkdir Developer
* cd Developer
* mkdir git
* cd git
* mkdir alkhwarizmix
* cd alkhwarizmix

* git clone https://github.com/alkhwarizmix/frameworks.git
* git clone https://github.com/alkhwarizmix/moqawalati.git
* cd frameworks/source/
* mvn -f alkhwarizmix-common.pom.xml clean install
* mvn -f install-PureMVC_AS3_MultiCore_Framework.pom.xml antrun:run install:install-file -DgroupId=org.puremvc -DartifactId=PureMVC_AS3_MultiCore_Framework -Dversion=1.0.5 -Dpackaging=swc -Dfile=target/download/PureMVC_AS3_MultiCore_1_0_5.swc
* cd alkhwarizmixFlexFramework
* mvn clean install -Dflex.flashPlayer.command="/Users/faresbelhaouas/Developer/tools/Flash Player.app/Contents/MacOS/Flash Player"
* cd ../../../../alkhwarizmix/moqawalati/source
* mvn clean install -Dflex.flashPlayer.command="/Users/faresbelhaouas/Developer/tools/Flash Player.app/Contents/MacOS/Flash Player"
* cd moqawalatiWeb
* mvn jetty:run-war
* http://localhost:9787/moqawalati-webapp/index.html


* cd ~/Developer/git/alkhwarizmix/frameworks/source/
* mvn -f alkhwarizmix-common.pom.xml clean install
* cd ~/Developer/git/alkhwarizmix/frameworks/source/
* mvn -f install-PureMVC_AS3_MultiCore_Framework.pom.xml antrun:run install:install-file -DgroupId=org.puremvc -DartifactId=PureMVC_AS3_MultiCore_Framework -Dversion=1.0.5 -Dpackaging=swc -Dfile=target/download/PureMVC_AS3_MultiCore_1_0_5.swc
* cd ~/Developer/git/alkhwarizmix/frameworks/source/alkhwarizmixFlexFramework
* mvn clean install -Dflex.flashPlayer.command="/Users/faresbelhaouas/Developer/tools/Flash Player.app/Contents/MacOS/Flash Player"
* cd ~/Developer/git/alkhwarizmix/moqawalati/source
* mvn clean install -Dflex.flashPlayer.command="/Users/faresbelhaouas/Developer/tools/Flash Player.app/Contents/MacOS/Flash Player"