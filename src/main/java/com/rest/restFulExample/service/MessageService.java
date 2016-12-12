package com.rest.restFulExample.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.rest.restFulExample.dataBase.DataBaseClass;
import com.rest.restFulExample.exception.DataNotFoundException;
import com.rest.restFulExample.model.Message;

public class MessageService {
	
	private Map<Long,Message> messages = DataBaseClass.getMessages();
	
	public MessageService(){
		messages.put(1L, new Message(1,"Hello World","karthik"));
		messages.put(2L, new Message(2,"Heelo Google","Ram"));
		messages.put(3L, new Message(3,"Hello Youtube","Leela"));
		messages.put(4L, new Message(4,"Hello Cuil","Kiwi"));
		messages.put(5L, new Message(5,"Hello Police","Robb"));
	}
	
	public List<Message> getAllMessages(){
		/*Message m1 = new Message(1,"Hello Google","Karthik");
		Message m2 = new Message(2,"Hello Yahoo","Gir");
		Message m3 = new Message(3,"Hello Cuil","Kill Bill Pandey");
		List<Message> list = new ArrayList<Message>();
		list.add(m1);
		list.add(m2);
		list.add(m3);
		return list;*/
		
		return new ArrayList<Message>(messages.values());
	}
	
	public List<Message> getAllMessagesForYear(int year){
		List<Message> messagesForYear = new ArrayList<Message>();
		Calendar cal = Calendar.getInstance();
		for(Message message: messages.values()){
			cal.setTime(message.getCreated());
			if(cal.get(Calendar.YEAR) == year){
				messagesForYear.add(message);
			}
		}
		return messagesForYear;
	}
	
	public List<Message> getAllMessagesPaginated(int start, int size){
		List<Message> list = new ArrayList<Message>(messages.values());
		if(start + size > list.size()){
			return new ArrayList<Message>();
		}
		return list.subList(start, start+size);
	}
	
	public Message getMessage(long id){
		Message message = messages.get(id);
		if(message == null){
			throw new DataNotFoundException("Message with id "+id+ " not found");
		}
		return message;
	}
	
	public Message addMessage(Message message){
		message.setId(messages.size()+1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message){
		if(message.getId() <= 0 ){
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message removeMessage(long id){
		return messages.remove(id);
	}
}
