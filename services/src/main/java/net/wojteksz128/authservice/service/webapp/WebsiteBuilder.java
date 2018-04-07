package net.wojteksz128.authservice.service.webapp;

import lombok.Data;
import net.wojteksz128.authservice.service.MessageType;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

public class WebsiteBuilder {

    private String pathToContent;
    private Model model;
    private List<Message> pageMessages;

    private WebsiteBuilder(Model model) {
        this.model = model;
        this.pageMessages = new ArrayList<>();
    }

    public static WebsiteBuilder create(Model model) {
        return new WebsiteBuilder(model);
    }

    public WebsiteBuilder withContent(String pathToContent) {
        this.pathToContent = pathToContent;

        return this;
    }

    public WebsiteBuilder withMessage(MessageType messageType, String title, String content) {
        final Message message = new Message();

        message.setMessageType(messageType);
        message.setTitle(title);
        message.setContent(content);
        pageMessages.add(message);

        return this;
    }

    public String build() {
        model.addAttribute("view", pathToContent);
        model.addAttribute("pageMessages", pageMessages);

        return "main_view";
    }

    @Data
    private class Message {

        private MessageType messageType;
        private String title;
        private String content;
    }
}
