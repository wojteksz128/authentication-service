package net.wojteksz128.webclient.webapp;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

public interface WebAppEndpoint {
    @RequestMapping({"/", "/index"})
    String mainScreen(Model model, Map<String, String> params);
}
