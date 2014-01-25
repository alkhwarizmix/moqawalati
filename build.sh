DEVELOPER_HOME=~/Developer
echo DEVELOPER_HOME=$DEVELOPER_HOME
FLASH_PLAYER_COMMAND="$DEVELOPER_HOME/tools/Flash Player.app/Contents/MacOS/Flash Player"
echo FLASH_PLAYER_COMMAND=$FLASH_PLAYER_COMMAND

cd ../frameworks/source/
mvn -f alkhwarizmix-common.pom.xml clean install
mvn -f install-PureMVC_AS3_MultiCore_Framework.pom.xml antrun:run install:install-file -DgroupId=org.puremvc -DartifactId=PureMVC_AS3_MultiCore_Framework -Dversion=1.0.5 -Dpackaging=swc -Dfile=target/download/PureMVC_AS3_MultiCore_1_0_5.swc

cd alkhwarizmixFlexFramework
mvn clean install -Dflex.flashPlayer.command="$FLASH_PLAYER_COMMAND"

cd ../../../../alkhwarizmix/moqawalati/source
mvn clean install -Dflex.flashPlayer.command="$FLASH_PLAYER_COMMAND"