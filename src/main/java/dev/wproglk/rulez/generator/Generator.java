package dev.wproglk.rulez.generator;

import dev.wproglk.rulez.rules.Rule;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

public class Generator {
    public static String generateHTML(final Rule rule) {
        final ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setCharacterEncoding("UTF-8");
        resolver.setPrefix("templates/generator/");
        resolver.setSuffix(".tpl.html");

        final Context context = new Context();
        context.setVariable("rule", rule);

        final TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(resolver);
        final String html = templateEngine.process("documentation", context);

        final Document doc = Jsoup.parse(html);
        doc.outputSettings(new Document.OutputSettings()
                        .indentAmount(2)
                .prettyPrint(true));
        return doc.outerHtml();
    }
}
