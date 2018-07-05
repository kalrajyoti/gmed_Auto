package com.gmed.patientportal;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.FlagTerm;
public class JavaMail {
	private final static String fileName = "smtp.properties";
	String returnPin = null;
	
	public String readmail(){
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(new File(System.getProperty("user.dir")+ "/src/test/resources/" +fileName)));
			Session session = Session.getDefaultInstance(props, null);
			Store store =session.getStore("imaps");
			store.connect("smtp.gmail.com", "kalrajyoti777@gmail.com","Admin@123456");
			Folder folder = store.getFolder("INBOX");
			folder.open(Folder.READ_WRITE);
			// Fetch unseen messages from inbox folder
			Message[] messages = folder.search(
					new FlagTerm(new Flags(Flags.Flag.SEEN), false));
			// Sort messages from recent to oldest
			Arrays.sort( messages, ( m1, m2 ) -> {
				try {
					return m2.getSentDate().compareTo( m1.getSentDate() );
				} catch ( MessagingException e ) {
					throw new RuntimeException( e );
				}
			} );
			for ( Message message : messages ) {
				System.out.println("content: " + message.getContent()+ " subject:" + message.getSubject() );
				String content = message.getContent().toString();
				String subject = message.getSubject().toString();
				if( content.contains("PIN Number:") && subject.contains("Please register for our Patient Portal")){
					int startIndex = content.indexOf("PIN Number: ") + 12;
					returnPin= content.substring(startIndex,startIndex + 10);
					message.setFlag(Flag.SEEN, true);
					continue;
				}
			}
		}
		catch(Exception e){
			System.out.println("exception catched"+e.getMessage());
		}
		return returnPin;
	}

}
