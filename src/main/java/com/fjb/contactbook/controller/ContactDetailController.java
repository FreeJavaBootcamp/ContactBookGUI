package com.fjb.contactbook.controller;

import com.fjb.contactbook.ContactBookApplication;
import com.fjb.contactbook.model.Contact;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;

import java.io.IOException;

public class ContactDetailController {
    @FXML
    private Text nameText;
    @FXML
    private Text phoneText;
    @FXML
    private Text emailText;

    @FXML
    public void initialize() {
        Contact contact = ContactBookApplication.getSelectedContact();
        nameText.setText(contact.getName());
        phoneText.setText(contact.getPhoneNumber());
        emailText.setText(contact.getEmail());
    }

    @FXML
    public void onDeleteButtonClicked() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to delete " + ContactBookApplication.getSelectedContact().getName() + " ?",
                ButtonType.CANCEL,
                ButtonType.YES);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            ContactBookApplication.getContacts().remove(ContactBookApplication.getSelectedContact());
            Node node;
            if(ContactBookApplication.getContacts().isEmpty()){
                node = (Node) FXMLLoader.load(getClass().getResource("/com/fjb/contactbook/empty-contact-pane.fxml"));
            } else {
                ContactBookApplication.setSelectedContact(ContactBookApplication.getContacts().get(0));
                node = (Node) FXMLLoader.load(getClass().getResource("/com/fjb/contactbook/contact-detail-pane.fxml"));
            }
            ContactBookApplication.refreshDetailsPane(node);
            ContactBookApplication.refreshContactListView();
        }
    }

    @FXML
    public void onEditButtonClicked() throws IOException {
        Node node = (Node) FXMLLoader.load(getClass().getResource("/com/fjb/contactbook/new-contact-pane.fxml"));
        ContactBookApplication.refreshDetailsPane(node);
    }

}
