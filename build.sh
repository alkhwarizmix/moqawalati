DEVELOPER_HOME=~/Developer
echo DEVELOPER_HOME=$DEVELOPER_HOME
FLASH_PLAYER_COMMAND="$DEVELOPER_HOME/tools/Flash-Player-Debugger.app/Contents/MacOS/Flash-Player-Debugger"
echo FLASH_PLAYER_COMMAND=$FLASH_PLAYER_COMMAND
MAVEN_OPTS="-Xmn128m -Xms1024m -Xmx2048m -Xss2m -XX:PermSize=256m -XX:MaxPermSize=768m -XX:+UseParallelGC -XX:+CMSClassUnloadingEnabled -XX:+CMSPermGenSweepingEnabled"
# MAVEN_OPTS="-XX:+UseParallelGC"
echo MAVEN_OPTS=$MAVEN_OPTS

cd ../frameworks/source/alkhwarizmixMavenRunAnt
mvn clean install
cd ..
mvn -f alkhwarizmix-common.pom.xml clean install
mvn -f install-PureMVC_AS3_MultiCore_Framework.pom.xml install install:install-file -Djsse.enableSNIExtension=false -DgroupId=org.puremvc -DartifactId=PureMVC_AS3_MultiCore_Framework -Dversion=1.0.5 -Dpackaging=swc -Dfile=target/download/PureMVC_AS3_MultiCore_1_0_5.swc
mvn -f install-as3crypto.pom.xml install install:install-file -Djsse.enableSNIExtension=false -DgroupId=com.hurlant.crypto -DartifactId=as3crypto -Dversion=1.3 -Dpackaging=swc -Dfile=target/download/as3crypto.swc
mvn -f install-FlexXB.pom.xml install install:install-file -Djsse.enableSNIExtension=false -DgroupId=com.googlecode.flexxb -DartifactId=FlexXB -Dversion=2.3.1 -Dpackaging=swc -Dfile=target/download/FlexXB.swc

cd alkhwarizmixFlexFramework
mvn clean install -Dflex.flashPlayer.command="$FLASH_PLAYER_COMMAND"

cd ../../../../alkhwarizmix/moqawalati/source
mvn clean cobertura:cobertura install -Dflex.flashPlayer.command="$FLASH_PLAYER_COMMAND"
