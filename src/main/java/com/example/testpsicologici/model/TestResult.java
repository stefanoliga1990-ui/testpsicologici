package com.example.testpsicologici.model;

import java.util.List;

public record TestResult(int score, int percentage, ResultContent general, List<AreaResult> areaResults,
                         List<AttachmentStyleResult> styleResults) {
}
