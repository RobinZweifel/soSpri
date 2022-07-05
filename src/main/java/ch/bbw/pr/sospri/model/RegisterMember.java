package ch.bbw.pr.sospri.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Transient;
import javax.validation.constraints.Pattern;

/**
 * To regist a new Member
 *
 * @author Robin Zweifel
 * @version 31.5.2022
 */
public class RegisterMember {

	@Length(min = 2, max = 20, message = "◦ Vorname ist unpassend")
	private String prename;

	@Length(min = 2, max = 20, message = "◦ Nachname ist unpassend")
	private String lastname;

	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "◦ Passwort nicht sicher genug (Min 8 Zeichen,Gross- und Kleinbuchstaben und Zahlen)")
	private String password;

	@Transient
	private String confirmation;

	private String message;

	private String authority;

	private String captchaResponse;

	public String getCaptchaResponse() {
		return captchaResponse;
	}

	public void setCaptchaResponse(String captchaResponse) {
		this.captchaResponse = captchaResponse;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

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
