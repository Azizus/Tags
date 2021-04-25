package com.airship.tags.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class TagEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@ElementCollection
	@CollectionTable(name = "user_tags_added", joinColumns = @JoinColumn(name = "userId"))
	@Column(name = "tags_added")
	private List<String> add = new ArrayList<String>();

	@ElementCollection
	@CollectionTable(name = "user_tags_removed", joinColumns = @JoinColumn(name = "userId"))
	@Column(name = "tags_removed")
	private List<String> remove = new ArrayList<String>();

	private Date date;
}
