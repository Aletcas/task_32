```markdown
### Команды запуска тестов

```bash
# Основные
mvn test
mvn test -Dgroups="smoke"
mvn test -Dtest=LoginTests
mvn test -Dtest=LoginTests#successfulLoginWithValidCredentials

# С параметрами
mvn test -Dgroups="smoke" -DHEADLESS=false
mvn test -DBROWSER=firefox -DHEADLESS=true
mvn test -DBASE_URL="https://qa.example.com" -DHEADLESS=true

# Отчеты
mvn clean test allure:report
allure open target/site/allure-maven-plugin
mvn test -Dtracing=true
mvn surefire-report:report
