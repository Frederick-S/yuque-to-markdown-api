package yuque2mk.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpSession

@RestController
class HomeController {
    @GetMapping("/")
    fun home(httpSession: HttpSession): String {
        return ""
    }
}