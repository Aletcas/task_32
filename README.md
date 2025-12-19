Полезные команды:
# Запуск всех тестов
mvn test

# Запуск тестов с определенным тегом
mvn test -Dgroups="smoke"

# Запуск конкретного тестового класса
mvn test -Dtest=LoginTests

# Запуск конкретного тестового метода
mvn test -Dtest=LoginTests#successfulLoginWithValidCredentials

# Запуск smoke-тестов в headed режиме
mvn test -Dgroups="smoke" -DHEADLESS=false

# Запуск в Firefox
mvn test -DBROWSER=firefox -DHEADLESS=true

# Запуск с кастомным окружением
mvn test -DBASE_URL="https://qa.example.com" -DHEADLESS=true

# Запуск тестов с генерацией Allure результатов
mvn clean test allure:report

# Просмотр отчета
allure open target/site/allure-maven-plugin
    
 # Запуск с записью трассировки
mvn test -Dtracing=true

# Генерация HTML отчета
mvn surefire-report:report   
