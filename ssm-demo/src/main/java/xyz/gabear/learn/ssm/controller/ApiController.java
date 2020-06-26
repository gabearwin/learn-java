package xyz.gabear.learn.ssm.controller;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.URL;

@Controller
@RequestMapping("/api/v2")
public class ApiController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

    @RequestMapping("/show")
    @ResponseBody
    public String show() {
        return "you are great";
    }

    @RequestMapping("/rss")
    public ModelAndView rss() {
        ModelAndView mv = new ModelAndView("rss");
        try {
            URL feedUrl = new URL("https://www.ifanr.com/feed");
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedUrl));
            mv.addObject("rss", feed);
        } catch (FeedException | IOException e) {
            e.printStackTrace();
        }
        logger.warn("请求成功");
        return mv;
    }
}
