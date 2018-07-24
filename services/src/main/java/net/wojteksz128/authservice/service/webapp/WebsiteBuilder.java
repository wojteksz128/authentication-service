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
    private List<String> modals;

    private WebsiteBuilder(Model model) {
        this.model = model;
        this.pageMessages = new ArrayList<>();
        this.modals = new ArrayList<>();
    }

    public static WebsiteBuilder create(Model model) {
        return new WebsiteBuilder(model);
    }

    public WebsiteBuilder withContent(String pathToContent) {
        this.pathToContent = pathToContent;

        return this;
    }

    @SuppressWarnings("UnusedReturnValue")
    public WebsiteBuilder withMessage(MessageType messageType, String title, String content) {
        final Message message = new Message();

        message.setMessageType(messageType);
        message.setTitle(title);
        message.setContent(content);
        pageMessages.add(message);

        return this;
    }

    public WebsiteBuilder withModal(String modalId) {
        this.modals.add(modalId);

        return this;
    }

    public String build() {
        model.addAttribute("view", pathToContent);
        model.addAttribute("pageMessages", pageMessages);
        model.addAttribute("modals", modals);

        return "main_view";
    }

    @Data
    private class Message {

        private MessageType messageType;
        private String title;
        private String content;
    }
}
