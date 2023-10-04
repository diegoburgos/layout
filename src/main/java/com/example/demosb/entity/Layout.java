package com.example.demosb.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
@Table
public class Layout {

	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	int x;
	int y;

	@Column(columnDefinition = "int default 0")
	int rColor;
	@Column(columnDefinition = "int default 0")
	int gColor;
	@Column(columnDefinition = "int default 0")
	int bColor;

	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastActivityDate;

	@PreUpdate
	public void setLastActivityDate() {
		setLastActivityDate(new Date());
	}
}
