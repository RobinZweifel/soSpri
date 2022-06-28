package ch.bbw.pr.sospri.member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * MemberService
 * 
 * @author Robin Zweifel
 * @version 31.5.2022
 *
 */
@Service
@Transactional
public class MemberService {
	@Autowired
	private MemberRepository repository;

	Logger logger = LoggerFactory.getLogger(MemberService.class);
	
	public Iterable<Member> getAll(){
		logger.info("getAll");
		return repository.findAll();
	}

	public void add(Member member) {
		logger.info("add " + member);
		repository.save(member);
	}

	public void update(Long id, Member member) {
		logger.info("update " + id + " " + member);
		repository.save(member);
	}

	public void deleteById(Long id) {
		logger.info("deleteById " + id);
		repository.deleteById(id);
	}
	
	public Member getById(Long id) {
		logger.info("getById " + id);
		Iterable<Member> memberitr = repository.findAll();
		
		for(Member member: memberitr){
			if (Objects.equals(member.getId(), id)) {
				return member;
			}
		}
		logger.error("getById " + id + " not found");
		return null;
	}
	
	public Member getByUserName(String username) {
		logger.info("getByUserName " + username);
		Iterable<Member> memberitr = repository.findAll();
		
		for(Member member: memberitr){
			if (member.getUsername().equals(username)) {
				return member;
			}
		}
		logger.error("getByUserName " + username + " not found");
		return null;
	}
}
