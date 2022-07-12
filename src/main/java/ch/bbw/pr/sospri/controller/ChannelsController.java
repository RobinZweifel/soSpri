package ch.bbw.pr.sospri.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ch.bbw.pr.sospri.service.MemberService;
import ch.bbw.pr.sospri.model.Message;
import ch.bbw.pr.sospri.service.MessageService;
/**
 * ChannelsController
 * @author Robin Zweifel
 * @version 31.5.2022
 */
@Controller
public class ChannelsController {
	@Autowired
	MessageService messageservice;
	@Autowired
	MemberService memberservice;

	Logger logger = LoggerFactory.getLogger(ChannelsController.class);

	@GetMapping("/get-channel")
	public String getRequestChannel(Model model) {
		logger.debug("getRequestChannel");
		model.addAttribute("messages", messageservice.getAll());
		
		Message message = new Message();
		message.setContent("Der zweite Pfeil trifft immer.");
		logger.debug("getRequestChannel message: " + message);
		model.addAttribute("message", message);
		return "channel";
	}

	@PostMapping("/add-message")
	public String postRequestChannel(Model model, @ModelAttribute Message message, BindingResult bindingResult, Authentication authentication) {
		logger.debug("postRequestChannel: message: " + message.toString());
		if(bindingResult.hasErrors()) {
			logger.error("postRequestChannel has errors" + bindingResult.getErrorCount());
			model.addAttribute("messages", messageservice.getAll());
			return "channel";
		}
		try{
			message.setAuthor(authentication.getName());
			message.setOrigin(new Date());
			messageservice.add(message);
		}catch (Exception e){
			message.setAuthor("Robin Zweifel");
			message.setOrigin(new Date());
			messageservice.add(message);
		}

		return "redirect:/get-channel";
	}
}