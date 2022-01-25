package dev.mvc.chatbot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChatbotCont {

    public ChatbotCont() {
        System.out.println("-> ChatbotCont created.");
      }

      // http://localhost:9091/chatbot/chatting.do
      @RequestMapping(value = "/chatbot/chatting.do")
      public ModelAndView chatting() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/chatbot/chatting");  // /WEB-INF/views/chatbot/chatting    
        return mav;
      }
}
