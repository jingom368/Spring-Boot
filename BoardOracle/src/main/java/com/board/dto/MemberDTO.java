package com.board.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {
	private String userid;
	private String username;
	private String password;
	private String job;
	private String gender;
	private String hobby;
	private String telno;
	private String email;
	private String zipcode;
	private String address;
	private String description;
	private LocalDate regdate;
	private LocalDate lastlogindate;
	private LocalDate lastpwdate;
	private LocalDate lastlogoutdate;
	private int pwcheck;
	private String role;
	private String org_filename;
	private String stored_filename;
	private long filesize;
	private String authkey;
	
}
