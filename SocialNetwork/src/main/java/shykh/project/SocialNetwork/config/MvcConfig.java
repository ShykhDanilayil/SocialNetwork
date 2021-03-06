package shykh.project.SocialNetwork.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/logIn").setViewName("index.html");
        registry.addViewController("/chat").setViewName("chatBox.html");
        registry.addViewController("/cabinet").setViewName("cabinet.html");
        registry.addViewController("/profile").setViewName("profile.html");
        registry.addViewController("/allUsers").setViewName("allUsers.html");
    }
}
