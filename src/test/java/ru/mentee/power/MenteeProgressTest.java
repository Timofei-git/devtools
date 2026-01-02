package ru.mentee.power;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MenteeProgressTest {

  @Test
  void shouldFormatSummaryWhenProgressCreated() {
    MenteeProgress progress = new MenteeProgress("Ирина", 1, 8);

    String result = progress.summary();

    assertThat(result).isEqualTo("Sprint 1 - Ирина: planned 8 h");
  }

  @Test
  void shouldDetectReadinessWhenHoursAboveThreshold() {
    MenteeProgress progress = new MenteeProgress("Ирина", 1, 4);

    assertThat(progress.readyForSprint()).isTrue();
  }

  @Test
  void shouldDetectLackOfReadinessWhenHoursBelowThreshold() {
    MenteeProgress progress = new MenteeProgress("Ирина", 1, 2);

    assertThat(progress.readyForSprint()).isFalse();
  }
}