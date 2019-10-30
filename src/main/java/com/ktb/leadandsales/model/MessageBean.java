package com.ktb.leadandsales.model;

import java.util.List;

import com.linecorp.bot.model.message.Message;

import lombok.Data;

@Data
public class MessageBean {

	private String userId;
	private List<Message> messages;
}
