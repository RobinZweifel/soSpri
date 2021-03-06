package ch.bbw.pr.sospri.member;

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
	
	public Iterable<Member> getAll(){
		return repository.findAll();
	}

	public void add(Member member) {
		repository.save(member);
	}

	public void update(Long id, Member member) {
		repository.save(member);
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
	public Member getById(Long id) {
		Iterable<Member> memberitr = repository.findAll();
		
		for(Member member: memberitr){
			if (Objects.equals(member.getId(), id)) {
				return member;
			}
		}
		System.out.println("MemberService:getById(), id does not exist in repository: " + id);
		return null;
	}
	
	public Member getByUserName(String username) {
		Iterable<Member> memberitr = repository.findAll();
		
		for(Member member: memberitr){
			if (member.getUsername().equals(username)) {
				return member;
			}
		}
		System.out.println("MemberService:getByUserName(), username does not exist in repository: " + username);
		return null;
	}
}
