package ifit.service.dto;

/**
* DTO of email attributes
* 
* @author  Gledson Rodrigues de Oliveira Junior (groliveirajr@gmail.com)
* @version 0.1
* @since   2020-02-14
*/

public class EmailDto {
	
	private String sender;
	private String receiver;
	private String subject;
	private String messageBody;

	public EmailDto(String receiver, String subject, String messageBody) {
		this.receiver = receiver;
		this.subject = subject;
		this.messageBody = messageBody;
	}
	
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	public String getSender() {
		return sender;
	}
	
	public String getReceiver() {
		return receiver;
	}
	
	public String getMessageBody() {
		return messageBody;
	}

	public String getTitle() {
		return subject;
	}

}
