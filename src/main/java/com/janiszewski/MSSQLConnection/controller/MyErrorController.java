package com.janiszewski.MSSQLConnection.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;


@Controller
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(Model model, HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            String errorMessage="";
            switch(statusCode){
                // HttpStatus.BAD_REQUEST.value()
                case 400: {
                    errorMessage = "This request is not allowed in the application. Would you mind double checking?";
                    break;
                }
                // HttpStatus.FORBIDDEN.value()
                case 403:{
                    errorMessage = "Looks like this place is not for you. You'd better run way from here...";
                    break;
                }
                // HttpStatus.NOT_FOUND.value()
                case 404:{
                    errorMessage = "The requested page dissapeared. Are you running the latest version?";
                    break;
                }
                // HttpStatus.INTERNAL_SERVER_ERROR.value()
                case 500:{
                    errorMessage = "Something is wrong with the server...";
                    break;
                }
                default: {
                    errorMessage = "I have no idea what is going on. Ask Good!";
                    break;
                }
            }
            model.addAttribute("ErrorCode",statusCode);
            model.addAttribute("ErrorMessage",errorMessage);
        }
        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
