package yuque2mk.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import javax.servlet.http.HttpSession

@Controller
class HomeController {
    @GetMapping("/")
    fun home(httpSession: HttpSession): String {
        return "index.html"
    }
}
