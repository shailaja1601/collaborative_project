package com.niit.collabaration.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.niit.collabaration.model.Message;
import com.niit.collabaration.model.OutputMessage;


@Controller
@RequestMapping("/")
public class ChatController {
	private Logger logger = LoggerFactory.getLogger(ChatController.class);

	  @MessageMapping("/chat")
	  @SendTo("/topic/message")
	  public OutputMessage sendMessage(Message message) {
		  logger.debug("Calling the method sendMessage()");
			logger.debug("Message id :" + message.getId());
			logger.debug("Message    : " + message.getMessage());
			

	    return new OutputMessage(message ,new Date());
	  }
}