package net.wojteksz128.authservice.service.webapp;

import org.springframework.ui.Model;

public class WebsiteBuilder {

    private String pathToContent;
    private Model model;

    private WebsiteBuilder(Model model) {
        this.model = model;
    }

    public static WebsiteBuilder create(Model model) {
        return new WebsiteBuilder(model);
    }

    public WebsiteBuilder withContent(String pathToContent) {
        this.pathToContent = pathToContent;

        return this;
    }

    public String build() {
        model.addAttribute("view", pathToContent);

        return "main_view";
    }
}
