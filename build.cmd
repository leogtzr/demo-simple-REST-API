@echo off

mvn clean package --threads 8 -Dmaven.test.skip=true -DskipTests && (
    java -jar "target\demo-0.0.1-SNAPSHOT.jar"
)

exit /b 0