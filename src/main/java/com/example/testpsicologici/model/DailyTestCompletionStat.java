package com.example.testpsicologici.model;

import java.time.LocalDate;

public record DailyTestCompletionStat(LocalDate date, long completions) {
}
