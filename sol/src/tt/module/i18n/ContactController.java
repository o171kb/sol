package tt.module.i18n;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import tt.bean.Contact;

@Controller
@SessionAttributes
public class ContactController {

    @RequestMapping(value = "/addContact.do", method = RequestMethod.POST)
    public String addContact(@ModelAttribute("contact") Contact contact, BindingResult result) {

        System.out.println("First Name:" + contact.getFirstname() + "Last Name:" + contact.getLastname());

        return "redirect:contacts.do";
    }

    @RequestMapping("/contacts.do")
    public ModelAndView showContacts(HttpServletRequest request, HttpSession session) {

        System.out.println(request.getParameter("lang"));

        // if(!StringUtil.isEmpty(request.getParameter("lang"))){
        // coLocaleChangeInterceptor.setParamName(request.getParameter("lang"));
        // }

        return new ModelAndView("contact", "command", new Contact());
    }
}
