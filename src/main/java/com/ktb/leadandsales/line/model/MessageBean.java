package com.ktb.leadandsales.line.model;

import java.util.List;

import com.linecorp.bot.model.message.Message;

import lombok.Data;

@Data
public class MessageBean {

	private String userId;
	private List<Message> messages;
}
