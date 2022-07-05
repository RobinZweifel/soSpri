package ch.bbw.pr.sospri.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.bbw.pr.sospri.model.Member;
import ch.bbw.pr.sospri.service.MemberService;
/**
 * UsersController
 * @author Robin Zweifel
 * @version 31.5.2022
 */
@Controller
public class MembersController {

	Logger logger = LoggerFactory.getLogger(MembersController.class);

	@Autowired
	MemberService memberservice;
	
	@GetMapping("/get-members")
	public String getRequestMembers(Model model) {
		logger.debug("getRequestMembers");
		model.addAttribute("members", memberservice.getAll());
		return "members";
	}
	
	@GetMapping("/edit-member")
	public String editMember(@RequestParam(name="id") long id, Model model) {
		logger.debug("editMember");
		Member member = memberservice.getById(id);
		model.addAttribute("member", member);
		return "editmember";
	}

	@PostMapping("/edit-member")
	public String editMember(Member member) {
		logger.debug("editMember post: edit member" + member);
		Member value = memberservice.getById(member.getId());
		value.setAuthority(member.getAuthority());
		memberservice.update(member.getId(), value);
		return "redirect:/get-members";
	}

	@GetMapping("/delete-member")
	public String deleteMember(@RequestParam(name = "id") long id) {
		logger.debug("deleteMember " + id);
		memberservice.deleteById(id);
		return "redirect:/get-members";
	}
}
