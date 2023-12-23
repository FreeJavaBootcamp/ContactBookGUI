package com.fjb.contactbook;

import com.fjb.contactbook.controller.HomeController;
import com.fjb.contactbook.model.Contact;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ContactBookApplication extends Application {

    private static List<Contact> contacts = new ArrayList<>();
    private static Contact selectedContact;

    private static HomeController homeController;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ContactBookApplication.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        homeController = fxmlLoader.getController();
        stage.setTitle("Contact Book Application");
        stage.setScene(scene);
        stage.show();
        initializeSidePanel();
    }

    public static void main(String[] args) {
        launch();
    }

    public static List<Contact> getContacts(){
        return contacts;
    }

    public void initializeSidePanel() throws IOException {
        Node node;
        if(contacts.isEmpty()){
            node = (Node) FXMLLoader.load(getClass().getResource("/com/fjb/contactbook/empty-contact-pane.fxml"));
        } else {
            selectedContact = contacts.get(0);
            node = (Node) FXMLLoader.load(getClass().getResource("/com/fjb/contactbook/contact-detail-pane.fxml"));
        }
        homeController.setDetailsAnchorPane(node);
    }

    public static void refreshDetailsPane(Node node){
        homeController.setDetailsAnchorPane(node);
    }

    public static void refreshContactListView() {
        Collections.sort(contacts, new Comparator<Contact>() {
            public int compare(Contact c1, Contact c2) {
                return c1.getName().compareTo(c2.getName());
            }});
        homeController.refreshContactListView(contacts);
    }

    public static Contact getSelectedContact() {
        return selectedContact;
    }

    public static void setSelectedContact(Contact contact){
        selectedContact = contact;
    }
}