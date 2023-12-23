package com.fjb.contactbook.controller;

import com.fjb.contactbook.ContactBookApplication;
import com.fjb.contactbook.model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.List;

public class HomeController {

    @FXML
    private ListView<Contact> contactListView;
    @FXML
    private AnchorPane detailsAnchorPane;

    @FXML
    public void onNewContactButtonClicked() throws IOException {
        ContactBookApplication.setSelectedContact(null);
        Node node = (Node) FXMLLoader.load(getClass().getResource("/com/fjb/contactbook/new-contact-pane.fxml"));
        detailsAnchorPane.getChildren().setAll(node);
    }

    @FXML
    public void onContactListViewClicked() throws IOException {
        ContactBookApplication.setSelectedContact(contactListView.getSelectionModel().getSelectedItem());
        Node node = (Node) FXMLLoader.load(getClass().getResource("/com/fjb/contactbook/contact-detail-pane.fxml"));
        detailsAnchorPane.getChildren().setAll(node);
    }

    public void setDetailsAnchorPane(Node node){
        this.detailsAnchorPane.getChildren().setAll(node);
    }

    public void refreshContactListView(List<Contact> contacts) {
        ObservableList<Contact> items = FXCollections.observableArrayList(contacts);
        contactListView.setItems(items);
    }
}