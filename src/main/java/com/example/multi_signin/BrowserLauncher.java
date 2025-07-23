package com.example.multi_signin;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.awt.Desktop;
import java.net.URI;

@Component
public class BrowserLauncher {

    @EventListener(ApplicationReadyEvent.class)
    public void launchBrowser() {
        System.setProperty("java.awt.headless", "false"); // Disable headless mode
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.browse(new URI("http://localhost:8081")); // Open your desired URL
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
    }
}
