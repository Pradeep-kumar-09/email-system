/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;

/**
 *
 * @author adrianadewunmi
 */
public class MailService {
    
    private Session session;
    private Store store;
    private Folder folder;
    
    private String protocol = "imaps";
    private String file = "INBOX";

    public MailService() {
    }
    
    public boolean isLoggedIn(){
        return store.isConnected();
    }
    
    /**
     * To login to the mail host server
     */
    
    public void login(String host, String username, String password) throws IOException, NoSuchProviderException, MessagingException{
        username = "Your_email@gmail.com";
        Path fileName = Paths.get("Path_to_password.txt_file");
        String pass = Files.readString(fileName);
        password = pass;
        host = "imap.gmail.com";
        URLName url = new URLName(protocol, host, 993, file, username, password);
                
        if (session == null) {
            Properties props = null;
            try {
                props = System.getProperties();
            } catch (SecurityException se) {
                props = new Properties();
            }
            session = Session.getInstance(props, null);
        }
        store = session.getStore(url);
        store.connect();
        folder = store.getFolder(url);
        folder.open(Folder.READ_WRITE);
    }
    
    /**
     * To logout from the mail host server
     * @throws javax.mail.MessagingException
     */
    
    public void logout() throws MessagingException{
        folder.close(false);
        store.close();
        store = null;
        session = null;
    }
    
    public int getMessageCount(){
        int messageCount = 0;
        try {
            messageCount = folder.getDeletedMessageCount();
        } catch (MessagingException me) {
            me.printStackTrace();
        }
        return messageCount;
    }
    
    public Message[] getMessages() throws MessagingException{
        return folder.getMessages();
    }
    
}
