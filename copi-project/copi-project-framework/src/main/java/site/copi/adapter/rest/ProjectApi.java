package site.copi.adapter.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/project")
public class ProjectApi {

    @GetMapping("/all")
    public String getAll() {
        System.out.println("ProjectApi.getAll");
        return "all";
    }

    @GetMapping("/none")
    public String getNone() {
        System.out.println("ProjectApi.getNone");
        return "none";
    }
}