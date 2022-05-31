package ch.bbw.pr.sospri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ch.bbw.pr.sospri.member.MemberService;
import ch.bbw.pr.sospri.member.RegisterMember;

import javax.validation.Valid;

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

		return "registerconfirmed";
	}
}