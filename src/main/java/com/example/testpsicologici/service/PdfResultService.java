package com.example.testpsicologici.service;

import com.example.testpsicologici.model.PsychologicalTest;
import com.example.testpsicologici.model.TestResult;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
public class PdfResultService {

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("d MMMM uuuu", Locale.ITALIAN);

    private final SpringTemplateEngine templateEngine;
    private final String pdfStylesheet;

    public PdfResultService(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
        this.pdfStylesheet = loadStylesheet();
    }

    public byte[] generate(PsychologicalTest test, TestResult result) {
        Context context = new Context(Locale.ITALIAN);
        context.setVariable("test", test);
        context.setVariable("result", result.general());
        context.setVariable("percentage", result.percentage());
        context.setVariable("areaResults", result.areaResults());
        context.setVariable("styleResults", result.styleResults());
        context.setVariable("generatedOn", DATE_FORMATTER.format(LocalDate.now()));
        context.setVariable("pdfStylesheet", pdfStylesheet);

        String html = templateEngine.process("result-pdf", context);
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withHtmlContent(html, null);
            builder.toStream(output);
            builder.run();
            return output.toByteArray();
        } catch (IOException exception) {
            throw new UncheckedIOException("Impossibile generare il PDF del risultato", exception);
        }
    }

    private String loadStylesheet() {
        ClassPathResource resource = new ClassPathResource("static/css/result-pdf.css");
        try (var input = resource.getInputStream()) {
            return new String(input.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException exception) {
            throw new UncheckedIOException("Impossibile caricare lo stile del PDF", exception);
        }
    }
}
