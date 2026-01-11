[![Java CI with Checkstyle and JaCoCo](https://github.com/Timofei-git/devtools/actions/workflows/ci.yml/badge.svg)](https://github.com/Timofei-git/devtools/actions/workflows/ci.yml) 
## Кодстайл-гайд проекта devtools

Проект следует правилам Google Java Style Guide с адаптацией.
Автоматическая проверка: ./gradlew checkstyleMain

### 1. Именование методов: camelCase

До:    public void add_student(Student s) { }
После: public void addStudent(Student student) { }

Почему: Java Convention требует camelCase для методов.
Источник: https://google.github.io/styleguide/javaguide.html#s5.3-camel-case

### 2. Пробелы после if/for/while

До:    if(condition) {
После: if (condition) {

Почему: улучшает читаемость, отделяет ключевое слово от выражения.
Источник: Oracle Code Conventions — Whitespace

### 3. Длина строки: максимум 120 символов

До:    public List getStudentsFromSpecificCityWithVeryLongName...
После: public List getStudentsByCity(String city) {

Почему: длинные строки затрудняют чтение в редакторе и при code review.
Источник: https://google.github.io/styleguide/javaguide.html#s4.4-column-limit

### 4. Порядок импортов

До:    import java.util.List; import java.util.ArrayList; import java.io.File;
После: import java.io.File; import java.util.ArrayList; import java.util.List;

Почему: алфавитный порядок упрощает поиск импортов.
Источник: IntelliJ IDEA → Code → Optimize Imports

### 5. Фигурные скобки для if

До:    if (condition) doSomething();
После: if (condition) { doSomething(); }

Почему: скобки обязательны даже для однострочных блоков.
Источник: https://google.github.io/styleguide/javaguide.html#s4.1.1-braces-always-used
## Сценарий ручной проверки DVT-6

### Запуск приложения
1. Откройте Gradle Tool Window (View → Tool Windows → Gradle)
2. Выполните: devtools → Tasks → application → run
3. Ожидаемый вывод в Run Tool Window:
   Суммарно: пройдено 25 из 36 уроков, осталось 11 уроков

### Запуск тестов
1. Откройте Gradle Tool Window
2. Выполните: devtools → Tasks → verification → test
3. Ожидаемый вывод: BUILD SUCCESSFUL, все тесты зелёные

### Отладка через Debug
1. Установите breakpoint на строке цикла while в ProgressTracker.calculateProgress
2. Запустите Debug: кликните правой кнопкой на main → Debug 'ProgressTracker.main()'
3. Используйте Step Over (F8) для прохождения итераций
4. Проверьте Variables: counter, remainingHours должны изменяться корректно
5. Используйте Evaluate Expression (Alt+F8): вычислите remainingLessons * 2
6. Ожидаемый результат Evaluate: 14 (для completedLessons=5, totalLessons=12)

### Что делать при ошибках
- Если вывод некорректен: проверьте логику цикла через Debug
- Если тесты красные: откройте вывод теста, найдите AssertionError, скорректируйте метод
- Если Debug не останавливается: убедитесь, что breakpoint установлен (красный кружок)

## Code Review Checklist

Используйте этот чеклист для само-ревью перед запросом ревью у ментора:

### Функциональность
- [ ] Код решает поставленную задачу полностью
- [ ] Обработаны граничные случаи (null, пустые данные, экстремальные значения)
- [ ] Обработка ошибок реализована корректно

### Тесты
- [ ] Добавлены тесты для нового функционала (или обновлены существующие)
- [ ] Все тесты проходят локально: `./gradlew test`
- [ ] Покрыты позитивные и негативные сценарии
- [ ] JaCoCo coverage ≥ 80% для нового кода

### Читаемость и стиль
- [ ] Имена переменных, методов и классов отражают назначение
- [ ] Нет дублирования кода (DRY principle)
- [ ] Checkstyle проходит без ошибок: `./gradlew checkstyleMain`
- [ ] Нет закомментированного кода или отладочного вывода (`System.out.println`)

### Документация
- [ ] README обновлён (если добавлена новая функциональность)
- [ ] Публичные методы имеют JavaDoc (если применимо)
- [ ] Примеры использования актуальны
- [ ] Runbook обновлён (если изменились команды запуска/проверки)

### Производительность и безопасность
- [ ] Нет очевидных проблем производительности
- [ ] Нет хардкода паролей, токенов или конфиденциальных данных

## Примеры Code Review комментариев

### Хорошие комментарии (конструктивные)

**Пример 1:**

**Проблема:** Метод `calculateDiscount` (строка 45) имеет 3 вложенных if-else и 40 строк.
**Почему это важно:** Сложная логика плохо тестируется и тяжело поддерживается.
**Предложение:** Вынести каждое условие в отдельный метод (например, `isEligibleForBonusDiscount()`)
и использовать паттерн Strategy для разных типов скидок.



**Пример 2:**

**Проблема:** Тест `testProcessOrder` (строка 78) проверяет только успешный сценарий.
**Почему это важно:** Не проверена обработка ошибок при недостаточном балансе.
**Предложение:** Добавить тест `testProcessOrder_InsufficientBalance_ThrowsException()`
с использованием `assertThatThrownBy()`.



### Плохие комментарии (неконструктивные)

**Пример 1:**

Этот код ужасен, полностью переписать.


**Почему плохо:** Нет конкретики (что именно плохо), нет предложения (как исправить),
токсичный тон (демотивирует автора).

**Пример 2:**

Здесь лучше использовать Stream API.


**Почему плохо:** Нет объяснения почему лучше, нет примера как переписать,
неясно какую проблему это решает.

## 🚀 CI/CD

[![Java CI with Checkstyle and JaCoCo](https://github.com/Timofei-git/devtools/actions/workflows/ci.yml/badge.svg)](https://github.com/Timofei-git/devtools/actions/workflows/ci.yml)

Проект использует автоматическую сборку через GitHub Actions. При каждом push в `master` или `feature/**` ветки, а также при создании pull request, выполняются:

✅ **Checkstyle** — проверка стиля кода  
✅ **Unit-тесты** — запуск всех тестов  
✅ **JaCoCo** — анализ покрытия тестами  
✅ **Сборка** — компиляция проекта

**Требования:** Все проверки должны проходить успешно перед мержем в master.

## Результаты само-ревью DVT-9

### Найденные проблемы

#### 1. Нет описания CI в секции README
**Файл:** README.md (начало файла)
**Проблема:** Нет описания CI в секции README
**Почему важно:** CI помогает сберечь время разработчика и ревьюера с помощью автоматической сборки проекта
**Исправление:** Добавлено описание ci в readme.md




#### 2. Забыт отладочный вывод
**Файл:** src\main\java\ru\mentee\power\ProgressDemo.java
**Проблема:** Оставлен `System.out.println("Debug: starting loop")`
**Почему важно:** Отладочный вывод замусоривает логи production-приложения и создаёт впечатление небрежности.
**Исправление:** Удалить строку или заменить на logger (если логирование настроено).

#### 3. Закомментированный код
**Файл:** src\main\java\ru\mentee\power\ProgressDemo.java
**Проблема:** Закомментированы 1 строка 
**Почему важно:** Закомментированный код создаёт путаницу: непонятно зачем он сохранён и актуален ли.
Если нужна история изменений — она в Git.
**Исправление:** Удалить закомментированный код. Если нужна старая версия — посмотреть в Git History.


## Информационный поиск — результаты DVT-11

### Запросы и источники

| № | Запрос | Операторы | Официальный источник | Альтернатива | Статус | Дата проверки |
|---|------|----------|---------------------|--------------|--------|---------------|
| 1 | Lombok Gradle Short|site:search.maven.org "lombok" OR site:mvnrepository.com "lombok"|https://search.maven.org/artifact/org.projectlombok/lombok/1.18.30/jar |https://mvnrepository.com/artifact/org.projectlombok/lombok/1.18.30 | 200 OK | 07.01.2026    |
| 2 |Java Stream API|site:docs.oracle.com "stream api" intitle:api java 25|https://docs.oracle.com/en/middleware/standalone/coherence/15.1.1/develop-applications/using-repository-api.html|https://blog.jetbrains.com/idea/2025/03/new-livestream-gatherers-the-api-your-stream-was-missing-2/ | 200 OK | 07.01.2026    |
| 3 | горячие клавиши IntelliJ IDEA| site:www.jetbrains.com/help "keymap" "intellij idea"|https://www.jetbrains.com/help/idea/settings-keymap.html |https://resources.jetbrains.com/storage/products/intellij-idea/docs/IntelliJIDEA_ReferenceCard.pdf | 200 OK | 07.01.2026    |

### AI-промпты и сравнение ассистентов

#### Промпт 1: План решения

**Задача:Нужно продумать и реализовать валидацию номера студента (number) в классе Student**
**Контекст:public record Student(String name, String city, String number) {
}
, Поле number представляет собой номер студента и должно проверяться при создании объекта.**
**Ограничения:Не писать готовый код в ответе**
**Ожидаемый результат:Описание логики валидации без примеров кода**
**Критерии успеха:отклонять строки, длина которых не соответствует формату номера студента**

**AI-1 ([ChatGpt]):Валидация номера студента сводится к тому, что:

значение обязательно присутствует;

его длина строго соответствует установленному формату;

при любом отклонении объект Student считается недопустимым и не создаётся.**

**AI-2 ([Deepseek]):Логика должна быть полной — покрывать все возможные ошибочные варианты.

Валидация должна происходить при создании объекта, чтобы в систему не попали объекты с некорректным номером.

Если требуется гибкость (разные форматы для разных факультетов), продумать механизм задания формата (например, через конфигурацию или фабрику валидаторов).

**

**Сравнение: оба AI предолжили проверки на ошибки при вводе номера(длина, null), при ошибках не создавать объект типа Student
Различия:Deepseek предложил сформировать разные форматы номеров, что усложняет логику работы программы
Оба AI дали качественный ответ и я бы использовал их вместе**

#### Промпт 2: Проверка кода

**Код для проверки:package ru.mentee.power.devtools.progress;

public class ProgressTracker {
public String calculateTotalProgress(Mentee[] mentees) {
if (mentees == null || mentees.length == 0) {
return "Enter at least 3 mentees";
}

    int totalCompleted = 0;
    int totalTotal = 0;
    int index = 0;

    while (index < mentees.length) {
      totalCompleted += mentees[index].completedLessons();
      totalTotal += mentees[index].totalLessons();
      index++;
    }
    int totalRemaining = totalTotal - totalCompleted;
    return "Summary: you have done " + totalCompleted + " from "
            + totalTotal + " lessons, there are " + totalRemaining + " left.";
}

public static void main(String[] args) {
ProgressTracker tracker = new ProgressTracker();

    Mentee[] mentees = {
        new Mentee("Иван", "Москва", "Backend разработка", 5, 12),
        new Mentee("Мария", "Санкт-Петербург", "Fullstack", 8, 12),
        new Mentee("Пётр", "Казань", "Java Backend", 12, 12)
    };

    String progress = tracker.calculateTotalProgress(mentees);
    System.out.println(progress);
}
}
**

**Что проверить:**
1. Логические ошибки
2. Соответствие Code Conventions
3. Производительность

**AI-1 ([deepseek]):1. Логические ошибки
Критическая ошибка: Проверка на входные данные некорректна

java
if (mentees == null || mentees.length == 0) {
return "Enter at least 3 mentees"; // ← ЛОГИЧЕСКАЯ ОШИБКА
}
Сообщение говорит "как минимум 3 менти", но метод должен работать с любым количеством ≥ 1. Если передать 1 или 2 менти, метод вернёт это сообщение вместо подсчёта прогресса.

Возможная ошибка: Нет проверки на отрицательные значения в completedLessons() и totalLessons(). Если totalLessons меньше completedLessons, totalRemaining станет отрицательным.

2. Соответствие Code Conventions
   Нарушения:

Имя метода должно быть в camelCase: calculateTotalProgress → корректно ✓

Имена переменных корректны ✓

Нарушение: Класс Mentee не импортирован и не определён в том же файле

Нарушение: Отсутствует документация JavaDoc для публичного метода

Форматирование строки в return можно улучшить для читаемости

3. Производительность
   Проблемы:

Использование while вместо for не влияет на производительность, но for был бы читаемее

Возможная оптимизация: Если класс Mentee находится в другом пакете, вызовы completedLessons() и totalLessons() могут быть менее производительными из-за boundary crossing

Нет кэширования результатов, если метод вызывается многократно с одними и теми же данными**

**AI-2 ([copilot]):🔍 1. Логические ошибки
Проверка количества менторов  
В условии if (mentees == null || mentees.length == 0) возвращается "Enter at least 3 mentees".
⚠️ Логическая ошибка: проверяется только 0, а сообщение говорит про "минимум 3". Нужно либо изменить условие на mentees.length < 3, либо поправить текст сообщения.

Подсчёт прогресса  
Логика суммирования completedLessons() и totalLessons() корректна.
totalRemaining = totalTotal - totalCompleted тоже правильно.

Пограничные случаи

Если у менти completedLessons() > totalLessons(), получится отрицательный totalRemaining. Стоит добавить проверку или ограничение.

Если массив пустой, сообщение должно быть консистентным (см. выше).

📏 2. Соответствие Code Conventions (Java Code Style)
Имена переменных

totalTotal звучит неудачно. Лучше назвать totalLessons.

index можно заменить на i или использовать for-each цикл для читаемости.

Сообщения пользователю  
Текст "Summary: you have done ..." лучше вынести в отдельный метод форматирования или использовать String.format для читаемости.

Структура класса

Класс ProgressTracker корректно оформлен.

Но метод calculateTotalProgress можно сделать static, так как он не зависит от состояния объекта. Тогда не придётся создавать new ProgressTracker() в main.

⚡ 3. Производительность
Для массива из нескольких элементов производительность не критична.

Но цикл while можно заменить на for-each, что будет и быстрее для чтения, и безопаснее:

java
for (Mentee mentee : mentees) {
totalCompleted += mentee.completedLessons();
totalLessons += mentee.totalLessons();
}
Если количество менти будет очень большим (тысячи), то текущая реализация всё равно работает за O(n) — оптимально для задачи.**

**Сравнение:оба AI нашли недочеты в логике работы программы и в производительности и предложили похожие решения. Можно сказать, что оба AI оказались одинаково полезны**