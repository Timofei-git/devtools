package ru.mentee.power.devtools.progress;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("Тестирование ProgressTracker")
class ProgressLoopTest {

  @Test
  @DisplayName("Должен корректно вычислить суммарный прогресс когда передан массив mentee")
  void shouldCalculateTotalProgressWhenMultipleMentees() {

    ProgressTracker tracker = new ProgressTracker();
    Mentee[] mentees = {
        new Mentee("Иван", "Москва", "Backend разработка", 5, 12),
        new Mentee("Мария", "Санкт-Петербург", "Fullstack", 8, 12),
        new Mentee("Пётр", "Казань", "Java Backend", 12, 12)
    };

    String result = tracker.calculateTotalProgress(mentees);

    assertThat(result)
                .contains("Summary: you have done 25 from 36 lessons,")
                .contains("there are 11 left.");
  }

  @Test
  @DisplayName("Должен корректно обработать массив когда все mentee завершили курс")
  void shouldCalculateTotalProgressWhenAllMenteesCompleted() {
        // given
    ProgressTracker tracker = new ProgressTracker();
    Mentee[] mentees = {
        new Mentee("Иван", "Москва", "Backend", 12, 12),
        new Mentee("Мария", "СПб", "Fullstack", 12, 12)
    };

        // when
    String result = tracker.calculateTotalProgress(mentees);

        // then
    assertThat(result)
                .contains("Summary: you have done 24 from 24 lessons,")
                .contains(" there are 0 left.");
  }

  @Test
  @DisplayName("Должен корректно обработать массив когда все mentee завершили курс")
  void shouldThrowIllegalArgumentException() {
        // given
    assertThatThrownBy(() -> {
      new Mentee("Иван", "Москва", "Изучить Java", 45, 30);
    })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Некорректные значения прогресса");
  }

  @Test
  @DisplayName("Должен корректно обработать массив когда все mentee завершили курс")
  void shouldThrowIllegalArgumentExceptionWithNegativeNumbers() {
        // given
    assertThatThrownBy(() -> {
      new Mentee("Иван", "Москва", "Изучить Java", -5, -56);
    })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Некорректные значения прогресса");
  }

  @Test
  @DisplayName("Должен корректно обработать массив когда все mentee завершили курс")
  void doesnotThrowIllegalArgumentExceptionWithNegativeNumbers() {
    assertThatCode(() -> {
      new Mentee("Иван", "Москва", "Изучить Java", 1, 2);
    }).doesNotThrowAnyException();
  }
}