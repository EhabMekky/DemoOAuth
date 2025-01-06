package org.example;

import Pojo.Api;
import Pojo.GetCourse;
import Pojo.Mobile;
import Pojo.WebAutomation;
public class Main {
    public static void main(String[] args) {

        GetCourse gc = new GetCourse();

        int sum = 0;
        for (WebAutomation webAutomation : gc.getCourses().getWebAutomation()) {
            sum += webAutomation.getPrice();
        }
        for (Api api : gc.getCourses().getApi()) {
            sum += api.getPrice();
        }
        for (Mobile mobile : gc.getCourses().getMobile()) {
            sum += mobile.getPrice();
        }
        System.out.println(sum);

        String api = gc.getCourses().getApi().get(1).getCourseTitle();

        if(api.equals("SoapUI Webservices testing")) {
            System.out.println(gc.getCourses().getApi().get(1).getPrice());
        }
        else {
            System.out.println("Not found");
        }    
    }
}
