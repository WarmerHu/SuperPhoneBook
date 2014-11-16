package com.superphonebook;

import android.app.Application;

import com.superphonebook.service.IPersonService;
import com.superphonebook.service.PersonService;

public class SuperPhoneBookApplication extends Application {
    private IPersonService personService;
    @Override
    public void onCreate() {
        super.onCreate();
        personService = new PersonService();
        
    }
    public IPersonService getPersonService() {
        return personService;
    }
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }
    
    
}
