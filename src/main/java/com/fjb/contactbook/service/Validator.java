package com.fjb.contactbook.service;

import com.fjb.contactbook.model.Contact;

public class Validator {
    public static boolean isValidContact(Contact contact){
        return (contact.getName() != null
                && contact.getName().length() > 3
                && contact.getPhoneNumber() != null
                && contact.getPhoneNumber().length() > 8);
    }
}
