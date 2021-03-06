package com.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@ManyToMany
	@JoinTable(name = "lector_department", 
			joinColumns = @JoinColumn(name = "id_department"),
			inverseJoinColumns = @JoinColumn(name = "id_lector"))
	private List<Lector> lectors = new ArrayList<Lector>();
	
	@ManyToOne
	private Lector head;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Lector> getLectors() {
		return lectors;
	}

	public void setLectors(List<Lector> lectors) {
		this.lectors = lectors;
	}

	public Lector getHead() {
		return head;
	}

	public void setHead(Lector head) {
		this.head = head;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", lectors=" + lectors + ", head=" + head + "]";
	}

	
	
	
	
}
