前端是`show.jsp`或者`show.ftl`，页面内用`${data}`取值。以下写法都是可以的。
```java
@RequestMapping("/test")
@Controller
public class MyController {
    private static final Logger logger = LoggerFactory.getLogger(MyController.class);
    @RequestMapping("/1")
    public String sendData1(Model model) {
        model.addAttribute("data", "第一种");
        return "show";
    }

    @RequestMapping("/2")
    public String sendData2(ModelMap model) {
        model.addAttribute("data", "第二种");
        return "show";
    }

    @RequestMapping("/3")
    public ModelAndView sendData3(ModelAndView mv) {
        mv.addObject("data", "第三种");
        mv.setViewName("show");
        return mv;
    }

    @RequestMapping("/4")
    public ModelAndView sendData4() {
        ModelAndView mv = new ModelAndView("show");
        mv.getModel().put("data", "第四种");
        return mv;
    }

    @RequestMapping("/5")
    public ModelAndView sendData5() {
        ModelAndView mv = new ModelAndView("show");
        mv.getModelMap().addAttribute("data", "第五种");
        return mv;
    }
}
```