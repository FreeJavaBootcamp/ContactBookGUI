package com.fjb.contactbook.controller;

import com.fjb.contactbook.ContactBookApplication;
import com.fjb.contactbook.model.Contact;
import com.fjb.contactbook.service.Validator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.io.IOException;

public class NewContactController {

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField emailTextField;

    Contact contact;

    @FXML
    public void initialize() {
        contact = ContactBookApplication.getSelectedContact();
        if(contact != null){
            nameTextField.setText(contact.getName());
            phoneTextField.setText(contact.getPhoneNumber());
            emailTextField.setText(contact.getEmail());
        }
    }

    @FXML
    public void onSaveButtonClicked() throws IOException {
        if(contact == null) {
            createNewContact();
        } else {
            updateContact();
        }

        contact.setName(nameTextField.getText().trim());
        contact.setPhoneNumber(phoneTextField.getText().trim());
        contact.setEmail(emailTextField.getText().trim());

        if(Validator.isValidContact(contact)){
            ContactBookApplication.getContacts().add(contact);
            ContactBookApplication.setSelectedContact(contact);
            Node node = (Node) FXMLLoader.load(getClass().getResource("/com/fjb/contactbook/contact-detail-pane.fxml"));
            ContactBookApplication.refreshDetailsPane(node);
            ContactBookApplication.refreshContactListView();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    "Invalid Contact details",
                    ButtonType.CLOSE);
            alert.showAndWait();
        }

    }

    @FXML
    public void onCancelButtonClicked() throws IOException {
        nameTextField.clear();
        phoneTextField.clear();
        emailTextField.clear();
        Node node;
        if(ContactBookApplication.getSelectedContact() == null){
            node = (Node) FXMLLoader.load(getClass().getResource("/com/fjb/contactbook/empty-contact-pane.fxml"));
        } else {
            node = (Node) FXMLLoader.load(getClass().getResource("/com/fjb/contactbook/contact-detail-pane.fxml"));
        }
        ContactBookApplication.refreshDetailsPane(node);
    }

    private void createNewContact() throws IOException {
        contact = new Contact();
    }

    private void updateContact() throws IOException {
        ContactBookApplication.getContacts().remove(contact);
    }
}
