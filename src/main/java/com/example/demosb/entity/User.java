package com.example.demosb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
@Table(name = "user_account", indexes = @Index(columnList = "email"))
public class User {

	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true, nullable = false)
	private String email;

	private String firstName;
	private String lastName;

	@Column(length = 60)
	private String password;
	private boolean enabled;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date registrationDate;

	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastActivityDate;

	private boolean locked;

	public User(String firstname, String lastname, String password, String email) {
		this.firstName = firstname;
		this.lastName = lastname;
		this.password = password;
		this.email = email;
		this.enabled = false;
		this.locked = false;
	}

	public User() {
		super();
		this.enabled = false;
	}

	@PreUpdate
	public void setLastActivityDate() {
		setLastActivityDate(new Date());
	}
}
