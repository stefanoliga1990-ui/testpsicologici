package com.example.testpsicologici.controller;

import java.util.LinkedHashMap;
import java.util.Map;

final class ReactPageData {

    private ReactPageData() {
    }

    static Map<String, Object> of(String page, Object... entries) {
        if (entries.length % 2 != 0) {
            throw new IllegalArgumentException("Le proprietà devono essere fornite come coppie chiave/valore");
        }
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("page", page);
        for (int index = 0; index < entries.length; index += 2) {
            data.put((String) entries[index], entries[index + 1]);
        }
        return data;
    }
}
