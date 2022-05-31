package ch.bbw.pr.sospri.member;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

/**
 * To regist a new Member
 *
 * @author Robin Zweifel
 * @version 31.5.2022
 */
public class RegisterMember {

	@Length(min = 2, max = 20, message = "◦ Vorname ist unpassend \n")
	private String prename;

	@Length(min = 2, max = 20, message = "◦ Nachname ist unpassend \n")
	private String lastname;

	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "◦ Passwort nicht sicher genug")
	private String password;
	private String confirmation;
	
	public String getPrename() {
		return prename;
	}
	public void setPrename(String prename) {
		this.prename = prename;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmation() {
		return confirmation;
	}
	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
	}
	@Override
	public String toString() {
		return "RegisterMember [prename=" + prename + ", lastname=" + lastname + ", password=" + password
				+ ", confirmation=" + confirmation + "]";
	}
}
