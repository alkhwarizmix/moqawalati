DEVELOPER_HOME=~/Developer
echo DEVELOPER_HOME=$DEVELOPER_HOME
FLASH_PLAYER_COMMAND="$DEVELOPER_HOME/tools/flash-player-debugger/Flash-Player-Debugger.app/Contents/MacOS/Flash-Player-Debugger"
echo FLASH_PLAYER_COMMAND=$FLASH_PLAYER_COMMAND
export MAVEN_OPTS="-Xmn128m -Xms1024m -Xmx2048m -Xss2m -XX:PermSize=256m -XX:MaxPermSize=768m -XX:+UseParallelGC -XX:+CMSClassUnloadingEnabled"
echo MAVEN_OPTS=$MAVEN_OPTS

cd ../frameworks/source/alkhwarizmixMavenRunAnt
mvn clean install
sleep 5
cd ..
mvn -f alkhwarizmix-common.pom.xml clean install
sleep 5
mvn -f install-PureMVC_AS3_MultiCore_Framework.pom.xml install install:install-file -Djsse.enableSNIExtension=false -DgroupId=org.puremvc -DartifactId=PureMVC_AS3_MultiCore_Framework -Dversion=1.0.5 -Dpackaging=swc -Dfile=target/download/PureMVC_AS3_MultiCore_1_0_5.swc
sleep 5
mvn -f install-as3crypto.pom.xml install install:install-file -Djsse.enableSNIExtension=false -DgroupId=com.hurlant.crypto -DartifactId=as3crypto -Dversion=1.3 -Dpackaging=swc -Dfile=target/download/as3crypto.swc
sleep 5
mvn -f install-FlexXB.pom.xml install install:install-file -Djsse.enableSNIExtension=false -DgroupId=com.googlecode.flexxb -DartifactId=FlexXB -Dversion=2.3.1 -Dpackaging=swc -Dfile=target/download/FlexXB.swc
sleep 5

cd alkhwarizmixFlexFramework
mvn clean install -Dflex.flashPlayer.command="$FLASH_PLAYER_COMMAND"
sleep 5

cd ../../../../alkhwarizmix/moqawalati/source
mvn clean cobertura:cobertura install -Dflex.flashPlayer.command="$FLASH_PLAYER_COMMAND"
sleep 5
