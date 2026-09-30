package com.example.testpsicologici.model;

import java.util.List;

public record AceExposureResult(String profileCode, boolean partial, List<AceGroupResult> groups) {
}
