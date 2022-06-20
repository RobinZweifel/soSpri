package ch.bbw.pr.sospri;

import ch.bbw.pr.sospri.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ch.bbw.pr.sospri.member.MemberService;
import ch.bbw.pr.sospri.member.RegisterMember;

import javax.validation.Valid;
import java.security.SecureRandom;
import java.util.Locale;
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

	@GetMapping("/get-register")
	public String getRequestRegistMembers(Model model) {
		System.out.println("getRequestRegistMembers");
		model.addAttribute("registerMember", new RegisterMember());
		return "register";
	}
	
	@PostMapping("/get-register")
	public String postRequestRegistMembers(@Valid RegisterMember registerMember, BindingResult result, Model model) {
		System.out.println("postRequestRegistMembers: registerMember");
		System.out.println(registerMember);

		if(result.hasErrors()){
			System.out.println("Error");
			return "register";
		}

		if(memberservice.getByUserName(registerMember.getPrename().toLowerCase()
				+"."+registerMember.getLastname().toLowerCase()) != null) {
			System.out.println("User allready exists, choose other first- or lastname.");

			registerMember.setMessage("Username " + registerMember.getPrename().toLowerCase() + "." + registerMember.getLastname().toLowerCase() + " allready exists");

			return "register";
		}

		if (!Objects.equals(registerMember.getPassword(), registerMember.getConfirmation())) {
			registerMember.setMessage("Password confermation is incorrect");
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

		memberservice.add(newMember);
		return "registerconfirmed";
	}

	public String hash(){
		return null;
	}
}
