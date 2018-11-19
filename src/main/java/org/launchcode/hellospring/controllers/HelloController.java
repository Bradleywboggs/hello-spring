package org.launchcode.hellospring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
//@RequestMapping(value="hello") This would set the whole controller class to have a path of /hello
// All other path values would be relative to this, i.e. if value="you" the url path would be /hello/you
public class HelloController {

    public static int greetingCount = 0;

    //Method creates a personalized greeting using name and language preference (obtained in form)
    public static String createMessage(String name, String language, int count) {
        if (language.equals("french")) {
            return "<h1> Bonjour " + name + "</h1>" +
                    "<p> Greeting counter: " + count + "</p>";
        } else if (language.equals("spanish")) {
            return "<h1> Hola " + name + "</h1>" +
                    "<p> Greeting counter: " + count + "</p>";
        } else if (language.equals("german")) {
            return "<h1> Guten tag " + name + "</h1>" +
                    "<p> Greeting counter: " + count + "</p>";
        } else if (language.equals("norwegian")) {
            return "<h1> Hallo " + name + "</h1>" +
                    "<p> Greeting counter: " + count + "</p>";
        } else {
            return "<h1> Hello "  + name + "</h1>" +
                    "<p> Greeting counter: " + count + "</p>";
        }
    }


    //Send language preference and name via form submission
    @RequestMapping(value="hello", method = RequestMethod.GET)
    @ResponseBody
    public String helloForm(){
        String html = "<form method='post'>" +
                "<input type='text' name='name'/>" +
                "<select name='language'>" +
                    "<option value='french'>French </option>" +
                    "<option value='english'> English </option>" +
                    "<option value='spanish'> Spanish </option>" +
                    "<option value='german'> German </option>" +
                    "<option value='norwegian'> Norwegian </option> </select>" +
                "<input type='submit' value='Greet Me!'/>" +
                "</form>";
        return html;
    }

    //Obtain language preference and name from form submission and pass into createMessage() method

     @RequestMapping(value="hello", method = RequestMethod.POST)
     @ResponseBody
     public String helloPost(HttpServletRequest request){
        String name = request.getParameter("name");
        String language = request.getParameter("language");

        greetingCount += 1;

        return createMessage(name, language, greetingCount);
    }

    // Insert name="yourNameHere" as a query param in the url.
    // e.g. localhost8080/hello/me?name=brad
    @RequestMapping(value="", method = RequestMethod.GET)
    @ResponseBody
    public String index(HttpServletRequest request) {

        String name = request.getParameter("name");

        if (name == null) {
            name = "World";
        }

        return "Hello " + name;
    }

    // append desired name at the end of the path.
    // e.g. localhost8080/hello/brad
    @RequestMapping(value = "hello/{name}")
    @ResponseBody
    public String helloUrlSegment(@PathVariable String name) {
        return "Hello " + name;
    }

     // Remove @ResponsBody and return a redirect to the desired path
     @RequestMapping(value = "goodbye")
     public String goodbye() {
        return "redirect:/";
     }



}
