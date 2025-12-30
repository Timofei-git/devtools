Для запуска с помощью Gradle Tool Window вы должны дважды кликнуть Ctrl  ввести команду gradle run
package ru.mentee.power - папка проекта, будет содерадть в себе другие папки

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