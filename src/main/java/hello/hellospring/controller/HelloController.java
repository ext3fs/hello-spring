package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

  @GetMapping("hello")
  public String hello(Model model) {
    model.addAttribute("data", "일영");
    return "hello";
  }

  @GetMapping("helloMvc")
  public String helloMvc(@RequestParam("name") String name, Model model) {
    model.addAttribute("name", name);
    return "helloMvc";
  }

  static public class Hello {
    private String name;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }

  @GetMapping("helloApi")
  @ResponseBody
  public Hello helloApi(@RequestParam("name") String name) {
    Hello obj = new Hello();
    obj.setName(name);
    return obj;
  }

}
