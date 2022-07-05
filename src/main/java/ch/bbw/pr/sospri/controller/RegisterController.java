package ch.bbw.pr.sospri.controller;

import ch.bbw.pr.sospri.service.ReCaptchaValidationService;
import ch.bbw.pr.sospri.model.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ch.bbw.pr.sospri.service.MemberService;
import ch.bbw.pr.sospri.model.RegisterMember;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.SecureRandom;
import java.util.Objects;

/**
 * RegisterController
 * @author Robin Zweifel
 * @version 31.5.2022
 */
@Controller
public class RegisterController {
	@Autowired
	MemberService memberservice;

	Logger logger = LoggerFactory.getLogger(RegisterController.class);

	@Autowired
	private ReCaptchaValidationService validator;

	@GetMapping("/get-register")
	public String getRequestRegistMembers(Model model) {
		logger.debug("getRequestRegistMembers");
		model.addAttribute("registerMember", new RegisterMember());
		return "register";
	}

	@PostMapping("/get-register")
	public String postRequestRegistMembers(@Valid RegisterMember registerMember, BindingResult result, Model model, @RequestParam(name="g-recaptcha-response") String captcha) {
		logger.debug("postRequestRegistMembers" + registerMember);

		if(result.hasErrors()){
			logger.error("postRequestRegistMembers has errors");
			return "register";
		}

		if(memberservice.getByUserName(registerMember.getPrename().toLowerCase() +"."+registerMember.getLastname().toLowerCase()) != null) {
			logger.error("postRequestRegistMembers user allready exists");
			registerMember.setMessage("Username " + registerMember.getPrename().toLowerCase() + "." + registerMember.getLastname().toLowerCase() + " allready exists");
			return "register";
		}

		if (!Objects.equals(registerMember.getPassword(), registerMember.getConfirmation())) {
			logger.error("postRequestRegistMembers password and confirmation do not match");
			return "register";
		}

		int strength = 10;
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
		String encodedPassword = bCryptPasswordEncoder.encode(registerMember.getPassword());

		Member newMember = new Member();
		newMember.setPrename(registerMember.getPrename());
		newMember.setLastname(registerMember.getLastname());
		newMember.setUsername(registerMember.getPrename().toLowerCase() + "." + registerMember.getLastname().toLowerCase());
		newMember.setPassword(registerMember.getPassword());
		newMember.setPassword(encodedPassword);
		newMember.setAuthority(registerMember.getAuthority());

		if(validator.validateCaptcha(captcha))
		{
			memberservice.add(newMember);
			model.addAttribute("employee", new RegisterMember());
			logger.debug("postRequestRegistMembers: Captcha success");
		}
		else {
			logger.error("postRequestRegistMembers captcha error");
			registerMember.setCaptchaResponse("Please verify that you are not a robot");
			return "register";
		}

		return "registerconfirmed";
	}
}
